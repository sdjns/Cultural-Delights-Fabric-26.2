package com.baisylia.culturaldelights;

import com.baisylia.culturaldelights.block.ModBlocks;
import com.baisylia.culturaldelights.item.ModItems;
import com.baisylia.culturaldelights.tab.ModCreativeModeTabs;
import com.baisylia.culturaldelights.world.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fabric entry point for Cultural Delights (ported from the 1.21.1 NeoForge sources).
 */
public class CulturalDelights implements ModInitializer {
	public static final String MOD_ID = "culturaldelights";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ModBlocks.register();
		ModItems.register();
		ModCreativeModeTabs.register();
		ModWorldGen.register();
		LOGGER.info("Cultural Delights loaded");
	}
}
