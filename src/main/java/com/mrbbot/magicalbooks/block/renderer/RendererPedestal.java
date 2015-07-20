package com.mrbbot.magicalbooks.block.renderer;

import com.mrbbot.magicalbooks.block.model.ModelPedestal;
import com.mrbbot.magicalbooks.block.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RendererPedestal extends TileEntitySpecialRenderer {
    private final ModelBase modelPedestal;
    private final RenderItem customRenderItem;

    public RendererPedestal() {
        modelPedestal = new ModelPedestal();
        customRenderItem = new RenderItem() {
            @Override
            public boolean shouldBob() {
                return false;
            }
        };

        customRenderItem.setRenderManager(RenderManager.instance);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float p8) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

        GL11.glPushMatrix();
        GL11.glRotatef(180f, 0.0f, 0.0f, 1.0f);

        this.bindTexture(Textures.Models.PEDESTAL);
        modelPedestal.render(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);

        GL11.glPopMatrix();
        GL11.glPopMatrix();

        GL11.glPushMatrix();

        TileEntityPedestal pedestal = (TileEntityPedestal)te;

        ItemStack itemStack = pedestal.getItemStack();

        if (itemStack != null) {
            EntityItem ghostEntityItem = new EntityItem(pedestal.getWorldObj());
            ghostEntityItem.hoverStart = 0.0F;
            ghostEntityItem.setEntityItemStack(itemStack);

            GL11.glTranslated(x + 0.5, y + 1.2, z + 0.5);

            pedestal.rotValue += 0.5f;
            GL11.glRotatef(pedestal.rotValue, 0, 1, 0);

            customRenderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
        }

        GL11.glPopMatrix();
    }
}
