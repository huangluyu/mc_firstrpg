package com.github.huangluyu.firstrpg.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigLoader {
	private static Configuration config;

    public static int moneyBurnTime;
    
    public static int enchantmentFireBurnId;
    
    public static int potionFallProtectId;

    public ConfigLoader(FMLPreInitializationEvent event)
    {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        registerConfig();

        config.save();
    }

    private static void registerConfig()
    {
    	moneyBurnTime = config.get(Configuration.CATEGORY_GENERAL, "moneyBurnTime", 1).getInt();
    	enchantmentFireBurnId = config.get(Configuration.CATEGORY_GENERAL, "enchantmentFireBurnId", 36).getInt();
    	potionFallProtectId = config.get(Configuration.CATEGORY_GENERAL, "potionFallProtectId", 32).getInt();
    }
}
