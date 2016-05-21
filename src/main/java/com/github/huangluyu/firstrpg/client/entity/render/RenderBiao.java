package com.github.huangluyu.firstrpg.client.entity.render;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBiao extends RenderSnowball
{
    private static final String __OBFID = "CL_00002430";

    public RenderBiao(RenderManager p_i46136_1_, RenderItem p_i46136_2_)
    {
        super(p_i46136_1_, Items.potionitem, p_i46136_2_);
    }

    public ItemStack func_177085_a(EntityPotion p_177085_1_)
    {
        return new ItemStack(this.field_177084_a, 1, p_177085_1_.getPotionDamage());
    }

    public ItemStack func_177082_d(Entity p_177082_1_)
    {
        return this.func_177085_a((EntityPotion)p_177082_1_);
    }
}