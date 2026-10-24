package com.baisylia.culturaldelights.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AvocadoPitBlock extends SaplingBlock {
	private static final VoxelShape SHAPE_PIT = Block.box(6, 0, 6, 10, 3, 10);

	public AvocadoPitBlock(TreeGrower treeGrower, BlockBehaviour.Properties properties) {
		super(treeGrower, properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_PIT;
	}
}
