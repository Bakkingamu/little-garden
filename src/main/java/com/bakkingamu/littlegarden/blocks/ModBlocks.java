package com.bakkingamu.littlegarden.blocks;

import com.bakkingamu.littlegarden.LittleGardenMod;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    @GameRegistry.ObjectHolder(LittleGardenMod.MODID + ":" + TeaPlantBlock.REGISTRY_NAME)
    public static TeaPlantBlock teaPlantBlock;

    @GameRegistry.ObjectHolder(LittleGardenMod.MODID + ":" + TeaPotBlock.REGISTRY_NAME)
    public static TeaPotBlock teaPotBlock;

    @GameRegistry.ObjectHolder(LittleGardenMod.MODID + ":" + CinnamonLogBlock.REGISTRY_NAME)
    public static CinnamonLogBlock cinnamonLogBlock;

    @GameRegistry.ObjectHolder(LittleGardenMod.MODID + ":" + CinnamonPlanksBlock.REGISTRY_NAME)
    public static CinnamonPlanksBlock cinnamonPlanksBlock;

}