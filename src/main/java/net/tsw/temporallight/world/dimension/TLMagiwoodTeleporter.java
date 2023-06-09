package net.tsw.temporallight.world.dimension;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;
import net.tsw.temporallight.block.custom.customPortalBlock;

import java.util.function.Function;

public class TLMagiwoodTeleporter implements ITeleporter  {
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDimension = true;

    protected final ServerWorld world;

    public TLMagiwoodTeleporter(BlockPos pos,boolean insideDimension,ServerWorld world) {
        this.thisPos = pos;
        this.insideDimension = insideDimension;
        this.world = world;
    }
    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destinationWorld,
                              float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        double y = 61;

        if (!insideDimension) {
            y = thisPos.getY();
        }

        BlockPos destinationPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());

        int tries = 0;
        while ((destinationWorld.getBlockState(destinationPos).getMaterial() != Material.AIR) &&
                !destinationWorld.getBlockState(destinationPos).isReplaceable(Fluids.EMPTY) &&
                destinationWorld.getBlockState(destinationPos.up()).getMaterial() != Material.AIR &&
                !destinationWorld.getBlockState(destinationPos.up()).isReplaceable(Fluids.EMPTY) && tries < 25) {
            destinationPos = destinationPos.up(2);
            tries++;
        }

        entity.setPositionAndUpdate(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());

        if (insideDimension) {
            boolean doSetBlock = true;
            for (BlockPos checkPos : BlockPos.getAllInBoxMutable(destinationPos.down(10).west(10), destinationPos.up(10).east(10))) {
                if (destinationWorld.getBlockState(checkPos).getBlock() instanceof customPortalBlock) {
                    doSetBlock = false;
                    break;
                }
            }

            if (doSetBlock) {
                // makePortal(destinationPos,entity.getHorizontalFacing().getAxis());
                //customPortalBlock.createPortal(thisPos,destinationPos,destinationWorld);
                //destinationWorld.setBlockState(destinationPos, blockRegistry.MAGIWOODPORTAL.get().getDefaultState());
            }
        }

        return entity;
    }


}
