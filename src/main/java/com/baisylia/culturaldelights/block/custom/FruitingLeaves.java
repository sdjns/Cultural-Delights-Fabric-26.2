package com.baisylia.culturaldelights.block.custom;

import com.baisylia.culturaldelights.item.ModItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

/**
 * Avocado leaves that grow fruit once they reach {@link #MAX_AGE}.
 */
public class FruitingLeaves extends LeavesBlock implements BonemealableBlock {
	public static final MapCodec<FruitingLeaves> CODEC = simpleCodec(FruitingLeaves::new);
	public static final int MAX_AGE = 4;
	public static final IntegerProperty AGE = BlockStateProperties.AGE_4;

	public FruitingLeaves(BlockBehaviour.Properties properties) {
		super(0.02F, properties);
		this.registerDefaultState(this.defaultBlockState()
				.setValue(AGE, 0)
				.setValue(DISTANCE, 7)
				.setValue(PERSISTENT, false)
				.setValue(WATERLOGGED, false));
	}

	@Override
	public MapCodec<? extends LeavesBlock> codec() {
		return CODEC;
	}

	@Override
	protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {
	}

	@Override
	protected boolean isRandomlyTicking(BlockState state) {
		return state.getValue(AGE) < MAX_AGE || state.getValue(DISTANCE) == 7 && !state.getValue(PERSISTENT);
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (this.decaying(state)) {
			dropResources(state, level, pos);
			level.removeBlock(pos, false);
			return;
		}

		int age = state.getValue(AGE);
		if (age < MAX_AGE) {
			BlockState grown = state.setValue(AGE, age + 1);
			level.setBlock(pos, grown, 2);
			level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(grown));
		}
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		int age = state.getValue(AGE);
		if (age == MAX_AGE) {
			int count = 1 + level.getRandom().nextInt(2) + 1;
			Block.popResource(level, pos, new ItemStack(ModItems.AVOCADO, count));
			level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
			BlockState picked = state.setValue(AGE, 1);
			level.setBlock(pos, picked, 2);
			level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, picked));
			return InteractionResult.SUCCESS;
		}
		return super.useWithoutItem(state, level, pos, player, hitResult);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, DISTANCE, PERSISTENT, WATERLOGGED);
	}

	@Override
	public boolean isValidBonemealTarget(net.minecraft.world.level.LevelReader level, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < MAX_AGE;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		level.setBlock(pos, state.setValue(AGE, Math.min(MAX_AGE, state.getValue(AGE) + 1)), 2);
	}
}
