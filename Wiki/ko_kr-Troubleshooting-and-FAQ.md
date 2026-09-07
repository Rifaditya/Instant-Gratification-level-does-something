🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# ❓ 문제 해결 및 자주 묻는 질문 (FAQ)

> 📌 **저장소 소스 고지사항**: 본 위키 문서는 **저장소 내의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 이전의 최신 커밋 또는 개발 중인 기능이 포함될 수 있습니다.

---

## ❓ 자주 묻는 질문

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

## 🛠️ 일반적인 오류 진단 및 해결 방법

| Issue / Symptom | Root Cause | Resolution |
| :--- | :--- | :--- |
| `RuntimeException: Level Does Something requires DasikLibrary to function` | Missing `dasik-library`. | Install `dasik-library >= 1.8.38` from Modrinth or CurseForge. |
| Configuration changes reset upon launch. | Corrupted JSON formatting. | Ensure valid JSON syntax in `level-does-something-client.json`. |
| Pitch shift clashes with sound physics mods. | Multiple sound interceptors. | Disable `enableClientSoundPitch` in client config. |

---

## 🔗 Related Documentation
* [[중앙 포털로 돌아가기|ko_kr-Home]]
* [[버전 호환성 매트릭스|ko_kr-Version-Compatibility]]
* [[26.2 구성 및 게임 규칙 매트릭스|ko_kr-26.2-Configuration-and-GameRules]]
* [[26.3 구성 및 게임 규칙 매트릭스|ko_kr-26.3-Configuration-and-GameRules]]
