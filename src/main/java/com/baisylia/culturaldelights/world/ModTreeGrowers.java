package com.baisylia.culturaldelights.world;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public final class ModTreeGrowers {
	public static final TreeGrower AVOCADO = new TreeGrower(
			"culturaldelights:avocado",
			Optional.empty(),
			Optional.of(ModWorldGen.AVOCADO_KEY),
			Optional.empty());

	private ModTreeGrowers() {
	}
}
