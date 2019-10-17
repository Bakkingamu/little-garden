package com.bakkingamu.littlegarden.capabilities.ingredients;

import com.bakkingamu.littlegarden.LittleGardenMod;
import com.bakkingamu.littlegarden.items.TeaBlend;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
public class BlendIngredientHandler {
    public static final ResourceLocation INGREDIENT_CAPABILITY = new ResourceLocation(LittleGardenMod.MODID, "blendingredient");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<ItemStack> event)
    {
        if (!(event.getObject().getItem() instanceof TeaBlend)) return;
        event.addCapability(INGREDIENT_CAPABILITY, new BlendIngredientProvider());
    }
}
