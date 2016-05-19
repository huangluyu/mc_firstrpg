package com.github.huangluyu.firstrpg.achievement;

import com.github.huangluyu.firstrpg.FirstRpg;
import com.github.huangluyu.firstrpg.block.BlockLoader;
import com.github.huangluyu.firstrpg.item.ItemLoader;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class AchievementLoader
{
    public static Achievement worseThanPig = new Achievement("achievement.frpg.worseThanPig",
            "frpg.worseThanPig", 5, -4, ItemLoader.money, AchievementList.buildSword);
    public static Achievement buildGrassBlock = new Achievement("achievement.frpg.buildGrassBlock",
            "frpg.buildGrassBlock", 0, 0, Blocks.vine, null);
    public static Achievement explosionFromGrassBlock = new Achievement("achievement.frpg.explosionFromGrassBlock",
            "frpg.explosionFromGrassBlock", 2, -1, BlockLoader.testBlock, buildGrassBlock);

    public static AchievementPage pageFMLTutor = new AchievementPage(FirstRpg.NAME, buildGrassBlock,
            explosionFromGrassBlock);

    public AchievementLoader()
    {
        worseThanPig.setSpecial().registerStat();
        buildGrassBlock.setIndependent().registerStat();
        explosionFromGrassBlock.setSpecial().registerStat();

        AchievementPage.registerAchievementPage(pageFMLTutor);
    }
}