🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 구성 및 게임 규칙(GameRules) 참조 (MC 26.3)

> 📌 **저장소 소스 고지사항**: 본 위키 문서는 **저장소 내의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 이전의 최신 커밋 또는 개발 중인 기능이 포함될 수 있습니다.

---

## 📋 공식 기술 정보

| Property | Value |
| :--- | :--- |
| **Target Minecraft Version** | `26.3` |
| **GameRule Category ID** | `leveldoessomething:level_power` |
| **GameRule Engine** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |
| **Client Configuration Path** | `.minecraft/config/level-does-something-client.json` |
| **Backup Path** | `.minecraft/config/level-does-something-client.json.bak` |
| **GUI Framework** | YetAnotherConfigLib v3 (`YaclScreenHelper.java`) |
| **ModMenu Entrypoint** | `net.instantgratification.leveldoessomething.config.ModMenuIntegration` |

---

## ⚙️ 서버 게임 규칙 참조 매트릭스

| GameRule Name | Type | Valid Range | Default | Description |
| :--- | :--- | :--- | :--- | :--- |
| `leveldoessomething:levelPowerCurveType` | `Integer` | `0 .. 2` | `1` | 0 = Exponential Tier steps, 1 = Smooth Logarithmic curve, 2 = Simple Linear scaling. |
| `leveldoessomething:levelPowerBaseMultiplier` | `Integer` | `0 .. 1000` | `10` | The base multiplier applied per level in basis points (1/10000). Default 10 = 0.1%. |
| `leveldoessomething:levelPowerMaxTier` | `Integer` | `1 .. 30` | `10` | The maximum tier multiplier allowed when using exponential tier system. |
| `leveldoessomething:levelPowerEnableAura` | `Boolean` | `true / false` | `true` | When true, players at or above level 30 spawn celestial aura particles. |
| `leveldoessomething:levelPowerEnableSoundPitch` | `Boolean` | `true / false` | `true` | When true, experience pickup sound is pitch-shifted with player level. |

### Commands Syntax
```mcfunction
# Query current curve type
/gamerule leveldoessomething:levelPowerCurveType

# Set Linear curve
/gamerule leveldoessomething:levelPowerCurveType 2

# Double multiplier (20 basis points)
/gamerule leveldoessomething:levelPowerBaseMultiplier 20

# Disable particle aura
/gamerule leveldoessomething:levelPowerEnableAura false
```

---

## 💻 클라이언트 구성 스키마
Located at `.minecraft/config/level-does-something-client.json`:
```json
{
  "enableClientAuraParticles": true,
  "enableClientSoundPitch": true
}
```

---

## 🔗 Related Documentation
* [[Minecraft 26.3 포털로 돌아가기|ko_kr-26.3-Home]]
* [[26.3 경험치 스케일링 및 수학 곡선|ko_kr-26.3-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 시각적 오라 및 오디오 피드백|ko_kr-26.3-Visual-Aura-and-Auditory-Feedback]]
* [[26.3 아키텍처 및 믹스인(Mixins) 분석|ko_kr-26.3-Architecture-and-Mixins]]
* [[중앙 포털로 돌아가기|ko_kr-Home]]
