package net.xpoinm.xpoinmmod.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.xpoinm.xpoinmmod.Xpoinmmod;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Xpoinmmod.MOD_ID);

    // Пример блока: Медный блок (исправлены скобки)
    public static final RegistryObject<Block> COPPER_BLOCK = registerBlock(
            "copper_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(3.0f, 10.0f)
                    .requiresCorrectToolForDrops()) // Закрывающая скобка добавлена
    );

    // Пример блока: Сырая медная руда (исправлены скобки)
    public static final RegistryObject<Block> RAW_COPPER_ORE = registerBlock(
            "raw_copper_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4.5f, 6.0f)
                    .requiresCorrectToolForDrops()) // Закрывающая скобка добавлена
    );

    // Метод регистрации блока и его предмета
    private static <T extends Block> RegistryObject<T> registerBlock(
            String name,
            Supplier<T> blockSupplier
    ) {
        RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
        ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(),
                        new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS))
        );
        return block;
    }
}