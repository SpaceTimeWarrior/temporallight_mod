package com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class KitsuneModel extends ModelBiped {
	public ModelRenderer tail1;
	public ModelRenderer tail2;
	public ModelRenderer earL;
	public ModelRenderer earR;

	public KitsuneModel() {
		super();
		textureWidth = 64;
		textureHeight = 64;

		bipedBody = new ModelRenderer(this, 0, 16);
		bipedBody.addBox(-4F, 0F, -2F, 8, 12, 4);
		bipedBody.setRotationPoint(0F, 0F, 0F);

		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8);
		bipedHead.setRotationPoint(0F, 0F, 0F);

		ModelRenderer snout = new ModelRenderer(this, 32, 32);
		snout.addBox(-2F, -2F, -8F, 4, 2, 4); // snout box
		snout.setRotationPoint(0F, 0F, 0F);
		bipedHead.addChild(snout);
		this.bipedHeadwear = new ModelRenderer(this, 0, 48);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 1.0f + 0.5F);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + 0.0F, 0.0F);

		tail1 = new ModelRenderer(this, 32, 38);
		tail1.addBox(-1F, -2F, -3F, 2, 2, 4);
		tail1.setRotationPoint(0F, 14F, 3F);
		tail1.rotateAngleX = 2.3562F;

		tail2 = new ModelRenderer(this, 40, 16);
		tail2.addBox(-1F, -2F, -3F, 2, 5, 2);
		tail2.setRotationPoint(0F, 20F, 5F);
		tail2.rotateAngleX = -2.3562F;

		earL = new ModelRenderer(this, 40, 23);
		earL.addBox(-1F, -2F, -1F, 3, 3, 2);
		earL.setRotationPoint(-4F, -8F, 2F);
		bipedHead.addChild(earL);
		earL.rotateAngleZ = -0.7854F;

		earR = new ModelRenderer(this, 32, 44);
		earR.addBox(-1F, -2F, -1F, 3, 3, 2);
		earR.setRotationPoint(3F, -8F, 2F);
		bipedHead.addChild(earR);
		earR.rotateAngleZ = 0.7854F;

		bipedLeftArm = new ModelRenderer(this, 24, 16);
		bipedLeftArm.addBox(-1F, -2F, -2F, 4, 12, 4);
		bipedLeftArm.setRotationPoint(0F, 2F, 0F);

		bipedRightArm = new ModelRenderer(this, 0, 32);
		bipedRightArm.addBox(-3F, -2F, -2F, 4, 12, 4);
		bipedRightArm.setRotationPoint(-0F, 2F, 0F);

		bipedLeftLeg = new ModelRenderer(this, 32, 0);
		bipedLeftLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
		bipedLeftLeg.setRotationPoint(1.0F, 12F, 0F);

		bipedRightLeg = new ModelRenderer(this, 16, 32);
		bipedRightLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
		bipedRightLeg.setRotationPoint(-1.0F, 12F, 0F);
	}
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity,f,f1,f2,f3,f4,f5);
		//bipedBody.render(f5);
		//bipedHead.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		//earL.render(f5);
		//earR.render(f5);
		//bipedLeftArm.render(f5);
		//bipedRightArm.render(f5);
		//bipedLeftLeg.render(f5);
		//bipedRightLeg.render(f5);
	}
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scaleFactor,entity);
		this.tail1.rotateAngleY = MathHelper.sin(ageInTicks * 0.1F) * 0.2F;
		this.tail2.rotateAngleY = MathHelper.sin(ageInTicks * 0.1F + 1F) * 0.2F;
	}

}
