/**
 * The code of this mod element is always locked.
 * <p>
 * You can register new events in this class too.
 * <p>
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.exmo.rottenketime as this package is managed by MCreator.
 * <p>
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 * <p>
 * This class will be added in the mod root package.
 */
package com.exfantasycode.mclib.Utils;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AttrUtil {
    public enum WearOrTake {
        WEAR,
        TAKE,

    }

    public static void entityAddAttrTF(AttrGether attrGether, LivingEntity entity, WearOrTake wearOrTake) {
        Attribute attribute = attrGether.attribute;
        AttributeModifier attributeModifier = attrGether.attributeModifier;

        switch (wearOrTake) {
            case WEAR:
                if (entity.getAttributes().hasAttribute(attribute))
                    if (!(entity.getAttribute(attribute).hasModifier(attributeModifier)))
                        entity.getAttribute(attribute).addPermanentModifier(attributeModifier);

                break;
            case TAKE:
                if (entity.getAttributes().hasAttribute(attribute))
                    if ((entity.getAttribute(attribute).hasModifier(attributeModifier)))
                        entity.getAttribute(attribute).removeModifier(attributeModifier);

                break;
        }
    }
}
