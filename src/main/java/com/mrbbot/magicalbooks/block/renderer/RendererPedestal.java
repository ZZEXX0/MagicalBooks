package com.mrbbot.magicalbooks.block.renderer;

import com.mrbbot.magicalbooks.block.model.ModelPedestal;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RendererPedestal extends TileEntitySpecialRenderer {
    private final ModelBase modelPedestal;

    public RendererPedestal() {
        modelPedestal = new ModelPedestal();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

        GL11.glPushMatrix();
        GL11.glRotatef(180f, 0.0f, 0.0f, 1.0f);

        this.bindTexture(Textures.Models.PEDESTAL);
        modelPedestal.render(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
