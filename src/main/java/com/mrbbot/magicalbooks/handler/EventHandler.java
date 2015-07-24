package com.mrbbot.magicalbooks.handler;

import com.mrbbot.magicalbooks.achievement.ModAchievements;
import com.mrbbot.magicalbooks.init.ModItems;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventHandler {
    @SubscribeEvent
    public void itemCrafted(PlayerEvent.ItemCraftedEvent event) {
        if(event.crafting != null && event.crafting.getItem().equals(ModItems.itemActivationStick))
            event.player.addStat(ModAchievements.achStick, 1);
    }
}
