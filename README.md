# Cultural Delights — Fabric 26.2 移植版

这是 [Baisylia/Cultural-Delights-1.21](https://github.com/Baisylia/Cultural-Delights-1.21)（1.21.1，**NeoForge** 工程）向 **Minecraft 26.2 + Fabric** 的移植尝试。

> 说明：你提到的"fabric 模组"在仓库里其实是 NeoForge 模组（`net.neoforged.moddev`）。这次移植同时做了两件事：
> 1. 平台从 NeoForge 换到 Fabric；
> 2. 版本从 1.21.1 升到 26.2（跨了 1.21.2 / 1.21.4 / 1.21.5 / 1.21.9 / 26.1 等多个大版本）。

## 当前状态

| 项目 | 状态 |
| --- | --- |
| `gradle build` | ✅ 通过，产出 `build/libs/culturaldelights-0.17.8+26.2.jar` |
| 专用服务器启动（`gradle runServer`） | ✅ 通过，0 个 ERROR（配方/战利品表/标签/世界生成全部加载成功） |
| 与《农夫乐事：重织》26.2 同时启动 | ✅ 45 个模组共存，0 个 ERROR |
| 方块 / 物品注册 | ✅ 21 个方块、66 个物品（含 3 种种子、17 个方块物品） |
| 资源（贴图/模型/语言/物品模型定义） | ✅ 117 张材质、12 种语言、66 个 `items/*.json`（26.2 新格式） |
| 世界生成 | ✅ 鳄梨树（丛林类群系）、野生黄瓜/玉米/茄子（对应群系），共 8 个 feature JSON + Fabric 群系注入 |
| 配方 | ✅ 37 个通用配方 + 32 个 FD 联动配方（装 FD 时生效） |
| 鱿鱼掉落 | ✅ 保留原版墨囊（1–3，受抢夺影响）+ 模组的生鱿鱼/发光鱿鱼物品 |
| Farmer's Delight 联动 | ✅ 与《重织》26.2 共存 + 32 条切割/厨锅配方已启用（两种环境均 0 ERROR） |

## 与 Farmer's Delight（重织 / Refabricated）的关系

Cultural Delights 是 Farmer's Delight 的附属模组，1.21.1 源码里大量使用 FD 的 API（`FeastBlock`、`WildCropBlock`、`ModBlocks.CARROT_CRATE`、`FoodValues`、`ModEffects.NOURISHMENT`…）。

26.2 上已经有 **Farmer's Delight Refabricated（农夫乐事：重织）**：Modrinth 上的 `26.2-3.6.29+refabricated`，mod id 仍然是 `farmersdelight`。

本移植的现状：

- **可以同时安装运行**：用 26.2-3.6.29 实测，45 个模组一起启动，服务器 **0 个 ERROR**；
- 本模组**不硬依赖** FD，单独装也能玩；装了 FD 后 `fabric.mod.json` 里把它标为推荐依赖（`recommends`），不会强制；
- **切割板 / 厨锅联动已启用**：32 条 FD 配方已按重织版 26.2 格式改写完成（`tool` 用 `fabric:type` / `#c:tools/knife`、`sound` 改字符串、`c:foods/milk` → `c:drinks/milk`），并加了 `fabric:all_mods_loaded` 条件——没装 FD 时自动跳过，不报错。

| 原 FD 内容 | 本移植的处理 |
| --- | --- |
| `FeastBlock`（盛宴方块） | 自写 `SimpleFeastBlock`：右键取一份食物、`servings` 属性、红石信号；模型/方块状态沿用原资源 |
| `WildCropBlock`（野生作物） | 自写 `WildCropBlock extends BushBlock`，掉落物走战利品表 |
| `ModBlocks.CARROT_CRATE` 属性 | 改用 `Blocks.OAK_PLANKS` 的复制属性 |
| `FoodValues` / `ModEffects.NOURISHMENT` | 食物数值全部保留；FD 的"滋养"效果、河豚卷的负面效果、发光鱿鱼的发光效果**未保留**（26.2 效果改为 `Consumable` 组件，需要额外数据组件代码） |
| 刀具切割 / 厨锅 / 发酵桶配方 | **暂未启用**，原始 JSON 备份在 `fd_recipe_backup/`，需按重织版格式改写后再放回 |

> 关于鱿鱼：原版 1.21.1 模组直接把鱿鱼的掉落表整个替换成了 `culturaldelights:squid`，会吞掉墨囊。
> 本移植在掉落表里**保留了两个池**：原版墨囊池（`ink_sac` 1–3，抢夺加成，与原版 26.2 完全一致）
> 和模组的生鱿鱼池；发光鱿鱼同理，保留 `glow_ink_sac`。

## 目录结构

```
cd-26.2/
├── build.gradle                # Loom 1.18.4 / Java 25 / Fabric API 0.161.0+26.2
├── gradle.properties
├── src/main/java/com/baisylia/culturaldelights/
│   ├── CulturalDelights.java   # ModInitializer
│   ├── ModRegistries.java      # 取代 DeferredRegister 的注册辅助
│   ├── block/ModBlocks.java    # 21 个方块
│   ├── block/custom/           # 作物、玉米、鳄梨叶、盛宴方块…
│   ├── item/ModItems.java      # 46 个物品 + 种子/方块物品
│   ├── item/ModFoods.java      # 食物数值
│   ├── tab/ModCreativeModeTabs.java
│   └── world/ModWorldGen.java  # Fabric BiomeModifications
├── src/main/resources/
│   ├── assets/culturaldelights/…  （含 26.2 的 items/*.json）
│   └── data/…                     （战利品表、配方、标签、worldgen）
└── fd_recipe_backup/           # 未启用的 FD / B&C 配方备份
```

## 构建与运行

需要 **JDK 25**（26.2 要求 Java 25）：

```bash
./gradlew build        # 产出 build/libs/culturaldelights-0.17.8+26.2.jar
./gradlew runServer    # 起一个开发用服务器（run/ 目录，需先放 eula.txt）
./gradlew runClient    # 起客户端（需要图形环境）
```

依赖版本（`gradle.properties`）：

- Minecraft 26.2，Fabric Loader 0.19.5，Fabric API 0.161.0+26.2
- Loom 1.18.4，Gradle 9.7.1

## 已知差异 / 后续工作

1. **FD 发酵桶配方**：`fermenting/pickle.json` 依赖 Brewin' and Chewin，该模组暂无 26.2 版本，暂未启用。
2. **效果组件**：26.2 的食物效果通过 `DataComponents.CONSUMABLE` 的 `Consumable` 提供，可按需补回"滋养"等效果。
3. **盛宴方块**：当前是简化版（无盘子掉落、无比较器细节），可继续补齐。
4. **客户端渲染**：26.2 的方块渲染层由模型自动推断，旧代码里的 `ItemBlockRenderTypes.setRenderLayer` 已删除；未在客户端实机验证画面。
5. **未移植**：FD 刀具/切割板、厨锅、发酵桶配方，苹果皮/JEI 联动，以及 NeoForge 的数据包（堆肥/燃料）→ 26.2 下分别对应 Fabric 的 `CompostingChanceRegistry`/`FuelValueRegistry`，可另行补充。
