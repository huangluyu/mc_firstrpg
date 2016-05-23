package com.github.huangluyu.firstrpg.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
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
    double damage = 4;
    public EntityPlayer shootingEntity;
    static boolean key,key2;
    static float size;
    /**
     * 在客户端同步时被调用的构造器。
     */
    public EntityBiao(World par1World) {
        super(par1World);
        setSize(size, size); //大小0.1格
    }

    /**
     * 我们在服务端生成时调用的构造器。
     */
    public EntityBiao(World par1World, EntityLivingBase player, double dmg) {
        super(par1World, player);
        this.shootingEntity = (EntityPlayer)player;
        this.damage = dmg;
        this.setSize(0.2F, 0.2F); //大小0.2格
    }

    //第二种构造方法
    public EntityBiao(World par1World, EntityLivingBase player, double dmg, double speed, float size, boolean key, int b) {
        super(par1World, player);
        this.shootingEntity = (EntityPlayer)player;
        this.damage = dmg;
        this.key = key;
        this.key2 = false;
        this.size = size;
        this.setSize(size, size); //大小0.2格
        
        int x = 0;
        if (b == 1)
        {
        	x = 0;
        }
        else if(b == 2)
        {
        	x = 5;
        } else if(b == 3)
        {
        	x = -5;
        }
        
        float f = 0.4F;
        this.motionX = (double)(MathHelper.sin((this.rotationYaw + x) / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionZ = (double)(MathHelper.cos((this.rotationYaw + x) / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionY = (double)(MathHelper.sin((this.rotationPitch + this.getInaccuracy()) / 180.0F * (float)Math.PI) * f);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, (float)speed * 2F, 0.001F);
    }
    
  //第三种构造方法
    public EntityBiao(World par1World, EntityLivingBase player, double dmg, double speed, float size, boolean key) {
        super(par1World, player);
        this.shootingEntity = (EntityPlayer)player;
        this.damage = dmg;
        this.key2 = key;
        this.size = 0;
        //this.setSize(size, size); //大小0.2格
        
        float f = 0.4F;
        this.motionX = (double)(MathHelper.sin((this.rotationYaw) / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionZ = (double)(MathHelper.cos((this.rotationYaw) / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionY = (double)(MathHelper.sin((this.rotationPitch + this.getInaccuracy()) / 180.0F * (float)Math.PI) * f);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, (float)speed * 2F, 0.001F);
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
    	if(key)
    	{
    		if (mop.entityHit != null )
	    	{
		        if (this.shootingEntity != null)
		        {
	        	DamageSource damagesource = causeBiaoDamage(this, this.shootingEntity);
	        	mop.entityHit.attackEntityFrom(damagesource, (float)this.damage);
		        }
	        }
    	}
    	else if(key2)
    	{
    		if (mop.entityHit != null )
	    	{
    			mop.entityHit.worldObj.addWeatherEffect(new EntityLightningBolt(mop.entityHit.worldObj, mop.entityHit.posX, mop.entityHit.posY, mop.entityHit.posZ));
	        }
    	}
    	else
    	{
	    	if (mop.entityHit != null )
	    	{
	    		if (this.shootingEntity != null)
		        {
	        	DamageSource damagesource = causeBiaoDamage(this, this.shootingEntity);
	        	mop.entityHit.attackEntityFrom(damagesource, (float)this.damage);
		        }
	        }
	    	this.setDead();
    	}
    }
    
    /**
     * 粒子效果。
     */
    public void setIsCritical(boolean key)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (key)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
        }
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
