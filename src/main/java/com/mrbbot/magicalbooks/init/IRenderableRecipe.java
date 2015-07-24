package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.gui.util.PositionedItemStack;

import java.util.List;

public interface IRenderableRecipe {
    public abstract List<PositionedItemStack> getPositionedItemStacks();
}
