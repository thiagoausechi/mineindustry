package dev.thiagoausechi.mineindustry.blocks.registries;

import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.registries.RegistryObject;

public interface IRegistry<T> {

    RegistryObject<T> register();

    void registerStatesAndModels(BlockStateProvider provider, RegistryObject<T> registryObject);
}
