package com.mrbbot.magicalbooks.nei;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.mrbbot.magicalbooks.init.InfusionRecipes;
import com.mrbbot.magicalbooks.init.ModBlocks;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.reference.Textures;
import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static codechicken.lib.gui.GuiDraw.changeTexture;
import static codechicken.lib.gui.GuiDraw.drawTexturedModalRect;
import static codechicken.lib.gui.GuiDraw.fontRenderer;

public class InfusionRecipeHandler extends TemplateRecipeHandler {
    public class CachedInfusion extends CachedRecipe {
        public List<PositionedStack> inputs;

        public PositionedStack output;

        private InfusionRecipes.InfusionRecipe recipe;

        public CachedInfusion(InfusionRecipes.InfusionRecipe recipe) {
            switch(recipe.getOthers().length) {
                case 2:
                    inputs = Arrays.asList(
                            new PositionedStack(recipe.getOthers()[0], 119-6, 58-19),
                            new PositionedStack(recipe.getOthers()[1], 43-6, 58-19),
                            new PositionedStack(recipe.getMain(), 81-6, 58-19));
                    break;
                case 4:
                    inputs = Arrays.asList(
                            new PositionedStack(recipe.getOthers()[0], 81-6, 20-19),
                            new PositionedStack(recipe.getOthers()[1], 119-6, 58-19),
                            new PositionedStack(recipe.getOthers()[2], 81-6, 96-19),
                            new PositionedStack(recipe.getOthers()[3], 43-6, 58-19),
                            new PositionedStack(recipe.getMain(), 81-6, 58-19));
                    break;
                case 6:
                    inputs = Arrays.asList(
                            new PositionedStack(recipe.getOthers()[0], 111-6, 28-19),
                            new PositionedStack(recipe.getOthers()[1], 119-6, 58-19),
                            new PositionedStack(recipe.getOthers()[2], 111-6, 88-19),
                            new PositionedStack(recipe.getOthers()[3], 51-6, 88-19),
                            new PositionedStack(recipe.getOthers()[4], 43-6, 58-19),
                            new PositionedStack(recipe.getOthers()[5], 51-6, 28-19),
                            new PositionedStack(recipe.getMain(), 81-6, 58-19));
                    break;
                case 8:
                    inputs = Arrays.asList(
                            new PositionedStack(recipe.getOthers()[0], 81-6, 20-19),
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

            this.recipe = recipe;
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return inputs;
        }

        @Override
        public PositionedStack getResult() {
            return output;
        }

        public InfusionRecipes.InfusionRecipe getRecipe() {
            return recipe;
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

    public final int XP_COLOUR = 0x80FF20;
    public final int HEALTH_COLOUR = 0xFF1313;

    @Override
    public void drawExtras(int recipe) {
        InfusionRecipes.InfusionRecipe infusion = ((CachedInfusion) arecipes.get(recipe)).getRecipe();

        float fHealth = infusion.getHealth();
        if(fHealth > 0) {

            String sHealth;
            int iHealth = (int)fHealth;
            if(iHealth != fHealth)
                sHealth = String.valueOf(fHealth);
            else
                sHealth = String.valueOf(iHealth);

            fontRenderer.drawStringWithShadow(sHealth, 40 - 6, 131 - 19, HEALTH_COLOUR);


        }
        if(infusion.getXp() > 0)
            fontRenderer.drawStringWithShadow(String.valueOf(infusion.getXp()) + "L", 112 - 6, 131 - 19, XP_COLOUR);
    }

    @Override
    public String getGuiTexture() {
        return Textures.Gui.INFUSION.toString();
    }

    public String getRecipeID() {
        return Reference.MOD_ID + ":" + Names.Blocks.PEDESTAL;
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
    public void loadCraftingRecipes(ItemStack result) {
        for (InfusionRecipes.InfusionRecipe recipe : InfusionRecipes.getRecipes()) {
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getOutput(), result)) {
                arecipes.add(new CachedInfusion(recipe));
            }
        }
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (inputId.equals(getRecipeID())) {
            for (InfusionRecipes.InfusionRecipe recipe : InfusionRecipes.getRecipes()) {
                arecipes.add(new CachedInfusion(recipe));
            }
        } else {
            super.loadUsageRecipes(inputId, ingredients);
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for (InfusionRecipes.InfusionRecipe recipe : InfusionRecipes.getRecipes()) {
            boolean hasItem = false;
            for(ItemStack stack : recipe.getOthers()) {
                if(NEIServerUtils.areStacksSameTypeCrafting(stack, ingredient))
                    hasItem = true;
            }
            if(NEIServerUtils.areStacksSameTypeCrafting(recipe.getMain(), ingredient))
                hasItem = true;
            if(NEIServerUtils.areStacksSameTypeCrafting(new ItemStack(ModBlocks.blockPedestal), ingredient))
                hasItem = true;
            if (hasItem) {
                arecipes.add(new CachedInfusion(recipe));
            }
        }
    }

    @Override
    public String getRecipeName()
    {
        return StatCollector.translateToLocal("gui.nei.magicalbooks:pedestalInfusion");
    }

}
