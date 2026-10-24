package com.baisylia.culturaldelights.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Simple cross-shaped wild plant. Farmer's Delight's WildCropBlock could not be used because
 * Farmer's Delight has no 26.2 release yet.
 */
public class WildCropBlock extends BushBlock {
	public static final MapCodec<BushBlock> CODEC = simpleCodec(WildCropBlock::new);
	private static final VoxelShape SHAPE = Block.column(10.0, 0.0, 13.0);

	public WildCropBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
