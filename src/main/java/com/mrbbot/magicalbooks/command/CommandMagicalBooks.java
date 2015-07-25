package com.mrbbot.magicalbooks.command;

import com.mrbbot.magicalbooks.Updater;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class CommandMagicalBooks implements ICommand {
    private List<String> aliases;

    public CommandMagicalBooks() {
        this.aliases = new ArrayList<String>();
        this.aliases.add("mbs");
    }

    @Override
    public String getCommandName() {
        return "mbs";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "mbs <cmd>";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(args.length == 0) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No argument! Usage: " + getCommandUsage(sender)));
            return;
        }
        if(args[0].equals("update")) {
            boolean onlyCheck = false;
            if(args.length > 1) onlyCheck = args[1].equals("check");
            sender.addChatMessage(new ChatComponentText("Checking for update..."));
            Thread updateThread = new Thread(new Updater(sender, onlyCheck));
            updateThread.setName("MagicalBooks update thread");
            updateThread.start();
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
