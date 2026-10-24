package com.baisylia.culturaldelights.block.custom;

import com.baisylia.culturaldelights.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CornUpperBlock extends CropBlock {
	public static final MapCodec<CornUpperBlock> CODEC = simpleCodec(CornUpperBlock::new);
	public static final IntegerProperty CORN_AGE = BlockStateProperties.AGE_3;
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)
	};

	public CornUpperBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<? extends CropBlock> codec() {
		return CODEC;
	}

	@Override
	protected IntegerProperty getAgeProperty() {
		return CORN_AGE;
	}

	@Override
	public int getMaxAge() {
		return 3;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(CORN_AGE)];
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(CORN_AGE);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.getBlock() == ModBlocks.CORN;
	}

	@Override
	protected int getBonemealAgeIncrease(Level level) {
		return super.getBonemealAgeIncrease(level) / 3;
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return (level.getRawBrightness(pos, 0) >= 8 || level.canSeeSky(pos))
				&& level.getBlockState(pos.below()).getBlock() == ModBlocks.CORN;
	}
}
