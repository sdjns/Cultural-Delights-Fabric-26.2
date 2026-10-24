package com.baisylia.culturaldelights;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

/**
 * Small registration helper replacing NeoForge's DeferredRegister.
 */
public final class ModRegistries {
	private ModRegistries() {
	}

	public static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
		ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, CulturalDelights.id(name));
		Block block = factory.apply(properties.setId(key));
		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

	public static Block registerWithItem(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
		Block block = registerBlock(name, factory, properties);
		registerBlockItem(name, block);
		return block;
	}

	public static Item registerBlockItem(String name, Block block) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, CulturalDelights.id(name));
		Item item = new BlockItem(block, new Item.Properties().setId(key).useBlockDescriptionPrefix());
		Registry.register(BuiltInRegistries.ITEM, key, item);
		Item.BY_BLOCK.put(block, item);
		return item;
	}

	/** Block item whose name key uses the item namespace, e.g. seeds. */
	public static Item registerSeedItem(String name, Block block) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, CulturalDelights.id(name));
		Item item = new BlockItem(block, new Item.Properties().setId(key));
		Registry.register(BuiltInRegistries.ITEM, key, item);
		Item.BY_BLOCK.put(block, item);
		return item;
	}

	public static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, CulturalDelights.id(name));
		Item item = factory.apply(properties.setId(key));
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	public static Item simpleItem(String name, Item.Properties properties) {
		return registerItem(name, Item::new, properties);
	}
}
