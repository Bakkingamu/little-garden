package com.example.examplemod.capabilities;

import com.example.examplemod.models.TeaType;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TeaVariantStorage implements Capability.IStorage<ITeaVariant> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ITeaVariant> capability, ITeaVariant instance, EnumFacing side) {
        return new NBTTagString(instance.getTeaType().name());
    }

    @Override
    public void readNBT(Capability<ITeaVariant> capability, ITeaVariant instance, EnumFacing side, NBTBase nbt) {
        instance.setTeaType(TeaType.valueOf(((NBTTagString)nbt).getString()));
    }
}
