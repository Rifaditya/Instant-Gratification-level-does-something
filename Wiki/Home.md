# Level Does Something — Official Documentation Portal

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 Multi-Version Selection Portal

Select your targeted Minecraft version to access version-isolated mechanics, formulas, GameRule documentation, and architecture:

| Minecraft Version | Release Channel | Build Artifact | Quick Navigation |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | Modern Standard | `level-does-something-1.0.5+26.2.jar` | [[👉 Enter MC 26.2 Wiki|26.2-Home]] |
| **Minecraft 26.3** | Modern Lead | `level-does-something-1.0.5+26.3.jar` | [[👉 Enter MC 26.3 Wiki|26.3-Home]] |

For compatibility across Fabric Loader, Java runtimes, and DasikLibrary, consult the comprehensive [[Version Compatibility Matrix|Version-Compatibility]].

---

## ⚡ Executive Philosophy & Core Mechanics

**Level Does Something** is built upon the **Instant Gratification (IG)** modding philosophy:  
> *"Respect the Player's Time, Not the Game's Rules."*

In vanilla Minecraft, accumulating experience levels beyond level 30 yields diminishing practical returns. Experience is primarily consumed as currency for anvil repairs and enchanting tables—a currency that is brutally wiped out upon death. **Level Does Something** transforms experience from a fragile consumable into an immediate, passive power fantasy:

```
  [ Vanilla Minecraft ]                      [ Level Does Something ]
  +--------------------------------+         +--------------------------------+
  |  Level 30 -> Cap for Enchants  |         |  Level 1..30+ -> Scales Power  |
  |  Levels 31..100+ -> Wasted     |  ===>   |  * +Mining Speed               |
  |  Death -> Total Power Reset    |         |  * +Attack Damage              |
  |  Passive Benefit -> None       |         |  * +Movement Speed             |
  +--------------------------------+         |  * +Luck & +Max Health (Hearts)|
                                             |  * Swirling Particle Aura      |
                                             |  * Ascension Pitch Chimes      |
                                             +--------------------------------+
```

### 1. Dynamic Attribute Scaling
Holding experience grants continuous, passive bonuses to five core player attributes:
1. **Block Break Speed** (`minecraft:block_break_speed`): Mine blocks faster with hands and tools (+1.0% per scale unit).
2. **Attack Damage** (`minecraft:attack_damage`): Deal heavier physical blow damage (+0.5% per scale unit).
3. **Movement Speed** (`minecraft:movement_speed`): Stride and sprint swifter across terrain (+0.2% per scale unit).
4. **Luck** (`minecraft:luck`): Increase chances of high-tier loot from fishing, structure chests, and mob drops (+1.0% per scale unit).
5. **Max Health** (`minecraft:max_health`): Expand your maximum health pool with extra hearts (+1.0% per scale unit), accompanied by immediate healing to populate new heart containers instantly.

### 2. Dual-Layer Visual & Auditory Feedback
- **Celestial Experience Aura**: At Level 30 and beyond, an ambient swirling vortex of emerald star particles (`minecraft:happy_villager`) and golden radiance (`minecraft:trial_spawner_detection`) spirals around the player.
- **Sound Pitch Modulation**: Collecting experience orbs produces progressively higher, pitch-shifted pickup tones, capped at 2.0x frequency.
- **Milestone Chimes**: Reaching major experience thresholds (Level 30, 60, and 100) triggers a server-wide resonant ascension chime (`minecraft:ui.toast.challenge_complete`).

### 3. Sovereign Authority & Client Freedom
- **Server Authority**: Server administrators maintain total control via namespaced `/gamerule` commands in the `leveldoessomething:level_power` category.
- **Client Autonomy**: Individual players can disable particle auras and pitch shifting locally via the in-game YetAnotherConfigLib (YACL v3) GUI or `config/level-does-something-client.json` without affecting server gameplay.

---

## 📚 Global Wiki Documentation Matrix

### 🌟 Shared Architectural Guides
- [[Version Compatibility Matrix|Version-Compatibility]] — Full engine specifications across MC 26.2 and 26.3, Loom toolchain, and DasikLibrary bounds.
- [[Troubleshooting and FAQ|Troubleshooting-and-FAQ]] — Solutions for heart desync, respawn attribute re-application, and performance tuning.
- [[Developer Setup and Building|Developer-Setup-and-Building]] — Unified Gradle 9.3+ build instructions, Loom compilation flags, and headless test workflows.

### 📦 Minecraft 26.2 Documentation Tree
- [[MC 26.2 Landing Portal|26.2-Home]]
- [[MC 26.2 Experience Scaling & Mathematical Curves|26.2-Experience-Scaling-and-Mathematical-Curves]]
- [[MC 26.2 Visual Aura & Auditory Feedback|26.2-Visual-Aura-and-Auditory-Feedback]]
- [[MC 26.2 Configuration & GameRules Reference|26.2-Configuration-and-GameRules]]
- [[MC 26.2 Subsystem Architecture & Mixin Inventory|26.2-Architecture-and-Mixins]]
- [[MC 26.2 Developer Setup & Gradle Toolchain|26.2-Developer-Setup-and-Building]]

### 📦 Minecraft 26.3 Documentation Tree
- [[MC 26.3 Landing Portal|26.3-Home]]
- [[MC 26.3 Experience Scaling & Mathematical Curves|26.3-Experience-Scaling-and-Mathematical-Curves]]
- [[MC 26.3 Visual Aura & Auditory Feedback|26.3-Visual-Aura-and-Auditory-Feedback]]
- [[MC 26.3 Configuration & GameRules Reference|26.3-Configuration-and-GameRules]]
- [[MC 26.3 Subsystem Architecture & Mixin Inventory|26.3-Architecture-and-Mixins]]
- [[MC 26.3 Developer Setup & Gradle Toolchain|26.3-Developer-Setup-and-Building]]

---

## ⚖️ Project License & Attribution

- **Lead Developer**: **Dasik (Rifaditya)**
- **License**: **GNU General Public License v3.0 (GPLv3)**
- **Repository**: [Instant-Gratification-level-does-something](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
