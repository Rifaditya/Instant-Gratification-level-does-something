# Design Changelog History

All conceptual changes and design refinements for **Level Does Something** are recorded below.

---

## [1.0.4+A-26.1.2] - 2026-05-22
### Fixed
- **Mixin Prefixing**: Prefixed `@Unique` fields and methods in `PlayerMixin` with `leveldoessomething$` to prevent namespace collisions.
- **Unused Imports**: Cleaned up unused imports across Java classes to satisfy the Sanitary Verification audit checklist.

### Changed
- **Mixin Configuration**: Configured missing `refmap` property inside `level-does-something.mixins.json` pointing to `level-does-something-refmap.json`.
- **Mod Metadata**: Added suggested dependencies for Cloth Config and ModMenu inside `fabric.mod.json`.
- **Documentation**: Generated the root `README.md`, `Doc/Players/guide.md`, and CurseForge/Modrinth platform description pages following the Standard Alignment standard.

## [1.0.3+A-26.1.2] - 2026-05-22
### Changed
- **Build Configuration**: Switched GUI dependencies to use `compileOnly` and `runtimeOnly` configurations, and commented out official Mojang mappings to resolve compile issues in non-obfuscated environments.

## [1.0.2+A-26.1.2] - 2026-05-22
### Changed
- **Build Configuration**: Restored mapping resolution in `build.gradle` by uncommenting `mappings loom.officialMojangMappings()`.

## [1.0.1+A-26.1.2] - 2026-05-22
### Added
- **Client Sound Pitch Shift Mixin**: Implemented `ClientPacketListenerMixin` to intercept experience pickup sounds and shift pitch higher based on level.
- **Localization File**: Created standard `en_us.json` language assets defining spaces-based GameRule names, detailed tooltips, and client config options.
- **Config & Build Verification**: Completed build preparations and resolved critical `getBoolean`/`getInt` GameRule lookup methods to use Mojang-mapped `.get(...)` calls.

### Changed
- **Removed Unused Mixin**: Cleaned up the mixins config JSON by removing the empty `ExperienceOrbMixin`.

---

## [1.0.0-draft.2] - 2026-05-22
### Added
- **Logarithmic Scaling Default**: Shifted the default progression curve from Step-wise Exponential to a Smooth Logarithmic Curve (`Curve 1`) for more balanced, early-game friendly progression.
- **Extended Passive Buffs**: Added passive scaling for `Attributes.LUCK` and `Attributes.MAX_HEALTH` to make higher level hoarding even more satisfying.
- **Out of Scope & Exclusions Section**: Formally excluded Knockback Resistance, Armor/Armor Toughness, Attack Speed, and Flight Speed/Gravity with documented design and technical reasoning.
- **Dual-Layer Visual Toggles**:
  - Added server-side `levelPowerEnableAura` GameRule toggle.
  - Added client-side config file (`level-does-something-client.json`) with `enableClientAuraParticles` and `enableClientSoundPitch` properties to allow players to disable graphics/sounds locally.
- **Architecture Overview**: Created `architecture.md` detailing the visual aura synchronization flow using native DataTrackers.

### Changed
- Re-aligned all documentation files to follow standard core guidelines, removing internal codename references.

---

## [1.0.0-draft.1] - 2026-05-22
### Added
- **Initial Concept**: Outlined the core idea of giving players statistical buffs based on accumulated experience levels.
- **Exponential Step Curve**: Proposed power-of-two level thresholds (2, 4, 8, 16, 32...) that double base modifiers.
- **Initial Attributes**: Planned boosts for block break speed, attack damage, and movement speed.
- **Aesthetic Ideas**: Suggested faint golden experience particles and sound pitch modifications for high-level players.
