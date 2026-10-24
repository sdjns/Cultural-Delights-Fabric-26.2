package com.baisylia.culturaldelights.block.custom;

import com.baisylia.culturaldelights.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Lower half of the two block tall corn plant.
 */
public class CornBlock extends BushBlock implements BonemealableBlock {
	public static final MapCodec<BushBlock> CODEC = simpleCodec(CornBlock::new);
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	public static final BooleanProperty SUPPORTING = BooleanProperty.create("supporting");
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)
	};

	public CornBlock(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0).setValue(SUPPORTING, false));
	}

	@Override
	public MapCodec<BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(AGE)];
	}

	public int getAge(BlockState state) {
		return state.getValue(AGE);
	}

	public int getMaxAge() {
		return 3;
	}

	public BlockState withAge(int age) {
		return this.defaultBlockState().setValue(AGE, age);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, SUPPORTING);
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (level.getRawBrightness(pos.above(), 0) >= 6) {
			int age = this.getAge(state);
			if (age <= this.getMaxAge() && random.nextInt(25) == 0) {
				if (age == this.getMaxAge()) {
					if (level.isEmptyBlock(pos.above()) && this.defaultBlockState().canSurvive(level, pos.above())) {
						level.setBlockAndUpdate(pos.above(), ModBlocks.CORN_UPPER.defaultBlockState());
					}
				} else {
					level.setBlock(pos, this.withAge(age + 1), 2);
				}
			}
		}
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return super.canSurvive(state, level, pos);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		BlockState upperState = level.getBlockState(pos.above());
		if (upperState.getBlock() instanceof CornUpperBlock) {
			return !((CornUpperBlock) upperState.getBlock()).isMaxAge(upperState);
		}
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		int ageGrowth = Math.min(this.getAge(state) + Mth.nextInt(level.getRandom(), 1, 4), 7);
		if (ageGrowth <= this.getMaxAge()) {
			level.setBlockAndUpdate(pos, state.setValue(AGE, ageGrowth));
			return;
		}

		BlockState top = level.getBlockState(pos.above());
		if (top.getBlock() == ModBlocks.CORN_UPPER) {
			if (top.getBlock() instanceof BonemealableBlock growable && growable.isValidBonemealTarget(level, pos.above(), top)) {
				growable.performBonemeal(level, level.getRandom(), pos.above(), top);
			}
		} else {
			int remainingGrowth = ageGrowth - this.getMaxAge() - 1;
			if (level.isEmptyBlock(pos.above()) && this.defaultBlockState().canSurvive(level, pos.above())) {
				level.setBlockAndUpdate(pos, state.setValue(AGE, this.getMaxAge()));
				level.setBlock(pos.above(), ModBlocks.CORN_UPPER.defaultBlockState().setValue(CornUpperBlock.CORN_AGE, remainingGrowth), 2);
			}
		}
	}
}
