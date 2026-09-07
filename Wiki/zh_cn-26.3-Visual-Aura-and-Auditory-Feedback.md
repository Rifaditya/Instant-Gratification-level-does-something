🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 视觉光环与音效反馈 (MC 26.3)

> 📌 **代码仓库来源免责声明**：本维基文档反映了**代码仓库中的当前源码状态**，可能包含领先于 CurseForge 和 Modrinth 平台公开发布版本的最新未发布提交或开发中功能。

---

## 📋 官方技术信息

| Property | Value |
| :--- | :--- |
| **Target Minecraft Version** | `26.3` |
| **Visual Renderer** | [`LevelAuraRenderer.java`](https://github.com/Rifaditya/Instant-Gratification-level-does-something) |
| **Audio Interceptor** | [`ClientPacketListenerMixin.java`](https://github.com/Rifaditya/Instant-Gratification-level-does-something) |
| **Threshold for Aura** | Experience Level $\ge 30$ |
| **Particle Types** | `minecraft:happy_villager` (Emerald) & `minecraft:trial_spawner_detection` (Gold) |
| **Sound Event** | `minecraft:entity.experience_orb.pickup` |
| **Milestone Chime** | `minecraft:ui.toast.challenge_complete` (at levels 30, 60, 100) |
| **Server GameRules** | `leveldoessomething:levelPowerEnableAura`, `leveldoessomething:levelPowerEnableSoundPitch` |
| **Client Toggles** | `enableClientAuraParticles`, `enableClientSoundPitch` |

---

## 🌌 The Celestial Experience Aura

When a player attains **Level 30** or above, they unlock a swirling celestial particle vortex that ascends around their character model.

```
                  *  .  +  (Gold Sparkle)
                 .  *  +  .
             +      ( O )      +  y = 2.2m (Apex)
              *    /  |  \    *
                + [ Player ] +    y = 1.0m (Midpoint)
                 *   / \   *
               +    *   +    +    y = 0.0m (Feet)
        ───────────────────────────────
```

### Dual-Layer Toggle System
1. **Server Authority (`levelPowerEnableAura`)**:
   - If `false`, server syncs `DATA_AURA_LEVEL = 0` to all clients.
2. **Client Autonomy (`enableClientAuraParticles`)**:
   - Local toggle in client config halts particle generation loop without affecting server state.

---

## 🧮 Mathematical Particle Simulation

During `ClientTickEvents.END_CLIENT_TICK`, `LevelAuraRenderer.tick(ClientLevel)` iterates over active players:

$$N_{\text{particles}} = \operatorname{clamp}\left(\left\lfloor \frac{\text{auraLevel} - 20}{15} \right\rfloor, 1, 5\right)$$

| Tracked Level | Calculation | Particles Per Tick | Visual Density |
| :--- | :--- | :--- | :--- |
| **0 - 29** | Aura Inactive | `0` | None |
| **30 - 34** | $(30 - 20) / 15 = 0.67 \to 1$ | `1` | Subtle ambient sparkle |
| **35 - 49** | $(35 - 20) / 15 = 1.00 \to 1$ | `1` | Delicate celestial dust |
| **50 - 64** | $(50 - 20) / 15 = 2.00 \to 2$ | `2` | Distinct ascending spiral |
| **65+** | Level Scaling | `3 - 5` | Radiance beacon |

---

## 🔗 Related Documentation
* [[返回 Minecraft 26.3 门户|zh_cn-26.3-Home]]
* [[26.3 经验强化与数学曲线|zh_cn-26.3-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 配置与游戏规则矩阵|zh_cn-26.3-Configuration-and-GameRules]]
* [[26.3 架构设计与 Mixin 解析|zh_cn-26.3-Architecture-and-Mixins]]
* [[返回中央门户|zh_cn-Home]]
