package dev.thiagoausechi.mineindustry.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        // This is an example of usage. I may create the Raw Iron Dust Block, but I'm not sure about balancing
        // nineBlockStorageRecipes(consumer, RecipeCategory.MISC, ModItems.RAW_IRON_DUST.get(),  ModBlocks.RAW_IRON_DUST_BLOCK.get());
    }
}
