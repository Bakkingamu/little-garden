package com.bakkingamu.littlegarden.capabilities.ingredients;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BlendIngredientProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IBlendIngredients.class)
    public static final Capability<IBlendIngredients> INGREDIENT_CAPABILITY = null;

    private IBlendIngredients instance = INGREDIENT_CAPABILITY.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == INGREDIENT_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == INGREDIENT_CAPABILITY ? INGREDIENT_CAPABILITY.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return INGREDIENT_CAPABILITY.getStorage().writeNBT(INGREDIENT_CAPABILITY, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        INGREDIENT_CAPABILITY.getStorage().readNBT(INGREDIENT_CAPABILITY, this.instance, null, nbt);
    }
}