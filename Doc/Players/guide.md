# 📖 Level Does Something! - Player Guide

Welcome to the official manual for **Level Does Something!** This mod is designed to make experience level hoarding rewarding by translating your level points into passive physical attributes and visual/auditory feedback.

---

## 🏁 Getting Started
1. **Dependencies**: Ensure you have **Fabric API** and **DasikLibrary** installed.
2. **Setup**: Drop the mod jar into your `.minecraft/mods` folder.
3. **Usage**: Experience points collected in-game will automatically start scaling your attributes based on the default configuration.

---

## 📈 Progression Curves

The mod supports three distinct progression math algorithms to calculate your attribute boosts. Server operators can configure this via the `/gamerule leveldoessomething:levelPowerCurveType <0, 1, or 2>` command.

### 1. Smooth Logarithmic (Curve Type 1 - Default)
*   **Formula**: `Multiplier = ln(Level + 1)`
*   **Description**: Grants quick, noticeable benefits at early levels, which gradually slow down at higher levels. This prevents players from becoming excessively overpowered while still rewarding level hoarding.
*   **Multiplier Example**:
    *   Level 0: `0.0x`
    *   Level 5: `~1.79x`
    *   Level 30: `~3.43x`
    *   Level 100: `~4.62x`

### 2. Exponential Steps (Curve Type 0)
*   **Formula**: Steps at power-of-two milestones (`Level 2, 4, 8, 16, 32...`), doubling the multiplier at each step, up to a configurable maximum tier (Default: Tier 10).
*   **Description**: Ideal for players who enjoy hitting large, distinct power milestones.
*   **Multiplier Example**:
    *   Levels 0-1: `0.0x`
    *   Levels 2-3: `1.0x` (Tier 1)
    *   Levels 4-7: `2.0x` (Tier 2)
    *   Levels 8-15: `4.0x` (Tier 3)
    *   Levels 16-31: `8.0x` (Tier 4)
    *   Level 32+: `16.0x` (Tier 5, up to configured Max Tier cap)

### 3. Simple Linear (Curve Type 2)
*   **Formula**: `Multiplier = Level`
*   **Description**: Flat, constant scaling per level. A direct 1:1 translation of level points to attribute units.
*   **Multiplier Example**:
    *   Level 5: `5.0x`
    *   Level 30: `30.0x`
    *   Level 100: `100.0x`

---

## 💪 Attribute Multipliers & Modifiers

The attribute modifier amounts applied are calculated as:
`Base Attribute Value * (ScaleFactor * (BaseMultiplier / 10.0))`
*(Note: A Base Multiplier of 10 basis points represents a multiplier ratio of 1.0, translating to a ~0.1% increase per unit level scale.)*

### Attributes Affected:
1.  **Block Break Speed** (`Attributes.BLOCK_BREAK_SPEED`): Scales by `0.01` per level unit. Helps you mine blocks faster.
2.  **Attack Damage** (`Attributes.ATTACK_DAMAGE`): Scales by `0.005` per level unit. Increases melee weapon damage.
3.  **Movement Speed** (`Attributes.MOVEMENT_SPEED`): Scales by `0.002` per level unit. Walk and run faster.
4.  **Luck** (`Attributes.LUCK`): Scales by `0.01` per level unit. Improves quality of loot generated in unopened chests or caught while fishing.
5.  **Max Health** (`Attributes.MAX_HEALTH`): Scales by `0.01` per level unit. Increases maximum health, granting extra hearts dynamically!

---

## 🎨 Ambient Feedback & Milestones

### Swirling Aura Particles
*   When a player reaches **Level 30 or higher**, a glowing ambient particle aura of green and gold sparkles swirls around them.
*   The particle density scales up slightly with higher levels (up to level 80).
*   Visual particles are synchronized through Entity Data keys, meaning all tracking players in range can see your aura.

### Auditory Milestone Chimes & XP Pitch-Shifting
*   **Milestone Chimes**: Triumphant bells are played globally when a player crosses levels **30, 60, and 100** for the first time in that life.
*   **Sound Pitch-Shifting**: Experience orb pickup sounds shift higher in pitch as you level up, giving acoustic feedback on your mounting power.

---

## ⚙️ Settings & Performance Toggles

To accommodate low-end PCs or avoid screen clutter, the mod features client-side toggles in addition to server GameRules.

### Client-Side Configuration
Located in `.minecraft/config/level-does-something-client.json` (or accessible through ModMenu and Cloth Config):
*   `enableClientAuraParticles` (Default: `true`): Set to `false` to disable particle rendering for all players locally. Useful if experiencing frame rate drops or to keep the screen clean.
*   `enableClientSoundPitch` (Default: `true`): Set to `false` to play experience pickup sounds at vanilla pitch.

---

## 🛑 Out of Scope Exclusions

Certain attributes are deliberately left out of this mod for design and technical balance:
*   **Knockback / Knockback Resistance**: Heavy knockback modifiers are managed exclusively by armor/shield attributes. Altering this based on level causes jarring combat physics.
*   **Armor / Armor Toughness**: Progression should require equipment. Leveling up shouldn't substitute armor tiers.
*   **Attack Speed**: Scaling attack speed directly destroys vanilla combat timing, resulting in visual jitter or weapon damage hit-registry errors.
*   **Gravity / Flight Speed**: Altering player gravity breaks physics engines on servers and causes rubberbanding or fall damage calculation errors.
