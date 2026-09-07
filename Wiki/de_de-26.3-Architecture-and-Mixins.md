🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# Subsystem-Architektur & Mixin-Bestand (MC 26.3)

> 📌 **Haftungsausschluss zur Repository-Quelle**: Diese Wiki-Dokumentation spiegelt den **aktuellen Quellcode-Zustand im Repository** wider, der möglicherweise die neuesten unveröffentlichten Commits oder in Entwicklung befindliche Funktionen vor den öffentlichen Versionen auf CurseForge und Modrinth enthält.

---

## 📋 Offizielle technische Informationen

| Property | Value |
| :--- | :--- |
| **Target Minecraft Version** | `26.3-snapshot-6` |
| **Java Package Layout** | `net.instantgratification.leveldoessomething` |
| **Mixin Configuration** | `level-does-something.mixins.json` |
| **Mixin Compatibility Level** | `JAVA_25` |
| **Networking Invariant** | Vanilla DataTrackers only (`DATA_AURA_LEVEL`), zero custom network packets |

---

## 🧩 Subsystem Class Breakdown

```
net.instantgratification.leveldoessomething/
├── LevelDoesSomethingFabric.java           # Entrypoint & initialization
├── config/
│   ├── LevelConfig.java                    # POJO client config schema
│   ├── ModMenuIntegration.java             # ModMenu API integration
│   └── YaclScreenHelper.java               # YetAnotherConfigLib v3 screen builder
├── mixin/
│   ├── PlayerMixin.java                    # Attribute modifier injection & cleanup
│   └── ClientPacketListenerMixin.java      # Pitch-shifted experience audio hook
└── util/
    ├── PlayerLevelAttributesHandler.java   # Scaling math & modifier management
    ├── LevelAuraRenderer.java              # Client tick particle spiral rendering
    └── ModVersionGuard.java                # Runtime classloader verification
```

---

## 🔗 Related Documentation
* [[Zurück zum Minecraft 26.3 Portal|de_de-26.3-Home]]
* [[26.3 Erfahrungsskalierung & Kurven|de_de-26.3-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 Visuelle Aura & Klänge|de_de-26.3-Visual-Aura-and-Auditory-Feedback]]
* [[26.3 Spielregeln & Konfiguration|de_de-26.3-Configuration-and-GameRules]]
* [[Zurück zum Hauptportal|de_de-Home]]
