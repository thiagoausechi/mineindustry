package dev.thiagoausechi.mineindustry.items;

import dev.thiagoausechi.mineindustry.References;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, References.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MINEINDUSTRY_TAB = CREATIVE_MODE_TABS.register("mineindustry",
            () -> CreativeModeTab.builder()
                    .icon(Items.REDSTONE::getDefaultInstance)
                    .title(Component.translatable("creativetab.mineindustry_tab"))
                    .displayItems((parameters, output) -> {
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
