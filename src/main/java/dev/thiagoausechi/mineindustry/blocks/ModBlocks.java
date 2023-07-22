package dev.thiagoausechi.mineindustry.blocks;

import dev.thiagoausechi.mineindustry.References;
import dev.thiagoausechi.mineindustry.blocks.registries.ExtractionNodeRegistry;
import dev.thiagoausechi.mineindustry.blocks.registries.IRegistry;
import dev.thiagoausechi.mineindustry.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;

// @formatter:off
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.MOD_ID);
    public static final HashMap<RegistryObject<Block>, IRegistry<Block>> BLOCKS_REGISTRIES = new HashMap<>();

    // Machines
    public static final RegistryObject<Block> DRILL         = registerBlock("drill", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    // Extraction Nodes
    public static final RegistryObject<Block> COAL_NODE     = registerBlock(new ExtractionNodeRegistry("coal", Items.COAL, "coal_block", "coal_ore"));
    public static final RegistryObject<Block> IRON_NODE     = registerBlock(new ExtractionNodeRegistry("iron", Items.RAW_IRON, "raw_iron_block", "iron_ore"));
    public static final RegistryObject<Block> COPPER_NODE   = registerBlock(new ExtractionNodeRegistry("copper", Items.RAW_COPPER, "raw_copper_block", "copper_ore"));
    public static final RegistryObject<Block> GOLD_NODE     = registerBlock(new ExtractionNodeRegistry("gold", Items.RAW_GOLD, "raw_gold_block", "gold_ore"));
    public static final RegistryObject<Block> REDSTONE_NODE = registerBlock(new ExtractionNodeRegistry("redstone", Items.REDSTONE_BLOCK, "redstone_block", "redstone_ore", 60));
    public static final RegistryObject<Block> SAND_NODE     = registerBlock(new ExtractionNodeRegistry("sand", Items.SAND, "sand", "sand"));

    /* REGISTRATION METHODS */
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blockRegistry = BLOCKS.register(name, block);
        registerBlockItem(name, blockRegistry);

        return blockRegistry;
    }

    private static RegistryObject<Block> registerBlock(IRegistry<Block> registry)
    {
        RegistryObject<Block> blockRegistry = registry.register();
        BLOCKS_REGISTRIES.put(blockRegistry, registry);
        return blockRegistry;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
