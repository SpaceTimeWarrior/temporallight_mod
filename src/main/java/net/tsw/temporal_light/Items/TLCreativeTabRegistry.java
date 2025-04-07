package net.tsw.temporal_light.Items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Temporal_Light;

public class TLCreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> CTABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Temporal_Light.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TemporalLightProphecytab = CTABS.register("temporallightprophecy",()-> CreativeModeTab.builder().icon(()->new ItemStack(TLItemRegistry.TIMECRYSTALSHARD.get())).title(Component.translatable("creativetab.temporallightprophecy")).displayItems(((pParameters, pOutput) -> {
        pOutput.accept((TLItemRegistry.TIMECRYSTALSHARD.get()));
    })).build());
    public static final RegistryObject<CreativeModeTab> TemporalLightMaterials = CTABS.register("temporallightmaterials",()-> CreativeModeTab.builder().icon(()->new ItemStack(TLItemRegistry.SYNTHTIMECRYSTALSHARD.get())).title(Component.translatable("creativetab.temporallightmaterials")).displayItems(((pParameters, pOutput) -> {
        pOutput.accept((TLItemRegistry.SYNTHTIMECRYSTALSHARD.get()));
        pOutput.accept((TLItemRegistry.TIMECRYSTALSHARD.get()));
        pOutput.accept((TLItemRegistry.HYPERSTEELINGOT.get()));
        pOutput.accept((TLBlocksRegistry.MAGIWOODPLANKS.get()));
        pOutput.accept((TLItemRegistry.LIGHTSTEELINGOT.get()));

    })).build());
    public static final RegistryObject<CreativeModeTab> TemporalLightBlocks = CTABS.register("temporallightblocks",()-> CreativeModeTab.builder().icon(()->new ItemStack(TLBlocksRegistry.HYPERSTEEL_BLOCK.get())).title(Component.translatable("creativetab.temporallightblocks")).displayItems(((pParameters, pOutput) -> {
        pOutput.accept(TLBlocksRegistry.HYPERSTEEL_BLOCK.get());
        pOutput.accept(TLBlocksRegistry.HYPERSTEEL_BUTTON.get());
        pOutput.accept(TLBlocksRegistry.HYPERSTEEL_PRESSUREPLATE.get());
        pOutput.accept(TLBlocksRegistry.HYPERSTEEL_RAILING.get());
        pOutput.accept(TLBlocksRegistry.HYPERSTEEL_RAILING_gate.get());
        pOutput.accept(TLBlocksRegistry.HYPERSTEEL_SLAB.get());
        pOutput.accept(TLBlocksRegistry.HYPERSTEEL_STAIRS.get());
        pOutput.accept(TLBlocksRegistry.SYNTHETICTIMECRYSTALORE.get());
        pOutput.accept(TLBlocksRegistry.HYPERSTEEL_ASSEMBLER.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOOD.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOODPLANKS.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOODLOG.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOODSTRIPPED.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOODSTRIPPEDLOG.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOODLEAVES.get());
        pOutput.accept(TLItemRegistry.MAGI_SIGN.get());
        pOutput.accept(TLItemRegistry.MAGI_HANGING_SIGN.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOOD_STAIRS.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOOD_BUTTON.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOOD_PRESSUREPLATE.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOOD_FENCE.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOOD_FENCE_gate.get());
        pOutput.accept(TLBlocksRegistry.MAGIWOOD_SLAB.get());

    })).build());
    public static final RegistryObject<CreativeModeTab> TemporalLightTOOLS = CTABS.register("temporallighttools",()-> CreativeModeTab.builder().icon(()->new ItemStack(TLItemRegistry.HYPERSTEELSWORD.get())).title(Component.translatable("creativetab.temporallighttools")).displayItems(((pParameters, pOutput) -> {
        pOutput.accept((TLItemRegistry.HYPERSTEELSWORD.get()));
        pOutput.accept((TLItemRegistry.HYPERSTEELAXE.get()));
        pOutput.accept((TLItemRegistry.HYPERSTEELPICKAXE.get()));
        pOutput.accept((TLItemRegistry.HYPERSTEELSHOVEL.get()));
        pOutput.accept((TLItemRegistry.HYPERSTEELHOE.get()));
        pOutput.accept((TLItemRegistry.HYPERSTEELHELMET.get()));
        pOutput.accept((TLItemRegistry.HYPERSTEELCHESTPLATE.get()));
        pOutput.accept((TLItemRegistry.HYPERSTEELLEGGINGS.get()));
        pOutput.accept((TLItemRegistry.HYPERSTEELBOOTS.get()));
    })).build());


    public static void register(IEventBus eventbus){
        CTABS.register(eventbus);
    }
}
