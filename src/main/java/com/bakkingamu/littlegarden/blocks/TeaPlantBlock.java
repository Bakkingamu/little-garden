package com.bakkingamu.littlegarden.blocks;

import com.bakkingamu.littlegarden.capabilities.variant.TeaVariantProvider;
import com.bakkingamu.littlegarden.items.ModItems;
import com.bakkingamu.littlegarden.items.TeaLeaves;
import com.bakkingamu.littlegarden.models.TeaType;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMushroomIsland;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TeaPlantBlock extends BlockCrops {
    public static final String NAME = "tea plant";
    public static final String UNLOCALIZED_NAME = "tea_plant_block";
    public static final String REGISTRY_NAME = "tea_plant_block_registry";
    public TeaPlantBlock(){
        super();
        this.setUnlocalizedName(UNLOCALIZED_NAME);
        this.setRegistryName(REGISTRY_NAME);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return getDefaultState();
    }

    @Override
    protected Item getSeed()
    {
        return ModItems.teaSeeds;
    }

    @Override
    protected Item getCrop()
    {
        return ModItems.teaLeaves;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        super.getDrops(drops, world, pos, state, fortune);
        Biome currentBiome = world.getBiome(pos);
        TeaType type = TeaPlantBlock.teaTypeFromBiome(currentBiome);
        applyType(drops,type);
    }

    public void applyType(NonNullList<ItemStack> stacks, TeaType type){
        stacks
                .stream()
                .filter((ItemStack stack) -> stack.getItem() instanceof TeaLeaves)
                .forEach((ItemStack stack) -> applyType(stack, type));
    }
    public void applyType(ItemStack stack, TeaType type){
        stack.getCapability(TeaVariantProvider.VARIANT_CAPABILITY, null).setTeaType(type);
    }

    static TeaType teaTypeFromBiome(Biome currentBiome){
        TeaType type;
        switch(currentBiome.getTempCategory()){
            case COLD:
                type = TeaType.Bitter;
                break;
            case WARM:
                type = TeaType.Dry;
                break;
            case OCEAN:
                type = TeaType.Moist;
                break;
            case MEDIUM:
                type = TeaType.Fresh;
                break;
            default:
                type = TeaType.Fresh;
        }

        if(currentBiome.getBiomeClass() == BiomeMushroomIsland.class) { }
        return type;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
}
