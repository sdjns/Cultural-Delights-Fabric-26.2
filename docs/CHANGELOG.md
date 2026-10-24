# 更新日志

格式参考 [Keep a Changelog](https://keepachangelog.com/zh-CN/1.1.0/)，版本号遵循语义化版本。

## [0.17.8+26.2] - 2026-10-24

### 新增
- 从 1.21.1 / NeoForge 移植到 **26.2 / Fabric**：
  - 21 个方块、66 个物品、创造模式标签页
  - 鳄梨树与野生作物世界生成（8 个 worldgen JSON + Fabric 群系注入）
  - 26.2 格式的物品模型定义（`assets/culturaldelights/items/*.json`）
- `docs/维护手册.md`、`一键备份.cmd`、`snapshot.ps1`

### 变更
- Farmer's Delight 相关依赖改为自包含实现（`SimpleFeastBlock`、`WildCropBlock`）
- 鱿鱼掉落改为「原版墨囊 + 模组生鱿鱼」双池；发光鱿鱼同理保留荧光墨囊

### 移除 / 暂缓
- FD 的刀具切割、厨锅、发酵桶配方（备份在 `fd_recipe_backup/`）
- FD 的「滋养」等药水效果（26.2 改为 `Consumable` 数据组件）

### 已知问题
- 客户端画面未实机验证
- 盛宴方块为简化实现（无盘子掉落等细节）
