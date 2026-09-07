🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# Aura Visuelle & Retours Sonores (MC 26.2)

> 📌 **Avertissement relatif à la source du dépôt** : Cette documentation wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure les derniers commits non publiés ou des fonctionnalités en cours de développement avant les versions publiques sur CurseForge et Modrinth.

---

## 📋 Fiche Technique Officielle

| Property | Value |
| :--- | :--- |
| **Target Minecraft Version** | `26.2` |
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
* [[Retour au Portail Minecraft 26.2|fr_fr-26.2-Home]]
* [[26.2 Évolution de l'Expérience & Courbes|fr_fr-26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.2 Configuration & Règles de Jeu|fr_fr-26.2-Configuration-and-GameRules]]
* [[26.2 Architecture & Mixins|fr_fr-26.2-Architecture-and-Mixins]]
* [[Retour au Portail Principal|fr_fr-Home]]
