package com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class KitsuneModeltransition extends ModelBase {
	public ModelRenderer body;
	public ModelRenderer head;
	public ModelRenderer tail1;
	public ModelRenderer tail2;
	public ModelRenderer earL;
	public ModelRenderer earR;
	public ModelRenderer armL;
	public ModelRenderer armR;
	public ModelRenderer legL;
	public ModelRenderer legR;

	public KitsuneModeltransition() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this, 0, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 10F, 0F);
		body.rotateAngleX = 0.2F; // slight lean

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 10F, -4F);
		ModelRenderer snout = new ModelRenderer(this, 32, 32);
		snout.addBox(-2F, -2F, -8F, 4, 2, 4);
		snout.setRotationPoint(0F, 0F, 0F);
		head.addChild(snout);

		earL = new ModelRenderer(this, 40, 23);
		earL.addBox(-1F, -2F, -1F, 3, 3, 2);
		earL.setRotationPoint(-4F, 2F, 0F);
		earL.rotateAngleZ = -0.5F;

		earR = new ModelRenderer(this, 32, 44);
		earR.addBox(-1F, -2F, -1F, 3, 3, 2);
		earR.setRotationPoint(3F, 2F, 0F);
		earR.rotateAngleZ = 0.5F;

		tail1 = new ModelRenderer(this, 32, 38);
		tail1.addBox(-1F, -2F, -3F, 2, 2, 4);
		tail1.setRotationPoint(0F, 14F, 3F);
		tail1.rotateAngleX = 1.5F;

		tail2 = new ModelRenderer(this, 40, 16);
		tail2.addBox(-1F, -2F, -3F, 2, 5, 2);
		tail2.setRotationPoint(0F, 18F, 5F);
		tail2.rotateAngleX = -1.5F;

		armL = new ModelRenderer(this, 24, 16);
		armL.addBox(-3F, -2F, -2F, 4, 12, 4);
		armL.setRotationPoint(-5F, 12F, 0F);
		armL.rotateAngleX = 0.4F;

		armR = new ModelRenderer(this, 0, 32);
		armR.addBox(-1F, -2F, -2F, 4, 12, 4);
		armR.setRotationPoint(5F, 12F, 0F);
		armR.rotateAngleX = 0.4F;

		legL = new ModelRenderer(this, 32, 0);
		legL.addBox(-2F, 0F, -2F, 4, 12, 4);
		legL.setRotationPoint(-2F, 12F, 2F);
		legL.rotateAngleX = -0.3F;

		legR = new ModelRenderer(this, 16, 32);
		legR.addBox(-2F, 0F, -2F, 4, 12, 4);
		legR.setRotationPoint(2F, 12F, 2F);
		legR.rotateAngleX = -0.3F;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		head.render(f5);
		earL.render(f5);
		earR.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		armL.render(f5);
		armR.render(f5);
		legL.render(f5);
		legR.render(f5);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
		this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);

		this.armL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount * 0.5F;
		this.armR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.5F;

		this.legL.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.5F;
		this.legR.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount * 0.5F;

		this.tail1.rotateAngleY = MathHelper.sin(ageInTicks * 0.1F) * 0.2F;
		this.tail2.rotateAngleY = MathHelper.sin(ageInTicks * 0.1F + 1F) * 0.2F;
	}
}
