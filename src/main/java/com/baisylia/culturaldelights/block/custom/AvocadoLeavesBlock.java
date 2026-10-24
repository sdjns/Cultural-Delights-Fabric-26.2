package com.baisylia.culturaldelights.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AvocadoLeavesBlock extends LeavesBlock {
	public static final MapCodec<AvocadoLeavesBlock> CODEC = simpleCodec(AvocadoLeavesBlock::new);

	public AvocadoLeavesBlock(BlockBehaviour.Properties properties) {
		super(0.02F, properties);
	}

	@Override
	public MapCodec<? extends LeavesBlock> codec() {
		return CODEC;
	}

	@Override
	protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
		// No custom leaf particles; vanilla leaves do not fall either.
	}
}
