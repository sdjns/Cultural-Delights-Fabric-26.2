# 移植笔记：1.21.1 / NeoForge → 26.2 / Fabric

记录这次移植中实际踩到的 API 变化，方便后续维护。

## 平台层（NeoForge → Fabric）

| 1.21.1 NeoForge | 26.2 Fabric |
| --- | --- |
| `DeferredRegister` / `DeferredBlock` / `DeferredItem` | `Registry.register(BuiltInRegistries.X, ResourceKey, value)` |
| `@Mod` 构造器 + `IEventBus` | `ModInitializer#onInitialize` |
| `ModConfig` | 暂无（原 Config 只有两个开关，未移植） |
| `BuildCreativeModeTabContentsEvent` | `FabricCreativeModeTab.builder().displayItems(...)` |
| `BiomeModifiers.AddFeaturesBiomeModifier`（数据包） | `BiomeModifications.addFeature(BiomeSelectors, step, placedKey)` |
| `DatapackBuiltinEntriesProvider` | 直接提供 `data/<ns>/worldgen/*.json` |
| `CommonHooks.canCropGrow` / `fireCropGrowPost` | 无对应事件，直接走 `randomTick` |
| `ItemAbility` + `getToolModifiedState`（斧头剥皮） | 未移植（Fabric 侧可用 `StrippableBlockRegistry`） |
| `isFlammable/getFlammability` | 26.2 改为数据驱动，未移植 |

## 26.2 本体 API 变化

- `ResourceLocation` → **`Identifier`**（包名 `net.minecraft.resources`）。
- `ResourceLocation.fromNamespaceAndPath` → `Identifier.fromNamespaceAndPath`。
- 方块/物品注册需要先 `Properties.setId(ResourceKey)`，否则 id 为空。
- `BlockItem` 的本地化键：`Item.Properties.useBlockDescriptionPrefix()`；种子这类用 `item.` 前缀（默认）。
- `Item.BY_BLOCK` 需要手动 `put`（否则 `Block.asItem()` 找不到物品）。
- 食物：`FoodProperties` 变成 `record(nutrition, saturation, canAlwaysEat)`，`FoodProperties.Builder` 仍可用（`nutrition`/`saturationModifier`/`alwaysEdible`），但**没有 effect/fast**；碗类改用 `Item.Properties.usingConvertsTo(Items.BOWL)`。
- `BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)` 会**连随机刻判定一起复制**，复制到自定义 `AGE` 的作物上会在构造时崩溃（`Cannot get property age`）。自定义状态属性的方块要用 `Properties.of()` 显式配置。
- `BlockBehaviour.Properties.noCollission()` → `noCollision()`。
- `LeavesBlock` 变成抽象类：构造器 `(float leafParticleChance, Properties)`，需实现 `spawnFallingLeavesParticle` 与 `codec()`。
- `Block#codec()` 的返回类型必须与父类完全一致（`MapCodec<BushBlock>`，不能用 `? extends`）。
- `CropBlock` 不再有 `getBaseSeedId`/`getPlant`（掉落交给战利品表），`getShape` 为 `protected`。
- 方块交互：`useWithoutItem` 返回 `InteractionResult`（`ItemInteractionResult` 已删除），`useItemOn` 签名含 `ItemStack`。
- `Level#random` 字段受保护 → `level.getRandom()`；`Level#isClientSide` → `isClientSide()`。
- `getAnalogOutputSignal` 增加 `Direction` 参数。
- `DirectionProperty` 已移除 → `EnumProperty<Direction>`（`BlockStateProperties.HORIZONTAL_FACING` 即是）。
- 原版 `minecraft:random_patch` 特性被移除 → 用 `minecraft:simple_block` + 放置修饰符（`count`/`in_square`/`heightmap`/`block_predicate_filter(would_survive)`）。
- 配方 JSON：材料从 `{"item": "x"}` / `{"tag": "t"}` 改成 **字符串 `"x"` / `"#t"`**；产物仍是 `{"id": "...", "count": n}`。
- 战利品表：NeoForge 的 `neoforge:can_item_perform_ability` 条件不存在，需改写或删除。
- 标签/模型格式大致不变；物品模型需要额外的 `assets/<ns>/items/<id>.json`（`{"model":{"type":"minecraft:model","model":"..."}}`）。
- 方块渲染层不再由 `ItemBlockRenderTypes` 指定，26.2 由模型自动判断。

## 构建环境注意事项

- 需要 JDK 25；Loom 1.18.4 + Gradle 9.7.1。
- 26.2 的 jar 未混淆（Mojang 官方名），构建脚本里**不需要** `mappings` 依赖。
- 必须启用 `loom { splitEnvironmentSourceSets() }`：否则 Loom 生成的 merged jar 只有 22 字节（空 zip），编译时找不到 `net.minecraft.*`。
