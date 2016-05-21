package com.github.huangluyu.firstrpg.entity;

import com.github.huangluyu.firstrpg.item.ItemLoader;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityGoldenChicken extends EntityChicken
{
	public EntityGoldenChicken(World worldIn)
    {
        super(worldIn);
        this.setSize(1.2F, 1.8F);
        this.tasks.addTask(8, new AIStackBlock(this));
    }

	//每tick更新
    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }
    
    //掉落物?
    @Override
    protected void dropFewItems(boolean arg1, int arg2)
    {
        if (this.rand.nextInt(10) == 0)
        {
            this.dropItem(ItemLoader.money, 1);
        }
        super.dropFewItems(arg1, arg2);
    }
    
    //初始化基本属性
    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(EntityGoldenChicken.wingSpeed);
        
        this.getEntityAttribute(EntityGoldenChicken.wingSpeed).setBaseValue(1 + this.rand.nextDouble());
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
    }
    
    public static final IAttribute wingSpeed = new RangedAttribute(null, "firstRpg.GoldenChicken.wingSpeed", 1.5D, 0.0D,
            4.0D).setDescription("Wing Speed").setShouldWatch(true);
    
    //注册DataWatcher
    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte) 0));
    }
    
    //数据上传
    @Override
    public boolean interact(EntityPlayer player)
    {
        if (!super.interact(player))
        {
            byte b = this.dataWatcher.getWatchableObjectByte(16);
            this.dataWatcher.updateObject(16, new Byte((byte) ((b + 1) % 5)));
        }
        return true;
    }
    
    //nbt存储
    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setByte("WingSpeedMultiplier", this.dataWatcher.getWatchableObjectByte(16));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        super.readEntityFromNBT(tagCompund);
        this.dataWatcher.updateObject(16, tagCompund.getByte("WingSpeedMultiplier"));
    }

    //AI
    protected static class AIStackBlock extends EntityAIBase
    {
        private final EntityGoldenChicken entity;

        public AIStackBlock(EntityGoldenChicken entity)
        {
            this.entity = entity;
        }

        //AI判断
        @Override
        public boolean shouldExecute()
        {
            return this.entity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
            // Minecraft 1.8.9: this.entity.worldObj.getGameRules().getBoolean("mobGriefing");
        }

        //捡起脚下方块并放置方块
        @Override
        public void updateTask()
        {
            BlockPos entityPos = new BlockPos(entity);
            if (entity.worldObj.isAirBlock(entityPos.up()))
            {
                AxisAlignedBB aabb = AxisAlignedBB.fromBounds(entityPos.getX() - 1, entityPos.getY(),
                        entityPos.getZ() - 1, entityPos.getX() + 2, entityPos.getY() + 1, entityPos.getZ() + 2);
                for (Object e : entity.worldObj.getEntitiesWithinAABB(EntityItem.class, aabb))
                {
                    ItemStack stack = ((EntityItem) e).getEntityItem();
                    Block block = Block.getBlockFromItem(stack.getItem());
                    if (block != null)
                    {
                        entity.setLocationAndAngles(entity.posX, entity.posY + 1, entity.posZ, entity.rotationYaw,
                                entity.rotationPitch);
                        entity.worldObj.setBlockState(entityPos, block.getDefaultState());
                        if (--stack.stackSize <= 0)
                        {
                            ((EntityItem) e).setDead();
                        }
                    }
                }
            }
        }
    }
    
    
}