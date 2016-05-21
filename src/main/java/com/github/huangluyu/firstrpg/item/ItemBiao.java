package com.github.huangluyu.firstrpg.item;

import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;
import com.github.huangluyu.firstrpg.entity.EntityBiao;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemBiao extends Item
{
    private static final String __OBFID = "CL_00000069";

    public ItemBiao()
    {
        this.maxStackSize = 1;
        this.setUnlocalizedName("biao");
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
//    	     判断是否是创造模式,否则消耗一个
//        if (!playerIn.capabilities.isCreativeMode)
//        {
//            --itemStackIn.stackSize;
//        }

    	//飞行音效
        worldIn.playSoundAtEntity(playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        //客户端判定,客户端为1
        if (!worldIn.isRemote)
        {
            worldIn.spawnEntityInWorld(new EntityBiao(worldIn, playerIn));
        }

        //成就
        playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
        return itemStackIn;
    }
}























//package com.github.huangluyu.firstrpg.item;
//
//import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;
//
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.enchantment.Enchantment;
//import net.minecraft.enchantment.EnchantmentHelper;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.projectile.EntityArrow;
//import net.minecraft.init.Items;
//import net.minecraft.item.EnumAction;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.stats.StatList;
//import net.minecraft.world.World;
//
//public class ItemBiao extends Item
//{
//    public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
//    private static final String __OBFID = "CL_00001777";
//
//    public ItemBiao()
//    {
//        this.maxStackSize = 1;
//        this.setMaxDamage(384);
//        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
//    }
//
//    /**
//     * Called when the player stops using an Item (stops holding the right mouse button).
//     *  
//     * @param timeLeft The amount of ticks left before the using would have been complete
//     */
//    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
//    {
//    	//var5是否是创造模式或者是无穷附魔
//        boolean var5 = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;
//
//        //如果var5成立或者人们包里有箭则可以射
//        if (var5 || playerIn.inventory.hasItem(ItemLoader.biao))
//        {
//        	//var6最大使用时间减去时间剩余
//            int var6 = this.getMaxItemUseDuration(stack) - timeLeft;
//            //实际时间再除以20
//            float var7 = (float)var6 / 20.0F;
//            //时间再计算
//            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
//
//            //小于1,则返回
//            if ((double)var7 < 0.1D)
//            {
//                return;
//            }
//
//            //大于1,则等于1
//            if (var7 > 1.0F)
//            {
//                var7 = 1.0F;
//            }
//
//            //war8实体箭产生
//            EntityArrow var8 = new EntityArrow(worldIn, playerIn, var7 * 2.0F);
//
//            //如果var7等于1,即拉满弓则产生粒子效果
//            if (var7 == 1.0F)
//            {
//                var8.setIsCritical(true);
//            }
//
//            //力量附魔
//            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
//
//            //var8箭的伤害根据力量附魔提升
//            if (var9 > 0)
//            {
//                var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
//            }
//
//            //击退附魔
//            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
//
//            //设置击退效果
//            if (var10 > 0)
//            {
//                var8.setKnockbackStrength(var10);
//            }
//
//            //火焰附加附魔
//            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
//            {
//                var8.setFire(100);
//            }
//
//            //扣除弓箭一点耐久
//            //stack.damageItem(1, playerIn);
//            //弓箭射出音效
//            worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
//
//            //判断是否需要消耗箭
//            if (var5)
//            {
//                var8.canBePickedUp = 2;
//            }
//            else
//            {
//            	//箭消耗1
//                //playerIn.inventory.consumeInventoryItem(Items.arrow);
//            }
//
//            //成就获取
//            playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
//
//            //是否超过世界范围,超过则回收
//            if (!worldIn.isRemote)
//            {
//                worldIn.spawnEntityInWorld(var8);
//            }
//        }
//    }
//
//    /**
//     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
//     * the Item before the action is complete.
//     */
//    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
//    {
//        return stack;
//    }
//
//    /**
//     * How long it takes to use or consume an item 需要多长时间使用这个物品(默认72000)
//     */
//    public int getMaxItemUseDuration(ItemStack stack)
//    {
//        return 120000;
//    }
//
//    /**
//     * returns the action that specifies what animation to play when the items is being used
//     */
//    public EnumAction getItemUseAction(ItemStack stack)
//    {
//        return EnumAction.BOW;
//    }
//
//    /**
//     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer 在人们装备并按下右键的时候激活
//     */
//    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
//    {
//    	//判断是否是创造模式或者包里有箭
//        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(ItemLoader.biao))
//        {
//        	//设置使用者的状态为正在使用,设置最大时间
//            playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
//        }
//
//        //返回物品?
//        return itemStackIn;
//    }
//
//    /**
//     * Return the enchantability factor of the item, most of the time is based on material. //能否附魔?
//     */
//    public int getItemEnchantability()
//    {
//        return 0;
//    }
//}
