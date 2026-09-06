# Troubleshooting & Frequently Asked Questions (FAQ)

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## ❓ Frequently Asked Questions

### Q1: What happens to my passive attribute bonuses when I die and respawn?
**Answer**: All level-based attribute bonuses are tied to your current living experience level (`experienceLevel`).
- When a player entity dies and is removed from the world, the `PlayerMixin.remove()` hook immediately executes:
  ```java
  PlayerLevelAttributesHandler.removePlayerAttributes(player);
  ```
  This removes all five `AttributeModifier` instances (`level_break_speed`, `level_attack_damage`, `level_movement_speed`, `level_luck`, and `level_max_health`) from the player's attribute map.
- Upon respawning, `Player.tick()` detects that your experience level is now 0 (or your retained level if `keepInventory` is active) and recalculates modifiers accordingly. No phantom attribute boosts persist after death.

---

### Q2: Why don't I get "empty hearts" when my Max Health increases?
**Answer**: In vanilla Minecraft, increasing `Attributes.MAX_HEALTH` via an attribute modifier increases the container capacity without adding health points, resulting in empty, unhealed heart containers.
**Level Does Something** implements an atomic instant-healing compensation routine inside `PlayerLevelAttributesHandler.applyMaxHealthModifier()`:
```java
double previousMaxHealth = player.getMaxHealth();
instance.removeModifier(id);
if (amount > 0.0) {
    AttributeModifier modifier = new AttributeModifier(id, amount, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    instance.addOrUpdateTransientModifier(modifier);
    
    // Calculate health difference and immediately heal player
    double newMaxHealth = player.getMaxHealth();
    double healthDiff = newMaxHealth - previousMaxHealth;
    if (healthDiff > 0) {
        player.heal((float) healthDiff);
    }
}
```
When your level increases and grants additional maximum health, the game immediately heals you by exactly $\Delta H = H_{	ext{new}} - H_{	ext{previous}}$, filling the newly gained heart containers instantly.

---

### Q3: The swirling particle aura causes frame drops on my lower-end GPU. Can I turn it off?
**Answer**: Yes! The aura system utilizes a **Dual-Layer Visual Toggle**:
1. **In-Game GUI**: Open **ModMenu** -> select **Level Does Something** -> configure **Enable Client Aura Particles** to `OFF`.
2. **Configuration File**: Open `.minecraft/config/level-does-something-client.json` and edit:
   ```json
   {
     "enableClientAuraParticles": false,
     "enableClientSoundPitch": true
   }
   ```
This completely halts the `LevelAuraRenderer.tick()` particle generation loop on your client without requiring operator permissions or affecting any other players on the server.

---

### Q4: Can server administrators disable the visual aura or sound pitch globally?
**Answer**: Yes. Server administrators have total authority via namespaced GameRules:
```mcfunction
# Disable ambient particle auras for all players across the server
/gamerule leveldoessomething:levelPowerEnableAura false

# Disable experience orb pickup sound pitch shifting
/gamerule leveldoessomething:levelPowerEnableSoundPitch false
```
When `levelPowerEnableAura` is set to `false`, the server stops synchronizing the player's visual aura level to surrounding clients via `DATA_AURA_LEVEL` (setting it to `0`), completely disabling all client particle rendering server-wide.

---

### Q5: Does this mod work on dedicated servers without players installing it?
**Answer**: **Yes, partially.**
- **Server Attributes**: The attribute modifiers (`BLOCK_BREAK_SPEED`, `ATTACK_DAMAGE`, `MOVEMENT_SPEED`, `LUCK`, `MAX_HEALTH`) are 100% vanilla Mojang attribute modifiers. Vanilla clients connecting to a modded server will receive all attribute boosts automatically.
- **Client Effects**: The particle aura spiral and the pitch-shifted experience pickup audio are client-side rendering features. Players without the mod installed on their client will enjoy the stat boosts but will not see the custom particle spirals or hear the pitch-shifted chimes.

---

### Q6: Can I exceed vanilla multiplier limits or configure extreme values?
**Answer**: Absolutely. In accordance with the **Player Agency & Anti-Nanny Invariant**, **Level Does Something** never imposes artificial gameplay ceilings or hand-holding limits.
- The `levelPowerBaseMultiplier` GameRule allows integer values up to `1000` (representing 100% boost per scale unit).
- The `levelPowerMaxTier` GameRule allows integer tiers up to `30` ($2^{30} pprox 1{,}073{,}741{,}824$ multiplier).
Server administrators have total freedom to create overpowered sandbox experiences or high-intensity challenge worlds.

---

## 🛠️ Common Error Diagnosis & Resolutions

| Issue / Symptom | Root Cause | Resolution |
| :--- | :--- | :--- |
| `RuntimeException: Level Does Something requires DasikLibrary to function` | Missing required dependency `dasik-library`. | Download and install `dasik-library >= 1.8.38` from Modrinth or CurseForge into your `mods/` directory. |
| Configuration changes in `level-does-something-client.json` reset upon launch. | Corrupted JSON formatting or file size exceeded 50 KB safety limit. | The mod automatically restores from `level-does-something-client.json.bak` if invalid JSON is detected. Ensure valid JSON syntax. |
| Pitch shift sounds distorted or clashes with sound physics mods. | Multiple mods intercepting `SoundEvents.EXPERIENCE_ORB_PICKUP`. | Disable `enableClientSoundPitch` in the client config to let sound physics mods manage audio rendering. |
| Attribute bonuses do not take effect immediately after running `/xp add`. | Experience level did not change (e.g. adding points rather than levels). | Use `/xp add @s 1 levels` or collect enough experience points to advance to the next integer level. |

---

## 🔗 Quick Links
* [[Return to Global Home|Home]]
* [[Version Compatibility Matrix|Version-Compatibility]]
* [[MC 26.2 Configuration Guide|26.2-Configuration-and-GameRules]]
* [[MC 26.3 Configuration Guide|26.3-Configuration-and-GameRules]]
