package net.xpoinm.xpoinmmod.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeMenuType;

public class ThirstOverlayContainer extends AbstractContainerMenu {
    private final int id;

    public ThirstOverlayContainer(int id) {
        super(null, id); // Замените null на ваш тип контейнера
        this.id = id;
    }

    // Фабричный метод для регистрации
    public static final MenuType<ThirstOverlayContainer> TYPE =
            IForgeMenuType.create((id, playerInventory, data) -> new ThirstOverlayContainer(id));

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return false;
    }
}