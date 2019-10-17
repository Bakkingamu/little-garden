package com.bakkingamu.littlegarden.items;

import com.bakkingamu.littlegarden.LittleGardenMod;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    @GameRegistry.ObjectHolder(LittleGardenMod.MODID + ":" + TeaSeeds.REGISTRY_NAME)
    public static TeaSeeds teaSeeds;

    @GameRegistry.ObjectHolder(LittleGardenMod.MODID + ":" + TeaLeaves.REGISTRY_NAME)
    public static TeaLeaves teaLeaves;

    @GameRegistry.ObjectHolder(LittleGardenMod.MODID + ":" + BrewedTea.REGISTRY_NAME)
    public static BrewedTea brewedTea;

    @GameRegistry.ObjectHolder(LittleGardenMod.MODID + ":" + TeaBlend.REGISTRY_NAME)
    public static TeaBlend teaBlend;
}