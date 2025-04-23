package net.xpoinm.xpoinmmod.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.xpoinm.xpoinmmod.item.CustomFoodItem;
import net.xpoinm.xpoinmmod.xpoinmmod;

public class ModItems {
    public static void register() {
        // Автоматическая регистрация через RegistryObject
    }

    public static final Item.Properties MANDARIN_PROPERTIES = new Item.Properties()
            .tab(CreativeModeTab.TAB_FOOD)
            .food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(0.3f)
                    .alwaysEat()
                    .build());

    public static final RegistryObject<Item> MANDARIN =
            xpoinmmod.ITEMS.register("mandarin",
                    () -> new CustomFoodItem(MANDARIN_PROPERTIES));
}