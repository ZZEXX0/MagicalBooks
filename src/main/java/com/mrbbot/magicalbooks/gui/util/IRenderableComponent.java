package com.mrbbot.magicalbooks.gui.util;

import com.mrbbot.magicalbooks.gui.GuiBookKnowledge;

public interface IRenderableComponent {
    public abstract void render(GuiBookKnowledge bookKnowledge, int guiX, int guiY);
}
