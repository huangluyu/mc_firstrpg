package com.github.huangluyu.firstrpg.item;

import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;

import net.minecraft.item.ItemPickaxe;

public class ItemRedstonePickaxe extends ItemPickaxe
{
    public ItemRedstonePickaxe()
    {
        super(ItemLoader.REDSTONE);
        this.setUnlocalizedName("redstonePickaxe");
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }
}
