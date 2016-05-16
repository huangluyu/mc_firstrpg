package com.github.huangluyu.firstrpg.block;

import com.github.huangluyu.firstrpg.common.EventLoader;
import com.github.huangluyu.firstrpg.creativetab.CreativeTabsLoader;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockBaseBlock extends Block{
	public BlockBaseBlock()
    {
        super(Material.ground);
        this.setLightLevel(2);
        this.setUnlocalizedName("testBlock");
        this.setHardness(0.5F);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
    }
	
	@Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
        EventLoader.PlayerClickGrassBlockEvent event;
        EventLoader.EVENT_BUS.post(event = new EventLoader.PlayerClickGrassBlockEvent(playerIn, pos, worldIn));
        if (!event.isCanceled() && !worldIn.isRemote)
        {
            worldIn.setBlockToAir(pos);
        }
    }
}
