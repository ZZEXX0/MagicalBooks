package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

    public static void init() {

        GameRegistry.addRecipe(new ItemStack(ModBlocks.blockPedestal), "SIS", " S ", "SES", 'S', Blocks.stone, 'I', Blocks.heavy_weighted_pressure_plate, 'E', Items.ender_pearl);

        GameRegistry.addRecipe(ModItems.fireActivationStick, "  R", " B ", "G  ", 'R', Items.redstone, 'B', Items.blaze_rod, 'G', Items.glowstone_dust);
        GameRegistry.addRecipe(ModItems.fireActivationStick, "  G", " B ", "R  ", 'R', Items.redstone, 'B', Items.blaze_rod, 'G', Items.glowstone_dust);
        GameRegistry.addShapelessRecipe(ModItems.fireActivationStick, ModItems.itemActivationStick);

        InfusionRecipes.addInfusion(new ItemStack(Blocks.dragon_egg, 2), new ItemStack(Blocks.dragon_egg), new ItemStack(Items.nether_star), new ItemStack(Items.ender_eye), new ItemStack(Items.nether_star), new ItemStack(Items.blaze_rod), new ItemStack(Items.nether_star), new ItemStack(Items.ender_eye), new ItemStack(Items.nether_star), new ItemStack(Items.blaze_rod));
        InfusionRecipes.addInfusion(new ItemStack(ModItems.bookCraft), new ItemStack(Items.book), new ItemStack(Blocks.crafting_table), new ItemStack(Items.iron_ingot), new ItemStack(Blocks.crafting_table), new ItemStack(Items.iron_ingot));

        LogHelper.info("Loaded " + InfusionRecipes.getRecipes().size() + " infusion recipe(s)...");
    }
}
