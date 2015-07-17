package com.mrbbot.magicalbooks.item.renderer;

import com.mrbbot.magicalbooks.block.model.ModelPedestal;
import com.mrbbot.magicalbooks.reference.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererPedestal implements IItemRenderer {

    private final ModelPedestal modelPedestal;

    public ItemRendererPedestal() {
        modelPedestal = new ModelPedestal();
    }


    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data)
    {
        switch (itemRenderType)
        {
            case ENTITY:
            {
                //-0.5f 0.12f 0.5f
                renderPedestal(0F, 0.12f, 0.0F);
                return;
            }
            case EQUIPPED:
            {
                renderPedestal(0.0F, 0.0F, 1.0F);
                return;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                renderPedestal(1.0F, 1.0F, 1.0F);
                return;
            }
            case INVENTORY:
            {
                renderPedestal(0F, 0.1F, 0.0F);
                return;
            }
            default:
        }
    }

    private void renderPedestal(float x, float y, float z)
    {
        GL11.glPushMatrix();

        // Scale, Translate, Rotate
        GL11.glScalef(1F, 1F, 1F);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(180F, 0, 0, 1f);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Models.PEDESTAL);

        // Render
        modelPedestal.render(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);

        GL11.glPopMatrix();
    }
}
