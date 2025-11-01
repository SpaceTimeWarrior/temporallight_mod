package com.TimeSpaceWarrior.TemporalLightMod;

import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.Random;

public class MobDropHandler {
    @SubscribeEvent
    public void onMobDrop(LivingDropsEvent event) {
        EntityLivingBase entity = event.entityLiving;
        World world = entity.worldObj;
        Random rand = entity.getRNG();


        if (rand.nextFloat() < 0.05f) {
            ItemStack drop;
            switch(rand.nextInt(99)){
                case 0:case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:

                case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
                    drop = new ItemStack(ItemRegistry.FIRE_CRYSTAL_ESSENCE, 1);
                    break;
                case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29:

                case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39:
                    drop = new ItemStack(ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE, 1);
                    break;
                case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49:

                case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59:
                    drop = new ItemStack(ItemRegistry.EARTH_CRYSTAL_ESSENCE,1);
                    break;
                case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68: case 69:

                case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79:
                    drop = new ItemStack(ItemRegistry.LIFE_CRYSTAL_ESSENCE,1);
                    break;
                case 80: case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89:

                case 90: case 91: case 92: case 93: case 94: case 95: case 96: case 97: case 98: case 99:
                default:
                    drop = new ItemStack(ItemRegistry.NULL_CRYSTAL_ESSENCE, 1);
            }

            addDrop(event, world, entity, drop);
        }

        if (entity instanceof EntityZombie) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.FIRE_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntityCreeper) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntityGhast) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.FIRE_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntityBlaze) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.FIRE_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntityMagmaCube) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.FIRE_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntityKitsune) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.EARTH_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntityIronGolem) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.EARTH_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntitySilverfish) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.EARTH_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntityWither) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.LIFE_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntityWitch) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.LIFE_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntitySlime) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.LIFE_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }
        if (entity instanceof EntityEnderman) {
            if (rand.nextFloat() < 0.40f) {           // 40 %
                ItemStack drop = new ItemStack(ItemRegistry.LIFE_CRYSTAL_ESSENCE, 1);
                addDrop(event, world, entity, drop);
            }
        }


        if (entity instanceof EntityKitsune) {
            int count = rand.nextInt(3) + 1;
            ItemStack drop = new ItemStack(ItemRegistry.NULL_CRYSTAL_ESSENCE, count);
            addDrop(event, world, entity, drop);
        }


    }

    /** Helper – creates an EntityItem and adds it to the event list */
    private void addDrop(LivingDropsEvent event, World world, EntityLivingBase entity, ItemStack stack) {
        EntityItem ei = new EntityItem(world,
                entity.posX, entity.posY, entity.posZ,
                stack.copy());
        ei.delayBeforeCanPickup = 10;                 // same as vanilla
        event.drops.add(ei);
    }
}
