package net.xpoinm.xpoinmmod.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.xpoinm.xpoinmmod.capability.ModCapabilities;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CustomFoodItem extends Item {
    private final float thirstEffect;
    private final float diseaseEffect;
    private final float fatigueEffect;

    public CustomFoodItem(Item.Properties properties, float thirst, float disease, float fatigue) {
        super(properties);
        this.thirstEffect = thirst;
        this.diseaseEffect = disease;
        this.fatigueEffect = fatigue;
    }

    @Override
    @Nonnull
    public ItemStack finishUsingItem(
            @Nonnull ItemStack stack,
            @Nonnull Level level,
            @Nonnull LivingEntity entity
    ) {
        stack = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof Player player) {
            player.getCapability(ModCapabilities.THIRST_CAPABILITY).ifPresent(cap ->
                    cap.setThirst(cap.getThirst() + thirstEffect)
            );
            player.getCapability(ModCapabilities.DISEASE_CAPABILITY).ifPresent(cap ->
                    cap.setDisease(cap.getDisease() + diseaseEffect)
            );
            player.getCapability(ModCapabilities.FATIGUE_CAPABILITY).ifPresent(cap ->
                    cap.setFatigue(cap.getFatigue() + fatigueEffect)
            );
        }

        return stack;
    }
}