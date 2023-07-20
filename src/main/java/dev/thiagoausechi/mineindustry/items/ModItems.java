package dev.thiagoausechi.mineindustry.items;

import dev.thiagoausechi.mineindustry.References;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// @formatter:off
public class ModItems {
    public static final DeferredRegister<Item> ITEMS         = DeferredRegister.create(ForgeRegistries.ITEMS, References.MOD_ID);

    public static final RegistryObject<Item> WRENCH          = ITEMS.register("wrench", () -> new WrenchItem(new Item.Properties()));

    public static final RegistryObject<Item> RAW_IRON_DUST   = ITEMS.register("raw_iron_dust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_COPPER_DUST = ITEMS.register("raw_copper_dust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_GOLD_DUST   = ITEMS.register("raw_gold_dust", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
