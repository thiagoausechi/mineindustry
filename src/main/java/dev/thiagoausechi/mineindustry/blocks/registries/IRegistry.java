package dev.thiagoausechi.mineindustry.blocks.registries;

import net.minecraftforge.registries.RegistryObject;

public interface IRegistry<T> {

    RegistryObject<T> register();
    
}
