package com.bakkingamu.littlegarden.tileentities;

import com.bakkingamu.littlegarden.items.BrewedTea;
import com.bakkingamu.littlegarden.items.TeaBlend;
import com.bakkingamu.littlegarden.items.TeaLeaves;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TeaPot extends TileEntity implements ITickable {
    public static final String NAME = "tea plant";
    public static final String UNLOCALIZED_NAME = "tea_plant_block";
    public static final String REGISTRY_NAME = "tea_plant_block_registry";
    public TeaPot(){
        super();
    }

    private ItemStackHandler inventory = new ItemStackHandler(1);
    private int tickInterval = 100;
    private int tick = 0;


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }



    @Override
    public void update() {
        if (inventory.getStackInSlot(0).getItem() instanceof TeaBlend) {
            if (tick == tickInterval) {
                tick = 0;
                inventory.setStackInSlot(0, BrewedTea.brewedTeaFromBlend(inventory.getStackInSlot(0)));
            } else {
                tick += 1;
            }
        }
    }



}
