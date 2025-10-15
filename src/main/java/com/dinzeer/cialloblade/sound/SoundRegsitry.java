package com.dinzeer.cialloblade.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import static com.dinzeer.cialloblade.Cialloblade.MODID;

public class SoundRegsitry {
    public static void init() {

    }

    public static final SoundEvent DOCIALLO
            = register("dociallo", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, "dociallo")));

    public static final SoundEvent DOSLASH
            = register("doslash", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, "doslash")));
    public static final SoundEvent DOCIALLO2
            = register("dociallo2", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, "dociallo2")));
    public static final SoundEvent SENPAI
            = register("senpai", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, "senpai")));


    public static SoundEvent register(String name, SoundEvent soundEvent) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(MODID, name), soundEvent);
    }
}
