package net.tsw.temporal_light;

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
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Blocks.entity.TLBlockEntityRegistry;
import net.tsw.temporal_light.Items.TLCreativeTabRegistry;
import net.tsw.temporal_light.Items.TLItemRegistry;
import net.tsw.temporal_light.compatability.TLcompatabilityRegistry;
import net.tsw.temporal_light.entity.TLEntityRegistry;
import net.tsw.temporal_light.entity.client.KitsuneRenderer;
import net.tsw.temporal_light.recipes.TLRecipeTypeRegistry;
import net.tsw.temporal_light.screen.TLMenuTypesRegistry;
import net.tsw.temporal_light.screen.TL_HS_Assembler_screen;
import net.tsw.temporal_light.util.TLWoodTypeRegistry;
import net.tsw.temporal_light.villagers.TLVillagerRegistry;
import net.tsw.temporal_light.world.Features.biome.TLBiomeRegistry;
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
        TLVillagerRegistry.register(modEventBus);
        TLRecipeTypeRegistry.register(modEventBus);
        TLBlockEntityRegistry.register(modEventBus);
        TLEntityRegistry.register(modEventBus);
        TLcompatabilityRegistry.register(modEventBus);
        TLMenuTypesRegistry.register(modEventBus);
        TLBiomeRegistry.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        System.out.println("Serializer ID: " + TLRecipeTypeRegistry.HYPERSTEEL_ASSEMBLE_SERIALIZER.getId());
        System.out.println("Recipe Type ID: " + TLRecipeTypeRegistry.TL_HS_ASSEMBLER.getId());

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
