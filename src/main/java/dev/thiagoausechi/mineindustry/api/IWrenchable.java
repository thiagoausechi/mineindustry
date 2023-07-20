package dev.thiagoausechi.mineindustry.api;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;

public interface IWrenchable {
    default InteractionResult onWrenched(BlockState state, UseOnContext context) {
        return InteractionResult.PASS;
    }

    default InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        return InteractionResult.SUCCESS;
    }
}
