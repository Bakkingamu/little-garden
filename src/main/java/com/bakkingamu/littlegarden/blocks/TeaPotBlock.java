package com.bakkingamu.littlegarden.blocks;

import com.bakkingamu.littlegarden.LittleGardenMod;
import com.bakkingamu.littlegarden.capabilities.itemstorage.ItemStorageProvider;
import com.bakkingamu.littlegarden.items.TeaBlend;
import com.bakkingamu.littlegarden.tileentities.TeaPot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Random;

public class TeaPotBlock extends Block {
    public static final String NAME = "tea plant";
    public static final String UNLOCALIZED_NAME = "tea_pot_block";
    public static final String REGISTRY_NAME = "tea_pot_block_registry";
    public TeaPotBlock(){
        super(Material.ROCK);
        this.setUnlocalizedName(UNLOCALIZED_NAME);
        this.setRegistryName(REGISTRY_NAME);
        this.setCreativeTab(CreativeTabs.BREWING);

    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
//        return new AxisAlignedBB(.3125,0,.3125,.3125,.5,.3125);
        return new AxisAlignedBB(.3125, 0, .0625, .6875, .5,.875);

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TeaPot tile = (TeaPot)worldIn.getTileEntity(pos);
            if(tile == null) return false;
            IItemHandler itemHandler = tile.getCapability(ItemStorageProvider.ITEM_STORAGE, facing);
            if(itemHandler == null) return false;
            if (!playerIn.isSneaking()) { // Write functionality if not sneaking
                if (itemHandler.getStackInSlot(0).isEmpty()) { // Insert if container is empty
                    if(playerIn.getHeldItem(hand).getItem() instanceof TeaBlend) //Only insert blends
                        itemHandler.insertItem(0,playerIn.getHeldItem(hand).splitStack(1),false);
                    else
                        return false;
                } else { // Retrieve held item if hand is empty
                    if(playerIn.getHeldItem(hand).isEmpty()) playerIn.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
                }
                tile.markDirty();
            } else { // Read functionality if not sneaking
                ItemStack stack = itemHandler.getStackInSlot(0);
                System.out.println(stack.getDisplayName());
                if (!stack.isEmpty()) {
                    playerIn.sendMessage(new TextComponentString(stack.getCount() + "x " + stack.getUnlocalizedName()));
                } else {
                    playerIn.sendMessage(new TextComponentString("Empty"));
                }
            }
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        TeaPot tile = (TeaPot)worldIn.getTileEntity(pos);
        if(tile != null){
            IItemHandler itemHandler = tile.getCapability(ItemStorageProvider.ITEM_STORAGE, null);
            if( itemHandler != null && itemHandler.getStackInSlot(0).getCount() > 0){
                double d0 = (double)pos.getX() + 0.5D;
                double d1 = (double)pos.getY() + 0.7D;
                double d2 = (double)pos.getZ() + 0.5D;
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);

            }
        }
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TeaPot();
    }
}
