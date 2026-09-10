# Level Does Something!

## 1. Executive Summary & Philosophy Fit
**Philosophy Alignment**: 
- **Instant Gratification (IG)**: *"Respect the Player's Time, Not the Game's Rules."* 
- In vanilla Minecraft, accumulating experience levels beyond 30 is largely redundant. It serves only as a consumable currency for enchanting and anvil repairs, which is completely lost upon death. This mod changes that loop by offering an immediate, passive power fantasy. By simply holding and hoarding raw experience, the player becomes stronger, faster, and more resilient. This creates a high-stakes risk-reward dynamic: hoarding levels makes you a powerhouse, but dying means losing that power.

---

## 2. Core Mechanics & Features

### Feature 1: Dynamic Level Tracking & Synchronized Recalculation
- **Description**: The mod dynamically tracks the player's current experience level (`experienceLevel`). Rather than running expensive recalculations every tick, it monitors the value and triggers an attribute update ONLY when the level actually changes.
- **Reference**: Recalculations are triggered inside the player's tick method when `lastExperienceLevel != experienceLevel`.

### Feature 2: Mathematical Scaling Curves
The mod supports three distinct scaling curves configured via GameRules, defaulting to the **Logarithmic** model:

1. **Curve 1: Smooth Logarithmic Curve (Default)**
   - Provides a continuous, smooth scaling curve that increases quickly at lower levels and tapers off at extremely high levels, rewarding initial survival while preventing absolute game-breaking extremes:
     $$\Delta A = B \times \ln(L + 1)$$
   - *Example scaling factors* ($S = \ln(L + 1)$):
     - Level 0: $S = 0.0$ $\rightarrow$ $+0.0\%$ boost
     - Level 5: $S \approx 1.79$ $\rightarrow$ $+1.79 \times B$ boost
     - Level 15: $S \approx 2.77$ $\rightarrow$ $+2.77 \times B$ boost
     - Level 30: $S \approx 3.43$ $\rightarrow$ $+3.43 \times B$ boost
     - Level 100: $S \approx 4.62$ $\rightarrow$ $+4.62 \times B$ boost
     - Level 1000: $S \approx 6.91$ $\rightarrow$ $+6.91 \times B$ boost

2. **Curve 0: Exponential Tier Steps**
   - The player's level $L$ is mapped to a power-of-two tier $T$:
     $$T = \begin{cases} 
     0 & \text{if } L < 2 \\
     \min(T_{max}, \lfloor \log_2(L) \rfloor) & \text{if } L \ge 2 
     \end{cases}$$
     where $T_{max}$ is configured by `levelPowerMaxTier` (default: 10). The multiplier is $S = 2^T$.

3. **Curve 2: Linear Scaling**
   - Predictable, straight-line scaling for direct progression:
     $$\Delta A = B \times L$$

---

### Feature 3: Passive Attribute Modifications
Five attributes are modified using the `ADD_MULTIPLIED_BASE` operation, which scales the base value of the attribute:

| Attribute | Base Multiplier ($B$) | UUID | Purpose |
| :--- | :--- | :--- | :--- |
| `Attributes.BLOCK_BREAK_SPEED` | `0.01` (1.0% per scale unit) | `4a8f98a2-7b6c-482a-bf31-68f7b2c9f5d1` | Increases hand and tool mining speed. |
| `Attributes.ATTACK_DAMAGE` | `0.005` (0.5% per scale unit) | `4a8f98a2-7b6c-482a-bf31-68f7b2c9f5d2` | Boosts physical attack damage. |
| `Attributes.MOVEMENT_SPEED` | `0.002` (0.2% per scale unit) | `4a8f98a2-7b6c-482a-bf31-68f7b2c9f5d3` | Boosts walking/running speed. |
| `Attributes.LUCK` | `0.01` (1.0% per scale unit) | `4a8f98a2-7b6c-482a-bf31-68f7b2c9f5d4` | Boosts rare loot drops from chests/fishing. |
| `Attributes.MAX_HEALTH` | `0.01` (1.0% per scale unit) | `4a8f98a2-7b6c-482a-bf31-68f7b2c9f5d5` | Grants extra hearts (max health) at high levels. |

