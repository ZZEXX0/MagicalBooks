package com.mrbbot.magicalbooks.jarremover;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JarRemover {
    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            JOptionPane.showMessageDialog(null, "No args!", "Magical Books", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File oldJar = new File(args[0]);
        boolean deleted = oldJar.delete();
        if (deleted) {
            JOptionPane.showMessageDialog(null, "Successfully removed old version!", "Magical Books", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Unable to remove old version!\nThis should be done manually to avoid conflicts.", "Magical Books", JOptionPane.INFORMATION_MESSAGE);
            File deleteReminder = new File(System.getProperty("user.dir") + File.separator + "mods" + File.separator + "000-DELETE \'" + oldJar.getName() + "\'");
            deleteReminder.createNewFile();
            if(Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(System.getProperty("user.dir") + File.separator + "mods"));
            }
        }
    }
}
