package com.baisylia.culturaldelights.block.custom;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Log/wood block. Flammability is data driven in 26.2, see data/minecraft/tags/block.
 */
public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
	public ModFlammableRotatedPillarBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}
}
