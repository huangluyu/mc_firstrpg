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
//        //����ѩ��
//        if (!worldIn.isRemote)
//        {
//        	worldIn.spawnEntityInWorld(new EntityBiao(worldIn, playerIn, 2));
//        }
//
//        //����TNT
////        if(!worldIn.isRemote)
////        {
////        EntityTNTPrimed entity = new EntityTNTPrimed(worldIn, playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ, playerIn);//getEyeHeight�����ǻ�ȡ�����"�۸�",��ͷ�����ŵ׵ľ���
////        float angle = (playerIn.rotationYaw/ 180F) * 3.141593F; //ˮƽ����ĽǶ�
////        float angle2 = (-playerIn.rotationPitch/ 180F) * 3.141593F; //��ֱ���������
////        final float speed = 5f; //TNT�����ٶ� - ��Ǹ�����˸���
////        entity.motionY = speed * MathHelper.sin(angle2); //������������ϵ��ٶ�,Ϊ�˷����Ķ����ȼ����Y����ٶ�
////        entity.motionX = speed * MathHelper.cos(angle2) * -MathHelper.sin(angle); //������������ٶ���XZƽ���ϵ�ͶӰ,�������ֽ�ͶӰ
////        entity.motionZ = speed * MathHelper.cos(angle2) * MathHelper.cos(angle);
////        worldIn.spawnEntityInWorld(entity); //����ʵ�忩
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
        	//var6���ʹ��ʱ���ȥʱ��ʣ��
            int var6 = this.getMaxItemUseDuration(stack) - timeLeft;
            //ʵ��ʱ���ٳ���20
            float var7 = (float)var6 / 20.0F;
            //ʱ���ټ���
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            double speed = 1;
            float size = 0.2F;
            boolean key = false;
            EntityBiao biao;
            EntityBiao biao1 = null;
            EntityBiao biao2 = null;
            
            //����1,���Ƽ�
            if (var7 > 1.0F)
            {
                speed = 0.5;
                size = 0.8F;
                key = true;      
                biao = new EntityBiao(worldIn, playerIn, 10, speed, size, key, 1);
            } 
            //����0.5��������
            else if (var7 > 0.5F)
            {
            	biao = new EntityBiao(worldIn, playerIn, (double) (var7 * 8.0F), 1, size, key, 1);
            	biao1 = new EntityBiao(worldIn, playerIn, (double) (var7 * 8.0F), 1.5, size, key, 2);
            	biao2 = new EntityBiao(worldIn, playerIn, (double) (var7 * 8.0F), 1.5, size, key, 3);
            }else 
            {
            	//war8ʵ�������
            	biao = new EntityBiao(worldIn, playerIn, (double) (var7 * 8.0F), speed, size, key, 1);
            }
            
            //���������Ч
            worldIn.playSoundAtEntity(playerIn, "random.bow", var7, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
            
            //������1
            
            if (!worldIn.isRemote)
            {
            	if(var7 > 1.0F)
            	{
                	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                    World world = Minecraft.getMinecraft().theWorld;
                    player.addChatMessage(new ChatComponentTranslation("������!", 1));
            	}
                else if(var7 > 0.5F)
            	{
            		//biao.setIsCritical(true);
	            	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	                World world = Minecraft.getMinecraft().theWorld;
	                player.addChatMessage(new ChatComponentTranslation("����һ��!", 1));
	                worldIn.spawnEntityInWorld(biao);
	                worldIn.spawnEntityInWorld(biao1);
	                worldIn.spawnEntityInWorld(biao2);
	                return;
            	}
            	worldIn.spawnEntityInWorld(biao);
            }
            
            
//          ������2
//		    if(!worldIn.isRemote)
//		    {
//			    float angle = (playerIn.rotationYaw/ 180F) * 3.141593F; //ˮƽ����ĽǶ�
//			    float angle2 = (-playerIn.rotationPitch/ 180F) * 3.141593F; //��ֱ���������
//			    final float speed = 0.5f; //TNT�����ٶ� - ��Ǹ�����˸���
//			    biao = new EntityBiao(worldIn, playerIn.posX, playerIn.posY + playerIn.getEyeHeight(), playerIn.posZ, playerIn, (double)2 + (var7 * 2.0F), speed, 0.3F);//getEyeHeight�����ǻ�ȡ�����"�۸�",��ͷ�����ŵ׵ľ���
//			    biao.motionY = speed * MathHelper.sin(angle2); //������������ϵ��ٶ�,Ϊ�˷����Ķ����ȼ����Y����ٶ�
//			    biao.motionX = speed * MathHelper.cos(angle2) * -MathHelper.sin(angle); //������������ٶ���XZƽ���ϵ�ͶӰ,�������ֽ�ͶӰ
//			    biao.motionZ = speed * MathHelper.cos(angle2) * MathHelper.cos(angle);
//			    worldIn.spawnEntityInWorld(biao); //����ʵ�忩
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
     * How long it takes to use or consume an item ��Ҫ�೤ʱ��ʹ�������Ʒ(Ĭ��72000)
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
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer ������װ���������Ҽ���ʱ�򼤻�
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
    	//�ж��Ƿ��Ǵ���ģʽ���߰����м�
        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(ItemLoader.biao))
        {
        	//����ʹ���ߵ�״̬Ϊ����ʹ��,�������ʹ��ʱ��
            playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        }

        //������Ʒ?
        return itemStackIn;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material. //�ܷ�ħ?
     */
    public int getItemEnchantability()
    {
        return 0;
    }
}
