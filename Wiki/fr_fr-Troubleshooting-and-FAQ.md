🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# ❓ Dépannage et Foire Aux Questions (FAQ)

> 📌 **Avertissement relatif à la source du dépôt** : Cette documentation wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure les derniers commits non publiés ou des fonctionnalités en cours de développement avant les versions publiques sur CurseForge et Modrinth.

---

## ❓ Foire Aux Questions

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

## 🛠️ Diagnostic d'Erreurs et Solutions

| Issue / Symptom | Root Cause | Resolution |
| :--- | :--- | :--- |
| `RuntimeException: Level Does Something requires DasikLibrary to function` | Missing `dasik-library`. | Install `dasik-library >= 1.8.38` from Modrinth or CurseForge. |
| Configuration changes reset upon launch. | Corrupted JSON formatting. | Ensure valid JSON syntax in `level-does-something-client.json`. |
| Pitch shift clashes with sound physics mods. | Multiple sound interceptors. | Disable `enableClientSoundPitch` in client config. |

---

## 🔗 Related Documentation
* [[Retour au Portail Principal|fr_fr-Home]]
* [[Matrice de Compatibilité|fr_fr-Version-Compatibility]]
* [[26.2 Configuration & Règles de Jeu|fr_fr-26.2-Configuration-and-GameRules]]
* [[26.3 Configuration & Règles de Jeu|fr_fr-26.3-Configuration-and-GameRules]]
