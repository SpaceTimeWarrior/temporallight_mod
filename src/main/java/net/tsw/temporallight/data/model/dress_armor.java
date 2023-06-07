package net.tsw.temporallight.data.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class dress_armor<T extends LivingEntity> extends BipedModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelRenderer bipedBodyup;
	private final ModelRenderer bipedBodyMid;
	private final ModelRenderer bipedBodyLow;


	public dress_armor(float modelSize) {
		super(RenderType::getArmorCutoutNoCull, modelSize, 0F, 64, 64);
		this.bipedBodyup = new ModelRenderer(this,16, 20);
		this.bipedBodyup.addBox(-1.0F, 5.4226F, -3.0937F, 8.0F, 2.0F, 4.0F);
		this.bipedBodyup.setRotationPoint(-3.0F, -1.95F, -3.0F);
		this.bipedBodyup.rotateAngleX = 0.4365F;
		this.bipedBodyMid = new ModelRenderer(this,0,32);
		this.bipedBodyMid.addBox(-6.0F, 12.0F, -4.0F, 12.0F, 3.0F, 8.0F);
		this.bipedBodyMid.setRotationPoint(0.0F, 0.0F + 0, 0.0F);
		this.bipedBodyLow = new ModelRenderer(this,0,43);
		this.bipedBodyLow.addBox(-7.0F, 15.0F, -5.0F, 14.0F, 6.0F, 10.0F);
		this.bipedBodyMid.addBox(-6.0F, 12.0F, -4.0F, 12.0F, 3.0F, 8.0F);
		this.bipedBodyLow.setRotationPoint(0.0F, 0.0F + 0, 0.0F);
		this.bipedBody.addChild(bipedBodyup);
		this.bipedBody.addChild(bipedBodyMid);
		this.bipedBodyMid.addChild(bipedBodyLow);
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(super.bipedBody, super.bipedRightArm, super.bipedLeftArm, super.bipedRightLeg, super.bipedLeftLeg, super.bipedHeadwear,this.bipedBodyup,this.bipedBodyMid,this.bipedBodyLow);
	}
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setRotationAngles(entityIn,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch);

		this.bipedBodyMid.rotationPointX=super.bipedLeftLeg.rotationPointX;
		this.bipedBodyMid.rotationPointY=super.bipedLeftLeg.rotationPointY;
		this.bipedBodyMid.rotationPointZ=super.bipedLeftLeg.rotationPointZ;
		this.bipedBodyMid.rotateAngleX=super.bipedLeftLeg.rotateAngleX;
		this.bipedBodyMid.rotateAngleY=super.bipedLeftLeg.rotateAngleY;
		this.bipedBodyMid.rotateAngleZ=super.bipedLeftLeg.rotateAngleZ;
		this.bipedBodyLow.rotationPointX=super.bipedLeftLeg.rotationPointX;
		this.bipedBodyLow.rotationPointY=super.bipedLeftLeg.rotationPointY;
		this.bipedBodyLow.rotationPointZ=super.bipedLeftLeg.rotationPointZ;
		this.bipedBodyLow.rotateAngleX=super.bipedLeftLeg.rotateAngleX;
		this.bipedBodyLow.rotateAngleY=super.bipedLeftLeg.rotateAngleY;
		this.bipedBodyLow.rotateAngleZ=super.bipedLeftLeg.rotateAngleZ;

	}

	/*@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}*/

}
/*
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_shoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}*/
/*
	public static LayerDefinition createBodyLayer() {


		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
		.texOffs(0, 32).addBox(-6.0F, 12.0F, -4.0F, 12.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 43).addBox(-7.0F, 15.0F, -5.0F, 14.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(16, 20).addBox(-1.0F, 5.4226F, -3.0937F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.0F, -3.0F, 0.4363F, 0.0F, 0.0F));


	}*/