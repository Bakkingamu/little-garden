package com.example.examplemod.items;

import com.example.examplemod.blocks.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class TeaSeeds extends ItemSeeds {
    public static final String NAME = "tea";
    public static final String UNLOCALIZED_NAME = "tea_seeds";
    public static final String REGISTRY_NAME = "tea_seeds_registry";
    public TeaSeeds(){
        super(ModBlocks.plantBlock, Blocks.FARMLAND);
        this.setCreativeTab(CreativeTabs.MISC);
        setRegistryName(REGISTRY_NAME);
        setUnlocalizedName(UNLOCALIZED_NAME);
    }
}
