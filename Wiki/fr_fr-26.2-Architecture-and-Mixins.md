🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# Architecture des Sous-systèmes & Inventaire Mixin (MC 26.2)

> 📌 **Avertissement relatif à la source du dépôt** : Cette documentation wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure les derniers commits non publiés ou des fonctionnalités en cours de développement avant les versions publiques sur CurseForge et Modrinth.

---

## 📋 Fiche Technique Officielle

| Property | Value |
| :--- | :--- |
| **Target Minecraft Version** | `26.2` |
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
* [[Retour au Portail Minecraft 26.2|fr_fr-26.2-Home]]
* [[26.2 Évolution de l'Expérience & Courbes|fr_fr-26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.2 Aura Visuelle & Retours Sonores|fr_fr-26.2-Visual-Aura-and-Auditory-Feedback]]
* [[26.2 Configuration & Règles de Jeu|fr_fr-26.2-Configuration-and-GameRules]]
* [[Retour au Portail Principal|fr_fr-Home]]
