🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 設定およびゲームルール (GameRules) リファレンス (MC 26.2)

> 📌 **リポジトリソースに関する免責事項**: このWikiドキュメントは**リポジトリ内の現在のソースコード状態**を反映しており、CurseForgeおよびModrinthでの公開リリース前の最新の未リリースコミットや開発中の機能が含まれている場合があります。

---

## 📋 公式技術仕様

| Property | Value |
| :--- | :--- |
| **Target Minecraft Version** | `26.2` |
| **GameRule Category ID** | `leveldoessomething:level_power` |
| **GameRule Engine** | `net.dasik.social.api.gamerule.DynamicGameRuleManager` |
| **Client Configuration Path** | `.minecraft/config/level-does-something-client.json` |
| **Backup Path** | `.minecraft/config/level-does-something-client.json.bak` |
| **GUI Framework** | YetAnotherConfigLib v3 (`YaclScreenHelper.java`) |
| **ModMenu Entrypoint** | `net.instantgratification.leveldoessomething.config.ModMenuIntegration` |

---

## ⚙️ サーバー側ゲームルールリファレンスマトリクス

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

## 💻 クライアント側設定スキーマ
Located at `.minecraft/config/level-does-something-client.json`:
```json
{
  "enableClientAuraParticles": true,
  "enableClientSoundPitch": true
}
```

---

## 🔗 Related Documentation
* [[Minecraft 26.2 ポータルに戻る|ja_jp-26.2-Home]]
* [[26.2 経験値スケーリングと数学曲線|ja_jp-26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.2 オーラ演出と音声フィードバック|ja_jp-26.2-Visual-Aura-and-Auditory-Feedback]]
* [[26.2 アーキテクチャと Mixin 解析|ja_jp-26.2-Architecture-and-Mixins]]
* [[グローバルホームに戻る|ja_jp-Home]]
