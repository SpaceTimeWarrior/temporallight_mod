package net.tsw.temporal_light.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.entity.kitsune.KitsuneEntity;

public class KitsuneModel<T extends KitsuneEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "kitsunemodel"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart tail;
	private final ModelPart tail_2;

	public KitsuneModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.leftArm = body.getChild("leftArm");
		this.rightArm = body.getChild("rightArm");
		this.leftLeg = body.getChild("leftLeg");
		this.rightLeg = body.getChild("rightLeg");
		this.tail = body.getChild("tail");
		this.tail_2 = tail.getChild("tail_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 32).addBox(-2.0F, -2.0F, -8.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ear_r_r1 = head.addOrReplaceChild("ear_r_r1", CubeListBuilder.create().texOffs(32, 44).addBox(-1.0F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -8.0F, 2.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition ear_l_r1 = head.addOrReplaceChild("ear_l_r1", CubeListBuilder.create().texOffs(40, 23).addBox(-1.0F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, 2.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_1 = tail.addOrReplaceChild("tail_1", CubeListBuilder.create().texOffs(32, 38).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, (float)Math.PI, 0.0F, 0.0F));

		PartDefinition tail_2 = tail.addOrReplaceChild("tail_2", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(24, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(KitsuneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		boolean isNightForm = entity.isNightForm();

		// Adjust stance based on form
		if (isNightForm) {
			// Quadrupedal: lower body, align limbs, head, and tail
			body.xRot = (float) Math.toRadians(90); // Rotate body horizontal
			body.y = 12.0F; // Lower body to ground
			head.y = 12.0F; // Align head with body height
			head.z = -2.0F; // Move head to front of body
			leftArm.y = 2.0F; // Front left leg
			leftArm.z = 0.0F;
			rightArm.y = 2.0F; // Front right leg
			rightArm.z = 0.0F;
			leftLeg.y = 14.0F; // Back left leg
			leftLeg.z = 2.0F;
			rightLeg.y = 14.0F; // Back right leg
			rightLeg.z = 2.0F;
			tail.y = 16.0F; // Align tail with body
			tail.z = 0.0F; // Attach to rear of body
			tail_2.xRot = 0.5236F; // Lock tip downward
			tail_2.zRot = -1F;
			tail_2.xScale = 0;
			tail_2.yScale = 0;
			tail_2.zScale = 0;
			tail_2.z = 4;
		} else {
			// Biped: upright stance
			body.xRot = 0.0F;
			body.y = 0.0F;
			head.y = 0.0F;
			head.z = 0.0F;
			leftArm.y = 2.0F;
			leftArm.z = 0.0F;
			rightArm.y = 2.0F;
			rightArm.z = 0.0F;
			leftLeg.y = 12.0F;
			leftLeg.z = 0.0F;
			rightLeg.y = 12.0F;
			rightLeg.z = 0.0F;
			tail.y = 12.0F;
			tail.z = 0.0F;
			tail_2.y = -1;
			tail_2.z = 7;
			tail_2.xScale=1;
			tail_2.yScale=1;
			tail_2.zScale=1;
			tail_2.xRot = -0.5236F; // Lock tip downward
		}

		this.applyHeadRotation(netHeadYaw, headPitch);

		// Handle animations
		if (entity.isTransforming()) {
			if (isNightForm) {
				this.animate(entity.TransformToNightAnimationState, KitsuneAnimations.KITSUNE_TRANSFORM_TO_NIGHT_FORM, ageInTicks, 1f);
				//System.out.println("Applying transform to night animation");
			} else {
				this.animate(entity.TransformToDayAnimationState, KitsuneAnimations.KITSUNE_TRANSFORM_TO_DAY_FORM, ageInTicks, 1f);
				//System.out.println("Applying transform to day animation");
			}
		} else if (entity.isAttacking()) {
			LivingEntity target = entity.getTarget();
			float distance = target != null ? entity.distanceTo(target) : 0.0f;
			if (distance > 20.0f) {
				if (!entity.Attack02AnimationState.isStarted()) {
					entity.Attack01AnimationState.stop();
					entity.Attack02AnimationState.start((int) ageInTicks);
				}
				this.animate(entity.Attack02AnimationState,
						isNightForm ? KitsuneAnimations.KITSUNE_NIGHT_FORM_ATTACKING_02 : KitsuneAnimations.KITSUNE_ATTACKING_02,
						ageInTicks, 1f);
				//System.out.println("Applying Attack02 animation (" + (isNightForm ? "night" : "day") + ")");
			} else {
				if (!entity.Attack01AnimationState.isStarted()) {
					entity.Attack02AnimationState.stop();
					entity.Attack01AnimationState.start((int) ageInTicks);
				}
				this.animate(entity.Attack01AnimationState,
						isNightForm ? KitsuneAnimations.KITSUNE_NIGHT_FORM_ATTACKING_01 : KitsuneAnimations.KITSUNE_ATTACKING_01,
						ageInTicks, 1f);
				//System.out.println("Applying Attack01 animation (" + (isNightForm ? "night" : "day") + ")");
			}
		} else {
			// Custom walk animation for night form to fix leg movement
			if (isNightForm) {
				float swing = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount * 0.5F; // Reduced swing amplitude
				float oppositeSwing = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.5F;
				leftArm.xRot = swing; // Front left leg forward
				rightArm.xRot = oppositeSwing; // Front right leg opposite
				leftLeg.xRot = oppositeSwing; // Back left leg opposite front
				rightLeg.xRot = swing; // Back right leg matches front left
				this.animate(entity.idleAnimationState, KitsuneAnimations.KITSUNE_NIGHT_FORM_IDLE, ageInTicks, 1f);
				this.animateWalk(KitsuneAnimations.KITSUNE_NIGHT_FORM_WALK, limbSwing, limbSwingAmount, 1f, 1f);
				//System.out.println("Applying custom night walk/idle animations");
			} else {
				this.animateWalk(KitsuneAnimations.KITSUNE_WALKING, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.idleAnimationState, KitsuneAnimations.KITSUNE_IDLE, ageInTicks, 1f);
				//System.out.println("Applying walk/idle animations (day)");
			}
		}
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return body;
	}
}