package dev.thiagoausechi.mineindustry.blocks;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

public class ExtractionNodeBlock extends Block {
    public static final EnumProperty<Quality> QUALITY = EnumProperty.create("quality", Quality.class);

    private final Item extractionResult;
    private int extractionRate = 30;

    public ExtractionNodeBlock(Item extractionResult) {
        super(BlockBehaviour.Properties.copy(Blocks.BEDROCK));
        this.registerDefaultState(this.defaultBlockState().setValue(QUALITY, Quality.IMPURE));
        this.extractionResult = extractionResult;
    }

    public Item getExtractionResult() {
        return extractionResult;
    }

    public Block setExtractionRate(int rate) {
        this.extractionRate = rate;
        return this;
    }

    public int getExtractionRate() {
        return extractionRate;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(QUALITY);
    }

    public enum Quality implements StringRepresentable {
        IMPURE("impure"),
        NORMAL("normal"),
        PURE("pure");

        private final String name;

        Quality(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public @NotNull String getSerializedName() {
            return this.name;
        }
    }
}
