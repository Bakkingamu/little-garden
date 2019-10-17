package com.bakkingamu.littlegarden.capabilities.variant;

import com.bakkingamu.littlegarden.LittleGardenMod;
import com.bakkingamu.littlegarden.items.TeaLeaves;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
public class TeaVariantHandler {
    public static final ResourceLocation VARIANT_CAPABILITY = new ResourceLocation(LittleGardenMod.MODID, "teavariant");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<ItemStack> event)
    {
        if (!(event.getObject().getItem() instanceof TeaLeaves)) return;
        event.addCapability(VARIANT_CAPABILITY, new TeaVariantProvider());
    }
}
