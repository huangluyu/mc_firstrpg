package com.github.huangluyu.firstrpg.item;

import com.github.huangluyu.firstrpg.FirstRpg;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.EnumHelper;


public class ItemLoader
{
    public static Item money = new ItemMoney();
    
    public static final Item.ToolMaterial REDSTONE = EnumHelper.addToolMaterial("REDSTONE", 3, 16, 16.0F, 0.0F, 10);
    public static final ItemArmor.ArmorMaterial REDSTONE_ARMOR = EnumHelper.addArmorMaterial("REDSTONE",
    		FirstRpg.MODID + ":" + "redstone", 10, new int[]
    {
            2, 6, 4, 2
    }, 10);
    
    public static ItemPickaxe redstonePickaxe = new ItemRedstonePickaxe();
    public static ItemFood redstoneApple = new ItemRedstoneApple();
    
    public static ItemArmor redstoneHelmet = new ItemRedstoneArmor.Helmet();
    public static ItemArmor redstoneChestplate = new ItemRedstoneArmor.Chestplate();
    public static ItemArmor redstoneLeggings = new ItemRedstoneArmor.Leggings();
    public static ItemArmor redstoneBoots = new ItemRedstoneArmor.Boots();
    
    public static ItemFood bigPowerPill = new ItemBigPowerPill();
    public static Item biao = new ItemBiao();
    
    public ItemLoader(FMLPreInitializationEvent event)
    {
        register(money, "money");
        register(redstonePickaxe, "redstone_pickaxe");
        register(redstoneApple, "redstone_apple");

        register(redstoneHelmet, "redstone_helmet");
        register(redstoneChestplate, "redstone_chestplate");
        register(redstoneLeggings, "redstone_leggings");
        register(redstoneBoots, "redstone_boots");
        
        register(bigPowerPill, "big_power_pill");
        register(biao, "biao");
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(money);
        registerRender(redstonePickaxe);
        registerRender(redstoneApple);

        registerRender(redstoneHelmet);
        registerRender(redstoneChestplate);
        registerRender(redstoneLeggings);
        registerRender(redstoneBoots);
        
        registerRender(bigPowerPill);
        registerRender(biao);
    }
    
    private static void register(Item item, String name)
    {
        GameRegistry.registerItem(item, name);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
                new ModelResourceLocation(GameRegistry.findUniqueIdentifierFor(item).toString(), "inventory"));
    }
}