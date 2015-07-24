package com.mrbbot.magicalbooks.gui.util;

import com.mrbbot.magicalbooks.init.IRenderableRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.Arrays;

import java.util.List;

public class StoredCraftingRecipe implements IRenderableRecipe {

    private ItemStack output;
    private ItemStack[] ingredients;
    private List<PositionedItemStack> positionedItemStacks;

    public StoredCraftingRecipe(ItemStack output, Object... objects) {
        this.output = output;

        this.ingredients = new ItemStack[objects.length];
        for(int i = 0; i < objects.length; i++) {
            Object ingredient = objects[i];
            if(ingredient != null) {
                if(ingredient instanceof Block) {
                    this.ingredients[i] = new ItemStack((Block)ingredient);
                }
                if(ingredient instanceof Item) {
                    this.ingredients[i] = new ItemStack((Item)ingredient);
                }
            } else {
                this.ingredients[i] = null;
            }
        }

        positionedItemStacks = Arrays.asList(
                new PositionedItemStack(ingredients[0], 17, 39),
                new PositionedItemStack(ingredients[1], 35, 39),
                new PositionedItemStack(ingredients[2], 53, 39),
                new PositionedItemStack(ingredients[3], 17, 57),
                new PositionedItemStack(ingredients[4], 35, 57),
                new PositionedItemStack(ingredients[5], 53, 57),
                new PositionedItemStack(ingredients[6], 17, 75),
                new PositionedItemStack(ingredients[7], 35, 75),
                new PositionedItemStack(ingredients[8], 53, 75),
                new PositionedItemStack(output, 111, 57));
    }

    public ItemStack getOutput() {
        return output;
    }

    public ItemStack[] getIngredients() {
        return ingredients;
    }

    @Override
    public List<PositionedItemStack> getPositionedItemStacks() {
        return positionedItemStacks;
    }
}
