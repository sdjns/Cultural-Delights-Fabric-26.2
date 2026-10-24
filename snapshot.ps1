# 一键备份：把源码 + 文档 + 已构建的 jar 打包到 ..\backups\
# 用法：双击 一键备份.cmd，或 powershell -ExecutionPolicy Bypass -File snapshot.ps1
param([string]$Note = "")

$ErrorActionPreference = "Stop"
$project = $PSScriptRoot
$parent = Split-Path $project -Parent
$backupDir = Join-Path $parent "backups"
New-Item -ItemType Directory -Force -Path $backupDir | Out-Null

# 版本号取自 gradle.properties
$version = "unknown"
$props = Join-Path $project "gradle.properties"
if (Test-Path $props) {
    $m = Select-String -Path $props -Pattern "^mod_version=(.+)$"
    if ($m) { $version = $m.Matches[0].Groups[1].Value.Trim() }
}

$stamp = Get-Date -Format "yyyyMMdd-HHmmss"
$staging = Join-Path $env:TEMP ("cd-snapshot-" + $stamp)
New-Item -ItemType Directory -Force -Path $staging | Out-Null

Write-Host "复制源码（排除 build/.gradle/run）..." -ForegroundColor Cyan
& robocopy.exe $project $staging /E /XD "$project\build" "$project\.gradle" "$project\run" /XF "*.log" /NFL /NDL /NJH /NJS /NP | Out-Null
if ($LASTEXITCODE -ge 8) { throw "robocopy 失败，退出码 $LASTEXITCODE" }

# 带上已构建的 jar
$dist = Join-Path $staging "dist"
New-Item -ItemType Directory -Force -Path $dist | Out-Null
$jar = Get-ChildItem (Join-Path $project "build\libs\*.jar") -ErrorAction SilentlyContinue |
    Where-Object { $_.Name -notlike "*sources*" } | Select-Object -First 1
if ($jar) { Copy-Item $jar.FullName $dist }

# 环境信息，方便换电脑恢复
$javaVersion = "未检测到 java"
try { $javaVersion = ((& java -version 2>&1) -join " ").Trim() } catch { }
$info = @(
    "Cultural Delights — Fabric 26.2 移植版",
    "备份时间: $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')",
    "版本号: $version",
    "备注: $Note",
    "",
    "运行环境:",
    "  Java: $javaVersion",
    "  Gradle: 9.7.1（项目自带 gradlew）",
    "  Minecraft: 26.2 (Fabric, Loader 0.19.5, API 0.161.0+26.2)",
    "  Loom: 1.18.4",
    "",
    "恢复方法:",
    "  1. 安装 JDK 25",
    "  2. 解压本压缩包",
    "  3. 在项目目录运行 gradlew.bat build",
    "  4. 产物在 build/libs/"
)
Set-Content -Path (Join-Path $staging "环境信息.txt") -Value $info -Encoding UTF8

$zip = Join-Path $backupDir ("cultural-delights-" + $version + "-" + $stamp + ".zip")
if (Test-Path $zip) { Remove-Item $zip -Force }
& tar.exe -a -c -f $zip -C $staging .
Remove-Item -Recurse -Force $staging

# 只保留最近 10 份
$old = Get-ChildItem $backupDir -Filter "cultural-delights-*.zip" | Sort-Object LastWriteTime -Descending | Select-Object -Skip 10
$old | Remove-Item -Force

$size = [math]::Round((Get-Item $zip).Length / 1MB, 2)
Write-Host ""
Write-Host "备份完成: $zip" -ForegroundColor Green
Write-Host "大小: $size MB" -ForegroundColor Green
Write-Host "备份目录: $backupDir" -ForegroundColor Green
