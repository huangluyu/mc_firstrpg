package com.github.huangluyu.firstrpg.item;

import java.util.ArrayList;
import java.util.List;

import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;
import com.github.huangluyu.firstrpg.entity.EntityBiao;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBigSword extends Item{
	public ItemBigSword()
    {
    	this.maxStackSize = 1;
    	this.setUnlocalizedName("bigSword");
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     *  
     * @param timeLeft The amount of ticks left before the using would have been complete
     */
	//右键蓄力落雷
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
    {
    	//var6最大使用时间减去时间剩余
    	int var6 = this.getMaxItemUseDuration(stack) - timeLeft;
        //实际时间再除以20
        float var7 = (float)var6 / 20.0F;
        //时间再计算
        var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
        int n=0;
        if(var7 > 0.5F)
        {
        	n = 1;
        }
        else if (var7 > 1.0F)
        {
        	n = 2;
        }
        else if (var7 > 1.5F)
        {
        	n = 3;
        }
        for (int i = 0; i < n; i++)
        {
			if (!worldIn.isRemote)
			{
				EntityBiao biao = new EntityBiao(worldIn, playerIn, 0, 4, 0.01F, true);
				worldIn.spawnEntityInWorld(biao);
			}
        }
    	//落雷打自己
    	//worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ));
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
        return EnumAction.BOW;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer 在人们装备并按下右键的时候激活
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
    	playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material. //能否附魔?
     */
    public int getItemEnchantability()
    {
        return 0;
    }
    
    /**
     * Called when the player Left Clicks (attacks) an entity.
     * Processed before damage is done, if return value is true further processing is canceled
     * and the entity is not attacked.
     *
     * @param stack The Item being used
     * @param player The player that is attacking
     * @param entity The entity being attacked
     * @return True to cancel the rest of the interaction.
     */
    //左键群体攻击
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	if (!player.worldObj.isRemote)
        {
    		double x_ = player.rotationYaw;
    		double y_ = player.rotationPitch;
    		int n = (int)y_ / 90;
    		
    		double x = player.posX;
    		double y = player.posY;
    		double z = player.posZ;

            World world = Minecraft.getMinecraft().theWorld;
            
            List list =  new ArrayList();
            List list1,list2,list3,list4;
            n = 1;
    	if (x_ >= 22.5 && x_ < 67.5)
    	{ //西南
    		list4 = player.worldObj.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(x-2, y+n-2, z-1, x-2, y+n+2, z-1));
    		list3 = player.worldObj.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(x-1, y+n-2, z  , x-1, y+n+2, z  ));
    		list2 = player.worldObj.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(x  , y+n-2, z+1, x  , y+n+2, z+1));
    		list1 = player.worldObj.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(x+1, y+n-2, z+2, x-2, y+n+2, z+2));
    		list.addAll(list1);  		
    		list.addAll(list2);
    		list.addAll(list3);
    		list.addAll(list4);
    		player.addChatMessage(new ChatComponentTranslation("西南!%s%s%s%s数量:%s,列表:%s", list1.size(), list2.size(), list3.size(), list4.size(), list.size(), list));
    		
    		for (int i = 0; i < list.size(); ++i)
            {
                Entity entity2 = (Entity)list.get(i);
                entity2.attackEntityFrom(DamageSource.lightningBolt, 5.0F);
            }
    	}
    	else if (x_ >= 67.5 && x_ < 112.5)
    	{ //西
    		//player.addChatMessage(new ChatComponentTranslation("x:%s,y:%s,z:%s", x, y, z));
    		list3 = player.worldObj.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(x-3, y+n-2, z  , x-3, y+n+2, z  ));
    		list2 = player.worldObj.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(x-2, y+n-2, z+1, x-2, y+n+2, z-1));
    		list1 = player.worldObj.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(x-1, y+n-2, z+2, x-1, y+n+2, z-2));
    		list.addAll(list1);  		
    		list.addAll(list2);
    		list.addAll(list3);
    		
    		player.addChatMessage(new ChatComponentTranslation("西!%s%s%s数量:%s", list1.size(), list2.size(), list3.size(), list.size()));
    		
    		for (int i = 0; i < list1.size(); ++i)
            {
                Entity entity2 = (Entity)list1.get(i);
                entity2.attackEntityFrom(DamageSource.fallingBlock, 5.0F);
            }
    	}
    	else if (x_ >= 112.5 && x_ < 157.5)
    	{ //西北
    		player.addChatMessage(new ChatComponentTranslation("西北"));
    	}
    	else if (x_ >= 157.5 && x_ < 202.5)
    	{ //北
    		player.addChatMessage(new ChatComponentTranslation("北"));
    	}
    	else if (x_ >= 202.5 && x_ < 247.5)
    	{ //东北
    		player.addChatMessage(new ChatComponentTranslation("东北"));
    	}
    	else if (x_ >= 247.5 && x_ < 292.5)
    	{ //东
    		player.addChatMessage(new ChatComponentTranslation("东"));
    	}
    	else if (x_ >= 292.5 && x_ < 337.5)
    	{ //东南
    		player.addChatMessage(new ChatComponentTranslation("东南"));
    	}
    	else if (x_ >= 337.5 || x_ < 22.5)
    	{ //南
    		player.addChatMessage(new ChatComponentTranslation("南"));
    	}
        }
        return true;
    }
}


