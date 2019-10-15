package com.example.examplemod.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BrewedTea extends ItemBucketMilk {
    public static final String NAME = "brewed_tea";
    public static final String UNLOCALIZED_NAME = "brewed_tea";
    public static final String REGISTRY_NAME = "brewed_tea_registry";
    private Integer doses;
    public BrewedTea(){
        super();
        this.setCreativeTab(CreativeTabs.FOOD);
        setMaxStackSize(16);
        setRegistryName(REGISTRY_NAME);
        setUnlocalizedName(UNLOCALIZED_NAME);

    }
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving){
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
        if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode)
        {
            stack.shrink(1);
        }
        if (entityplayer instanceof EntityPlayerMP)
        {
            CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
        }
        entityLiving.addPotionEffect(new PotionEffect(PotionTypes.REGENERATION.getEffects().get(0)));
        return stack;
    }

}
