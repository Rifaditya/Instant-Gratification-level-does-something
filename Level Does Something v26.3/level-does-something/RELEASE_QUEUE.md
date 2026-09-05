# 📋 Level Does Something Release Queue & Backlog

This file tracks which built versions (from `/Archive/builds/`) have been manually uploaded to Modrinth/CurseForge.
Open this file in your editor and change `[ ]` to `[x]` when you publish a version.

## 🚀 Published & Backlog Queue

* ⏸️ **1.0.5+26.3** (On-Hold) - Modern Lead build held in archive until MC 26.2 reaches 1.0.5 feature parity on Modrinth/CurseForge. - - **YACL v3 Migration**: Replaced Cloth Config screen with YetAnotherConfigLib v3. - - **Ko-fi Support Integration**: Added top-pinned solo developer support button linking directly to Ko-fi in config screen.
* ⏸️ **1.0.4+26.3** (On-Hold) - Modern Lead build held in archive until MC 26.2 reaches 1.0.4 feature parity on Modrinth/CurseForge.
- [x] **`1.0.0-draft.1`** (2026-05-22) - - **Initial Concept**: Outlined the core idea of giving players statistical buffs based on accumulated experience levels. - - **Exponential Step Curve**: Proposed power-of-two level thresholds (2, 4, 8, 16, 32...) that double base modifiers.
- [x] **`1.0.0-draft.2`** (2026-05-22) - - **Logarithmic Scaling Default**: Shifted the default progression curve from Step-wise Exponential to a Smooth Logarithmic Curve (`Curve 1`) for more balanced, early-game friendly progression. - - **Extended Passive Buffs**: Added passive scaling for `Attributes.LUCK` and `Attributes.MAX_HEALTH` to make higher level hoarding even more satisfying.
- [x] **`1.0.1+A-26.1.2`** (2026-05-22) - - **Client Sound Pitch Shift Mixin**: Implemented `ClientPacketListenerMixin` to intercept experience pickup sounds and shift pitch higher based on level. - - **Localization File**: Created standard `en_us.json` language assets defining spaces-based GameRule names, detailed tooltips, and client config options.
- [x] **`1.0.2+A-26.1.2`** (2026-05-22) - - **Build Configuration**: Restored mapping resolution in `build.gradle` by uncommenting `mappings loom.officialMojangMappings()`.
- [x] **`1.0.3+A-26.1.2`** (2026-05-22) - - **Build Configuration**: Switched GUI dependencies to use `compileOnly` and `runtimeOnly` configurations, and commented out official Mojang mappings to resolve compile issues in non-obfuscated environments.
- [x] **`1.0.4+A-26.1.2`** (2026-05-22) - - **Mixin Prefixing**: Prefixed `@Unique` fields and methods in `PlayerMixin` with `leveldoessomething$` to prevent namespace collisions. - - **Unused Imports**: Cleaned up unused imports across Java classes to satisfy the Sanitary Verification audit checklist.
