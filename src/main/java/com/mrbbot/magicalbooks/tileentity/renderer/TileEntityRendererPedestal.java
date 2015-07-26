package com.mrbbot.magicalbooks.tileentity.renderer;

import com.mrbbot.magicalbooks.item.ItemMagicalBook;
import com.mrbbot.magicalbooks.tileentity.TileEntityPedestal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererPedestal extends TileEntitySpecialRenderer {

    private EntityItem entityItem;

    public TileEntityRendererPedestal() {
        entityItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0, 0, 0);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_180535_8_, int p_180535_9_) {
        TileEntityPedestal pedestal = (TileEntityPedestal)tileEntity;
        if(pedestal.getItemStack() != null) {
            GL11.glPushMatrix();
            GL11.glTranslated(x + 0.5, y + 0.9, z + 0.5);
            GL11.glRotatef(pedestal.rotation, 0, 1, 0);

            entityItem.hoverStart = 0.0f;
            entityItem.setEntityItemStack(pedestal.getItemStack());

            float offsetY = 0;
            if(pedestal.getItemStack().getItem() != null && pedestal.getItemStack().getItem() instanceof ItemMagicalBook) {
                GlStateManager.scale(2, 2, 2);
                offsetY = -0.2f;
            }
            Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entityItem, 0, offsetY, 0, 0, 0);

            GL11.glPopMatrix();
        }
    }
}
