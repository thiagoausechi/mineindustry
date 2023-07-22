package dev.thiagoausechi.mineindustry.blocks.registries;

import dev.thiagoausechi.mineindustry.References;
import dev.thiagoausechi.mineindustry.blocks.ExtractionNodeBlock;
import dev.thiagoausechi.mineindustry.blocks.ModBlocks;
import dev.thiagoausechi.mineindustry.items.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.registries.RegistryObject;

public class ExtractionNodeRegistry implements IRegistry<Block> {

    public String name;
    public String baseTexture;
    public String oreTexture;

    public Item extractionResult;
    public int extractionRate = 30;

    public ExtractionNodeRegistry(String type, Item extractionResult, String oreTexture) {
        this(type, extractionResult, oreTexture, "stone", 30);
    }

    public ExtractionNodeRegistry(String type, Item extractionResult, String oreTexture, String baseTexture) {
        this(type, extractionResult, oreTexture, baseTexture, 30);
    }

    public ExtractionNodeRegistry(String type, Item extractionResult, String oreTexture, String baseTexture, int extractionRate) {
        this.name = type + "_extraction_node";
        this.oreTexture = oreTexture;
        this.baseTexture = baseTexture;
        this.extractionResult = extractionResult;
        this.extractionRate = extractionRate;
    }

    public RegistryObject<Block> register() {
        RegistryObject<Block> blockRegistry = ModBlocks.BLOCKS.register(name, () -> new ExtractionNodeBlock(extractionResult).setExtractionRate(extractionRate));
        ModItems.ITEMS.register(name, () -> new BlockItem(blockRegistry.get(), new Item.Properties()));

        return blockRegistry;
    }

    public void registerStatesAndModels(BlockStateProvider provider, RegistryObject<Block> registryObject) {
        String blockPath = registryObject.getId().getPath();

        provider.getVariantBuilder(registryObject.get())
                .partialState().with(ExtractionNodeBlock.QUALITY, ExtractionNodeBlock.Quality.IMPURE).addModels(generateNodeModel(provider, "impure", blockPath))
                .partialState().with(ExtractionNodeBlock.QUALITY, ExtractionNodeBlock.Quality.NORMAL).addModels(generateNodeModel(provider, "normal", blockPath))
                .partialState().with(ExtractionNodeBlock.QUALITY, ExtractionNodeBlock.Quality.PURE).addModels(generateNodeModel(provider, "pure", blockPath));

        // TODO Temporally solution, as the variants will be shown in the Creative Tab
        provider.itemModels().getBuilder(blockPath).parent(generateNodeModel(provider, "impure", blockPath)[0].model);
    }

    private ConfiguredModel[] generateNodeModel(BlockStateProvider provider, String quality, String blockPath) {
        ModelFile model = provider.models().withExistingParent(quality + "_" + blockPath, new ResourceLocation(References.MOD_ID, "block/templates/" + quality + "_extraction_node_template"))
                .texture("base", new ResourceLocation("block/" + baseTexture))
                .texture("ore", new ResourceLocation("block/" + oreTexture));

        return new ConfiguredModel[]{new ConfiguredModel(model), new ConfiguredModel(model, 0, 180, false)};
    }
}
