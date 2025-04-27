package net.xpoinm.xpoinmmod.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.xpoinm.xpoinmmod.Xpoinmmod; // Убедитесь, что имя мода верное
import net.xpoinm.xpoinmmod.container.ThirstOverlayContainer;

import static net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registration {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Xpoinmmod.MOD_ID);

    // Перемещаем объявление CONTAINERS выше статического блока
    public static final DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Xpoinmmod.MOD_ID);

    // Статический блок для регистрации
    static {
        CONTAINERS.register("thirst_overlay_container", () ->
                IForgeMenuType.create((id, playerInventory, data) -> new ThirstOverlayContainer(id))
        );
    }

    public static void init(IEventBus eventBus) {
        // Удаляем дублирующееся объявление modEventBus
        ModItems.ITEMS.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
    }
}