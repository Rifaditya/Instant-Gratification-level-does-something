🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# Arquitetura de Subsistemas e Inventário de Mixins (MC 26.2)

> 📌 **Aviso Legal da Fonte do Repositório**: Esta documentação wiki reflete o **estado atual do código-fonte no repositório**, podendo incluir os commits mais recentes não lançados ou recursos em desenvolvimento antes dos lançamentos públicos no CurseForge e Modrinth.

---

## 📋 Ficha Técnica Oficial

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
* [[Voltar ao Portal do Minecraft 26.2|pt_br-26.2-Home]]
* [[26.2 Escalonamento de XP & Curvas|pt_br-26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.2 Aura Visual & Efeitos Sonoros|pt_br-26.2-Visual-Aura-and-Auditory-Feedback]]
* [[26.2 Configuração & Regras de Jogo|pt_br-26.2-Configuration-and-GameRules]]
* [[Voltar ao Portal Principal|pt_br-Home]]
