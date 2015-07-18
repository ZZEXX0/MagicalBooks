package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.reference.Names;

public class ItemActivationStick extends ItemMagicalBooks {
    public ItemActivationStick() {
        super();
        setUnlocalizedName(Names.Items.ACTIVATION_STICK);
        setLore("It glows with energy...");
        setHasEffect(true);
    }
}
