package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.reference.Names;

public class ItemActivationRod extends ItemActivationStick {
    public ItemActivationRod() {
        super();
        setUnlocalizedName(Names.Items.ACTIVATION_ROD);
        setLore("It glows with energy...");
        setHasEffect(true);
    }
}
