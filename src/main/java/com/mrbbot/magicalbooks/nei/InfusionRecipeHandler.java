package com.mrbbot.magicalbooks.nei;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.mrbbot.magicalbooks.init.InfusionRecipes;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.reference.Textures;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static codechicken.lib.gui.GuiDraw.changeTexture;
import static codechicken.lib.gui.GuiDraw.drawTexturedModalRect;

public class InfusionRecipeHandler extends TemplateRecipeHandler {
    public class CachedInfusion extends CachedRecipe {
        public List<PositionedStack> inputs;

        public PositionedStack output;

        public CachedInfusion(InfusionRecipes.InfusionRecipe recipe) {
            switch(recipe.getOthers().length) {
                case 2:
                    inputs = Arrays.asList( new PositionedStack(recipe.getOthers()[0], 119-6, 58-19),
                                            new PositionedStack(recipe.getOthers()[1], 43-6, 58-19),
                                            new PositionedStack(recipe.getMain(), 81-6, 58-19));
                    break;
                case 4:
                    inputs = Arrays.asList( new PositionedStack(recipe.getOthers()[0], 81-6, 20-19),
                                            new PositionedStack(recipe.getOthers()[1], 119-6, 58-19),
                                            new PositionedStack(recipe.getOthers()[2], 81-6, 96-19),
                                            new PositionedStack(recipe.getOthers()[3], 43-6, 58-19),
                                            new PositionedStack(recipe.getMain(), 81-6, 58-19));
                    break;
                case 8:
                    inputs = Arrays.asList( new PositionedStack(recipe.getOthers()[0], 81-6, 20-19),
                                            new PositionedStack(recipe.getOthers()[1], 111-6, 28-19),
                                            new PositionedStack(recipe.getOthers()[2], 119-6, 58-19),
                                            new PositionedStack(recipe.getOthers()[3], 111-6, 88-19),
                                            new PositionedStack(recipe.getOthers()[4], 81-6, 96-19),
                                            new PositionedStack(recipe.getOthers()[5], 51-6, 88-19),
                                            new PositionedStack(recipe.getOthers()[6], 43-6, 58-19),
                                            new PositionedStack(recipe.getOthers()[7], 51-6, 28-19),
                                            new PositionedStack(recipe.getMain(), 81-6, 58-19));
                    break;
                default:
                    inputs = new ArrayList<PositionedStack>();
                    break;
            }

            output = new PositionedStack(recipe.getOutput(), 81-6, 128-19);
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return inputs;
        }

        @Override
        public PositionedStack getResult() {
            return output;
        }
    }

    @Override
    public int recipiesPerPage() {
        return 1;
    }

    @Override
    public void drawBackground(int recipe) {
        GL11.glColor4f(1, 1, 1, 1);
        changeTexture(getGuiTexture());
        drawTexturedModalRect(-6, -19, 0, 0, 176, 166);
    }

    @Override
    public String getGuiTexture() {
        return Textures.Gui.INFUSION.toString();
    }

    public String getRecipeID() {
        return Reference.MOD_ID + ":" + Names.Blocks.PEDESTAL;
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (InfusionRecipes.InfusionRecipe recipe : InfusionRecipes.getRecipes()) {
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getOutput(), result)) {
                arecipes.add(new CachedInfusion(recipe));
            }
        }
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(getRecipeID())) {
            for (InfusionRecipes.InfusionRecipe recipe : InfusionRecipes.getRecipes()) {
                arecipes.add(new CachedInfusion(recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public String getRecipeName()
    {
        return StatCollector.translateToLocal("gui.nei.magicalbooks:pedestalInfusion");
    }

}
