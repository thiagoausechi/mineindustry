package dev.thiagoausechi.mineindustry.items;

import dev.thiagoausechi.mineindustry.sounds.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WrenchItem extends Item {
    public WrenchItem(Properties pProperties) {
        super(pProperties);
    }

    /**
     * Called when this item is used when targeting a Block
     */
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();

        if (player != null) {
            level.playSound(player, player.getX(), player.getY(), player.getZ(), ModSounds.WRENCH_USE.get(), SoundSource.BLOCKS, 1F, 1F);
            player.awardStat(Stats.ITEM_USED.get(this));

            if (!level.isClientSide) {
                BlockPos blockPos = context.getClickedPos();
                if (!this.handleInteraction(player, level.getBlockState(blockPos), level, blockPos, context.getItemInHand())) {
                    return InteractionResult.FAIL;
                }
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private boolean handleInteraction(Player player, BlockState blockState, LevelAccessor level, BlockPos blockPos, ItemStack itemStack) {
        // TODO Here will be added the multiple interactions using WrenchAPI
        return true;
    }
}
