package com.bakkingamu.littlegarden;

import com.bakkingamu.littlegarden.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = LittleGardenMod.MODID, name = LittleGardenMod.NAME, version = LittleGardenMod.VERSION)
public class LittleGardenMod
{
    public static final String MODID = "littlegarden";
    public static final String NAME = "Little Garden";
    public static final String VERSION = "1.0";
    public static final String RESOURCE_INVENTORY  = "inventory";
    private static Logger logger;

    @SidedProxy(clientSide = "com.bakkingamu.littlegarden.proxy.ClientProxy", serverSide = "com.bakkingamu.littlegarden.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static LittleGardenMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

}
