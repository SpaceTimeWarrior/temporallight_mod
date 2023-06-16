package net.tsw.temporallight.Entity.Mob;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.controller.FlyingMovementController;

public class pheonixflyingmovementcontroller extends FlyingMovementController {
    public pheonixflyingmovementcontroller(MobEntity mob, int range, boolean no_gravity) {
        super(mob, range, no_gravity);
    }
}
