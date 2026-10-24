# Farmer's Delight 配方原始备份（1.21.1 格式）

⚠️ 这里的 JSON 是**1.21.1 / NeoForge 时代的原始格式**，仅作历史参考。

从 **0.18.0+26.2** 起，32 条配方已按《农夫乐事：重织》26.2 的格式改写并放回：

```
src/main/resources/data/culturalrecipes/recipe/{cooking,cutting,smelting,...}
src/main/resources/data/farmersdelight/recipe/...
```

它们都带：

```json
"fabric:load_conditions": [
  { "condition": "fabric:all_mods_loaded", "values": ["farmersdelight"] }
]
```

所以没装 FD 时会被跳过，不会报错。

唯一没有启用的：`fermenting/pickle.json` —— 它依赖 Brewin' and Chewin，该模组暂无 26.2 版本。
