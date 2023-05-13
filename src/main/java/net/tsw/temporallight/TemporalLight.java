package net.tsw.temporallight;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.container.ContainerRegistry;
import net.tsw.temporallight.data.recipes.RecipieTypeRegistry;
import net.tsw.temporallight.item.ItemRegistry;
import net.tsw.temporallight.screen.assemblerScreen;
import net.tsw.temporallight.tileentity.TileEntityRegistry;
import net.tsw.temporallight.util.TLItemModelProperties;
import net.tsw.temporallight.util.configRegistry;
import net.tsw.temporallight.world.biome.TLBiomeRegistry;
import net.tsw.temporallight.world.structure.TLStructureRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TemporalLight.MOD_ID)
public class TemporalLight
{
    public static final String MOD_ID = "temporallight";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static boolean optifineLoaded = false;
    public static int portalcooldown = 0;
    public TemporalLight() {
        // Register the setup method for modloading
        IEventBus Eventbus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, configRegistry.SPEC, "temporallight-common.toml");
        ItemRegistry.register(Eventbus);
        blockRegistry.register(Eventbus);
        TileEntityRegistry.register(Eventbus);
        ContainerRegistry.register(Eventbus);
        TLStructureRegistry.register(Eventbus);
        RecipieTypeRegistry.register(Eventbus);
        TLBiomeRegistry.register(Eventbus);


        Eventbus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        Eventbus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        Eventbus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        Eventbus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        event.enqueueWork(()->{
            AxeItem.BLOCK_STRIPPING_MAP = new ImmutableMap.Builder<Block,Block>().putAll(AxeItem.BLOCK_STRIPPING_MAP)
                    .put(blockRegistry.MAGIWOODLOG.get(),blockRegistry.MAGIWOODSTRIPPEDLOG.get())
                    .put(blockRegistry.MAGIWOOD.get(),blockRegistry.MAGIWOODSTRIPPED.get()).build();
        });
        TLStructureRegistry.setupStructures();

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        //LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
        RenderTypeLookup.setRenderLayer(blockRegistry.MAGIWOODSAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(blockRegistry.MAGIWOODLEAVES.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(blockRegistry.MAGIWOODPORTAL.get(), RenderType.getTranslucentMovingBlock());
        //RenderTypeLookup.setRenderLayer(blockRegistry.MAGIWOODPORTAL.get(), RenderType.getCutout());
        TLItemModelProperties.makeBow(ItemRegistry.HYPERSTEELBOW.get());
        ScreenManager.registerFactory(ContainerRegistry.ASSEMBLER_CONTAINER.get(), assemblerScreen::new);

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("net/tsw/temporallight", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
       /* LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));*/
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
