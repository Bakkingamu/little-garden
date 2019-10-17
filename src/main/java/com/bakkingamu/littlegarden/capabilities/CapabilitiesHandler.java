package com.bakkingamu.littlegarden.capabilities;

import com.bakkingamu.littlegarden.LittleGardenMod;
import com.bakkingamu.littlegarden.blocks.TeaPotBlock;
import com.bakkingamu.littlegarden.capabilities.ingredients.BlendIngredientProvider;
import com.bakkingamu.littlegarden.capabilities.itemstorage.ItemStorageProvider;
import com.bakkingamu.littlegarden.capabilities.variant.TeaVariantProvider;
import com.bakkingamu.littlegarden.items.BrewedTea;
import com.bakkingamu.littlegarden.items.TeaBlend;
import com.bakkingamu.littlegarden.items.TeaLeaves;
import com.bakkingamu.littlegarden.tileentities.TeaPot;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CapabilitiesHandler {
    public static final ResourceLocation VARIANT_CAPABILITY = new ResourceLocation(LittleGardenMod.MODID, "teavariant");
    public static final ResourceLocation INGREDIENT_CAPABILITY = new ResourceLocation(LittleGardenMod.MODID, "blendingredient");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<ItemStack> event)
    {
        if(event.getObject().getItem() instanceof TeaLeaves){
            event.addCapability(VARIANT_CAPABILITY, new TeaVariantProvider());
        }
        if(event.getObject().getItem() instanceof TeaBlend){
            event.addCapability(INGREDIENT_CAPABILITY, new BlendIngredientProvider());
        }
        if(event.getObject().getItem() instanceof BrewedTea){
            event.addCapability(INGREDIENT_CAPABILITY, new BlendIngredientProvider());
        }
    }

    @SubscribeEvent
    public void attachCapabilityBlocks(AttachCapabilitiesEvent<TileEntity> event){
        if(event.getObject() instanceof TeaPot){
            System.out.println("Attaching");
            event.addCapability(new ResourceLocation("InventoryCapability"), new ItemStorageProvider());
        }
    }
}
