🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 配置與遊戲規則參考 (MC 26.3)

> 📌 **代碼倉庫來源免責聲明**：本維基文件反映了**代碼倉庫中的當前源碼狀態**，可能包含領先於 CurseForge 和 Modrinth 平台公開發布版本的最新未發布提交或開發中功能。

---

## 📋 官方技術資訊

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

## ⚙️ 伺服端遊戲規則參考矩陣

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

## 💻 客戶端配置規範
Located at `.minecraft/config/level-does-something-client.json`:
```json
{
  "enableClientAuraParticles": true,
  "enableClientSoundPitch": true
}
```

---

## 🔗 Related Documentation
* [[返回 Minecraft 26.3 門戶|zh_tw-26.3-Home]]
* [[26.3 經驗強化與數學曲線|zh_tw-26.3-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 視覺光環與音效回饋|zh_tw-26.3-Visual-Aura-and-Auditory-Feedback]]
* [[26.3 架構設計與 Mixin 解析|zh_tw-26.3-Architecture-and-Mixins]]
* [[返回中央門戶|zh_tw-Home]]
