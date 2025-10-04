package cn.mcmod_mmf.mmlib.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.Target;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class AbstractConditionalDatapackEntriesProvider implements DataProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final Predicate<String> namespacePredicate;

    private static final List<RegistryDataLoader.RegistryData<?>> DATA_PACK_REGISTRIES;
    private static final List<RegistryDataLoader.RegistryData<?>> DATA_PACK_REGISTRIES_VIEW;

    static {
        DATA_PACK_REGISTRIES = new ArrayList<>(RegistryDataLoader.WORLDGEN_REGISTRIES);
        DATA_PACK_REGISTRIES_VIEW = Collections.unmodifiableList(DATA_PACK_REGISTRIES);
    }


    public AbstractConditionalDatapackEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, Set<String> modIds) {
        Predicate predicate;
        if (modIds == null) {
            predicate = (namespace) -> true;
        } else {
            Objects.requireNonNull(modIds);
            predicate = modIds::contains;
        }

        this.namespacePredicate = predicate;
        this.registries = registries;
        this.output = output;
    }

    public AbstractConditionalDatapackEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, RegistrySetBuilder datapackEntriesBuilder, Set<String> modIds) {
        this(output, registries.thenApply((r) -> constructDatapackRegistries(r, datapackEntriesBuilder)), modIds);
    }

    private static HolderLookup.Provider constructDatapackRegistries(HolderLookup.Provider original, RegistrySetBuilder datapackEntriesBuilder) {
        HashSet<? extends ResourceKey<? extends Registry<?>>> builderKeys = new HashSet<>(datapackEntriesBuilder.entries.stream().map(RegistrySetBuilder.RegistryStub::key).toList());

        getDataPackRegistriesWithDimensions().filter((data) -> !builderKeys.contains(data.key())).forEach((data) -> datapackEntriesBuilder.add(data.key(), (context) -> {
        }));
        return datapackEntriesBuilder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), original);
    }

    public abstract <T> Map<ResourceKey<T>, List<ConditionJsonProvider>> getConditions();

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        return this.registries.thenCompose((provider) -> {
            DynamicOps<JsonElement> dynamicops = RegistryOps.create(JsonOps.INSTANCE, provider);
            return CompletableFuture.allOf(getDataPackRegistriesWithDimensions().flatMap((p_256552_) -> this.dumpRegistry(pOutput, provider, dynamicops, p_256552_).stream()).toArray(CompletableFuture[]::new));
        });
    }

    private <T> Optional<CompletableFuture<?>> dumpRegistry(CachedOutput pOutput, HolderLookup.Provider pRegistries, DynamicOps<JsonElement> pOps, RegistryDataLoader.RegistryData<T> pRegistryData) {
        ResourceKey<? extends Registry<T>> resourcekey = pRegistryData.key();
        return pRegistries.lookup(resourcekey).map((lookup) -> {
            PackOutput.PathProvider packoutput$pathprovider = this.output.createPathProvider(Target.DATA_PACK, prefixNamespace(resourcekey.location()));
            return CompletableFuture.allOf(lookup.listElements().filter((holder) -> this.namespacePredicate.test(holder.key().location().getNamespace())).map((reference) -> {
                JsonArray conditions = new JsonArray();
                if (this.getConditions().containsKey(reference.key())) {
                    for (ConditionJsonProvider c : this.getConditions().get(reference.key())) {
                        conditions.add(c.toJson());
                    }
                }

                return dumpConditionalValue(packoutput$pathprovider.json(reference.key().location()), pOutput, pOps, pRegistryData.elementCodec(), reference.value(), conditions);
            }).toArray(CompletableFuture[]::new));
        });
    }

    private static <E> CompletableFuture<?> dumpConditionalValue(Path pValuePath, CachedOutput pOutput, DynamicOps<JsonElement> pOps, Encoder<E> pEncoder, E pValue, JsonArray conditions) {
        Optional<JsonElement> optional = pEncoder.encodeStart(pOps, pValue).resultOrPartial((p_255999_) -> LOGGER.error("Couldn't serialize element {}: {}", pValuePath, p_255999_));
        if (optional.isPresent()) {
            JsonObject result = optional.get().getAsJsonObject();
            if (!conditions.isJsonNull() && !conditions.isEmpty()) {
                result.add(ResourceConditions.CONDITIONS_KEY, conditions);
            }

            return DataProvider.saveStable(pOutput, result, pValuePath);
        } else {
            return CompletableFuture.completedFuture(null);
        }
    }

    private static Stream<RegistryDataLoader.RegistryData<?>> getDataPackRegistriesWithDimensions() {
        return Stream.concat(DATA_PACK_REGISTRIES_VIEW.stream(), RegistryDataLoader.DIMENSION_REGISTRIES.stream());
    }

    private static String prefixNamespace(ResourceLocation registryKey) {
        return registryKey.getNamespace().equals("minecraft") ? registryKey.getPath() : registryKey.getNamespace() + "/" + registryKey.getPath();
    }
}
