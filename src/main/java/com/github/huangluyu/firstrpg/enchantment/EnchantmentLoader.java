package com.github.huangluyu.firstrpg.enchantment;

import org.apache.logging.log4j.LogManager;

import com.github.huangluyu.firstrpg.FirstRpg;
import com.github.huangluyu.firstrpg.common.ConfigLoader;

import net.minecraft.enchantment.Enchantment;

public class EnchantmentLoader
{
    public static Enchantment fireBurn;

    public EnchantmentLoader()
    {
        try
        {
            EnchantmentLoader.fireBurn = new EnchantmentFireBurn();
            Enchantment.addToBookList(EnchantmentLoader.fireBurn);
        }
        catch (Exception e)
        {
            LogManager.getLogger(FirstRpg.MODID).error(
                    "Duplicate or illegal enchantment id: {}, the registry of class '{}' will be skipped. ",
                    ConfigLoader.enchantmentFireBurnId, EnchantmentFireBurn.class.getName());
        }
    }
}