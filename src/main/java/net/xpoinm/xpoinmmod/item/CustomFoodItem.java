package net.xpoinm.xpoinmmod.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xpoinm.xpoinmmod.capability.ModCapabilities;

public class CustomFoodItem extends Item {
    public CustomFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        stack = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof Player player) {
            player.getCapability(ModCapabilities.SICKNESS).ifPresent(sickness -> {
                sickness.addSickness(player, 5f);
            });
        }

        return stack;
    }
}