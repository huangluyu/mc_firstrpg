package com.github.huangluyu.firstrpg.item;

import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;

import net.minecraft.item.ItemFood;

public class ItemBigPowerPill extends ItemFood{
	//¥Û¡¶ÕË
	public ItemBigPowerPill()
    {
        super(2, 0F, false);
        this.setAlwaysEdible();
        this.setUnlocalizedName("bigPowerPill");
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }
}
