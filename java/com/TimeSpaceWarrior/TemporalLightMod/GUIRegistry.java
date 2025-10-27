package com.TimeSpaceWarrior.TemporalLightMod;

import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import com.TimeSpaceWarrior.TemporalLightMod.gui.ContainerHyperSteelAssembler;
import com.TimeSpaceWarrior.TemporalLightMod.gui.ContainerKitsune;
import com.TimeSpaceWarrior.TemporalLightMod.gui.GUIHyperSteelAssembler;
import com.TimeSpaceWarrior.TemporalLightMod.gui.GUIKitsune;
import com.TimeSpaceWarrior.TemporalLightMod.tile_entity.HyperSteel_Assembler_TileEntity;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

public class GUIRegistry implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x,y,z);
        if(entity !=null){
            switch (ID){
                case TemporalLightMod.HYPERSTEEL_ASSEMBLER_GUIID:
                    if(entity instanceof HyperSteel_Assembler_TileEntity){
                        return new ContainerHyperSteelAssembler(player.inventory,(HyperSteel_Assembler_TileEntity)entity);
                    }else{
                        return null;
                    }
            }
        }else{
            AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1);
            EntityKitsune kitsune = (EntityKitsune) world.findNearestEntityWithinAABB(EntityKitsune.class,aabb,player);
            if (kitsune!=null) {
                System.out.println("found a kitsune"+(EntityKitsune)kitsune);
                return new ContainerKitsune(player.inventory, (EntityKitsune) kitsune);
            }
            /*List<Entity> entityList = world.getLoadedEntityList();
            for(int i=0;i<entityList.size();i++){
                if(!(entityList.get(i) instanceof EntityLiving)){
                    continue;
                }
                EntityLiving entitys = (EntityLiving) entityList.get(i);
                if((int)entitys.posX == x && (int)entitys.posY==y && (int)entitys.posZ == z){
                    switch (ID){
                        case TemporalLightMod.KITSUNE_GUIID:
                            if (entitys != null) {
                                if (entitys instanceof EntityKitsune) {
                                    System.out.println("found kitsune"+(EntityKitsune) entitys);
                                    return new ContainerKitsune(player.inventory, (EntityKitsune) entitys);
                                }
                            }
                        default:
                    }
                }
            }*/
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x,y,z);
        if(entity !=null){
            switch (ID){
                case TemporalLightMod.HYPERSTEEL_ASSEMBLER_GUIID:
                    if(entity instanceof HyperSteel_Assembler_TileEntity){
                        return new GUIHyperSteelAssembler(player.inventory,(HyperSteel_Assembler_TileEntity)entity);
                    }else{
                        return null;
                    }
            }
        }else{
            AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x - 1, y - 1, z - 1, x + 1, y + 1, z + 1);
            EntityKitsune kitsune = (EntityKitsune) world.findNearestEntityWithinAABB(EntityKitsune.class,aabb,player);
            if (kitsune!=null) {
                System.out.println("found a kitsune"+(EntityKitsune)kitsune);
                return new GUIKitsune(player.inventory, (EntityKitsune) kitsune);
            }
            /*List<Entity> entityList = world.getLoadedEntityList();
            for(int i=0;i<entityList.size();i++){
                if(!(entityList.get(i) instanceof EntityKitsune)){
                    continue;
                }
                EntityLiving entitys = (EntityLiving) entityList.get(i);
                if((int)entitys.posX == x && (int)entitys.posY==y && (int)entitys.posZ == z){
                    switch (ID){
                        case TemporalLightMod.KITSUNE_GUIID:
                            if (entitys != null) {
                                if (entitys instanceof EntityKitsune) {
                                    System.out.println("found a kitsune"+(EntityKitsune)entitys);
                                    return new GUIKitsune(player.inventory, (EntityKitsune) entitys);
                                }
                            }else{
                                System.out.println("unknown value");
                            }
                        default:
                    }
                }
            }*/
        }
        return null;
    }
}
