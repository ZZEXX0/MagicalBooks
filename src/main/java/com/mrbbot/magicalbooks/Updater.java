package com.mrbbot.magicalbooks;

import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater implements Runnable {
    private final ICommandSender sender;
    private final boolean onlyCheck;

    public Updater(ICommandSender sender, boolean onlyCheck) {
        this.sender = sender;
        this.onlyCheck = onlyCheck;
    }

    @Override
    public void run() {
        try {
            String[] latestVersion = getLatestVersion().split("-");
            String[] thisVersion = Reference.VERSION.split("-");
            if(!latestVersion[0].equals(thisVersion[0])) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You're not using the latest version of Magical Books!"));
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Latest Version: " + latestVersion[0] + "-" + latestVersion[1] + " Your Version: " + thisVersion[0] + "-" + thisVersion[1]));
                return;
            }
            if(latestVersion[1].equals(thisVersion[1])) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "You're using the latest version of Magical Books!"));
            } else {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You're not using the latest version of Magical Books!"));
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Latest Version: " + latestVersion[0] + "-" + latestVersion[1] + " Your Version: " + thisVersion[0] + "-" + thisVersion[1]));
                if(!onlyCheck) {
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Updating..."));
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Removing old version..."));
                    final File oldJar = getMagicalBooksJar();
                    if(oldJar != null) {
                        boolean deleted = oldJar.delete();
                        if(deleted) {
                            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Removed old version!"));
                        } else {
                            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Unable to remove old version!"));
                            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "This should be removed on exit."));
                            if(!new File("MagicalBooksJarRemover.jar").exists())
                                downloadJarRemover();
                            LogHelper.info("Registering shutdown hook...");
                            Runtime.getRuntime().addShutdownHook(new Thread() {
                                @Override
                                public void run() {
                                    System.gc();
                                    System.out.println("Running JAR remover...");
                                    String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                                    String mbjr = System.getProperty("user.dir") + File.separator + "MagicalBooksJarRemover.jar";
                                    String oldJarS = System.getProperty("user.dir") + File.separator + "mods" + File.separator + oldJar.getName();
                                    ProcessBuilder builder = new ProcessBuilder(java, "-jar", mbjr, oldJarS);
                                    try {
                                        builder.start();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } else {
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Unable to locate old version!"));
                        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Update unsuccessful!"));
                        return;
                    }
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Downloading latest version..."));
                    downloadVersion(latestVersion[0] + "-" + latestVersion[1]);
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Downloaded latest version!"));
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Update successful! Restart to apply changes."));
                } else {
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Run \"/mbs update\" to update!"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(ICommandSender sender, boolean onlyCheck, String side) {
        sender.addChatMessage(new ChatComponentText("Checking for update on " + side + "..."));
        Thread updateThread = new Thread(new Updater(sender, onlyCheck));
        updateThread.setName("MagicalBooks update thread");
        updateThread.start();
    }

    public File getMagicalBooksJar() {
        File modsDir = new File(System.getProperty("user.dir") + File.separator + "mods");
        for(File file : modsDir.listFiles()) {
            String filename = file.getName().toLowerCase();
            if(filename.contains("magicalbooks")) {
                return file;
            }
        }
        return null;
    }

    public void downloadVersion(String version) throws Exception {
        URL website = new URL("https://dl.dropboxusercontent.com/u/80809271/MagicalBooks/MagicalBooks-" + version + ".jar");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "mods" + File.separator + "MagicalBooks-" + version + ".jar");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    public void downloadJarRemover() throws Exception {
        URL website = new URL("https://dl.dropboxusercontent.com/u/80809271/MagicalBooks/MagicalBooksJarRemover.jar");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + File.separator + "MagicalBooksJarRemover.jar");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    public String getLatestVersion() throws Exception {
        URL versionURL = new URL("https://dl.dropboxusercontent.com/u/80809271/MagicalBooks/magicalbooks.version");
        BufferedReader in = new BufferedReader(new InputStreamReader(versionURL.openStream()));
        String version = in.readLine();
        in.close();
        return version;
    }
}
