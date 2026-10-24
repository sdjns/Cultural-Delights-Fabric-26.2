package com.baisylia.culturaldelights.world;

import com.baisylia.culturaldelights.CulturalDelights;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * World generation hooks. The features themselves live in data/culturaldelights/worldgen.
 */
public final class ModWorldGen {
	public static final ResourceKey<ConfiguredFeature<?, ?>> AVOCADO_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, CulturalDelights.id("avocado"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> CORN_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, CulturalDelights.id("corn"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> EGGPLANT_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, CulturalDelights.id("eggplant"));
	public static final ResourceKey<ConfiguredFeature<?, ?>> CUCUMBER_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, CulturalDelights.id("cucumber"));

	public static final ResourceKey<PlacedFeature> AVOCADO_PLACED = ResourceKey.create(Registries.PLACED_FEATURE, CulturalDelights.id("avocado_placed"));
	public static final ResourceKey<PlacedFeature> CORN_PLACED = ResourceKey.create(Registries.PLACED_FEATURE, CulturalDelights.id("corn_placed"));
	public static final ResourceKey<PlacedFeature> EGGPLANT_PLACED = ResourceKey.create(Registries.PLACED_FEATURE, CulturalDelights.id("eggplant_placed"));
	public static final ResourceKey<PlacedFeature> CUCUMBER_PLACED = ResourceKey.create(Registries.PLACED_FEATURE, CulturalDelights.id("cucumber_placed"));

	private ModWorldGen() {
	}

	public static void register() {
		BiomeModifications.addFeature(
				BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE),
				GenerationStep.Decoration.VEGETAL_DECORATION,
				AVOCADO_PLACED);

		BiomeModifications.addFeature(
				BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SAVANNA_PLATEAU),
				GenerationStep.Decoration.VEGETAL_DECORATION,
				CORN_PLACED);

		BiomeModifications.addFeature(
				BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE),
				GenerationStep.Decoration.VEGETAL_DECORATION,
				EGGPLANT_PLACED);

		BiomeModifications.addFeature(
				BiomeSelectors.includeByKey(Biomes.SWAMP, Biomes.MANGROVE_SWAMP),
				GenerationStep.Decoration.VEGETAL_DECORATION,
				CUCUMBER_PLACED);
	}
}
