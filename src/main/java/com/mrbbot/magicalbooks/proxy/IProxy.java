package com.mrbbot.magicalbooks.proxy;

import net.minecraft.command.ICommandSender;

public interface IProxy {
    public abstract void registerRenderers();
    public abstract void runUpdateThread(ICommandSender sender, boolean onlyCheck);
}
