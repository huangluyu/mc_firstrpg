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
//    	     �ж��Ƿ��Ǵ���ģʽ,��������һ��
//        if (!playerIn.capabilities.isCreativeMode)
//        {
//            --itemStackIn.stackSize;
//        }

    	//������Ч
        worldIn.playSoundAtEntity(playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        //�ͻ����ж�,�ͻ���Ϊ1
        if (!worldIn.isRemote)
        {
            worldIn.spawnEntityInWorld(new EntityBiao(worldIn, playerIn));
        }

        //�ɾ�
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
//    	//var5�Ƿ��Ǵ���ģʽ���������ħ
//        boolean var5 = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;
//
//        //���var5�����������ǰ����м��������
//        if (var5 || playerIn.inventory.hasItem(ItemLoader.biao))
//        {
//        	//var6���ʹ��ʱ���ȥʱ��ʣ��
//            int var6 = this.getMaxItemUseDuration(stack) - timeLeft;
//            //ʵ��ʱ���ٳ���20
//            float var7 = (float)var6 / 20.0F;
//            //ʱ���ټ���
//            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
//
//            //С��1,�򷵻�
//            if ((double)var7 < 0.1D)
//            {
//                return;
//            }
//
//            //����1,�����1
//            if (var7 > 1.0F)
//            {
//                var7 = 1.0F;
//            }
//
//            //war8ʵ�������
//            EntityArrow var8 = new EntityArrow(worldIn, playerIn, var7 * 2.0F);
//
//            //���var7����1,�����������������Ч��
//            if (var7 == 1.0F)
//            {
//                var8.setIsCritical(true);
//            }
//
//            //������ħ
//            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
//
//            //var8�����˺�����������ħ����
//            if (var9 > 0)
//            {
//                var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
//            }
//
//            //���˸�ħ
//            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
//
//            //���û���Ч��
//            if (var10 > 0)
//            {
//                var8.setKnockbackStrength(var10);
//            }
//
//            //���渽�Ӹ�ħ
//            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
//            {
//                var8.setFire(100);
//            }
//
//            //�۳�����һ���;�
//            //stack.damageItem(1, playerIn);
//            //���������Ч
//            worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
//
//            //�ж��Ƿ���Ҫ���ļ�
//            if (var5)
//            {
//                var8.canBePickedUp = 2;
//            }
//            else
//            {
//            	//������1
//                //playerIn.inventory.consumeInventoryItem(Items.arrow);
//            }
//
//            //�ɾͻ�ȡ
//            playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
//
//            //�Ƿ񳬹����緶Χ,���������
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
//     * How long it takes to use or consume an item ��Ҫ�೤ʱ��ʹ�������Ʒ(Ĭ��72000)
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
//     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer ������װ���������Ҽ���ʱ�򼤻�
//     */
//    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
//    {
//    	//�ж��Ƿ��Ǵ���ģʽ���߰����м�
//        if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(ItemLoader.biao))
//        {
//        	//����ʹ���ߵ�״̬Ϊ����ʹ��,�������ʱ��
//            playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
//        }
//
//        //������Ʒ?
//        return itemStackIn;
//    }
//
//    /**
//     * Return the enchantability factor of the item, most of the time is based on material. //�ܷ�ħ?
//     */
//    public int getItemEnchantability()
//    {
//        return 0;
//    }
//}
