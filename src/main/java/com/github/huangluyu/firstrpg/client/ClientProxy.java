package com.github.huangluyu.firstrpg.client;

import com.github.huangluyu.firstrpg.client.entity.EntityRenderLoader;
import com.github.huangluyu.firstrpg.common.CommonProxy;
import com.github.huangluyu.firstrpg.entity.EntityBiao;

import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        new ItemRenderLoader();
        new EntityRenderLoader();
        new KeyLoader();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}