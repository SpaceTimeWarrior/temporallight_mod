package com.TimeSpaceWarrior.TemporalLightMod.world.dimension;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.world.DimensionRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.world.dimension.TeleporterKitsune;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class KitsunePBlock extends Block {
    public KitsunePBlock(Material portalmat) {
        super(portalmat);
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
        super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
        if (p_149674_1_.provider.isSurfaceWorld() && p_149674_1_.getGameRules().getGameRuleBooleanValue("doMobSpawning") && p_149674_5_.nextInt(2000) < p_149674_1_.difficultySetting.getDifficultyId()) {
            int l;
            for(l = p_149674_3_; !World.doesBlockHaveSolidTopSurface(p_149674_1_, p_149674_2_, l, p_149674_4_) && l > 0; --l) {
            }

            if (l > 0 && !p_149674_1_.getBlock(p_149674_2_, l + 1, p_149674_4_).isNormalCube()) {
                Entity entity = ItemMonsterPlacer.spawnCreature(p_149674_1_, 57, (double)p_149674_2_ + 0.5, (double)l + 1.1, (double)p_149674_4_ + 0.5);
                if (entity != null) {
                    entity.timeUntilPortal = entity.getPortalCooldown();
                }
            }
        }
    }
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP) {
            EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
            if (thePlayer.timeUntilPortal > 0) {
                thePlayer.timeUntilPortal = 10;
            } else if (thePlayer.dimension != DimensionRegistry.Kitsune_Forests_DIMID) {
                thePlayer.timeUntilPortal = 10;
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, DimensionRegistry.Kitsune_Forests_DIMID, new TeleporterKitsune(thePlayer.mcServer.worldServerForDimension(DimensionRegistry.Kitsune_Forests_DIMID)));
            } else {
                thePlayer.timeUntilPortal = 10;
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0, new TeleporterKitsune(thePlayer.mcServer.worldServerForDimension(0)));
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return Item.getItemById(0);
    }

    @SideOnly(Side.CLIENT)
    public MapColor getMapColor(int p_149728_1_) {
        return MapColor.diamondColor;
    }

    public static boolean tryToCreatePortal(World world, int x, int y, int z) {
        byte b0 = 0;
        byte b1 = 0;
        if (world.getBlock(x - 1, y, z) == BlockRegistry.MAGIWOODPLANK || world.getBlock(x + 1, y, z) == BlockRegistry.MAGIWOODPLANK) {
            b0 = 1;
        }

        if (world.getBlock(x, y, z - 1) == BlockRegistry.MAGIWOODPLANK || world.getBlock(x, y, z + 1) == BlockRegistry.MAGIWOODPLANK) {
            b1 = 1;
        }

        if (b0 == b1) {
            return false;
        } else {
            if (world.getBlock(x - b0, y, z - b1) == null) {
                x -= b0;
                z -= b1;
            }

            int l;
            int i1;
            for(l = -1; l <= 2; ++l) {
                for(i1 = -1; i1 <= 3; ++i1) {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
                        Block j1 = world.getBlock(x + b0 * l, y + i1, z + b1 * l);
                        if (flag) {
                            if (j1 != BlockRegistry.MAGIWOODPLANK) {
                                return false;
                            }
                        } else if (j1 != null && j1 != Blocks.fire) {
                            return false;
                        }
                    }
                }
            }

            for(l = 0; l < 2; ++l) {
                for(i1 = 0; i1 < 3; ++i1) {
                    world.setBlock(x + b0 * l, y + i1, z + b1 * l, BlockRegistry.KITSUNEPORTAL, 0, 2);
                }
            }

            return true;
        }
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        byte b0 = 0;
        byte b1 = 1;
        if (world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this) {
            b0 = 1;
            b1 = 0;
        }

        int i1;
        for(i1 = y; world.getBlock(x, i1 - 1, z) == this; --i1) {
        }

        if (world.getBlock(x, i1 - 1, z) != BlockRegistry.MAGIWOODPLANK) {
            world.markBlockForUpdate(x, y, z);
        } else {
            int j1;
            for(j1 = 1; j1 < 4 && world.getBlock(x, i1 + j1, z) == this; ++j1) {
            }

            if (j1 == 3 && world.getBlock(x, i1 + j1, z) == BlockRegistry.MAGIWOODPLANK) {
                boolean flag = world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this;
                boolean flag1 = world.getBlock(x, y, z - 1) == this || world.getBlock(x, y, z + 1) == this;
                if (flag && flag1) {
                    world.markBlockForUpdate(x, y, z);
                } else if ((world.getBlock(x + b0, y, z + b1) != BlockRegistry.MAGIWOODPLANK || world.getBlock(x - b0, y, z - b1) != this) && (world.getBlock(x - b0, y, z - b1) != BlockRegistry.MAGIWOODPLANK || world.getBlock(x + b0, y, z + b1) != this)) {
                    world.markBlockForUpdate(x, y, z);
                }
            } else {
                world.markBlockForUpdate(x, y, z);
            }
        }

    }

    public boolean onBlockActivated(World world, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        EntityPlayerMP entityPlayerMP;
        ServerConfigurationManager serverConfigurationManager;
        if (world.provider.dimensionId != DimensionRegistry.Kitsune_Forests_DIMID) {
            if (p_149727_5_ instanceof EntityPlayerMP) {
                entityPlayerMP = (EntityPlayerMP)p_149727_5_;
                serverConfigurationManager = entityPlayerMP.mcServer.getConfigurationManager();
                WorldServer worldServer = entityPlayerMP.mcServer.worldServerForDimension(DimensionRegistry.Kitsune_Forests_DIMID);
                entityPlayerMP.timeUntilPortal = 10;
                entityPlayerMP.setInPortal();
                serverConfigurationManager.transferPlayerToDimension(entityPlayerMP, DimensionRegistry.Kitsune_Forests_DIMID, new TeleporterKitsune(worldServer));
            }
        } else if (p_149727_5_ instanceof EntityPlayerMP) {
            entityPlayerMP = (EntityPlayerMP)p_149727_5_;
            serverConfigurationManager = entityPlayerMP.mcServer.getConfigurationManager();
            entityPlayerMP.setInPortal();
            entityPlayerMP.timeUntilPortal = 10;
            serverConfigurationManager.transferPlayerToDimension(entityPlayerMP, 0);
        }

        return true;
    }



    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (rand.nextInt(100) == 0) {
            world.playSound((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for(int l = 0; l < 4; ++l) {
            double d0 = (double)((float)x + rand.nextFloat());
            double d1 = (double)((float)y + rand.nextFloat());
            double d2 = (double)((float)z + rand.nextFloat());
            double d3 = 0.0;
            double d4 = 0.0;
            double d5 = 0.0;
            int i1 = rand.nextInt(2) * 2 - 1;
            d3 = ((double)rand.nextFloat() - 0.5) * 0.5;
            d4 = ((double)rand.nextFloat() - 0.5) * 0.5;
            d5 = ((double)rand.nextFloat() - 0.5) * 0.5;
            if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this) {
                d0 = (double)x + 0.5 + 0.25 * (double)i1;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            } else {
                d2 = (double)z + 0.5 + 0.25 * (double)i1;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }

            world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
        }

    }

}
