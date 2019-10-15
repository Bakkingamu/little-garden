package com.example.examplemod.items;

import com.example.examplemod.ExampleMod;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    @GameRegistry.ObjectHolder(ExampleMod.MODID + ":" + TeaSeeds.REGISTRY_NAME)
    public static TeaSeeds teaSeeds;

    @GameRegistry.ObjectHolder(ExampleMod.MODID + ":" + TeaLeaves.REGISTRY_NAME)
    public static TeaLeaves teaLeaves;

    @GameRegistry.ObjectHolder(ExampleMod.MODID + ":" + BrewedTea.REGISTRY_NAME)
    public static BrewedTea brewedTea;
}