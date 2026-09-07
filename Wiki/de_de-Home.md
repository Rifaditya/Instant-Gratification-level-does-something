# Level Does Something — Offizielles Dokumentationsportal

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 Versionsauswahl-Portal

Wählen Sie Ihre Zielversion von Minecraft, um auf versionsspezifische Mechaniken, mathematische Formeln und Konfigurationen zuzugreifen:

| Minecraft-Version | Kanal | Build-Artefakt | Schnellzugriff |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | Modern Standard | `level-does-something-1.0.5+26.2.jar` | [[👉 MC 26.2 Wiki öffnen|de_de-26.2-Home]] |
| **Minecraft 26.3** | Modern Lead | `level-does-something-1.0.5+26.3.jar` | [[👉 MC 26.3 Wiki öffnen|de_de-26.3-Home]] |

Detaillierte Informationen zu Fabric Loader, Java und DasikLibrary finden Sie in der [[Versionskompatibilitätsmatrix|de_de-Version-Compatibility]].

---

## ⚡ Spielphilosophie & Kernmechaniken

**Level Does Something** basiert auf der **Instant Gratification (Sofortige Belohnung)** Designphilosophie:  
> *„Respektiere die Zeit des Spielers, nicht veraltete Spielregeln.“*

Im normalen Minecraft bietet das Sammeln von Erfahrungsstufen über Level 30 hinaus kaum Mehrwert. Es dient nur als verbrauchbare Währung für Verzauberungen und Reparaturen am Amboss, die beim Tod weitgehend verloren geht. Diese Mod verwandelt das Halten von Erfahrung in kontinuierliche passive Macht!

### 1. Dynamische Attributskalierung
Das Halten von XP-Leveln stärkt 5 wichtige Attribute:
* **Blockabbau-Geschwindigkeit** (`minecraft:block_break_speed`): Schnelleres Abbauen mit Händen und Werkzeugen (+1.0% pro Skalierungseinheit).
* **Angriffsschaden** (`minecraft:attack_damage`): Stärkere Nahkampfangriffe (+0.5% pro Skalierungseinheit).
* **Bewegungsgeschwindigkeit** (`minecraft:movement_speed`): Höheres Geh- und Sprinttempo (+0.2% pro Skalierungseinheit).
* **Glück** (`minecraft:luck`): Bessere Chancen auf seltene Beute beim Angeln und in Schatztruhen (+1.0% pro Skalierungseinheit).
* **Maximale Gesundheit** (`minecraft:max_health`): Zusätzliche Herzen (+1.0% pro Skalierungseinheit) mit **sofortiger Heilungskompensation** für neu gewonnene Herzen!

### 2. Visuelle Aura & Akustisches Feedback
* **Himmlische Erfahrungs-Aura (ab Level 30)**: Aufsteigende Spirale aus grünen Smaragdfunken (`minecraft:happy_villager`) und goldenen Partikeln (`minecraft:trial_spawner_detection`).
* **Erfahrungs-Tonhöhenskalierung**: Das Einsammeln von XP-Kugeln erklingt in immer höheren Tönen (bis zu 2.0x).
* **Meilenstein-Glockenklang**: Das Erreichen von Level 30, 60 und 100 löst einen feierlichen Klang aus (`minecraft:ui.toast.challenge_complete`).

---

## 📚 Wichtige Bereiche
* [[⚙️ Versionskompatibilitätsmatrix|de_de-Version-Compatibility]]
* [[❓ Fehlerbehebung & FAQ|de_de-Troubleshooting-and-FAQ]]
* [[🛠️ Entwickler-Setup & Gradle-Builds|de_de-Developer-Setup-and-Building]]
* [[26.2 Erfahrungsskalierung & Kurven|26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 Erfahrungsskalierung & Kurven|26.3-Experience-Scaling-and-Mathematical-Curves]]

---

## ⚖️ Lizenz & Urheberrecht
* **Entwickler**: **Dasik (Rifaditya)**
* **Lizenz**: **GNU General Public License v3.0 (GPLv3)**
* **Quellcode**: [GitHub-Repository](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
