package net.tsw.Temporal_Light;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tsw.Temporal_Light.Intermod.TLcompatabilityRegistry;
import net.tsw.Temporal_Light.Loot.TLLootModifiers;
import net.tsw.Temporal_Light.Recipe.TLRecipeTypeRegistry;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import net.tsw.Temporal_Light.blocks.entity.TLBlockEntityRegistry;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import net.tsw.Temporal_Light.entity.kitsune.client.KitsuneRenderer;
import net.tsw.Temporal_Light.entity.phoenixF.client.phoenixFRenderer;
import net.tsw.Temporal_Light.items.TLCreativeTabRegistry;
import net.tsw.Temporal_Light.items.TLItemRegistry;
import net.tsw.Temporal_Light.menu.TLMenuTypesRegistry;
import net.tsw.Temporal_Light.screen.TL_HS_Assembler_screen;
import net.tsw.Temporal_Light.util.TLWoodTypeRegistry;
import net.tsw.Temporal_Light.villager.TLVillagerRegistry;
import net.tsw.Temporal_Light.world.biome.TLBiomeRegistry;
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
        TLBiomeRegistry.register(modEventBus);
        // Register the commonSetup method for modloading
        TLVillagerRegistry.register(modEventBus);
        TLEntityRegistry.register(modEventBus);
        TLLootModifiers.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            Sheets.addWoodType(TLWoodTypeRegistry.MAGI);
            ItemProperties.register(TLItemRegistry.HYPERSTEELSHIELD.get(), ResourceLocation.parse("blocking"), (p_174575_, p_174576_, p_174577_, p_174578_) -> {
                return p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F;
            });
            EntityRenderers.register(TLEntityRegistry.KITSUNE.get(), KitsuneRenderer::new);
            EntityRenderers.register(TLEntityRegistry.PHOENIX_F.get(), phoenixFRenderer::new);
            EntityRenderers.register(TLEntityRegistry.LIGHTNING_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(TLEntityRegistry.FIREBALL.get(), ThrownItemRenderer::new);
            EntityRenderers.register(TLEntityRegistry.EARTHBALL.get(),ThrownItemRenderer::new);
            EntityRenderers.register(TLEntityRegistry.LIFEBALL.get(),ThrownItemRenderer::new);

            MenuScreens.register(TLMenuTypesRegistry.TL_HS_ASSEMBLER_MENU.get(), TL_HS_Assembler_screen::new);

        }
    }
}
