🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# Configuration et Référence des GameRules (MC 26.3)

> 📌 **Avertissement relatif à la source du dépôt** : Cette documentation wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure les derniers commits non publiés ou des fonctionnalités en cours de développement avant les versions publiques sur CurseForge et Modrinth.

---

## 📋 Fiche Technique Officielle

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

## ⚙️ Matrice des GameRules Serveur

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

## 💻 Schéma de Configuration Client
Located at `.minecraft/config/level-does-something-client.json`:
```json
{
  "enableClientAuraParticles": true,
  "enableClientSoundPitch": true
}
```

---

## 🔗 Related Documentation
* [[Retour au Portail Minecraft 26.3|fr_fr-26.3-Home]]
* [[26.3 Évolution de l'Expérience & Courbes|fr_fr-26.3-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 Aura Visuelle & Retours Sonores|fr_fr-26.3-Visual-Aura-and-Auditory-Feedback]]
* [[26.3 Architecture & Mixins|fr_fr-26.3-Architecture-and-Mixins]]
* [[Retour au Portail Principal|fr_fr-Home]]
