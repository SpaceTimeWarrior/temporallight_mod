package net.tsw.temporallight.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;

public class ContainerRegistry {
    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, TemporalLight.MOD_ID);
    public static final  RegistryObject<ContainerType<assemblerContainer>> ASSEMBLER_CONTAINER
            = CONTAINERS.register("assemblercontainer",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                 BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new assemblerContainer(windowId, world, pos, inv, inv.player);
            })));
    public static void register(IEventBus eventbus){
        CONTAINERS.register(eventbus);
    }
}
