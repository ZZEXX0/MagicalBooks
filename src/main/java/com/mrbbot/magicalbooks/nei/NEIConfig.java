package com.mrbbot.magicalbooks.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.mrbbot.magicalbooks.reference.Reference;
import net.minecraft.client.Minecraft;

public class NEIConfig implements IConfigureNEI {
    @Override
    public void loadConfig() {
        InfusionRecipeHandler infusionRecipeHandler = new InfusionRecipeHandler();

        API.registerRecipeHandler(infusionRecipeHandler);
        API.registerUsageHandler(infusionRecipeHandler);
    }

    @Override
    public String getName() {
        return Reference.MOD_NAME;
    }

    @Override
    public String getVersion() {
        return Reference.VERSION;
    }
}
