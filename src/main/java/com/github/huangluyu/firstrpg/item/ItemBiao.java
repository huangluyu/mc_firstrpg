//package com.github.huangluyu.firstrpg.item;
//
//import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;
//import com.github.huangluyu.firstrpg.entity.EntityBiao;
//
//import net.minecraft.creativetab.CreativeTabs;
//import net.minecraft.entity.item.EntityTNTPrimed;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.projectile.EntitySnowball;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.stats.StatList;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.World;
//
//public class ItemBiao extends Item
//{
//    private static final String __OBFID = "CL_00000069";
//
//    public ItemBiao()
//    {
//        this.maxStackSize = 1;
//        this.setUnlocalizedName("biao");
//        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
//    }
//
//    /**
//     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
//     */
//    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
//    {
//        worldIn.playSoundAtEntity(playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
//
//        //发射雪球
//        if (!worldIn.isRemote)
//        {
//        	worldIn.spawnEntityInWorld(new EntityBiao(worldIn, playerIn, 2));
//        }
//
//        //发射TNT
////        if(!worldIn.isRemote)
////        {
////        EntityTNTPrimed entity = new EntityTNTPrimed(worldIn, playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ, playerIn);//getEyeHeight方法是获取物体的"眼高",即头部到脚底的距离
////        float angle = (playerIn.rotationYaw/ 180F) * 3.141593F; //水平方向的角度
////        float angle2 = (-playerIn.rotationPitch/ 180F) * 3.141593F; //垂直方向的仰角
////        final float speed = 5f; //TNT飞行速度 - 抱歉我卖了个萌
////        entity.motionY = speed * MathHelper.sin(angle2); //算出三个方向上的速度,为了方便阅读我先计算的Y轴分速度
////        entity.motionX = speed * MathHelper.cos(angle2) * -MathHelper.sin(angle); //根据仰角算出速度在XZ平面上的投影,再正交分解投影
////        entity.motionZ = speed * MathHelper.cos(angle2) * MathHelper.cos(angle);
////        worldIn.spawnEntityInWorld(entity); //放置实体咯
////        }
//        return itemStackIn;
//    }
//}
//






















package com.github.huangluyu.firstrpg.item;

import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;
import com.github.huangluyu.firstrpg.entity.EntityBiao;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBiao extends Item
{
    public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
    private static final String __OBFID = "CL_00001777";

    public ItemBiao()
    {
    	this.maxStackSize = 1;
    	this.setUnlocalizedName("biao");
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     *  
     * @param timeLeft The amount of ticks left before the using would have been complete
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
    {
        if (playerIn.inventory.hasItem(ItemLoader.biao))
        {
        	//var6最大使用时间减去时间剩余
            int var6 = this.getMaxItemUseDuration(stack) - timeLeft;
            //实际时间再除以20
            float var7 = (float)var6 / 20.0F;
            //时间再计算
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            double speed = 1;
            float size = 0.2F;
            boolean key = false;
            EntityBiao biao;
            EntityBiao biao1 = null;
            EntityBiao biao2 = null;
            
            //大于1,则穿云箭
            if (var7 > 1.0F)
            {
                speed = 0.5;
                size = 0.8F;
                key = true;      
                biao = new EntityBiao(worldIn, playerIn, 10, speed, size, key, 1);
            } 
            //大于0.5则三连发
            else if (var7 > 0.5F)
            {
            	biao = new EntityBiao(worldIn, playerIn, (double) (var7 * 8.0F), 1, size, key, 1);
            	biao1 = new EntityBiao(worldIn, playerIn, (double) (var7 * 8.0F), 1.5, size, key, 2);
            	biao2 = new EntityBiao(worldIn, playerIn, (double) (var7 * 8.0F), 1.5, size, key, 3);
            }else 
            {
            	//war8实体箭产生
            	biao = new EntityBiao(worldIn, playerIn, (double) (var7 * 8.0F), speed, size, key, 1);
            }
            
            //弓箭射出音效
            worldIn.playSoundAtEntity(playerIn, "random.bow", var7, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
            
            //发射镖1
            
            if (!worldIn.isRemote)
            {
            	if(var7 > 1.0F)
            	{
                	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                    World world = Minecraft.getMinecraft().theWorld;
                    player.addChatMessage(new ChatComponentTranslation("穿云镖!", 1));
            	}
                else if(var7 > 0.5F)
            	{
            		//biao.setIsCritical(true);
	            	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	                World world = Minecraft.getMinecraft().theWorld;
	                player.addChatMessage(new ChatComponentTranslation("惊天一击!", 1));
	                worldIn.spawnEntityInWorld(biao);
	                worldIn.spawnEntityInWorld(biao1);
	                worldIn.spawnEntityInWorld(biao2);
	                return;
            	}
            	worldIn.spawnEntityInWorld(biao);
            }
            
            
//          发射镖2
//		    if(!worldIn.isRemote)
//		    {
//			    float angle = (playerIn.rotationYaw/ 180F) * 3.141593F; //水平方向的角度
//			    float angle2 = (-playerIn.rotationPitch/ 180F) * 3.141593F; //垂直方向的仰角
//			    final float speed = 0.5f; //TNT飞行速度 - 抱歉我卖了个萌
//			    biao = new EntityBiao(worldIn, playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ, playerIn, (double)2 + (var7 * 2.0F), speed, 0.3F);//getEyeHeight方法是获取物体的"眼高",即头部到脚底的距离
//			    biao.motionY = speed * MathHelper.sin(angle2); //算出三个方向上的速度,为了方便阅读我先计算的Y轴分速度
//			    biao.motionX = speed * MathHelper.cos(angle2) * -MathHelper.sin(angle); //根据仰角算出速度在XZ平面上的投影,再正交分解投影
//			    biao.motionZ = speed * MathHelper.cos(angle2) * MathHelper.cos(angle);
//			    worldIn.spawnEntityInWorld(biao); //放置实体咯
//		    }
        }
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
        return stack;
    }

    /**
     * How long it takes to use or consume an item 需要多长时间使用这个物品(默认72000)
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 100;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BLOCK;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer 在人们装备并按下右键的时候激活
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
    	//判断是否是创造模式或者包里有箭
        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(ItemLoader.biao))
        {
        	//设置使用者的状态为正在使用,设置最大使用时间
            playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        }

        //返回物品?
        return itemStackIn;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material. //能否附魔?
     */
    public int getItemEnchantability()
    {
        return 0;
    }
}
