package com.github.huangluyu.firstrpg.client;

import com.github.huangluyu.firstrpg.block.BlockLoader;
import com.github.huangluyu.firstrpg.item.ItemLoader;

public class ItemRenderLoader {
	public ItemRenderLoader()
    {
        ItemLoader.registerRenders();
        BlockLoader.registerRenders();
    }
}
