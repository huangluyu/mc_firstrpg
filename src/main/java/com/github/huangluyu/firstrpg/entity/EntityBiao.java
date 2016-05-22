package com.github.huangluyu.firstrpg.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBiao extends EntityThrowable 
{

    //伤害值
    int damage = 4;
    public EntityPlayer shootingEntity;
    
    /**
     * 在客户端同步时被调用的构造器。
     */
    public EntityBiao(World par1World) {
        super(par1World);
        setSize(0.2F, 0.2F); //大小0.1格
    }

    /**
     * 我们在服务端生成时调用的构造器。
     */
    public EntityBiao(World par1World, EntityLivingBase PLayer, int dmg) {
        super(par1World, PLayer);
        this.shootingEntity = (EntityPlayer)PLayer;
        this.damage = dmg;
        setSize(0.2F, 0.2F); //大小0.1格
    }

    public static DamageSource causeBiaoDamage(EntityBiao biao, EntityPlayer player)
    {
        return new EntityDamageSource("player", player);
    }
    
    /**
     * 当实体碰撞到其他世界物品时被调用。进行碰撞计算。
     */
    @Override
    protected void onImpact(MovingObjectPosition mop) {
    	if (mop.entityHit != null)
        {
	        if (this.shootingEntity == null){}
	        else
	        {
        	DamageSource damagesource = causeBiaoDamage(this, this.shootingEntity);
        	mop.entityHit.attackEntityFrom(damagesource, (float)2);
	        }
        }
    	this.setDead();
    }
    
    
    /**
     * 获取实体的初速度值。
     */
    protected float func_70182_d()
    {
        return 5.0F;
    }
    
    /**
     * 获取重力加速度的大小。
     */
    protected float getGravityVelocity()
    {
        return 0.001F; //重力影响极小
    }

	
}
