package dev.thiagoausechi.mineindustry.datagen;

import dev.thiagoausechi.mineindustry.References;
import dev.thiagoausechi.mineindustry.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, References.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.BLOCKS_REGISTRIES.forEach((block, registry) -> registry.registerStatesAndModels(this, block));
    }

}
