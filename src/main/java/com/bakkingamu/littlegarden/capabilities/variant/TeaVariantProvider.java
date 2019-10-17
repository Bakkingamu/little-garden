package com.bakkingamu.littlegarden.capabilities.variant;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class TeaVariantProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(ITeaVariant.class)
    public static final Capability<ITeaVariant> VARIANT_CAPABILITY = null;

    private ITeaVariant instance = VARIANT_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == VARIANT_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == VARIANT_CAPABILITY ? VARIANT_CAPABILITY.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return VARIANT_CAPABILITY.getStorage().writeNBT(VARIANT_CAPABILITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        VARIANT_CAPABILITY.getStorage().readNBT(VARIANT_CAPABILITY, this.instance, null, nbt);
    }
}