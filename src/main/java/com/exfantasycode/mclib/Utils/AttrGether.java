package com.exfantasycode.mclib.Utils;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AttrGether {
    public Holder<Attribute> attribute;
    public AttributeModifier attributeModifier;

    public AttrGether(Holder<Attribute> attribute, AttributeModifier attributeModifier) {
        this.attribute = attribute;
        this.attributeModifier = attributeModifier;
    }
}
