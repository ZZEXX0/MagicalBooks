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

        //health xp

        InfusionRecipes.addInfusion(9.5f, 30, new ItemStack(Blocks.dragon_egg, 2), new ItemStack(Blocks.dragon_egg), new ItemStack(Items.nether_star), new ItemStack(Items.ender_eye), new ItemStack(Items.nether_star), new ItemStack(Items.blaze_powder), new ItemStack(Items.nether_star), new ItemStack(Items.ender_eye), new ItemStack(Items.nether_star), new ItemStack(Items.blaze_powder));

        //BOOKS
        InfusionRecipes.addInfusion(0, 3, new ItemStack(ModItems.bookCraft), new ItemStack(Items.book), new ItemStack(Blocks.crafting_table), new ItemStack(Items.iron_ingot), new ItemStack(Blocks.crafting_table), new ItemStack(Items.iron_ingot));

        //NETHER STAR BOOKS
        InfusionRecipes.addInfusion(0, 10, new ItemStack(ModItems.bookStar), new ItemStack(Items.book), new ItemStack(Items.blaze_powder), new ItemStack(Items.nether_star), new ItemStack(Items.blaze_powder), new ItemStack(Items.blaze_powder), new ItemStack(Items.nether_star), new ItemStack(Items.blaze_powder));
        InfusionRecipes.addInfusion(5, 10, new ItemStack(ModItems.bookGrowth), new ItemStack(ModItems.bookStar), new ItemStack(Items.water_bucket), new ItemStack(Blocks.leaves), new ItemStack(Items.bone), new ItemStack(Blocks.waterlily), new ItemStack(Items.water_bucket), new ItemStack(Items.wheat_seeds), new ItemStack(Items.bone), new ItemStack(Blocks.vine));

        //DRAGON BOOKS
        //book jump

        LogHelper.info("Loaded " + InfusionRecipes.getRecipes().size() + " infusion recipe(s)...");
    }
}
