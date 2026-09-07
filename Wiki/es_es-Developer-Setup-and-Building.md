🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 🛠️ Guía de Configuración y Compilación de Desarrollador

> 📌 **Descargo de responsabilidad de fuente de repositorio**: Esta documentación de wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes no publicadas o características en desarrollo antes de los lanzamientos públicos en CurseForge y Modrinth.

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

## ⚖️ Licencia y Créditos
* **Developer**: **Dasik (Rifaditya)**
* **License**: **GNU General Public License v3.0 (GPLv3)**
* **Repository**: [Instant-Gratification-level-does-something](https://github.com/Rifaditya/Instant-Gratification-level-does-something)

> 📌 **Descargo de responsabilidad de fuente de repositorio**: Esta documentación de wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes no publicadas o características en desarrollo antes de los lanzamientos públicos en CurseForge y Modrinth.
