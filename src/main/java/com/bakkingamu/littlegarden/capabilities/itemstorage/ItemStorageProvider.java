package com.bakkingamu.littlegarden.capabilities.itemstorage;

import com.bakkingamu.littlegarden.capabilities.variant.ITeaVariant;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStorageProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IItemHandler.class)
    public static final Capability<IItemHandler> ITEM_STORAGE = null;

    private IItemHandler instance = ITEM_STORAGE.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == ITEM_STORAGE;

    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == ITEM_STORAGE ? ITEM_STORAGE.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return ITEM_STORAGE.getStorage().writeNBT(ITEM_STORAGE, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        ITEM_STORAGE.getStorage().readNBT(ITEM_STORAGE, this.instance, null, nbt);
    }
}