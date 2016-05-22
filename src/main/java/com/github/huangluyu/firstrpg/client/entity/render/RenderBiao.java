package com.github.huangluyu.firstrpg.client.entity.render;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBiao extends RenderSnowball
{
    public RenderBiao(RenderManager p_i46137_1_, Item p_i46137_2_,
			RenderItem p_i46137_3_) {
		super(p_i46137_1_, p_i46137_2_, p_i46137_3_);
		// TODO Auto-generated constructor stub
	}

	private static final String __OBFID = "CL_00002430";

    public ItemStack func_177085_a(EntityPotion p_177085_1_)
    {
        return new ItemStack(this.field_177084_a, 1, p_177085_1_.getPotionDamage());
    }

    public ItemStack func_177082_d(Entity p_177082_1_)
    {
        return this.func_177085_a((EntityPotion)p_177082_1_);
    }
}