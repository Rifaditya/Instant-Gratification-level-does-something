🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# ❓ トラブルシューティング & よくある質問 (FAQ)

> 📌 **リポジトリソースに関する免責事項**: このWikiドキュメントは**リポジトリ内の現在のソースコード状態**を反映しており、CurseForgeおよびModrinthでの公開リリース前の最新の未リリースコミットや開発中の機能が含まれている場合があります。

---

## ❓ よくある質問

### Q1: What happens to my passive attribute bonuses when I die and respawn?
**Answer**: All level-based attribute bonuses are tied to your current living experience level (`experienceLevel`).
- When a player entity dies, `PlayerMixin.remove()` executes:
  ```java
  PlayerLevelAttributesHandler.removePlayerAttributes(player);
  ```
- Upon respawning, attributes reset for Level 0 (or retained level if `keepInventory` is active).

---

### Q2: Why don't I get "empty hearts" when my Max Health increases?
**Answer**: In vanilla Minecraft, increasing `Attributes.MAX_HEALTH` leaves empty heart containers.
**Level Does Something** implements an atomic instant-healing compensation routine inside `PlayerLevelAttributesHandler.applyMaxHealthModifier()`:
```java
double newMaxHealth = player.getMaxHealth();
double healthDiff = newMaxHealth - previousMaxHealth;
if (healthDiff > 0) {
    player.heal((float) healthDiff);
}
```
Filling the newly gained heart containers instantly.

---

### Q3: Can I toggle the particle aura off for performance?
**Answer**: Yes! Via **ModMenu** -> configure **Enable Client Aura Particles** to `OFF`, or edit `.minecraft/config/level-does-something-client.json`:
```json
{
  "enableClientAuraParticles": false,
  "enableClientSoundPitch": true
}
```

---

### Q4: Can server administrators disable the aura globally?
**Answer**: Yes via `/gamerule leveldoessomething:levelPowerEnableAura false`.

---

### Q5: Can I exceed vanilla multiplier limits?
**Answer**: Yes! In accordance with the **Player Agency & Anti-Nanny Invariant**, `levelPowerBaseMultiplier` allows up to `1000` and `levelPowerMaxTier` allows up to `30`.

---

## 🛠️ 一般的なエラー診断と解決策

| Issue / Symptom | Root Cause | Resolution |
| :--- | :--- | :--- |
| `RuntimeException: Level Does Something requires DasikLibrary to function` | Missing `dasik-library`. | Install `dasik-library >= 1.8.38` from Modrinth or CurseForge. |
| Configuration changes reset upon launch. | Corrupted JSON formatting. | Ensure valid JSON syntax in `level-does-something-client.json`. |
| Pitch shift clashes with sound physics mods. | Multiple sound interceptors. | Disable `enableClientSoundPitch` in client config. |

---

## 🔗 Related Documentation
* [[グローバルホームに戻る|ja_jp-Home]]
* [[バージョン互換性マトリクス|ja_jp-Version-Compatibility]]
* [[26.2 設定とゲームルール (GameRules)|ja_jp-26.2-Configuration-and-GameRules]]
* [[26.3 設定とゲームルール (GameRules)|ja_jp-26.3-Configuration-and-GameRules]]
