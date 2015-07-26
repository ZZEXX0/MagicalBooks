package com.mrbbot.magicalbooks.gui.util;

import com.mrbbot.magicalbooks.gui.GuiBookKnowledge;

public class RenderText implements IRenderableComponent {

    private String text;
    private int posX;
    private int posY;
    private int colour;

    public RenderText(String text, int posX, int posY, int colour) {
        this.text = text;
        this.posX = posX;
        this.posY = posY;
        this.colour = colour;
    }

    public RenderText(String text, int posX, int posY) {
        this(text, posX, posY, 4210752);
    }

    @Override
    public void render(GuiBookKnowledge bookKnowledge, int guiX, int guiY) {
        String[] lines = text.split("\n");
        for(int i = 0; i < lines.length; i++) {
            bookKnowledge.getFontRenderer().drawString(lines[i], guiX + posX, guiY + posY + (i * 12), colour);
        }
    }
}
