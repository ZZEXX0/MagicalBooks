package com.mrbbot.magicalbooks.gui.util;

import com.mrbbot.magicalbooks.gui.GuiBookKnowledge;
import com.mrbbot.magicalbooks.init.IRenderableRecipe;
import com.mrbbot.magicalbooks.reference.Textures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;

public class RenderRecipe implements IRenderableComponent {
    private IRenderableRecipe recipe;
    private int posX;
    private int posY;

    public RenderRecipe(IRenderableRecipe recipe, int posX, int posY) {
        this.recipe = recipe;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void render(GuiBookKnowledge bookKnowledge, int guiX, int guiY) {
        GlStateManager.color(1, 1, 1, 1);
        bookKnowledge.mc.getTextureManager().bindTexture(Textures.GUI_BOOK_KNOWLEDGE);
        if(recipe instanceof StoredCraftingRecipe)
            bookKnowledge.drawTexturedModalRect(guiX + 77 + posX, guiY + 57 + posY, 0, 180, 22, 15);
        RenderHelper.enableGUIStandardItemLighting();
        for(PositionedItemStack stack : recipe.getPositionedItemStacks()) {
            if(stack.getItemStack() != null)
                bookKnowledge.renderItemStack(stack.getItemStack(), stack.getX() + posX + guiX, stack.getY() + posY + guiY);
        }
        RenderHelper.disableStandardItemLighting();
    }
}
