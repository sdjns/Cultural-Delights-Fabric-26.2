package com.baisylia.culturaldelights.block;

import com.baisylia.culturaldelights.ModRegistries;
import com.baisylia.culturaldelights.block.custom.*;
import com.baisylia.culturaldelights.item.ModItems;
import com.baisylia.culturaldelights.world.ModTreeGrowers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

/**
 * All blocks of the mod. Ported from the NeoForge 1.21.1 sources to Fabric 26.2.
 */
public final class ModBlocks {
	public static final Block WILD_CUCUMBERS = ModRegistries.registerWithItem("wild_cucumbers",
			WildCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).noOcclusion());

	public static final Block WILD_CORN = ModRegistries.registerWithItem("wild_corn",
			WildCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).noOcclusion());

	public static final Block WILD_EGGPLANTS = ModRegistries.registerWithItem("wild_eggplants",
			WildCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).noOcclusion());

	public static final Block AVOCADO_PIT = ModRegistries.registerWithItem("avocado_pit",
			p -> new AvocadoPitBlock(ModTreeGrowers.AVOCADO, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

	public static final Block AVOCADO_SAPLING = ModRegistries.registerWithItem("avocado_sapling",
			p -> new SaplingBlock(ModTreeGrowers.AVOCADO, p), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));

	public static final Block AVOCADO_LOG = ModRegistries.registerWithItem("avocado_log",
			ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LOG));

	public static final Block AVOCADO_WOOD = ModRegistries.registerWithItem("avocado_wood",
			ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_WOOD));

	public static final Block AVOCADO_LEAVES = ModRegistries.registerWithItem("avocado_leaves",
			AvocadoLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES));

	public static final Block FRUITING_AVOCADO_LEAVES = ModRegistries.registerWithItem("fruiting_avocado_leaves",
			FruitingLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES));

	public static final Block CUCUMBERS = ModRegistries.registerBlock("cucumbers",
			CucumbersBlock::new, cropProperties());

	public static final Block EGGPLANTS = ModRegistries.registerBlock("eggplants",
			EggplantsBlock::new, cropProperties());

	public static final Block CORN = ModRegistries.registerBlock("corn",
			CornBlock::new, cropProperties());

	public static final Block CORN_UPPER = ModRegistries.registerBlock("corn_upper",
			CornUpperBlock::new, cropProperties());

	public static final Block AVOCADO_CRATE = ModRegistries.registerWithItem("avocado_crate",
			Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

	public static final Block CUCUMBER_CRATE = ModRegistries.registerWithItem("cucumber_crate",
			Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

	public static final Block PICKLE_CRATE = ModRegistries.registerWithItem("pickle_crate",
			Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

	public static final Block CORN_COB_CRATE = ModRegistries.registerWithItem("corn_cob_crate",
			Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

	public static final Block EGGPLANT_CRATE = ModRegistries.registerWithItem("eggplant_crate",
			Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

	public static final Block WHITE_EGGPLANT_CRATE = ModRegistries.registerWithItem("white_eggplant_crate",
			Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

	public static final Block EXOTIC_ROLL_MEDLEY = ModRegistries.registerWithItem("exotic_roll_medley",
			p -> new SimpleFeastBlock(p, 8, () -> ModItems.TROPICAL_ROLL),
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion());

	public static final Block EGGPLANT_PARMESAN_BLOCK = ModRegistries.registerWithItem("eggplant_parmesan_block",
			p -> new SimpleFeastBlock(p, 4, () -> ModItems.EGGPLANT_PARMESAN),
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion());

	private static BlockBehaviour.Properties cropProperties() {
		return BlockBehaviour.Properties.of()
				.noCollision()
				.randomTicks()
				.instabreak()
				.noOcclusion()
				.sound(SoundType.CROP)
				.pushReaction(PushReaction.DESTROY);
	}

	private ModBlocks() {
	}

	public static void register() {
		// Class loading performs the registration.
	}
}
