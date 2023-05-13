package net.tsw.temporallight.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

    public class PortalTileEntity extends TileEntity {
        public PortalTileEntity(TileEntityType<?> p_i48283_1_) {
            super(p_i48283_1_);
        }
        public PortalTileEntity(){
            this(TileEntityRegistry.assemblertileentity.get());
        }

        @OnlyIn(Dist.CLIENT)
        public boolean shouldRenderFace(Direction face) {
            return face == Direction.UP;
        }
    }
