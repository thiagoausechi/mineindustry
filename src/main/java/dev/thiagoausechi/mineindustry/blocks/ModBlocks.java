package dev.thiagoausechi.mineindustry.blocks;

import dev.thiagoausechi.mineindustry.References;
import dev.thiagoausechi.mineindustry.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.MOD_ID);
    public static final ArrayList<RegistryObject<? extends Block>> DATA_GEN = new ArrayList<>();

    public static final RegistryObject<Block> DRILL = registerBlock("drill", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)), false);

    /* REGISTRATION METHODS */
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, true);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Boolean simpleBlockGen) {
        RegistryObject<T> blockRegistry = BLOCKS.register(name, block);
        registerBlockItem(name, blockRegistry);

        if (simpleBlockGen)
            DATA_GEN.add(blockRegistry);

        return blockRegistry;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
