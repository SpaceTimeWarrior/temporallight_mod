package net.tsw.Temporal_Light.entity.phoenixM;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class phoenix_mEntity extends Chicken {
    public phoenix_mEntity(EntityType<? extends phoenix_mEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public void aiStep() {
        super.aiStep();
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0D) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }
    }
}
