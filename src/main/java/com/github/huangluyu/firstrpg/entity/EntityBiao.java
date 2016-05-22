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

    //�˺�ֵ
    int damage = 4;
    public EntityPlayer shootingEntity;
    
    /**
     * �ڿͻ���ͬ��ʱ�����õĹ�������
     */
    public EntityBiao(World par1World) {
        super(par1World);
        setSize(0.2F, 0.2F); //��С0.1��
    }

    /**
     * �����ڷ��������ʱ���õĹ�������
     */
    public EntityBiao(World par1World, EntityLivingBase PLayer, int dmg) {
        super(par1World, PLayer);
        this.shootingEntity = (EntityPlayer)PLayer;
        this.damage = dmg;
        setSize(0.2F, 0.2F); //��С0.1��
    }

    public static DamageSource causeBiaoDamage(EntityBiao biao, EntityPlayer player)
    {
        return new EntityDamageSource("player", player);
    }
    
    /**
     * ��ʵ����ײ������������Ʒʱ�����á�������ײ���㡣
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
     * ��ȡʵ��ĳ��ٶ�ֵ��
     */
    protected float func_70182_d()
    {
        return 5.0F;
    }
    
    /**
     * ��ȡ�������ٶȵĴ�С��
     */
    protected float getGravityVelocity()
    {
        return 0.001F; //����Ӱ�켫С
    }

	
}
