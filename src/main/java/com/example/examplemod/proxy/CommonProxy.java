package com.example.examplemod.proxy;

import com.example.examplemod.*;
import com.example.examplemod.blocks.PlantBlock;
import com.example.examplemod.capabilities.ITeaVariant;
import com.example.examplemod.capabilities.TeaVariant;
import com.example.examplemod.capabilities.TeaVariantHandler;
import com.example.examplemod.capabilities.TeaVariantStorage;
import com.example.examplemod.items.BrewedTea;
import com.example.examplemod.items.ModItems;
import com.example.examplemod.items.TeaLeaves;
import com.example.examplemod.items.TeaSeeds;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
        GameRegistry.addSmelting(ModItems.teaLeaves, new ItemStack(ModItems.brewedTea, 2), 1.0f);
        CapabilityManager.INSTANCE.register(ITeaVariant.class, new TeaVariantStorage(), TeaVariant::new);
        MinecraftForge.EVENT_BUS.register(new TeaVariantHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new PlantBlock());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        TeaSeeds ts = new TeaSeeds();
        ModelLoader.setCustomModelResourceLocation(ts, 0, new ModelResourceLocation(ExampleMod.MODID + ":" + TeaSeeds.REGISTRY_NAME, "inventory"));
        event.getRegistry().register(ts);

        TeaLeaves tl = new TeaLeaves();
        ModelLoader.setCustomModelResourceLocation(tl, 0, new ModelResourceLocation(ExampleMod.MODID + ":" + TeaLeaves.REGISTRY_NAME, "inventory"));
        event.getRegistry().register(tl);

        BrewedTea t2 = new BrewedTea();
        ModelLoader.setCustomModelResourceLocation(t2, 0, new ModelResourceLocation(ExampleMod.MODID + ":" + BrewedTea.REGISTRY_NAME, "inventory"));
        event.getRegistry().register(t2);

    }
}