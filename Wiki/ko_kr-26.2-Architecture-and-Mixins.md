🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 서브시스템 아키텍처 및 믹스인 인벤토리 (MC 26.2)

> 📌 **저장소 소스 고지사항**: 본 위키 문서는 **저장소 내의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 이전의 최신 커밋 또는 개발 중인 기능이 포함될 수 있습니다.

---

## 📋 공식 기술 정보

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
* [[Minecraft 26.2 포털로 돌아가기|ko_kr-26.2-Home]]
* [[26.2 경험치 스케일링 및 수학 곡선|ko_kr-26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.2 시각적 오라 및 오디오 피드백|ko_kr-26.2-Visual-Aura-and-Auditory-Feedback]]
* [[26.2 구성 및 게임 규칙 매트릭스|ko_kr-26.2-Configuration-and-GameRules]]
* [[중앙 포털로 돌아가기|ko_kr-Home]]
