package com.mrbbot.magicalbooks.gui.util;

import com.mrbbot.magicalbooks.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class ImageButton extends TextButton {
    private int texX;
    private int texY;
    private int texXHover;
    private int texYHover;

    public ImageButton(int guiId, int x, int y, int width, int height, int texX, int texY, int texXHover, int texYHover, int subPage, boolean setSubPage) {
        super(guiId, x, y, width, height, null, subPage, setSubPage);
        this.texX = texX;
        this.texY = texY;
        this.texXHover = texXHover;
        this.texYHover = texYHover;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            this.mouseDragged(mc, mouseX, mouseY);

            mc.getTextureManager().bindTexture(Textures.GUI_BOOK_KNOWLEDGE);
            GlStateManager.color(1, 1, 1, 1);
            if(this.hovered)
                this.drawTexturedModalRect(this.xPosition, this.yPosition, texXHover, texYHover, width, height);
            else
                this.drawTexturedModalRect(this.xPosition, this.yPosition, texX, texY, width, height);
        }
    }
}
