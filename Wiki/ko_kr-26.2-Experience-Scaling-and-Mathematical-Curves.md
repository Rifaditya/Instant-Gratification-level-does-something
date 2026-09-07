🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 경험치 스케일링 및 수학 곡선 (MC 26.2)

> 📌 **저장소 소스 고지사항**: 본 위키 문서는 **저장소 내의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 이전의 최신 커밋 또는 개발 중인 기능이 포함될 수 있습니다.

---

## 📋 공식 기술 정보

| Property | Value |
| :--- | :--- |
| **Target Minecraft Version** | `26.2` |
| **Source Implementation** | [`PlayerLevelAttributesHandler.java`](https://github.com/Rifaditya/Instant-Gratification-level-does-something) |
| **Attribute Modifier Operation** | `AttributeModifier.Operation.ADD_MULTIPLIED_BASE` |
| **Controlling GameRules** | `leveldoessomething:levelPowerCurveType`, `leveldoessomething:levelPowerBaseMultiplier`, `leveldoessomething:levelPowerMaxTier` |
| **Trigger Hook** | `PlayerMixin.tick()` (evaluated upon `lastExperienceLevel != player.experienceLevel`) |
| **Cleanup Hook** | `PlayerMixin.remove()` invoking `PlayerLevelAttributesHandler.removePlayerAttributes()` |
| **Immediate Heart Compensation** | Active ($\Delta H = H_{\text{new}} - H_{\text{prev}}$, healing player if $\Delta H > 0$) |

---

## 🔄 서바이벌 플레이어 진행 흐름

```
       [ PLAYER EARNS EXPERIENCE ]
                  │
                  ▼
       [ Integer Level Increases ]
                  │
                  ▼ (Player.tick() detects delta)
       ┌─────────────────────────────────────────┐
       │ PlayerLevelAttributesHandler Evaluates: │
       │ 1. Current GameRule Curve Type (0, 1, 2)│
       │ 2. Calculates Scale Factor S            │
       │ 3. Computes Global Multiplier M_global  │
       └─────────────────────────────────────────┘
                  │
                  ▼
       ┌─────────────────────────────────────────┐
       │ Updates 5 Vanilla AttributeModifiers:   │
       │ • BLOCK_BREAK_SPEED (+1.0% × S × M)     │
       │ • ATTACK_DAMAGE     (+0.5% × S × M)     │
       │ • MOVEMENT_SPEED    (+0.2% × S × M)     │
       │ • LUCK              (+1.0% × S × M)     │
       │ • MAX_HEALTH        (+1.0% × S × M)     │
       └─────────────────────────────────────────┘
                  │
                  ▼
       ┌─────────────────────────────────────────┐
       │ Extra Hearts Immediately Healed!        │
       │ DATA_AURA_LEVEL synced to clients       │
       └─────────────────────────────────────────┘
```

1. **Experience Accumulation**: As the player defeats mobs, mines ores, smelts items, or breeds animals, integer experience level increases.
2. **Atomic Change Detection**: During `Player.tick()`, the server checks whether `lastExperienceLevel != player.experienceLevel`.
3. **Transient Modifier Refresh**: Existing mod modifiers are safely cleared by Identifier, and freshly computed `AttributeModifier` instances are attached.
4. **Heart Population**: If `MAX_HEALTH` increases, the difference in maximum health is computed immediately and the player is healed by $\Delta H$.
5. **Death & Cleanup**: If the player dies, all five modifiers are stripped at `Player.remove()`.

---

## 🧮 수학 곡선 및 계산 모델

### Curve Type 0: Exponential Tier Progression
Steps every 10 levels:
$$T = \min\left(\left\lfloor \frac{L}{10} \right\rfloor, \text{maxTier}\right)$$
$$S_{\text{tier}} = 2^T - 1$$

### Curve Type 1: Smooth Logarithmic Curve (Default)
Continuous progression with diminishing returns:
$$S_{\text{log}} = \ln(1.0 + 0.1 \times L)$$

### Curve Type 2: Simple Linear Scaling
Direct linear scaling:
$$S_{\text{lin}} = L$$

---

## 🔗 Related Documentation
* [[Minecraft 26.2 포털로 돌아가기|ko_kr-26.2-Home]]
* [[26.2 시각적 오라 및 오디오 피드백|ko_kr-26.2-Visual-Aura-and-Auditory-Feedback]]
* [[26.2 구성 및 게임 규칙 매트릭스|ko_kr-26.2-Configuration-and-GameRules]]
* [[26.2 아키텍처 및 믹스인(Mixins) 분석|ko_kr-26.2-Architecture-and-Mixins]]
* [[중앙 포털로 돌아가기|ko_kr-Home]]
