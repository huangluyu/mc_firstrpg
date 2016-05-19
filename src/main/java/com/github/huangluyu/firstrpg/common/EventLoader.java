package com.github.huangluyu.firstrpg.common;

import com.github.huangluyu.firstrpg.achievement.AchievementLoader;
import com.github.huangluyu.firstrpg.block.BlockLoader;
import com.github.huangluyu.firstrpg.client.KeyLoader;
import com.github.huangluyu.firstrpg.enchantment.EnchantmentLoader;
import com.github.huangluyu.firstrpg.entity.EntityGoldenChicken;
import com.github.huangluyu.firstrpg.item.ItemLoader;
import com.github.huangluyu.firstrpg.potion.PotionLoader;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventLoader
{
	public EventLoader()
    {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
        EventLoader.EVENT_BUS.register(this);
    }

//    @SubscribeEvent
//    public void onPlayerItemPickup(PlayerEvent.ItemPickupEvent event)
//    {
//        if (event.player.isServerWorld())
//        {
//            event.player.addChatComponentMessage(new ChatComponentText(event.pickedUp.toString()));
//        }
//    }

//    @SubscribeEvent
//    public void onPlayerInteract(PlayerInteractEvent event)
//    {
//        if (!event.world.isRemote)
//        {
//            event.entityPlayer.addChatComponentMessage(new ChatComponentText(event.pos.toString()));
//        }
//    }
    
	//ÖíÉ±
	@SubscribeEvent
    public void onEntityInteract(EntityInteractEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        if (player.isServerWorld() && event.target instanceof EntityPig)
        {
            EntityPig pig = (EntityPig) event.target;
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack != null && (stack.getItem() == Items.wheat || stack.getItem() == Items.wheat_seeds))
            {
                player.attackEntityFrom((new DamageSource("byPig")).setDifficultyScaled().setExplosion(), 8.0F);
                player.worldObj.createExplosion(pig, pig.posX, pig.posY, pig.posZ, 2.0F, false);
                pig.setDead();
            }
        }
    }
	
	//»ðÑæ×ÆÉÕ¸½Ä§
	@SubscribeEvent
    public void onBlockHarvestDrops(BlockEvent.HarvestDropsEvent event)
    {
        if (!event.world.isRemote && event.harvester != null)
        {
            ItemStack itemStack = event.harvester.getHeldItem();
            if (EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.fireBurn.effectId, itemStack) > 0
                    && itemStack.getItem() != Items.shears)
            {
                for (ItemStack stack : event.drops)
                {
                    ItemStack newStack = FurnaceRecipes.instance().getSmeltingResult(stack);
                    if (newStack != null)
                    {
                        newStack.stackSize = stack.stackSize;
                        event.drops.set(event.drops.indexOf(stack), newStack);
                    }
                    else if (stack != null)
                    {
                        Block block = Block.getBlockFromItem(stack.getItem());
                        boolean b = (block == null);
                        if (!b && (block.isFlammable(event.world, event.pos, EnumFacing.DOWN)
                                || block.isFlammable(event.world, event.pos, EnumFacing.EAST)
                                || block.isFlammable(event.world, event.pos, EnumFacing.NORTH)
                                || block.isFlammable(event.world, event.pos, EnumFacing.SOUTH)
                                || block.isFlammable(event.world, event.pos, EnumFacing.UP)
                                || block.isFlammable(event.world, event.pos, EnumFacing.WEST)))
                        {
                            event.drops.remove(stack);
                        }
                    }
                }
            }
        }
    }
	
	//×ó¼üµã»÷²âÊÔ·½¿é
    @Cancelable
    public static class PlayerClickGrassBlockEvent extends PlayerInteractEvent
    {
        public PlayerClickGrassBlockEvent(EntityPlayer player, BlockPos pos, World world)
        {
            super(player, PlayerInteractEvent.Action.LEFT_CLICK_BLOCK, pos, null, world);
        }
    }
    
    public static final EventBus EVENT_BUS = new EventBus();
    
    //¿ÕÊÖ×ó¼üµã»÷²âÊÔ·½¿é
    @SubscribeEvent
    public void onPlayerClickGrassBlock(PlayerClickGrassBlockEvent event)
    {
    	if (!event.world.isRemote)
        {
            ItemStack heldItem = event.entityPlayer.getHeldItem();
            if (heldItem == null)
            {
                BlockPos pos = event.pos;
                event.world.spawnEntityInWorld(
                        new EntityTNTPrimed(event.world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, null));
                event.entityPlayer.triggerAchievement(AchievementLoader.explosionFromGrassBlock);
            }
            else if (ItemLoader.money.equals(heldItem.getItem()))
            {
                EntityLiving entityLiving = new EntityGoldenChicken(event.world);
                BlockPos pos = event.pos;
                entityLiving.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                --heldItem.stackSize;
                event.world.spawnEntityInWorld(entityLiving);
            }
        }
    }
    
    //Ò©Ë®Ð§¹û-Ë¤Âä±£»¤
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event)
    {
        if (event.source.getDamageType().equals("fall"))
        {
            PotionEffect effect = event.entityLiving.getActivePotionEffect(PotionLoader.potionFallProtection);
            if (effect != null)
            {
                if (effect.getAmplifier() == 0)
                {
                    event.ammount /= 2;
                }
                else
                {
                    event.ammount = 0;
                }
            }
        }
    }
    
    //h¼üÏÔÊ¾Ê±¼ä
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (KeyLoader.showTime.isPressed())
        {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            World world = Minecraft.getMinecraft().theWorld;
            player.addChatMessage(new ChatComponentTranslation("chat.frpg.time", world.getTotalWorldTime()));
        }

    }
    
    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event)
    {
        if (event.entityLiving instanceof EntityPlayer && event.source.getDamageType().equals("byPig"))
        {
            ((EntityPlayer) event.entityLiving).triggerAchievement(AchievementLoader.worseThanPig);
        }
    }

    @SubscribeEvent
    public void onPlayerItemCrafted(PlayerEvent.ItemCraftedEvent event)
    {
        if (event.crafting.getItem() == Item.getItemFromBlock(BlockLoader.testBlock))
        {
            event.player.triggerAchievement(AchievementLoader.buildGrassBlock);
        }
    }
}