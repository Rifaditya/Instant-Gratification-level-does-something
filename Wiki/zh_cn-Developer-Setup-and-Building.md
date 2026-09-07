🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 🛠️ 开发者环境配置与构建指南

> 📌 **代码仓库来源免责声明**：本维基文档反映了**代码仓库中的当前源码状态**，可能包含领先于 CurseForge 和 Modrinth 平台公开发布版本的最新未发布提交或开发中功能。

This unified guide explains workstation requirements, repository architecture, and commands for building all targets of **Level Does Something**.

---

## 💻 Workstation Prerequisites
- **Java Development Kit**: JDK 25 (`release = 25`).
- **Gradle**: 9.3+ (via `./gradlew` wrapper).
- **Fabric Loom**: 1.15+.
- **DasikLibrary Dependency**: `>= 1.8.38`.

---

## 📂 Repository Layout
```
Level Does Something/
├── Level Does Something v26.2/
│   └── level-does-something/        # Minecraft 26.2 Project Root
└── Level Does Something v26.3/
    └── level-does-something/        # Minecraft 26.3 Project Root
```

---

## 🔨 Build Commands

### Minecraft 26.2
```bash
cd "Level Does Something v26.2/level-does-something"
./gradlew build --no-daemon
```

### Minecraft 26.3
```bash
cd "Level Does Something v26.3/level-does-something"
./gradlew build --no-daemon
```

---

## ⚖️ 许可证与制作团队
* **Developer**: **Dasik (Rifaditya)**
* **License**: **GNU General Public License v3.0 (GPLv3)**
* **Repository**: [Instant-Gratification-level-does-something](https://github.com/Rifaditya/Instant-Gratification-level-does-something)

> 📌 **代码仓库来源免责声明**：本维基文档反映了**代码仓库中的当前源码状态**，可能包含领先于 CurseForge 和 Modrinth 平台公开发布版本的最新未发布提交或开发中功能。
