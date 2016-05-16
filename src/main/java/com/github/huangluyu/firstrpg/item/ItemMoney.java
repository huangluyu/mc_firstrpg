package com.github.huangluyu.firstrpg.item;

import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;

import net.minecraft.item.Item;

public class ItemMoney extends Item 
{
	public ItemMoney()
	{
		super();
		this.setUnlocalizedName("money");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}
}
