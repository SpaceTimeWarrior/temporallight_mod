package net.tsw.temporal_light.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.temporal_light.Temporal_Light;

public class TLMenuTypesRegistry {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Temporal_Light.MOD_ID);

    public static final RegistryObject<MenuType<TL_HS_Assembler_Menu>>TL_HS_ASSEMBLER_MENU = MENUS.register("tl_hs_assembler_menu",()-> IForgeMenuType.create(TL_HS_Assembler_Menu::new));

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> Factory){
        return MENUS.register(name,()-> IForgeMenuType.create(Factory));
    }
    public static void register(IEventBus eventBus){
        System.out.println("Registering menu types...");
        MENUS.register(eventBus);
    }
}
