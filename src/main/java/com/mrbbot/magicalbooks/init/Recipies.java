package com.mrbbot.magicalbooks.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Recipies {

    public static void init() {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.blockPedestal), "SIS", " S ", "SES", 'S', Blocks.stone, 'I', Blocks.heavy_weighted_pressure_plate, 'E', Items.ender_pearl);

        GameRegistry.addRecipe(ModItems.fireActivationStick, "  R", " B ", "G  ", 'R', Items.redstone, 'B', Items.blaze_rod, 'G', Items.glowstone_dust);
        GameRegistry.addRecipe(ModItems.fireActivationStick, "  G", " B ", "R  ", 'R', Items.redstone, 'B', Items.blaze_rod, 'G', Items.glowstone_dust);
        GameRegistry.addShapelessRecipe(ModItems.fireActivationStick, ModItems.itemActivationStick);
    }
}
