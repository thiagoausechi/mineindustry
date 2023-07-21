package dev.thiagoausechi.mineindustry.items;

import dev.thiagoausechi.mineindustry.References;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// @formatter:off
public class ModItems {

    public static final Map<RegistryObject<? extends Item>, String> DATA_GEN = new HashMap<>();
    public static final DeferredRegister<Item> ITEMS         = DeferredRegister.create(ForgeRegistries.ITEMS, References.MOD_ID);

    // Tools
    public static final RegistryObject<Item> WRENCH          = registerHandheldItem("wrench", () -> new WrenchItem(new Item.Properties().stacksTo(1)));

    // Material
    public static final RegistryObject<Item> RAW_IRON_DUST   = registerItem("raw_iron_dust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_COPPER_DUST = registerItem("raw_copper_dust", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_GOLD_DUST   = registerItem("raw_gold_dust", () -> new Item(new Item.Properties()));

    // Product
    public static final RegistryObject<Item> IRON_ROD        = registerItem("iron_rod", () -> new Item(new Item.Properties()));

    /* REGISTRATION METHODS */
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return registerItem(name, item, "item/generated");
    }

    private static <T extends Item> RegistryObject<T> registerHandheldItem(String name, Supplier<T> item) {
        return registerItem(name, item, "item/handheld");
    }

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item, String parentModel) {
        RegistryObject<T> itemRegistry = ITEMS.register(name, item);
        DATA_GEN.put(itemRegistry, parentModel);

        return itemRegistry;
    }
}
