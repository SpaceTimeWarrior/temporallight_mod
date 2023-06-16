package net.tsw.temporallight.Entity.Mob.models;// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.util.math.MathHelper;
import net.tsw.temporallight.Entity.Mob.PheonixEntity;

public class pheonix_01<Entity extends PheonixEntity> extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer headwear2;
	private final ModelRenderer nose;
	private final ModelRenderer headwear;
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer tail_r1;
	private final ModelRenderer bodywear;
	private final ModelRenderer arms;
	private final ModelRenderer mirrored;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public pheonix_01() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

		headwear2 = new ModelRenderer(this);
		headwear2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(headwear2);
		setRotationAngle(headwear2, -1.5708F, 0.0F, 0.0F);
		

		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, -2.0F, 0.0F);
		head.addChild(nose);
		nose.setTextureOffset(24, 0).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);

		headwear = new ModelRenderer(this);
		headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(headwear);
		headwear.setTextureOffset(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.51F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(tail);
		

		tail_r1 = new ModelRenderer(this);
		tail_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail.addChild(tail_r1);
		setRotationAngle(tail_r1, -0.7418F, 0.0F, 0.0F);
		tail_r1.setTextureOffset(33, 49).addBox(-1.5F, 6.0F, 7.0F, 3.0F, 2.0F, 12.0F, 0.0F, false);

		bodywear = new ModelRenderer(this);
		bodywear.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(bodywear);
		bodywear.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, 0.5F, false);

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, 2.95F, -1.05F);
		setRotationAngle(arms, -0.7505F, 0.0F, 0.0F);
		arms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		mirrored = new ModelRenderer(this);
		mirrored.setRotationPoint(0.0F, 21.05F, 1.05F);
		arms.addChild(mirrored);
		mirrored.setTextureOffset(44, 22).addBox(4.0F, -23.05F, -3.05F, 4.0F, 8.0F, 4.0F, 0.0F, true);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		right_leg.setTextureOffset(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
		left_leg.setTextureOffset(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);

		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		arms.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}