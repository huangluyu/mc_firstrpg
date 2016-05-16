package com.github.huangluyu.firstrpg;

import com.github.huangluyu.firstrpg.common.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author ustc_zzzz
 */
@Mod(modid = FirstRpg.MODID, name = FirstRpg.NAME, version = FirstRpg.VERSION, acceptedMinecraftVersions = "[1.8,)")
public class FirstRpg
{
    public static final String MODID = "frpg";
    public static final String NAME = "firstrpg";
    public static final String VERSION = "0.1.0";

    @Instance(FirstRpg.MODID)
    public static FirstRpg instance;
  
    @SidedProxy(clientSide = "com.github.huangluyu.firstrpg.client.ClientProxy", 
            serverSide = "com.github.huangluyu.firstrpg.common.CommonProxy")
    
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}