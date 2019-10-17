package com.bakkingamu.littlegarden.blocks;

import net.minecraft.block.BlockPlanks;

public class CinnamonPlanksBlock extends BlockPlanks {
    public static final String NAME = "cinnamon_planks";
    public static final String UNLOCALIZED_NAME = "cinnamon_planks_block";
    public static final String REGISTRY_NAME = "cinnamon_planks_block_registry";
    public CinnamonPlanksBlock(){
        super();
        this.setUnlocalizedName(UNLOCALIZED_NAME);
        this.setRegistryName(REGISTRY_NAME);
    }
}
