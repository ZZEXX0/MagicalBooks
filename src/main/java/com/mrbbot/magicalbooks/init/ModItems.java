package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.item.*;
import com.mrbbot.magicalbooks.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class ModItems {
    public static final ItemMagicalBooks itemActivationStick = new ItemActivationStick();
    public static final ItemMagicalBooks bookGrowth = new ItemBookGrowth();
    public static final ItemMagicalBooks bookJump = new ItemBookJump();
    public static final ItemMagicalBooks bookCraft = new ItemBookCraft();

    public static ItemStack fireActivationStick;

    public static void init() {
        fireActivationStick = new ItemStack(ModItems.itemActivationStick);
        fireActivationStick.addEnchantment(Enchantment.fireAspect, 2);

        GameRegistry.registerItem(itemActivationStick, Names.Items.ACTIVATION_STICK);
        GameRegistry.registerItem(bookGrowth, Names.Items.BOOK_GROWTH);
        GameRegistry.registerItem(bookJump, Names.Items.BOOK_JUMP);
        GameRegistry.registerItem(bookCraft, Names.Items.BOOK_CRAFT);
    }
}
