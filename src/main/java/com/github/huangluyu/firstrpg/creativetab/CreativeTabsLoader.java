package com.github.huangluyu.firstrpg.creativetab;

import com.github.huangluyu.firstrpg.item.ItemLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader
{
    public static CreativeTabs tabFMLTutor;

    public CreativeTabsLoader(FMLPreInitializationEvent event)
    {
        tabFMLTutor = new CreativeTabs("tabFMLTutor")
        {
            @Override
            public Item getTabIconItem()
            {
                return ItemLoader.money;
            }
        };
    }
}
