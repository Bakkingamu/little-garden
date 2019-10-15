package com.example.examplemod.items;

import com.example.examplemod.capabilities.ITeaVariant;
import com.example.examplemod.capabilities.TeaVariantProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeFireworks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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
