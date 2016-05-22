package com.github.huangluyu.firstrpg.entity;

import com.github.huangluyu.firstrpg.FirstRpg;
import com.github.huangluyu.firstrpg.client.entity.render.RenderBiao;
import com.github.huangluyu.firstrpg.client.entity.render.RenderGoldenChicken;
import com.github.huangluyu.firstrpg.item.ItemBiao;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class EntityLoader
{
	public static Item itemBiao = new ItemBiao();
	
    public EntityLoader()
    {
        registerEntity(EntityGoldenChicken.class, "GoldenChicken", 80, 3, true);
        registerEntityEgg(EntityGoldenChicken.class, 0xffff66, 0x660000);
        registerEntitySpawn(EntityGoldenChicken.class, 8, 2, 4, EnumCreatureType.CREATURE, BiomeGenBase.plains,
                BiomeGenBase.desert);
        
        registerEntity(EntityBiao.class, "Biao", 80, 3, true);
        
        
    }

    //注册实体
    public static void registerEntity(Class<? extends Entity> entityClass, String name, int trackingRange,
            int updateFrequency, boolean sendsVelocityUpdates)
    {
        int entityID = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
        EntityRegistry.registerModEntity(entityClass, name, entityID, FirstRpg.instance, trackingRange, updateFrequency,
                sendsVelocityUpdates);
    }
    
    //注册怪物蛋
    public static void registerEntityEgg(Class<? extends Entity> entityClass, int eggPrimary, int eggSecondary)
    {
        EntityRegistry.registerEgg(entityClass, eggPrimary, eggSecondary);
    }
    
    //模型生成
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerEntityRender(EntityGoldenChicken.class,
                new RenderGoldenChicken(Minecraft.getMinecraft().getRenderManager()));
    }

    @SideOnly(Side.CLIENT)
    private static void registerEntityRender(Class<? extends Entity> entityClass, Render renderer)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderer);
    }
    
    //自然生成
    private static void registerEntitySpawn(Class<? extends Entity> entityClass, int spawnWeight, int min,
            int max, EnumCreatureType typeOfCreature, BiomeGenBase... biomes)
    {
        if (EntityLiving.class.isAssignableFrom(entityClass))
        {
            Class<? extends EntityLiving> entityLivingClass = entityClass.asSubclass(EntityLiving.class);
            EntityRegistry.addSpawn(entityLivingClass, spawnWeight, min, max, typeOfCreature, biomes);
        }
    }
}