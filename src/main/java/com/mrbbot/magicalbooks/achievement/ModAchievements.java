package com.mrbbot.magicalbooks.achievement;

import com.mrbbot.magicalbooks.init.ModItems;
import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements {
    public static Achievement achStick = new Achievement(Names.Achievements.STICK, Names.Achievements.STICK, -1, 0, ModItems.itemActivationStick, null);
    public static Achievement achKnowledge = new Achievement(Names.Achievements.KNOWLEDGE, Names.Achievements.KNOWLEDGE, 1, 0, ModItems.bookKnowledge, achStick);

    public static AchievementPage achPageMagicalBooks;

    public static void init() {
        achStick.registerStat();
        achKnowledge.setSpecial().registerStat();

        achPageMagicalBooks = new AchievementPage("Magical Books", achStick, achKnowledge);
        AchievementPage.registerAchievementPage(achPageMagicalBooks);
    }
}
