package com.github.huangluyu.firstrpg.crafting;

import com.github.huangluyu.firstrpg.block.BlockLoader;
import com.github.huangluyu.firstrpg.common.ConfigLoader;
import com.github.huangluyu.firstrpg.item.ItemLoader;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingLoader
{
    public CraftingLoader()
    {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }

    private static void registerRecipe()
    {
    	GameRegistry.addRecipe(new ItemStack(ItemLoader.money), new Object[]
        {
                "###", "#*#", "###", '#', Items.gold_ingot, '*', Items.egg
        });
        GameRegistry.addRecipe(new ItemStack(BlockLoader.testBlock), new Object[]
        {
                "##", "##", '#', Blocks.vine
        });
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.vine, 4), BlockLoader.testBlock);
        GameRegistry.addRecipe(new ItemStack(ItemLoader.redstonePickaxe), new Object[]
        {
                "###", " * ", " * ", '#', Items.redstone, '*', Items.stick
        });
        GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneApple), new Object[]
        {
                "###", "#*#", "###", '#', Items.redstone, '*', Items.apple
        });
        
        GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneHelmet), new Object[]
	    {
	            "###", "# #", '#', Items.redstone
	    });
	    GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneChestplate), new Object[]
	    {
	            "# #", "###", "###", '#', Items.redstone
	    });
	    GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneLeggings), new Object[]
	    {
	            "###", "# #", "# #", '#', Items.redstone
	    });
	    GameRegistry.addRecipe(new ItemStack(ItemLoader.redstoneBoots), new Object[]
	    {
	            "# #", "# #", '#', Items.redstone
	    });
    	    
    }

    private static void registerSmelting()
    {
    	GameRegistry.addSmelting(BlockLoader.testBlock, new ItemStack(Items.coal), 0.5F);
    }

    private static void registerFuel()
    {
    	GameRegistry.registerFuelHandler(new IFuelHandler()
        {
            @Override
            public int getBurnTime(ItemStack fuel)
            {
            	return ItemLoader.money != fuel.getItem() ? 0 : Math.max(0, ConfigLoader.moneyBurnTime) * 20;
            }
        });
    }
}