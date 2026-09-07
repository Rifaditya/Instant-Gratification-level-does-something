🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# サブシステムアーキテクチャと Mixin インベントリ (MC 26.3)

> 📌 **リポジトリソースに関する免責事項**: このWikiドキュメントは**リポジトリ内の現在のソースコード状態**を反映しており、CurseForgeおよびModrinthでの公開リリース前の最新の未リリースコミットや開発中の機能が含まれている場合があります。

---

## 📋 公式技術仕様

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
* [[Minecraft 26.3 ポータルに戻る|ja_jp-26.3-Home]]
* [[26.3 経験値スケーリングと数学曲線|ja_jp-26.3-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 オーラ演出と音声フィードバック|ja_jp-26.3-Visual-Aura-and-Auditory-Feedback]]
* [[26.3 設定とゲームルール (GameRules)|ja_jp-26.3-Configuration-and-GameRules]]
* [[グローバルホームに戻る|ja_jp-Home]]
