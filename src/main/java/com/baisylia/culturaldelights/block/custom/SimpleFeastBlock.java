package com.baisylia.culturaldelights.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

/**
 * Simplified replacement for Farmer's Delight's FeastBlock: right clicking takes one serving.
 */
public class SimpleFeastBlock extends HorizontalDirectionalBlock {
	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 8);
	private static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0);

	private final int maxServings;
	private final Supplier<Item> servingItem;
	private MapCodec<SimpleFeastBlock> codec;

	public SimpleFeastBlock(BlockBehaviour.Properties properties, int maxServings, Supplier<Item> servingItem) {
		super(properties);
		this.maxServings = maxServings;
		this.servingItem = servingItem;
		this.codec = simpleCodec(p -> new SimpleFeastBlock(p, this.maxServings, this.servingItem));
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(SERVINGS, maxServings));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return this.codec;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, SERVINGS);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(SERVINGS, this.maxServings);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		int current = state.getValue(SERVINGS);
		if (current <= 0) {
			return InteractionResult.PASS;
		}

		if (!level.isClientSide()) {
			ItemStack stack = new ItemStack(this.servingItem.get());
			if (!player.getInventory().add(stack)) {
				player.drop(stack, false);
			}
			level.setBlock(pos, state.setValue(SERVINGS, current - 1), 3);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
		return Math.max(0, state.getValue(SERVINGS));
	}
}
