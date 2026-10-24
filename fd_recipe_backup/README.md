# Farmer's Delight 依赖配方备份

这些配方在 1.21.1 版本里使用 Farmer's Delight / Brewin' and Chewin 的配方类型或物品
（`farmersdelight:cooking`、`farmersdelight:cutting`、`brewinandchewin:fermenting`、
`farmersdelight:knife` 等），在 26.2 上暂时无法生效，因此没有放进 `src/main/resources`。

等 Farmer's Delight 发布 26.2 版本后，把本目录内容拷回：

```
fd_recipe_backup/culturalrecipes/recipe/  ->  src/main/resources/data/culturalrecipes/recipe/
fd_recipe_backup/farmersdelight/          ->  src/main/resources/data/farmersdelight/
```

并把 `SimpleFeastBlock` / `WildCropBlock` 换回 FD 的 `FeastBlock` / `WildCropBlock` 即可恢复完整联动。
