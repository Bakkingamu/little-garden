package com.bakkingamu.littlegarden.items;

import com.bakkingamu.littlegarden.capabilities.variant.ITeaVariant;
import com.bakkingamu.littlegarden.capabilities.variant.TeaVariantProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;


public class TeaLeaves extends Item {
    public static final String NAME = "tea_leaves";
    public static final String UNLOCALIZED_NAME = "tea_leaves";
    public static final String REGISTRY_NAME = "tea_leaves_registry";
    public TeaLeaves(){
        super();
        this.setCreativeTab(CreativeTabs.FOOD);
        setRegistryName(REGISTRY_NAME);
        setUnlocalizedName(UNLOCALIZED_NAME);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        ITeaVariant variant = stack.getCapability(TeaVariantProvider.VARIANT_CAPABILITY, null);
        tooltip.add("Tea Type: " + (variant != null ? variant.getTeaType().name() : "Empty"));
    }
}
