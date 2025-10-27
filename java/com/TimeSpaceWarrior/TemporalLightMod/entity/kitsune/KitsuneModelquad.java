package com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class KitsuneModelquad extends ModelQuadruped {
	//public ModelRenderer body;
	//public ModelRenderer head;
	public ModelRenderer tail1;
	public ModelRenderer tail2;
	public ModelRenderer earL;
	public ModelRenderer earR;
	//public ModelRenderer leg1;
	//public ModelRenderer leg2;
	//public ModelRenderer leg3;
	//public ModelRenderer leg4;

	public KitsuneModelquad() {
		super(0,0);
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this, 0, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 10F, -6F);

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 11F, -6F);

		ModelRenderer snout = new ModelRenderer(this, 32, 32);
		snout.addBox(-2F, -2F, -8F, 4, 2, 4); // snout box
		snout.setRotationPoint(0F, 0F, 0F);
		head.addChild(snout);

		earL = new ModelRenderer(this, 40, 23);
		earL.addBox(-1F, -2F, -1F, 3, 3, 2);
		earL.setRotationPoint(-4F, -8F, 2F);
		head.addChild(earL);
		earL.rotateAngleZ = -0.7854F;

		earR = new ModelRenderer(this, 32, 44);
		earR.addBox(-1F, -2F, -1F, 3, 3, 2);
		earR.setRotationPoint(3F, -8F, 2F);
		head.addChild(earR);
		earR.rotateAngleZ = 0.7854F;

		tail1 = new ModelRenderer(this, 32, 38);
		tail1.addBox(-1F, -2F, -3F, 2, 2, 4);
		tail1.setRotationPoint(0F, 14F, 3F);
		tail1.rotateAngleX = 2.3562F;

		tail2 = new ModelRenderer(this, 40, 16);
		tail2.addBox(-1F, -2F, -3F, 2, 5, 2);
		tail2.setRotationPoint(0F, 20F, 5F);
		tail2.rotateAngleX = -2.3562F;

		leg1 = new ModelRenderer(this, 0, 32);
		leg1.addBox(-2F, 0F, -2F, 4, 12, 4);
		leg1.setRotationPoint(-3F, 12F, -4F);

		leg2 = new ModelRenderer(this, 0, 32);
		leg2.addBox(-2F, 0F, -2F, 4, 12, 4);
		leg2.setRotationPoint(3F, 12F, -4F);

		leg3 = new ModelRenderer(this, 0, 32);
		leg3.addBox(-2F, 0F, -2F, 4, 12, 4);
		leg3.setRotationPoint(-3F, 12F, 4F);

		leg4 = new ModelRenderer(this, 0, 32);
		leg4.addBox(-2F, 0F, -2F, 4, 12, 4);
		leg4.setRotationPoint(3F, 12F, 4F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity,f,f1,f2,f3,f4,f5);
		//body.render(f5);
		//head.render(f5);
		//earL.render(f5);
		//earR.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		//leg1.render(f5);
		//leg2.render(f5);
		//leg3.render(f5);
		//leg4.render(f5);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scaleFactor,entity);

		this.tail1.rotateAngleY = MathHelper.sin(ageInTicks * 0.1F) * 0.2F;
		this.tail2.rotateAngleY = MathHelper.sin(ageInTicks * 0.1F + 1F) * 0.2F;
	}

}
