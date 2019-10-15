package com.example.examplemod.blocks;

import com.example.examplemod.ExampleMod;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    @GameRegistry.ObjectHolder(ExampleMod.MODID + ":" + PlantBlock.REGISTRY_NAME)
    public static PlantBlock plantBlock;
}