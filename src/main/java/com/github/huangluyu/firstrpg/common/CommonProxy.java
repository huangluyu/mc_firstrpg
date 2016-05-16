package com.github.huangluyu.firstrpg.common;

import com.github.huangluyu.firstrpg.block.BlockLoader;
import com.github.huangluyu.firstrpg.crafting.CraftingLoader;
import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;
import com.github.huangluyu.firstrpg.enchantment.EnchantmentLoader;
import com.github.huangluyu.firstrpg.item.ItemLoader;
import com.github.huangluyu.firstrpg.potion.PotionLoader;
import com.github.huangluyu.frpg.achievement.AchievementLoader;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event)
    {
		new ConfigLoader(event);
		new CreativeTabsLoader(event);
		new ItemLoader(event);
        new BlockLoader(event);
        new PotionLoader(event);
    }

    public void init(FMLInitializationEvent event)
    {
    	new CraftingLoader();
    	new EnchantmentLoader();
    	new AchievementLoader();
    	new EventLoader();
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}