---

### Feature 4: Aesthetic Feedback & Dual-Layer Visual Toggles

To ensure players can customize their performance and visual clutter, the experience aura features **both Server-side and Client-side toggles**.

1. **Globally Visible Aura Particles**:
   - **Visuals**: A soft, swirling ambient column of experience-themed particles that spiral upward from the player's feet to above their head.
     - Spawns emerald-green star sparkles (`minecraft:happy_villager`) and golden particles (`minecraft:trial_spawner_detection`).
     - Spawning rate and velocity scale dynamically with the player's experience level, starting at Level 30 (light dust) up to Level 100+ (a dense, radiant celestial halo).

2. **Server-Wide Control (GameRule)**:
   - The server host can disable the aura system entirely using the `levelPowerEnableAura` GameRule. When set to `false`, the server stops tracking and synchronizing visual levels to clients, preventing all aura rendering across the server.

3. **Client-Side Control (Local Config / Command)**:
   - Even if the server has auras enabled, individual players can turn off aura rendering locally via a client configuration option (`enableClientAuraParticles = false`).
   - When disabled locally, the client will suppress the rendering of all experience aura particles (both their own and those of other players) on their screen, resolving performance bottlenecks or personal visual preferences.

4. **Ascension Sound Pitching**:
   - Experience orb collection sounds (`entity.experience_orb.pickup`) have their pitch shifted up as the player accumulates levels.
   - Crossing a major milestone (e.g., Level 30, 60, 100) plays a resonant ascension chime. Can be disabled client-side.

---

## 3. Configuration & Localization (Sovereign Mandate)

All configurations are handled via server GameRules and client config files. Below are the key mappings and display properties.

### GameRules Registry (Server-Side)

1. **levelPowerCurveType**
   - **Type**: `Integer`
   - **Default**: `1` (Logarithmic)
   - **Display Name**: `Level Power Curve Type`
   - **Translation Key**: `gamerule.level_does_something.levelPowerCurveType.description`
   - **Description**: "The mathematical curve used to calculate attribute scaling. 0 = Exponential Tier steps, 1 = Smooth Logarithmic curve, 2 = Simple Linear scaling. Default: 1."

2. **levelPowerBaseMultiplier**
   - **Type**: `Double`
   - **Default**: `0.001`
   - **Display Name**: `Level Power Base Multiplier`
   - **Translation Key**: `gamerule.level_does_something.levelPowerBaseMultiplier.description`
   - **Description**: "The base percentage multiplier applied to the player's attributes per level or tier. A value of 0.001 represents a 0.1% increase. Default: 0.001."

3. **levelPowerMaxTier**
   - **Type**: `Integer`
   - **Default**: `10`
   - **Display Name**: `Level Power Max Tier`
   - **Translation Key**: `gamerule.level_does_something.levelPowerMaxTier.description`
   - **Description**: "The maximum tier multiplier allowed when using the exponential tier system. Prevents modifiers from doubling beyond this value. Default: 10 (caps multiplier at 1024x base)."

4. **levelPowerEnableAura**
   - **Type**: `Boolean`
   - **Default**: `true`
   - **Display Name**: `Level Power Enable Aura`
   - **Translation Key**: `gamerule.level_does_something.levelPowerEnableAura.description`
   - **Description**: "When true, players at or above level 30 will spawn ambient experience-themed particles visible to all surrounding players. Default: true."

5. **levelPowerEnableSoundPitch**
   - **Type**: `Boolean`
   - **Default**: `true`
   - **Display Name**: `Level Power Enable Sound Pitch`
   - **Translation Key**: `gamerule.level_does_something.levelPowerEnableSoundPitch.description`
   - **Description**: "When true, experience collection sounds will be pitch-shifted higher as the player's level increases, providing auditory feedback. Default: true."

