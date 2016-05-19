package com.github.huangluyu.firstrpg.entity;

import com.github.huangluyu.firstrpg.item.ItemLoader;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.world.World;

public class EntityGoldenChicken extends EntityChicken
{
    public EntityGoldenChicken(World worldIn)
    {
        super(worldIn);
        this.setSize(1.2F, 1.8F);
    }

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

}