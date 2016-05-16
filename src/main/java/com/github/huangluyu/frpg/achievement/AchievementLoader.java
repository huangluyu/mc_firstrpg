package com.github.huangluyu.frpg.achievement;

import com.github.huangluyu.firstrpg.item.ItemLoader;

import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;

public class AchievementLoader
{
    public static Achievement worseThanPig = new Achievement("achievement.frpg.worseThanPig",
            "frpg.worseThanPig", 5, -4, ItemLoader.money, AchievementList.buildSword);

    public AchievementLoader()
    {
        worseThanPig.setSpecial().registerStat();
    }
}