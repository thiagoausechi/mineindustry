package dev.thiagoausechi.mineindustry.items;

import dev.thiagoausechi.mineindustry.api.IWrenchable;
import dev.thiagoausechi.mineindustry.sounds.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class WrenchItem extends Item {
    public WrenchItem(Properties properties) {
        super(properties);
    }

    /**
     * Called when this item is used when targeting a Block
     */
    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();

        if (player == null || !player.mayBuild())
            return super.useOn(context);

        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        Block block = state.getBlock();

        if (!(block instanceof IWrenchable actor)) {
            if (canWrenchPickup(state))
                return pickup(context);

            return super.useOn(context);
        }

        if (player.isShiftKeyDown())
            return actor.onSneakWrenched(state, context);

        player.awardStat(Stats.ITEM_USED.get(this));

        return actor.onWrenched(state, context);
    }

    private static InteractionResult pickup(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);

        if (!(world instanceof ServerLevel))
            return InteractionResult.SUCCESS;

        if (player != null && !player.isCreative())
            Block.getDrops(state, (ServerLevel) world, pos, world.getBlockEntity(pos), player, context.getItemInHand())
                    .forEach(itemStack -> player.getInventory().placeItemBackInInventory(itemStack));
        state.spawnAfterBreak((ServerLevel) world, pos, ItemStack.EMPTY, false);
        world.destroyBlock(pos, false);

        world.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.WRENCH_USE.get(), SoundSource.BLOCKS, 1F, 1F);

        return InteractionResult.SUCCESS;
    }

    private boolean canWrenchPickup(BlockState state) {
        // TODO Matches Wrench Pickup Tag
        // For now, it works with every block present in the Redstone Creative Tab
        return BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.REDSTONE_BLOCKS).contains(state.getBlock().asItem().getDefaultInstance());
    }
}
