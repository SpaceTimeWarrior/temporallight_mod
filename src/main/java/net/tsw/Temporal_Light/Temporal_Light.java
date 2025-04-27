package net.tsw.Temporal_Light;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.Temporal_Light.Intermod.TLcompatabilityRegistry;
import net.tsw.Temporal_Light.Recipe.TLRecipeTypeRegistry;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import net.tsw.Temporal_Light.blocks.entity.TLBlockEntityRegistry;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import net.tsw.Temporal_Light.entity.client.KitsuneRenderer;
import net.tsw.Temporal_Light.items.TLCreativeTabRegistry;
import net.tsw.Temporal_Light.items.TLItemRegistry;
import net.tsw.Temporal_Light.menu.TLMenuTypesRegistry;
import net.tsw.Temporal_Light.screen.TL_HS_Assembler_screen;
import net.tsw.Temporal_Light.util.TLNetworkHandler;
import net.tsw.Temporal_Light.util.TLWoodTypeRegistry;
import net.tsw.Temporal_Light.villager.TLVillagerRegistry;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Temporal_Light.MOD_ID)
public class Temporal_Light
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "temporal_light";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public Temporal_Light(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        TLCreativeTabRegistry.register(modEventBus);
        TLItemRegistry.register(modEventBus);
        TLBlocksRegistry.register(modEventBus);
        TLRecipeTypeRegistry.register(modEventBus);
        TLBlockEntityRegistry.register(modEventBus);
        TLcompatabilityRegistry.register(modEventBus);
        TLMenuTypesRegistry.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        TLVillagerRegistry.register(modEventBus);
        TLEntityRegistry.register(modEventBus);
        TLNetworkHandler.register();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            Sheets.addWoodType(TLWoodTypeRegistry.MAGI);
            EntityRenderers.register(TLEntityRegistry.KITSUNE.get(), KitsuneRenderer::new);
            MenuScreens.register(TLMenuTypesRegistry.TL_HS_ASSEMBLER_MENU.get(), TL_HS_Assembler_screen::new);

        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
}
