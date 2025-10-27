package com.TimeSpaceWarrior.TemporalLightMod.render;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneModel;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneModelquad;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneModeltransition;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

import java.util.Map;
import java.util.UUID;

public class RenderKitsune extends RenderLiving {
    public RenderKitsune(ModelBase base, float shadowrad) {
        super(base, shadowrad);
        this.field_82423_g = new ModelBiped();
        this.field_82425_h = new ModelBiped(0.5F);
    }
    protected ModelBiped field_82423_g;
    protected ModelBiped field_82425_h;
    public static String[] bipedArmorFilenamePrefix = new String[] {"leather", "chainmail", "iron", "diamond", "gold"};
    private final KitsuneModel modelDay = new KitsuneModel();
    private static final Map field_110859_k = Maps.newHashMap();
    private final KitsuneModelquad modelNight = new KitsuneModelquad();
    private final KitsuneModeltransition modeltransition = new KitsuneModeltransition();
    public EntityKitsune kitsune;


    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        super.preRenderCallback(entity,partialTickTime);
        kitsune = (EntityKitsune) entity;
        if (kitsune.isNightForm()) {
            GL11.glScalef(0.6F, 0.6F, 0.6F); // Smaller at night
        } else {
            GL11.glScalef(1.0F, 1.0F, 1.0F); // Full size during day
        }
    }
    @Override
    public void doRender(EntityLivingBase entity, double x, double y, double z, float yaw, float partialTicks) {
        super.doRender(entity, x, y, z, yaw, partialTicks);

        EntityKitsune kitsune = (EntityKitsune) entity;
        System.out.println("do render:"+kitsune.toString());

    }
    @Override
    protected int shouldRenderPass(EntityLivingBase entity, int pass, float partialTicks) {
        EntityKitsune kitsune = (EntityKitsune) entity;
        if (kitsune.isDayForm()) {
            return shouldRenderPas((EntityLiving)entity, pass, partialTicks); // allow armor rendering
        }
        return -1; // suppress armor in night/transition forms
    }
    public static ResourceLocation getArmorResource(Entity entity, ItemStack stack, int slot, String type)
    {
        ItemArmor item = (ItemArmor)stack.getItem();
        String s1 = String.format("textures/models/armor/%s_layer_%d%s.png",
                bipedArmorFilenamePrefix[item.renderIndex], (slot == 2 ? 2 : 1), type == null ? "" : String.format("_%s", type));

        s1 = net.minecraftforge.client.ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
        ResourceLocation resourcelocation = (ResourceLocation)field_110859_k.get(s1);

        if (resourcelocation == null)
        {
            resourcelocation = new ResourceLocation(s1);
            field_110859_k.put(s1, resourcelocation);
        }

        return resourcelocation;
    }
    protected int shouldRenderPas(EntityLiving p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        ItemStack itemstack = p_77032_1_.func_130225_q(3 - p_77032_2_);

        if (itemstack != null)
        {
            Item item = itemstack.getItem();

            if (item instanceof ItemArmor)
            {
                ItemArmor itemarmor = (ItemArmor)item;
                this.bindTexture(getArmorResource(p_77032_1_, itemstack, p_77032_2_, null));
                ModelBiped modelbiped = p_77032_2_ == 2 ? this.field_82425_h : this.field_82423_g;
                modelbiped.bipedHead.showModel = p_77032_2_ == 0;
                modelbiped.bipedHeadwear.showModel = p_77032_2_ == 0;
                modelbiped.bipedBody.showModel = p_77032_2_ == 1 || p_77032_2_ == 2;
                modelbiped.bipedRightArm.showModel = p_77032_2_ == 1;
                modelbiped.bipedLeftArm.showModel = p_77032_2_ == 1;
                modelbiped.bipedRightLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
                modelbiped.bipedLeftLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
                modelbiped = net.minecraftforge.client.ForgeHooksClient.getArmorModel(p_77032_1_, itemstack, p_77032_2_, modelbiped);
                this.setRenderPassModel(modelbiped);
                modelbiped.onGround = this.mainModel.onGround;
                modelbiped.isRiding = this.mainModel.isRiding;
                modelbiped.isChild = this.mainModel.isChild;

                //Move out of if to allow for more then just CLOTH to have color
                int j = itemarmor.getColor(itemstack);
                if (j != -1)
                {
                    float f1 = (float)(j >> 16 & 255) / 255.0F;
                    float f2 = (float)(j >> 8 & 255) / 255.0F;
                    float f3 = (float)(j & 255) / 255.0F;
                    GL11.glColor3f(f1, f2, f3);

                    if (itemstack.isItemEnchanted())
                    {
                        return 31;
                    }

                    return 16;
                }

                GL11.glColor3f(1.0F, 1.0F, 1.0F);

                if (itemstack.isItemEnchanted())
                {
                    return 15;
                }

                return 1;
            }
        }

        return -1;
    }


    public void renderEquippedItems(EntityLiving entity, float partialTicks) {
        //System.out.println("renderEquippedItems called");

        if (!(entity instanceof EntityKitsune)) {
            //System.out.println("Entity is not a Kitsune: " + entity.getClass().getSimpleName());
            return;
        }

        EntityKitsune kitsune = (EntityKitsune) entity;

        if (!kitsune.isDayForm()) {
            //System.out.println("Kitsune is not in day form — skipping gear render");
            return;
        }

        //System.out.println("Kitsune is in day form — proceeding with gear render");

        ItemStack heldItem = entity.getHeldItem();
        ItemStack helmet = entity.func_130225_q(3); // slot 3 = helmet

        //System.out.println("Held item: " + (heldItem != null ? heldItem.getDisplayName() : "null"));
        //System.out.println("Helmet item: " + (helmet != null ? helmet.getDisplayName() : "null"));

        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        super.renderEquippedItems(entity, partialTicks);

        if (heldItem != null) {
            //System.out.println(" Rendering held item: " + heldItem.getItem().getUnlocalizedName());
            GL11.glPushMatrix();
            this.modelDay.bipedRightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            Item item = heldItem.getItem();
            boolean is3D = MinecraftForgeClient.getItemRenderer(heldItem, IItemRenderer.ItemRenderType.EQUIPPED) != null;

            if (item instanceof ItemBlock) {
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-0.375F, -0.375F, 0.375F);
            } else if (item == Items.bow) {
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(0.625F, -0.625F, 0.625F);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else if (item.isFull3D()) {
                GL11.glScalef(0.625F, -0.625F, 0.625F);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else {
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(0.375F, 0.375F, 0.375F);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            this.renderManager.itemRenderer.renderItem(entity, heldItem, 0);
            GL11.glPopMatrix();
        }
    }

    protected void func_82422_c() {
            GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
        }


    @Override
        protected void renderModel (EntityLivingBase entity,float limbSwing, float limbSwingAmount, float ageInTicks,
        float netHeadYaw, float headPitch, float scale){
            EntityKitsune kitsune = (EntityKitsune) entity;
            if (kitsune.isTransition()) {
                this.mainModel = modeltransition;
            } else if (kitsune.isNightForm()) {
                this.mainModel = modelNight;
            } else {
                this.mainModel = modelDay;
                this.setRenderPassModel(modelDay);
            }
            //System.out.println("render model:"+kitsune.toString());
            this.bindEntityTexture(entity);
            if (kitsune.isDayForm()) {
                this.mainModel = modelDay;
                this.renderEquippedItems((EntityLiving) kitsune, 1.0f);
            }
            super.renderModel(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }


    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if(entity instanceof EntityKitsune){
            EntityKitsune kitsune = (EntityKitsune)entity;
            //if(kitsune.initialized) {
                switch (kitsune.getVariant()) {
                    case 0:
                        return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/kitsune/kitsune_brown.png");
                    case 1:
                        return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/kitsune/kitsune_blonde.png");
                    case 2:
                        return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/kitsune/kitsune_dk_gray.png");
                    case 3:
                        return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/kitsune/kitsune_orange.png");
                    case 4:
                        return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/kitsune/kitsune_white.png");
                    default:
                        return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/kitsune/kitsune_brown.png");
                }
            //}else{
            //    return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/kitsune/kitsune_brown.png");
            //}
        }
        return null;
    }
}
