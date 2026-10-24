package com.baisylia.culturaldelights.item;

import com.baisylia.culturaldelights.ModRegistries;
import com.baisylia.culturaldelights.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * All items of the mod, registered on the Fabric item registry.
 */
public final class ModItems {
	public static final Item CUCUMBER_SEEDS = ModRegistries.registerSeedItem("cucumber_seeds", ModBlocks.CUCUMBERS);
	public static final Item CORN_KERNELS = ModRegistries.registerSeedItem("corn_kernels", ModBlocks.CORN);
	public static final Item EGGPLANT_SEEDS = ModRegistries.registerSeedItem("eggplant_seeds", ModBlocks.EGGPLANTS);

	public static final Item AVOCADO = ModRegistries.simpleItem("avocado", new Item.Properties().food(ModFoods.AVOCADO));
	public static final Item CUT_AVOCADO = ModRegistries.simpleItem("cut_avocado", new Item.Properties().food(ModFoods.CUT_AVOCADO));
	public static final Item CUCUMBER = ModRegistries.simpleItem("cucumber", new Item.Properties().food(ModFoods.CUCUMBER));
	public static final Item PICKLE = ModRegistries.simpleItem("pickle", new Item.Properties().food(ModFoods.PICKLE));
	public static final Item CUT_CUCUMBER = ModRegistries.simpleItem("cut_cucumber", new Item.Properties().food(ModFoods.CUT_CUCUMBER));
	public static final Item CUT_PICKLE = ModRegistries.simpleItem("cut_pickle", new Item.Properties().food(ModFoods.CUT_PICKLE));
	public static final Item EGGPLANT = ModRegistries.simpleItem("eggplant", new Item.Properties().food(ModFoods.EGGPLANT));
	public static final Item CUT_EGGPLANT = ModRegistries.simpleItem("cut_eggplant", new Item.Properties().food(ModFoods.CUT_EGGPLANT));
	public static final Item SMOKED_EGGPLANT = ModRegistries.simpleItem("smoked_eggplant", new Item.Properties().food(ModFoods.SMOKED_EGGPLANT));
	public static final Item SMOKED_TOMATO = ModRegistries.simpleItem("smoked_tomato", new Item.Properties().food(ModFoods.SMOKED_TOMATO));
	public static final Item SMOKED_CUT_EGGPLANT = ModRegistries.simpleItem("smoked_cut_eggplant", new Item.Properties().food(ModFoods.SMOKED_CUT_EGGPLANT));
	public static final Item SMOKED_WHITE_EGGPLANT = ModRegistries.simpleItem("smoked_white_eggplant", new Item.Properties().food(ModFoods.SMOKED_WHITE_EGGPLANT));
	public static final Item WHITE_EGGPLANT = ModRegistries.simpleItem("white_eggplant", new Item.Properties().food(ModFoods.WHITE_EGGPLANT));
	public static final Item CORN_COB = ModRegistries.simpleItem("corn_cob", new Item.Properties().food(ModFoods.CORN_COB));
	public static final Item SQUID = ModRegistries.simpleItem("squid", new Item.Properties().food(ModFoods.SQUID));
	public static final Item COOKED_SQUID = ModRegistries.simpleItem("cooked_squid", new Item.Properties().food(ModFoods.COOKED_SQUID));
	public static final Item GLOW_SQUID = ModRegistries.simpleItem("glow_squid", new Item.Properties().food(ModFoods.GLOW_SQUID));
	public static final Item RAW_CALAMARI = ModRegistries.simpleItem("raw_calamari", new Item.Properties().food(ModFoods.RAW_CALAMARI));
	public static final Item COOKED_CALAMARI = ModRegistries.simpleItem("cooked_calamari", new Item.Properties().food(ModFoods.COOKED_CALAMARI));
	public static final Item POPCORN = ModRegistries.simpleItem("popcorn", new Item.Properties().food(ModFoods.POPCORN));
	public static final Item CORN_DOUGH = ModRegistries.simpleItem("corn_dough", new Item.Properties().food(ModFoods.CORN_DOUGH));
	public static final Item TORTILLA = ModRegistries.simpleItem("tortilla", new Item.Properties().food(ModFoods.TORTILLA));
	public static final Item TORTILLA_CHIPS = ModRegistries.simpleItem("tortilla_chips", new Item.Properties().food(ModFoods.TORTILLA_CHIPS));
	public static final Item ELOTE = ModRegistries.simpleItem("elote", new Item.Properties().food(ModFoods.ELOTE));
	public static final Item EMPANADA = ModRegistries.simpleItem("empanada", new Item.Properties().food(ModFoods.EMPANADA));
	public static final Item HEARTY_SALAD = ModRegistries.simpleItem("hearty_salad", new Item.Properties().stacksTo(16).food(ModFoods.HEARTY_SALAD).usingConvertsTo(Items.BOWL));
	public static final Item BEEF_BURRITO = ModRegistries.simpleItem("beef_burrito", new Item.Properties().food(ModFoods.BEEF_BURRITO));
	public static final Item MUTTON_SANDWICH = ModRegistries.simpleItem("mutton_sandwich", new Item.Properties().food(ModFoods.MUTTON_SANDWICH));
	public static final Item EGGPLANT_PARMESAN = ModRegistries.simpleItem("eggplant_parmesan", new Item.Properties().stacksTo(16).food(ModFoods.EGGPLANT_PARMESAN).usingConvertsTo(Items.BOWL));
	public static final Item POACHED_EGGPLANTS = ModRegistries.simpleItem("poached_eggplants", new Item.Properties().stacksTo(16).food(ModFoods.POACHED_EGGPLANTS).usingConvertsTo(Items.BOWL));
	public static final Item EGGPLANT_BURGER = ModRegistries.simpleItem("eggplant_burger", new Item.Properties().food(ModFoods.EGGPLANT_BURGER));
	public static final Item AVOCADO_TOAST = ModRegistries.simpleItem("avocado_toast", new Item.Properties().food(ModFoods.AVOCADO_TOAST));
	public static final Item CREAMED_CORN = ModRegistries.simpleItem("creamed_corn", new Item.Properties().stacksTo(16).food(ModFoods.CREAMED_CORN).usingConvertsTo(Items.BOWL));
	public static final Item CHICKEN_TACO = ModRegistries.simpleItem("chicken_taco", new Item.Properties().food(ModFoods.CHICKEN_TACO));
	public static final Item SPICY_CURRY = ModRegistries.simpleItem("spicy_curry", new Item.Properties().stacksTo(16).food(ModFoods.SPICY_CURRY).usingConvertsTo(Items.BOWL));
	public static final Item PORK_WRAP = ModRegistries.simpleItem("pork_wrap", new Item.Properties().food(ModFoods.PORK_WRAP));
	public static final Item FISH_TACO = ModRegistries.simpleItem("fish_taco", new Item.Properties().food(ModFoods.FISH_TACO));
	public static final Item MIDORI_ROLL = ModRegistries.simpleItem("midori_roll", new Item.Properties().food(ModFoods.MIDORI_ROLL));
	public static final Item MIDORI_ROLL_SLICE = ModRegistries.simpleItem("midori_roll_slice", new Item.Properties().food(ModFoods.MIDORI_ROLL_SLICE));
	public static final Item EGG_ROLL = ModRegistries.simpleItem("egg_roll", new Item.Properties().food(ModFoods.EGG_ROLL));
	public static final Item CHICKEN_ROLL = ModRegistries.simpleItem("chicken_roll", new Item.Properties().food(ModFoods.CHICKEN_ROLL));
	public static final Item CHICKEN_ROLL_SLICE = ModRegistries.simpleItem("chicken_roll_slice", new Item.Properties().food(ModFoods.CHICKEN_ROLL_SLICE));
	public static final Item PUFFERFISH_ROLL = ModRegistries.simpleItem("pufferfish_roll", new Item.Properties().food(ModFoods.PUFFERFISH_ROLL));
	public static final Item TROPICAL_ROLL = ModRegistries.simpleItem("tropical_roll", new Item.Properties().food(ModFoods.TROPICAL_ROLL));
	public static final Item RICE_BALL = ModRegistries.simpleItem("rice_ball", new Item.Properties().food(ModFoods.RICE_BALL));
	public static final Item CALAMARI_ROLL = ModRegistries.simpleItem("calamari_roll", new Item.Properties().food(ModFoods.CALAMARI_ROLL));

	private ModItems() {
	}

	public static void register() {
		// Class loading performs the registration.
	}
}
