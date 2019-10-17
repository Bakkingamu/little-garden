package com.bakkingamu.littlegarden.proxy;

import com.bakkingamu.littlegarden.LittleGardenMod;
import com.bakkingamu.littlegarden.blocks.*;
import com.bakkingamu.littlegarden.capabilities.CapabilitiesHandler;
import com.bakkingamu.littlegarden.capabilities.ingredients.BlendIngredients;
import com.bakkingamu.littlegarden.capabilities.ingredients.BlendIngredientsStorage;
import com.bakkingamu.littlegarden.capabilities.ingredients.IBlendIngredients;
import com.bakkingamu.littlegarden.capabilities.variant.ITeaVariant;
import com.bakkingamu.littlegarden.capabilities.variant.TeaVariantStorage;
import com.bakkingamu.littlegarden.items.*;
import com.bakkingamu.littlegarden.capabilities.variant.TeaVariant;
import com.bakkingamu.littlegarden.recipes.TeaBlendRecipe;
import com.bakkingamu.littlegarden.tileentities.TeaPot;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
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
        // Tea Variant Capabilities
        CapabilityManager.INSTANCE.register(ITeaVariant.class, new TeaVariantStorage(), TeaVariant::new);
        CapabilityManager.INSTANCE.register(IBlendIngredients.class, new BlendIngredientsStorage(), BlendIngredients::new);
        MinecraftForge.EVENT_BUS.register(new CapabilitiesHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event){
        (event.getRegistry()).register(new TeaBlendRecipe().setRegistryName("tea_blend_recipe"));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new TeaPlantBlock());
        event.getRegistry().register(new CinnamonLogBlock());
        event.getRegistry().register(new CinnamonPlanksBlock());
        event.getRegistry().register(new TeaPotBlock());
        //tile entity needs modid in front?
        GameRegistry.registerTileEntity(TeaPot.class, LittleGardenMod.MODID + ":" + "tea_pot_block_registry.json");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        TeaSeeds teaSeeds = new TeaSeeds();
        ModelLoader.setCustomModelResourceLocation(teaSeeds, 0, new ModelResourceLocation(LittleGardenMod.MODID + ":" + TeaSeeds.REGISTRY_NAME, "inventory"));
        event.getRegistry().register(teaSeeds);

        TeaLeaves teaLeaves = new TeaLeaves();
        ModelLoader.setCustomModelResourceLocation(teaLeaves, 0, new ModelResourceLocation(LittleGardenMod.MODID + ":" + TeaLeaves.REGISTRY_NAME, "inventory"));
        event.getRegistry().register(teaLeaves);

        BrewedTea brewedTea = new BrewedTea();
        ModelLoader.setCustomModelResourceLocation(brewedTea, 0, new ModelResourceLocation(LittleGardenMod.MODID + ":" + BrewedTea.REGISTRY_NAME, "inventory"));
        event.getRegistry().register(brewedTea);

        TeaBlend teaBlend = new TeaBlend();
        ModelLoader.setCustomModelResourceLocation(teaBlend, 0, new ModelResourceLocation(LittleGardenMod.MODID + ":" + TeaBlend.REGISTRY_NAME, "inventory"));
        event.getRegistry().register(teaBlend);

        Item itemTeaPot = new ItemBlock(ModBlocks.teaPotBlock).setRegistryName(LittleGardenMod.MODID,TeaPotBlock.REGISTRY_NAME).setCreativeTab(CreativeTabs.DECORATIONS);
        event.getRegistry().register(itemTeaPot);
        ModelLoader.setCustomModelResourceLocation(itemTeaPot,0,new ModelResourceLocation(LittleGardenMod.MODID + ":" + TeaPotBlock.REGISTRY_NAME));

        Item itemCinnamonLog = new ItemBlock(ModBlocks.cinnamonLogBlock).setRegistryName(LittleGardenMod.MODID,CinnamonLogBlock.REGISTRY_NAME).setCreativeTab(CreativeTabs.MATERIALS);
        event.getRegistry().register(itemCinnamonLog);
        ModelLoader.setCustomModelResourceLocation(itemCinnamonLog,0,new ModelResourceLocation(LittleGardenMod.MODID + ":" + CinnamonLogBlock.REGISTRY_NAME));


        Item itemCinnamonPlanks = new ItemBlock(ModBlocks.cinnamonPlanksBlock).setRegistryName(LittleGardenMod.MODID,CinnamonPlanksBlock.REGISTRY_NAME).setCreativeTab(CreativeTabs.MATERIALS);
        event.getRegistry().register(itemCinnamonPlanks);
        ModelLoader.setCustomModelResourceLocation(itemCinnamonPlanks,0,new ModelResourceLocation(LittleGardenMod.MODID + ":" + CinnamonPlanksBlock.REGISTRY_NAME));

    }
}