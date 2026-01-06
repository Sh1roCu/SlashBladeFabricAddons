package com.yakumosakura.yakumoblade.item;

import cn.sh1rocu.sfaddons.SFAddons;
import com.exfantasycode.mclib.Utils.SlashBlade.SlashEffectUtils;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

import static com.yakumosakura.yakumoblade.Yakumoblade.MODID;

public class YakumBladeSlashItem extends ItemSlashBlade {


    public YakumBladeSlashItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }


    public static String returnValue(ISlashBladeState state) {
        if (!state.getSpecialEffects().isEmpty()) {
            return "";
        }
        return Component.translatable("slashblade.tooltip.special_effect.none").getString();
    }


    @Environment(EnvType.CLIENT)
    @Override
    public void appendSpecialEffects(List<Component> tooltip, @NotNull ISlashBladeState state) {


        int colorCode = state.getColorCode();
        int dynamicColor = (colorCode != 0) ? (colorCode & 0xFFFFFF) : 0xFFFFFF;


        tooltip.add(Component.literal("-----------------------").withStyle(style -> style.withColor(ChatFormatting.GOLD)));
        tooltip.add(Component.translatable("slashblade.tooltip.special_effect.title", returnValue(state)).withStyle(style -> style.withColor(ChatFormatting.GOLD)));
        if (!state.getSpecialEffects().isEmpty()) {
            Minecraft mcinstance = Minecraft.getInstance();
            Player player = mcinstance.player;
            state.getSpecialEffects().forEach((se) -> {
                boolean showingLevel = SpecialEffect.getRequestLevel(se) > 0;
                tooltip.add(Component.translatable("slashblade.tooltip.special_effect", SpecialEffect.getDescription(se), Component.literal(showingLevel ? String.valueOf(SpecialEffect.getRequestLevel(se)) : "").withStyle(SpecialEffect.isEffective(se, player.experienceLevel) ? ChatFormatting.RED : ChatFormatting.DARK_GRAY))
                        .withStyle(style -> style.withColor(ChatFormatting.GOLD)));

                if (Screen.hasShiftDown()) {
                    tooltip.add(Component.translatable("se." + se.toLanguageKey().replace("/", ".") + ".desc")
                            .withStyle(style -> style.withColor(ChatFormatting.LIGHT_PURPLE)));
                    for (int i = 0; i < 300; i++) {
                        String subKey = "se." + se.toLanguageKey().replace("/", ".") + ".desc_" + (i + 1);
                        if (I18n.exists(subKey)) {
                            tooltip.add(Component.translatable(subKey)
                                    .withStyle(style -> style.withColor(ChatFormatting.LIGHT_PURPLE)));
                        } else {
                            break;
                        }
                    }
                }
            });
            if (!Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("se." + SFAddons.MOD_ID + "." + MODID + ".shift_nodown")
                        .withStyle(style -> style.withColor(dynamicColor)));
            }
        }
        tooltip.add(Component.literal("-----------------------").withStyle(style -> style.withColor(ChatFormatting.GOLD)));
    }

    @Environment(EnvType.CLIENT)
    public void appendToolTip(List<Component> tooltip, @Nullable ItemStack stack, @NotNull ISlashBladeState state) {
        if (stack == null) return;

        String translationKey = gettranslationKey(stack);
        String[] split = translationKey.split("\\.");


        if (translationKey.isEmpty()) {
            return;
        }

        int colorCode = state.getColorCode();
        int dynamicColor = (colorCode != 0) ? (colorCode & 0xFFFFFF) : 0xFFFFFF;
        // 修复点1：数组越界保护
        if (split.length < 3) {
            Minecraft.getInstance().player.sendSystemMessage(
                    Component.literal("Invalid translation key: " + translationKey)
                            .withStyle(style -> style.withColor(dynamicColor)));
            return;
        }

        String baseKey = "slashblade.tooltip." + split[1] + "." + split[2];

        // 修复点2：动态检测有效翻译条目

        if (I18n.exists(baseKey)) {
            tooltip.add(Component.translatable(baseKey)
                    .withStyle(style -> style.withColor(dynamicColor)));
        }
        for (int i = 0; i < 12; i++) {
            String subKey = baseKey + "_" + (i + 1);
            if (I18n.exists(subKey)) {
                tooltip.add(Component.translatable(subKey)
                        .withStyle(style -> style.withColor(dynamicColor)));
            }
        }
    }


    public static String gettranslationKey(ItemStack stack) {
        return CapabilitySlashBlade.BLADESTATE.maybeGet(stack)
                .map(state -> {
                    // 示例获取方式，需根据实际Capability结构修改
                    String key = state.getTranslationKey();
                    return key != null ? key : "fallback.key";
                })
                .orElse("blade");
    }


    public void appendSwordType(ItemStack stack, @javax.annotation.Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

        EnumSet<SwordType> swordType = SwordType.from(stack);
        final String effectPrefix = SFAddons.MOD_ID + ":" + MODID + "/";


        ISlashBladeState state = SlashBladeUtil.getState(stack).get();
        int colorCode = state.getColorCode();
        int dynamicColor = (colorCode != 0) ? (colorCode & 0xFFFFFF) : 0xFFFFFF; // 默认白色


        // 使用效果配置数组简化条件判断
        EffectConfig[] effectConfigs = {
                new EffectConfig(YASpecialEffectsRegistry.StarSname, "starsname", ChatFormatting.LIGHT_PURPLE),
                new EffectConfig(YASpecialEffectsRegistry.Greenstarsoul, "starblade", ChatFormatting.GREEN),
                new EffectConfig(YASpecialEffectsRegistry.yuyuko, "yuyuko", ChatFormatting.BLUE),
                new EffectConfig(YASpecialEffectsRegistry.blackslashbreak, "blackslashbreak", ChatFormatting.DARK_RED),
                new EffectConfig(YASpecialEffectsRegistry.SUN_SOUL, "sun_soul", ChatFormatting.DARK_RED),
                new EffectConfig(YASpecialEffectsRegistry.Foxsoul, "starblade", ChatFormatting.DARK_RED),
                new EffectConfig(YASpecialEffectsRegistry.FoxsoulEX, "starblade", ChatFormatting.DARK_RED),
                new EffectConfig(YASpecialEffectsRegistry.STAR_SOUL, "starblade", ChatFormatting.BLUE)
        };

        for (EffectConfig config : effectConfigs) {
//            if (config.translationKey.equals("change_mode")){
//                tooltip.add(Component.translatable("yakumoblade.sword_type." + config.translationKey)
//                        .withStyle(config.color));
//                break;
//            }
            if (SlashEffectUtils.hasSpecialEffect(stack, effectPrefix + SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(config.effect).getPath())) {
                tooltip.add(Component.translatable("yakumoblade.sword_type." + config.translationKey)
                        .withStyle(style -> style.withColor(dynamicColor)));
                return;
            }
        }

        // 默认类型处理
        if (swordType.contains(SwordType.BEWITCHED)) {
            tooltip.add(Component.translatable("slashblade.sword_type.bewitched").withStyle(style -> style.withColor(dynamicColor)));
        } else if (swordType.contains(SwordType.ENCHANTED)) {
            tooltip.add(Component.translatable("slashblade.sword_type.enchanted").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("slashblade.sword_type.noname").withStyle(ChatFormatting.BLUE));
        }
        tooltip.add(Component.literal("-----------------------").withStyle(style -> style.withColor(ChatFormatting.GOLD)));
    }


    private static class EffectConfig {
        final SpecialEffect effect;
        final String translationKey;
        final ChatFormatting color;

        EffectConfig(SpecialEffect effect, String translationKey, ChatFormatting color) {
            this.effect = effect;
            this.translationKey = translationKey;
            this.color = color;
        }
    }


    @NotNull
    @Override
    public Component getName(@NotNull ItemStack stack) {
        // 从物品堆栈获取颜色代码（需要确保bladeState存在）
        ISlashBladeState state = SlashBladeUtil.getState(stack).get();
        int colorCode = state.getColorCode();
        int rgb = colorCode & 0xFFFFFF;

        // 获取原始名称并附加颜色
        return super.getName(stack).copy()
                .withStyle(style -> style.withColor(rgb));
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        CapabilitySlashBlade.BLADESTATE.maybeGet(stack).ifPresent((s) -> {
            this.appendSwordType(stack, worldIn, tooltip, flagIn);
            this.appendProudSoulCount(tooltip, stack, s);
            this.appendKillCount(tooltip, stack, s);
            this.appendSlashArt(stack, tooltip, s);
            this.appendRefineCount(tooltip, stack, s);
            this.appendSpecialEffects(tooltip, s);
            this.appendToolTip(tooltip, stack, s);
        });

    }


    @Override
    public void appendKillCount(List<Component> tooltip, @NotNull ItemStack stack, ISlashBladeState state) {

        int killCount = state.getKillCount();
        if (killCount > 0) {
            MutableComponent killCountComponent = Component.translatable("slashblade.tooltip.killcount", killCount).withStyle(ChatFormatting.DARK_PURPLE);
            if (killCount > 1000) {
                killCountComponent = killCountComponent.withStyle(ChatFormatting.LIGHT_PURPLE);
            }

            tooltip.add(killCountComponent);
        }

    }

    @Override
    public void appendProudSoulCount(List<Component> tooltip, @NotNull ItemStack stack, ISlashBladeState state) {
        int proudsoul = state.getProudSoulCount();
        if (proudsoul > 0) {
            MutableComponent countComponent = Component.translatable("slashblade.tooltip.proud_soul", proudsoul).withStyle(ChatFormatting.DARK_PURPLE);
            if (proudsoul > 1000) {
                countComponent = countComponent.withStyle(ChatFormatting.LIGHT_PURPLE);
            }

            tooltip.add(countComponent);
        }
    }


    @Override
    public void appendSlashArt(ItemStack stack, List<Component> tooltip, @NotNull ISlashBladeState s) {
        EnumSet<SwordType> swordType = SwordType.from(stack);
        if (s.getKillCount() < 1000) {
            super.appendSlashArt(stack, tooltip, s);
            return;
        }
        if (swordType.contains(SwordType.BEWITCHED) && !swordType.contains(SwordType.SEALED)) {
            int colorCode = s.getColorCode(); // 获取负数十进制颜色值
            int rgb = colorCode & 0xFFFFFF; // 转换为RGB十六进制值（去除符号位）
            tooltip.add(Component.translatable("slashblade.tooltip.slash_art",
                            s.getSlashArts().getDescription())
                    .withStyle(style -> style.withColor(rgb)));

        }
    }

}
