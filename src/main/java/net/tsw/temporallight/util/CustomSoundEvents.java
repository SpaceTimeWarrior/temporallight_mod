package net.tsw.temporallight.util;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.integration.compatabilityItemRegistry;

public class CustomSoundEvents {
    public static final DeferredRegister<SoundEvent> CUSTOMSOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TemporalLight.MOD_ID);
    public static final RegistryObject<SoundEvent> PHOENIXIDLE = registerSoundEvent("phoenix_talk");
    public static final RegistryObject<SoundEvent> PHOENIXACCEPT = registerSoundEvent("phoenix_accept");
    public static final RegistryObject<SoundEvent> PHOENIXDecline = registerSoundEvent("phoenix_decline");


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return CUSTOMSOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(TemporalLight.MOD_ID, name)));
    }
    public static void register(IEventBus eventbus) {
        CUSTOMSOUNDS.register(eventbus);

    }
}
