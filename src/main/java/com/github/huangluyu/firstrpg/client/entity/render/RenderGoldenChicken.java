package com.github.huangluyu.firstrpg.client.entity.render;

import com.github.huangluyu.firstrpg.FirstRpg;
import com.github.huangluyu.firstrpg.client.entity.model.ModelGoldenChicken;
import com.github.huangluyu.firstrpg.entity.EntityGoldenChicken;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGoldenChicken extends RenderLiving
{
    private static final ResourceLocation GOLDEN_CHICKEN_TEXTURE = new ResourceLocation(
            FirstRpg.MODID + ":" + "textures/entity/golden_chicken.png");

    public RenderGoldenChicken(RenderManager renderManager)
    {
        super(renderManager, new ModelGoldenChicken(), 0.5F);
    }

    protected void preRenderCallbackGoldenChicken(EntityGoldenChicken entity, float partialTickTime)
    {
        GlStateManager.scale(2.5F, 2.5F, 2.5F);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime)
    {
        this.preRenderCallbackGoldenChicken((EntityGoldenChicken) entity, partialTickTime);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return RenderGoldenChicken.GOLDEN_CHICKEN_TEXTURE;
    }

    @Override
    public void doRender(EntityLiving entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}