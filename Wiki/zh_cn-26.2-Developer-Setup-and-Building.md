🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 开发者配置与工具链指南 (MC 26.2)

> 📌 **代码仓库来源免责声明**：本维基文档反映了**代码仓库中的当前源码状态**，可能包含领先于 CurseForge 和 Modrinth 平台公开发布版本的最新未发布提交或开发中功能。

---

## 🛠️ Workstation Setup for Minecraft 26.2

### Subproject Location
The source code for Minecraft 26.2 resides at:
`Level Does Something v26.2/level-does-something`

### Toolchain Dependencies
```properties
minecraft_version=26.2
parchment_minecraft_version=26.2
parchment_version=2026.02.15

fabric_version=0.149.0+26.2
fabric_loader_version=0.19.3
dasik_library_version=1.8.38
```

---

## 🔨 Build & Run Commands

```powershell
cd "Level Does Something v26.2/level-does-something"
./gradlew build --no-daemon
./gradlew runClient
```

---

## 🔗 Related Documentation
* [[返回 Minecraft 26.2 门户|zh_cn-26.2-Home]]
* [[26.2 架构设计与 Mixin 解析|zh_cn-26.2-Architecture-and-Mixins]]
* [[版本兼容性矩阵|zh_cn-Version-Compatibility]]
* [[返回中央门户|zh_cn-Home]]
