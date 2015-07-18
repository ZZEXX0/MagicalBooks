package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {

    public static void init() {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.blockPedestal), "SIS", " S ", "SES", 'S', Blocks.stone, 'I', Blocks.heavy_weighted_pressure_plate, 'E', Items.ender_pearl);

        GameRegistry.addRecipe(ModItems.fireActivationStick, "  R", " B ", "G  ", 'R', Items.redstone, 'B', Items.blaze_rod, 'G', Items.glowstone_dust);
        GameRegistry.addRecipe(ModItems.fireActivationStick, "  G", " B ", "R  ", 'R', Items.redstone, 'B', Items.blaze_rod, 'G', Items.glowstone_dust);
        GameRegistry.addShapelessRecipe(ModItems.fireActivationStick, ModItems.itemActivationStick);

        InfusionRecipes.addInfusion(new ItemStack(ModItems.bookJump), new ItemStack(Items.book), new ItemStack(Items.nether_star), new ItemStack(Items.feather));
        InfusionRecipes.addInfusion(new ItemStack(Blocks.crafting_table), new ItemStack(Items.iron_ingot), new ItemStack(Blocks.wooden_slab), new ItemStack(Items.stick), new ItemStack(Blocks.wooden_slab), new ItemStack(Items.stick));
        InfusionRecipes.addInfusion(new ItemStack(Blocks.crafting_table), new ItemStack(Items.iron_ingot), new ItemStack(Blocks.planks), new ItemStack(Items.stick), new ItemStack(Blocks.planks), new ItemStack(Items.stick));

        LogHelper.info("Loaded " + InfusionRecipes.getRecipes().size() + " infusion recipe(s)...");
    }
}
