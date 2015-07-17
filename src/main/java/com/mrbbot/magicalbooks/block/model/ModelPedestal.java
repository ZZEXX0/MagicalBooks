package com.mrbbot.magicalbooks.block.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Pedastal - mrbbot
 * Created using Tabula 4.0.1
 */
public class ModelPedestal extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer pole;
    public ModelRenderer rightTop;
    public ModelRenderer bottomTop;
    public ModelRenderer backTop;
    public ModelRenderer leftTop;
    public ModelRenderer frontTop;
    public ModelRenderer topTop;

    public ModelPedestal() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.frontTop = new ModelRenderer(this, 0, 17);
        this.frontTop.setRotationPoint(-6.0F, -8.0F, -7.0F);
        this.frontTop.addBox(0.0F, 0.0F, 0.0F, 13, 4, 1);
        this.backTop = new ModelRenderer(this, 0, 17);
        this.backTop.setRotationPoint(-7.0F, -8.0F, 6.0F);
        this.backTop.addBox(0.0F, 0.0F, 0.0F, 13, 4, 1);
        this.pole = new ModelRenderer(this, 0, 30);
        this.pole.setRotationPoint(-3.0F, -4.0F, -3.0F);
        this.pole.addBox(0.0F, 0.0F, 0.0F, 6, 8, 6);
        this.topTop = new ModelRenderer(this, 16, 4);
        this.topTop.setRotationPoint(-6.0F, -7.0F, -6.0F);
        this.topTop.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12);
        this.base = new ModelRenderer(this, 0, 44);
        this.base.setRotationPoint(-8.0F, 4.0F, -8.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 16, 4, 16);
        this.leftTop = new ModelRenderer(this, 0, 0);
        this.leftTop.setRotationPoint(-7.0F, -8.0F, -7.0F);
        this.leftTop.addBox(0.0F, 0.0F, 0.0F, 1, 4, 13);
        this.rightTop = new ModelRenderer(this, 0, 0);
        this.rightTop.setRotationPoint(6.0F, -8.0F, -6.0F);
        this.rightTop.addBox(0.0F, 0.0F, 0.0F, 1, 4, 13);
        this.bottomTop = new ModelRenderer(this, 16, 17);
        this.bottomTop.setRotationPoint(-6.0F, -5.0F, -6.0F);
        this.bottomTop.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.frontTop.render(f5);
        this.backTop.render(f5);
        this.pole.render(f5);
        this.topTop.render(f5);
        this.base.render(f5);
        this.leftTop.render(f5);
        this.rightTop.render(f5);
        this.bottomTop.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
