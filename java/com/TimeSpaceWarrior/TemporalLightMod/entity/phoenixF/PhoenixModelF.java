package com.TimeSpaceWarrior.TemporalLightMod.entity.phoenixF;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class PhoenixModelF extends ModelBase {
	public final ModelRenderer phoenix;

	// Head parts
	public final ModelRenderer villagerHead;
	public final ModelRenderer headwear2;
	public final ModelRenderer beak;
	public final ModelRenderer headwear;

	// Body parts
	public final ModelRenderer villagerBody;
	public final ModelRenderer villagerArms;
	public final ModelRenderer bodywear;

	// Legs
	public final ModelRenderer rightLeg;
	public final ModelRenderer leftLeg;

	// Tail
	//public final ModelRenderer tail;
	//public final ModelRenderer tail_r1;

	public final ModelRenderer mirrored;

	public PhoenixModelF() {
		textureWidth = 64;
		textureHeight = 64;

		phoenix = new ModelRenderer(this);
		phoenix.setRotationPoint(0.0F, 24.0F, 0.0F);

		villagerHead = new ModelRenderer(this,0,0);
		villagerHead.setRotationPoint(0.0F, -24.0F, 0.0F);
		phoenix.addChild(villagerHead);
		villagerHead.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);

		headwear2 = new ModelRenderer(this);
		headwear2.setRotationPoint(0.0F, 0.0F, 0.0F);
		villagerHead.addChild(headwear2);
		setRotation(headwear2, -1.5708F, 0.0F, 0.0F);

		beak = new ModelRenderer(this,24,0);
		beak.setRotationPoint(0.0F, -2.0F, 0.0F);
		villagerHead.addChild(beak);
		beak.addBox(-2.0F, -3.0F, -6.0F, 4, 4, 2, 0.0F);

		headwear = new ModelRenderer(this,32,0);
		headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		villagerHead.addChild(headwear);
		headwear.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.51F);
		// ---------- BODY ----------
		villagerBody = new ModelRenderer(this,16,20);
		villagerBody.setRotationPoint(0.0F, -24.0F, 0.0F);
		phoenix.addChild(villagerBody);
		villagerBody.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);

		villagerArms = new ModelRenderer(this,44,22);
		villagerArms.setRotationPoint(0.0F, 2.95F, -1.05F);
		villagerBody.addChild(villagerArms);
		setRotation(villagerArms, -0.7505F, 0.0F, 0.0F);
		villagerArms.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);

		bodywear = new ModelRenderer(this,0,38);
		bodywear.setRotationPoint(0.0F, 0.0F, 0.0F);
		villagerBody.addChild(bodywear);
		bodywear.addBox(-4.0F, 0.0F, -3.0F, 8, 20, 6, 0.5F);

		rightLeg = new ModelRenderer(this,0,22);
		rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		villagerBody.addChild(rightLeg);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

		leftLeg = new ModelRenderer(this,0,22);
		leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
		villagerBody.addChild(leftLeg);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		leftLeg.mirror=true;

		//tail = new ModelRenderer(this);
		//tail.setRotationPoint(0.0F, 0.0F, 0.0F);
		//villagerBody.addChild(tail);

		//tail_r1 = new ModelRenderer(this,33,49);
		//tail_r1.setRotationPoint(0.0F, 10.0F, 1.0F);
		//tail.addChild(tail_r1);
		//setRotation(tail_r1, -0.7418F, 0.0F, 0.0F);
		//tail_r1.addBox(-1.5F, -0.6972F, -0.4932F, 3, 2, 12, 0.0F);

		mirrored = new ModelRenderer(this,44,22);
		mirrored.setRotationPoint(6.0F, 1.0F, -1.0F);
		villagerBody.addChild(mirrored);
		setRotation(mirrored, -0.7418F, 0.0F, 0.0F);
		mirrored.addBox(-2.0F, -0.05F, -1.05F, 4, 8, 4, 0.0F);
		mirrored.mirror=true;
	}
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		phoenix.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount,float ageInTicks, float netHeadYaw, float headPitch,float scaleFactor, Entity entity) {
		villagerHead.rotateAngleY = netHeadYaw * (float)Math.PI / 180F;
		villagerHead.rotateAngleX = headPitch * (float)Math.PI / 180F;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		leftLeg.rotateAngleX  = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		float tailSwing = MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount;
		//tail.rotateAngleY = tailSwing;
		//tail.rotateAngleX = -0.7418F + MathHelper.sin(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
		if(entity instanceof EntityPhoenixF) {
			EntityPhoenixF phoen =(EntityPhoenixF) entity;
			if (phoen.isJumping()||phoen.isFalling()) {
				float flap = MathHelper.sin(ageInTicks * 0.8F) * 0.8F;
				if (phoen.isFalling()) flap *= 1.5F;
				villagerArms.rotateAngleX = -0.7505F + flap;
				mirrored.rotateAngleX = -0.7418F - flap;
			}
		}
	}
}