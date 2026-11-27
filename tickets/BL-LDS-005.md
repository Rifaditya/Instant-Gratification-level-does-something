# 🎫 [BL-LDS-005] Adopt Per-Mod Advancement-Triggered Early Access Support Prompt (`DasikPromptRegistry`)

- **Ticket ID**: `[BL-LDS-005]`
- **Category**: `[TECH_DEBT]` / `[GOVERNANCE]`
- **Priority**: `[MEDIUM]`
- **Target Version**: `Opportunistic Per-Mod (Next Release Cycle per Single-Ticket Release Policy)`
- **Scope**: `LevelDoesSomethingFabric.java`, `LevelDoesSomethingClientConfig.java`, `ModMenu / YACL Config Screen`, `gradle.properties`, `fabric.mod.json`, `LevelDoesSomethingSidednessContractTest.java`
- **Status**: `📌 DEFERRED`
- **Grilling Status**: `✅ PASSED`
- **Target Repository / Root**: `Instant Gratification Collection/Level Does Something`
- **Parent Umbrella**: [BL-WS-047](file:///E:/Minecraft%20Project/tickets/BL-WS-047.md)
- **Upstream Engine**: [BL-DASIK-022](file:///E:/Minecraft%20Project/MC-DasikLibrary/tickets/BL-DASIK-022.md) (`✅ RESOLVED` in `mavenLocal()`)
- **Governance Mandate**: [Monetization & Creator Support Policy](file:///E:/Minecraft%20Project/.agents/rules/Monetization%20&%20Creator%20Support%20Policy.md) (§6–§7), [Single-Ticket Release Policy](file:///E:/Minecraft%20Project/.agents/rules/Single-Ticket%20Release%20Policy.md)
- **Date Added**: `2026-11-23`

---

## 🎯 1. Goal & Definition of Done (The 4-Pillar Objective Architecture)

1. **North Star Objective**: Register **Level Does Something** (`MOD_ID`: `leveldoessomething`) with `DasikLibrary`'s centralized `DasikPromptRegistry` using the Director-approved trigger advancement `minecraft:adventure/kill_a_mob` and a pure-Java per-mod client opt-out toggle (`LevelDoesSomethingClientConfig.showEarlyAccessPrompt = true`), exposed cleanly in YACL / ModMenu without duplicating prompt logic or server `GameRules`.
2. **In-Game Experience (Before vs. After)**:
   - *Before*: **Level Does Something** has no milestone-triggered Early Access prompt wired into `DasikPromptRegistry`, relying only on generic world-start greetings that players easily tune out on login, and lacks a per-mod opt-out toggle in its client settings.
   - *After*: When a player earns `minecraft:adventure/kill_a_mob` while playing with **Level Does Something**, `DasikLibrary`'s central 5-minute FIFO pacing queue gently delivers a single Early Access & Discord chat note 3 seconds later—unless the player toggled `showEarlyAccessPrompt` off for **Level Does Something** (or silenced support prompts globally in `DasikLibrary`).
3. **🚫 Anti-Goals (Off-Limits / Do Not Touch)**:
   - ❌ **Zero Custom Advancement Listeners, Polling, or Pacing Queues**: Do NOT write custom `ClientAdvancements` mixins, chat prompt builders, tick counters, or cooldown timers inside **Level Does Something**. All advancement polling, 3-second initial delay, and 5-minute global FIFO pacing live strictly in `DasikLibrary`.
   - ❌ **Zero Server `GameRules` or Server-to-Vanilla Chat Broadcasts**: `showEarlyAccessPrompt` MUST remain strictly a client-side config boolean (`syncGameRule = false`). Never register a `GameRule` or send support messages from server ticks.
   - ❌ **Zero Multi-Ticket Bundling**: Per [Single-Ticket Release Policy](file:///E:/Minecraft%20Project/.agents/rules/Single-Ticket%20Release%20Policy.md), do NOT bundle this governance ticket into an unrelated bugfix or gameplay release (`1 Ticket = 1 Version Bump`).
   - ❌ **Zero Client Class Leakage in Common Code**: `LevelDoesSomethingClientConfig` MUST remain pure Java with zero `net.minecraft.client.*`, `com.mojang.blaze3d.*`, YACL, or ModMenu imports so dedicated servers load common classes without crashing.
4. **✅ Definition of Done (Binary Verification Checklist)**:
   - [ ] `dasik_library_version` in `gradle.properties` and `dasik-library` dependency bounds in `fabric.mod.json` (and `neoforge.mods.toml`/`mods.toml` if multi-loader) meet `>= 1.13.2` (`26.2`/`26.3`), `>= 1.13.2+26.1.2` (`26.1.2`), `>= 1.4.4+1.21.11` (`1.21.11`), `>= 1.4.5+1.21.1` (`1.21.1`), and `>= 1.4.7+1.20.1` (`1.20.1`) across all compiled anchors.
   - [ ] `LevelDoesSomethingClientConfig.showEarlyAccessPrompt` (default `true`) exists as pure Java and is toggleable cleanly inside the mod's YACL / ModMenu screen.
   - [ ] `net.instantgratification.leveldoessomething.LevelDoesSomethingFabric` calls `DasikPromptRegistry.register(MOD_ID, MOD_NAME, TRIGGER_ADVANCEMENT_ID, () -> LevelDoesSomethingClientConfig.showEarlyAccessPrompt)` with `TRIGGER_ADVANCEMENT_ID = "minecraft:adventure/kill_a_mob"`.
   - [ ] `LevelDoesSomethingSidednessContractTest` passes dedicated-server classloading, dynamic supplier toggle, GPLv3 header, and zero-`GameRule` assertions across all anchors (`./gradlew test build --no-daemon --console=plain`).

---

## ❓ 2. Problem Statement & Player Impact

1. **Timing Disconnect at World Login**: Under legacy `BL-WS-030` behavior, creator support prompts fired only on world login, when players are busy loading chunks and dismiss chat passively. Under [Monetization & Creator Support Policy](file:///E:/Minecraft%20Project/.agents/rules/Monetization%20&%20Creator%20Support%20Policy.md) §7 and [`BL-DASIK-022`](file:///E:/Minecraft%20Project/MC-DasikLibrary/tickets/BL-DASIK-022.md), each mod triggers at a meaningful gameplay milestone (`minecraft:adventure/kill_a_mob`).
2. **Stale `dasik-library` Version Pins**: Current anchor manifests pin older `dasik_library_version` releases that predate `net.dasik.social.api.prompt.DasikPromptRegistry` (`1.13.2` / `1.13.2+26.1.2` / `1.4.4+1.21.11` / `1.4.5+1.21.1` / `1.4.7+1.20.1`), which must be bumped before compilation.

---

## 🔬 3. Architectural Blueprint & Target Classes

- **Detected Ground-Truth Targets**:
  - Mod ID(s): `leveldoessomething` | Display Name: `"Level Does Something"` | `fabric.mod.json` Environment: `*`
  - Primary Entrypoint: `net.instantgratification.leveldoessomething.LevelDoesSomethingFabric` (`ModInitializer`)
  - Existing Config / GUI Files: `LevelDoesSomethingConfig.java`, `ModMenuIntegration.java`, `YaclScreenHelper.java`, `ClothConfigScreenHelper.java`
  - Sidedness Test Target: Create `LevelDoesSomethingSidednessContractTest.java` matching studio template standards
- **Approved Trigger Constant**:
  ```java
  public static final String MOD_ID = "leveldoessomething";
  public static final String MOD_NAME = "Level Does Something";
  public static final String TRIGGER_ADVANCEMENT_ID = "minecraft:adventure/kill_a_mob";
  ```
- **1-Line Central Registration (`net.instantgratification.leveldoessomething.LevelDoesSomethingFabric#onInitialize()`)**:
  ```java
  DasikPromptRegistry.register(
          MOD_ID,
          MOD_NAME,
          TRIGGER_ADVANCEMENT_ID,
          () -> LevelDoesSomethingClientConfig.showEarlyAccessPrompt
  );
  ```
- **Pure-Java Client Opt-Out Holder (`net.instantgratification.leveldoessomething.config.LevelDoesSomethingClientConfig`)**:
  ```java
  // Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
  package net.instantgratification.leveldoessomething.config;

  /**
   * Pure-Java client configuration holder for Level Does Something.
   * Strictly a client-side opt-out toggle (never a server GameRule) with zero
   * net.minecraft.client.* or com.mojang.blaze3d.* linkage.
   */
  public final class LevelDoesSomethingClientConfig {
      public static boolean showEarlyAccessPrompt = true;

      private LevelDoesSomethingClientConfig() {}
  }
  ```
- **YACL / ModMenu Exposure**:
- **Existing GUI Architecture**: Uses custom `YaclScreenHelper` / `ModMenuIntegration` (`net.instantgratification.leveldoessomething.config.ModMenuIntegration`).
- **Pure-Java Opt-Out Holder**: Create `net.instantgratification.leveldoessomething.config.LevelDoesSomethingClientConfig` containing `public static boolean showEarlyAccessPrompt = true;` (with zero `net.minecraft.client.*` imports) and add a dedicated boolean toggle option for `showEarlyAccessPrompt` in `YaclScreenHelper` / ModMenu config screen (strictly client-side persistence, never a server `GameRule`).

---

## ⚓ 4. Multi-Era Anchor Parity Specifications

- **Required `dasik_library_version` Floor Across Studio Anchors** (all published in `mavenLocal()` under [`BL-DASIK-022`](file:///E:/Minecraft%20Project/MC-DasikLibrary/tickets/BL-DASIK-022.md)):
  - **MC `26.2` / `26.3` (`26.x`)**: `>= 1.13.2` (`fabric.mod.json`: `"dasik-library": ">=1.13.2"`)
  - **MC `26.1.2`**: `>= 1.13.2+26.1.2` (`fabric.mod.json`: `"dasik-library": ">=1.13.2+26.1.2"`)
  - **MC `1.21.11`**: `>= 1.4.4+1.21.11` (`fabric.mod.json`: `"dasik-library": ">=1.4.4+1.21.11"`)
  - **MC `1.21.1`**: `>= 1.4.5+1.21.1` (`fabric.mod.json`: `"dasik-library": ">=1.4.5+1.21.1"`)
  - **MC `1.20.1`**: `>= 1.4.7+1.20.1` (`fabric.mod.json`: `"dasik-library": ">=1.4.7+1.20.1"`)
- **Discovered On-Disk Anchor Pins in This Repository**:
  - `gradle.properties` (MC `26.1.2`): current `dasik_library_version = 1.7.1` -> bump to **`>= 1.13.2+26.1.2`**.
  - `Level Does Something v26.2\level-does-something\gradle.properties` (MC `26.2`): current `dasik_library_version = 1.8.38` -> bump to **`>= 1.13.2`**.
  - `Level Does Something v26.3\level-does-something\gradle.properties` (MC `26.3`): current `dasik_library_version = 1.9.2` -> bump to **`>= 1.13.2`**.

---

## 🛡️ 5. Edge Cases, Collisions & Failure Modes

| Dimension / Risk | Intended Impact | Forbidden Collateral Impact | Safeguard |
| :--- | :--- | :--- | :--- |
| **Dedicated Server Sidedness (Knot)** | `DasikPromptRegistry.register(...)` executes safely on server & client | `ClassNotFoundException` / `NoClassDefFoundError` on `net.minecraft.client.*` | `LevelDoesSomethingClientConfig` holds zero client imports; verified by `LevelDoesSomethingSidednessContractTest` |
| **Optional GUI Mods Absent** | Mod runs normally if ModMenu or YACL is not installed | Crash during config screen construction or initialization | Keep `modmenu` & `yet-another-config-lib` strictly in `"suggests"` with safe fallback |
| **Vanilla Clients on Modded Server** | Vanilla players connect seamlessly without downloading the mod | Server broadcasting prompt packets or chat spam to vanilla clients | Prompt polling & rendering stay 100% client-side inside `DasikLibrary` |
| **Shared Vanilla Trigger Collision** | Shared milestone `minecraft:adventure/kill_a_mob` across multiple Dasik mods stays non-intrusive | Stacked chat spam when multiple mods trigger back-to-back | `DasikLibrary`'s 5-minute (6000-tick) global FIFO pacing queue spaces prompts cleanly |
| **Server `GameRule` Isolation** | Opt-out toggle remains local to player's client preferences | Polluting world saves or `/gamerule` tab-completion with prompt flags | Never register any `GameRule` for Early Access prompts (`syncGameRule = false`) |
| **Single-Ticket Release Cadence** | Ships cleanly as its own dedicated `1 Ticket = 1 Version Bump` release | Bundling with unrelated bugfixes or breaking SemVer continuity | Execute opportunistically per [Single-Ticket Release Policy](file:///E:/Minecraft%20Project/.agents/rules/Single-Ticket%20Release%20Policy.md) |

---

## 🚀 6. Implementation Steps (Code-Level Plan)

1. **Lifecycle & SemVer Pre-Bump**: Confirm target release slot per [Single-Ticket Release Policy](file:///E:/Minecraft%20Project/.agents/rules/Single-Ticket%20Release%20Policy.md) and pre-bump `mod_version` in `gradle.properties` across each targeted anchor.
2. **Bump `dasik-library` Pins**: Update `dasik_library_version` in `gradle.properties` and `fabric.mod.json` (plus `neoforge.mods.toml` / `mods.toml` if present) to `>= 1.13.2` (`26.2`/`26.3`), `>= 1.13.2+26.1.2` (`26.1.2`), `>= 1.4.4+1.21.11` (`1.21.11`), `>= 1.4.5+1.21.1` (`1.21.1`), and `>= 1.4.7+1.20.1` (`1.20.1`).
3. **Add Pure-Java `LevelDoesSomethingClientConfig` & YACL Toggle**: Create `net.instantgratification.leveldoessomething.config.LevelDoesSomethingClientConfig` with `public static boolean showEarlyAccessPrompt = true;`, persist client-side state if applicable, and wire the toggle into the YACL / ModMenu screen (strictly `syncGameRule = false`).
4. **Register Trigger in Entrypoint**: Add `TRIGGER_ADVANCEMENT_ID = "minecraft:adventure/kill_a_mob"` and the 1-line `DasikPromptRegistry.register(MOD_ID, MOD_NAME, TRIGGER_ADVANCEMENT_ID, () -> LevelDoesSomethingClientConfig.showEarlyAccessPrompt)` call inside `net.instantgratification.leveldoessomething.LevelDoesSomethingFabric#onInitialize()`.
5. **Update / Add `LevelDoesSomethingSidednessContractTest`**: Assert `Class.forName(LevelDoesSomethingClientConfig)` loads in a headless server JVM, `DasikPromptRegistry.isRegistered("minecraft:adventure/kill_a_mob")` passes post-init, toggling `showEarlyAccessPrompt` dynamically flips `PromptRegistration.isEnabled()`, and zero `GameRules` match `early_access|support_prompt`.
6. **Multi-Anchor Verify & Release**: Run `./gradlew test build --no-daemon --console=plain` across all compiled anchors, update `CHANGELOG.md` & `History.md`, and complete the 5-Link Release Chain.

---

## 🏁 7. 5-Pillar Verification Checklist

- [ ] **Pillar 1 (Chaos Property Fuzzing)**: Config boolean persistence & default fallback (`true`) survive malformed/missing JSON states without exceptions.
- [ ] **Pillar 2 (Concurrency Load)**: `DasikPromptRegistry` concurrent registration reads and dynamic `BooleanSupplier` evaluations pass thread-safe access checks without races.
- [ ] **Pillar 3 (Registration & Brigadier AST)**: Confirm existing mod commands/registries remain unaffected and zero `GameRule` keys are registered for `early_access` or `support_prompt`.
- [ ] **Pillar 4 (Behavioral State Simulation)**: Headless unit/integration test confirms `DasikPromptRegistry.get("minecraft:adventure/kill_a_mob")` is registered on mod init and dynamically reflects `LevelDoesSomethingClientConfig.showEarlyAccessPrompt` (`true` -> `false` -> `true`).
- [ ] **Pillar 5 (`LevelDoesSomethingSidednessContractTest` Dedicated-Server Safety & License Hygiene)**: Dedicated-server simulation confirms zero `net.minecraft.client.*` / `com.mojang.blaze3d.*` leakage in common config/init paths, zero `GameRule` prompt keys, and valid single-line `// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3` headers.
- [ ] **Multi-Anchor Compilation**: `./gradlew test build --no-daemon --console=plain` exits `0` across all active anchors for this mod.

---

## 🛡️ 8. Autonomous Goal Dependability & Self-Healing Contract

- **Pre-Flight Disk Truth**: Verify `net.dasik.social.api.prompt.DasikPromptRegistry` artifacts in `mavenLocal()` (`~/.m2/repository/net/dasik/social/dasik-library/`) before running Gradle builds across anchors (`26.3`, `26.2`, `26.1.2`, `1.21.11`, `1.21.1`, `1.20.1`).
- **Self-Healing Budget**: Run Gradle strictly with `--no-daemon --console=plain`; allow a 1-attempt self-healing retry if an older anchor uses a distinct `MOD_ID` constant or subproject `settings.gradle` layout, and never mark this ticket `✅ RESOLVED` until all compiled anchors pass `LevelDoesSomethingSidednessContractTest` and clean build checks.

---

## 🔗 9. Cross-References & Governance

- Parent Umbrella Ticket: [BL-WS-047](file:///E:/Minecraft%20Project/tickets/BL-WS-047.md)
- Upstream Engine Ticket: [BL-DASIK-022](file:///E:/Minecraft%20Project/MC-DasikLibrary/tickets/BL-DASIK-022.md)
- Governing Policies:
  - [Monetization & Creator Support Policy](file:///E:/Minecraft%20Project/.agents/rules/Monetization%20&%20Creator%20Support%20Policy.md) (§6–§7)
  - [Single-Ticket Release Policy](file:///E:/Minecraft%20Project/.agents/rules/Single-Ticket%20Release%20Policy.md)
  - [Bytecode Testing & ClassLoader Safety Policy](file:///E:/Minecraft%20Project/.agents/rules/Bytecode%20Testing%20&%20ClassLoader%20Safety%20Policy.md)
  - [Optional GUI Dependencies](file:///E:/Minecraft%20Project/.agents/rules/Optional%20GUI%20Dependencies.md)
