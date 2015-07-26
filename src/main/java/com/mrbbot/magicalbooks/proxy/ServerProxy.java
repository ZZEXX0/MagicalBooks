package com.mrbbot.magicalbooks.proxy;

import com.mrbbot.magicalbooks.Updater;
import net.minecraft.command.ICommandSender;

public class ServerProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        //client side only
    }

    @Override
    public void runUpdateThread(ICommandSender sender, boolean onlyCheck) {
        Updater.update(sender, onlyCheck, "server");
    }
}
