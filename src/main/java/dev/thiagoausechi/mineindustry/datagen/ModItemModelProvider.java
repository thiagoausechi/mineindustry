package dev.thiagoausechi.mineindustry.datagen;

import dev.thiagoausechi.mineindustry.References;
import dev.thiagoausechi.mineindustry.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, References.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.DATA_GEN.forEach(this::withParent);
    }

    private ItemModelBuilder withParent(RegistryObject<? extends Item> item, String parent) {
        String itemPath = item.getId().getPath();
        return withExistingParent(itemPath,
                new ResourceLocation(parent)).texture("layer0",
                new ResourceLocation(References.MOD_ID, "item/" + itemPath));
    }
}