---

### Client Configuration (`level-does-something-client.json`)

1. **`enableClientAuraParticles`**
   - **Type**: `Boolean`
   - **Default**: `true`
   - **Description**: "If set to false, disables rendering of all level-based experience aura particles locally, improving client performance."

2. **`enableClientSoundPitch`**
   - **Type**: `Boolean`
   - **Default**: `true`
   - **Description**: "If set to false, disables client-side pitch shifting of experience collection sounds."

---

## 4. Implementation Hooks & Mixins

The mod targets the following Minecraft 26.1.2 classes:

1. **`net.minecraft.world.entity.player.Player`**
   - **Mixin Target**: `tick()`
   - **Injection**: Detect if `experienceLevel != lastExperienceLevel`. If so, update the custom `AttributeModifier`s registered on the player for block break speed, attack damage, movement speed, luck, and max health.
   - **Mixin Target**: `defineSynchedData(SynchedEntityData.Builder)`
   - **Injection**: Register a custom data tracker field (e.g. `DATA_AURA_LEVEL`) to synchronize the player's visual level tier to all tracking clients.
   
2. **`net.minecraft.world.entity.ExperienceOrb`**
   - **Mixin Target**: `playerTouch(Player)`
   - **Injection**: Intercept the experience pickup sound event trigger and apply the pitch-shifted factor based on the player's level.

---

## 5. Out of Scope & Excluded Features
To preserve core vanilla progression dynamics and prevent overlap with other mechanics, the following attributes/features are explicitly out of scope:

1. **`Attributes.KNOCKBACK_RESISTANCE`**
   - *Reason*: Knockback resistance is fundamentally tied to armor and shield properties (e.g., Netherite gear). Adding it as a passive level-based benefit would bypass armor-tier rewards and disrupt standard PvP/PvE hit weight dynamics.
2. **`Attributes.ARMOR` & `Attributes.ARMOR_TOUGHNESS`**
   - *Reason*: Defense and protection must remain a material progression loop (wood $\rightarrow$ iron $\rightarrow$ diamond $\rightarrow$ netherite). Passive armor points from holding levels would make early-to-mid-game armors obsolete and trivialise environmental hazards too early.
3. **`Attributes.ATTACK_SPEED`**
   - *Reason*: Modifying attack speed directly disrupts weapon swing animations and sweep attack timers, leading to desyncs between client-side visuals and server-side hit registration.
4. **`Attributes.FLYING_SPEED` / `Attributes.GRAVITY`**
   - *Reason*: Manipulating flight speeds or local gravity values shifts the core movement engine drastically, resulting in server-side anti-cheat rubberbanding and erratic controls.

---

## 6. Quality Assurance & Testing Plan

### Debug Commands
- `/xp add @s 32 levels` - Verify that attributes scale up immediately.
- `/xp set @s 0 levels` - Verify that attributes return exactly to vanilla base values (checks floating point stability).
- `/gamerule levelPowerCurveType 1` - Switch curve to logarithmic and verify smooth progression.

### Manual Verification Cases
- **Attribute Modification Validation**: Check the player's Attributes tab/menu or verify that mining blocks or taking damage has a noticeable change at Level 128.
- **Client Synchronization**: Ensure that sprint speed adjustments do not cause client-server jitter or rubberbanding.
- **Death/Respawn Cleanup**: Ensure all attribute modifiers are safely cleaned up and recalculated upon player death and respawn.
- **Visual Toggles Validation**:
  - Test disabling `levelPowerEnableAura` GameRule on the server, and verify that no client spawns particles.
  - Test disabling `enableClientAuraParticles` in the client config, and verify that particles are disabled for that client even if the server GameRule is enabled.
