# 🎫 [BL-LDS-006] Remove `SynchedEntityData.defineId` from `PlayerMixin` for Vanilla-Client Compatibility

- **Ticket ID**: `[BL-LDS-006]`
- **Category**: `[BUGFIX]`
- **Priority**: `[CRITICAL]`
- **Scope**: `MC 26.1.2 (Root), MC 26.2, MC 26.3` (All 3 Active Anchors in Repo)
- **Status**: `✅ RESOLVED`
- **Resolution Date**: `2026-11-27`
- **Target Repository / Root**: `Instant Gratification Collection/Level Does Something`
- **Discovered By**: `verify_mods.py` Layer 3 Vanilla Server Compat Audit (Suite 12)
- **Grilling Status**: `✅ PASSED` (Verified against live `PlayerMixin.java`, `LevelAuraRenderer.java`, `LevelDoesSomethingFabric.java`, and Director decisions: All 3 anchors + Dual-Delivery + runtime `RULE_ENABLE_AURA` reactivity)

---

## 🎯 1. Goal & Definition of Done (The 4-Pillar Objective Architecture)

1. **North Star Objective**: Remove custom `SynchedEntityData.defineId(Player.class, ...)` registration from `PlayerMixin` across `26.1.2` (root), `26.2`, and `26.3`, replacing it with Dual-Delivery aura synchronization (optional S2C `CustomPacketPayload` for modded clients + throttled per-player `ServerLevel.sendParticles(...)` fallback for unmodded vanilla clients) so vanilla clients join cleanly without tracker ID corruption while modded clients retain full `enableClientAuraParticles` control.
2. **In-Game Experience (Before vs. After)**:
   - *Before*: `PlayerMixin` injects `leveldoessomething$DATA_AURA_LEVEL` into vanilla `Player`'s `SynchedEntityData`, shifting entity tracker IDs between modded servers and unmodded vanilla clients (`code_auditor.py` Suite 12 `CRITICAL` failure). Additionally, toggling `/gamerule leveldoessomething:level_power_enable_aura` at runtime does not update existing players until their XP level changes.
   - *After*: Vanilla `Player` `SynchedEntityData` stays 100% untouched. Modded clients receive optional `LevelAuraSyncPayload` (`leveldoessomething:aura_sync`) and render via `LevelAuraRenderer` (respecting `enableClientAuraParticles`); unmodded vanilla clients receive throttled `ServerLevel.sendParticles(ServerPlayer, ...)` every 4 ticks when `RULE_ENABLE_AURA` is `true` and `experienceLevel >= 30`; runtime `RULE_ENABLE_AURA` toggles take effect immediately.
3. **🚫 Anti-Goals (Off-Limits / Do Not Touch)**:
   - ❌ Do not modify player attribute scaling formulas in `PlayerLevelAttributesHandler.java`.
   - ❌ Do not alter experience pickup sound pitch shifting in `ClientPacketListenerMixin.java`.
   - ❌ Do not backport or scaffold legacy anchors (`1.20.1`, `1.21.1`, `1.21.11`) or Anchor 7 (`v26.x`).
   - ❌ Do not make Level Does Something a required client download (preserve Server-Side Primacy).
   - ❌ Do not refactor `ModMenuIntegration` in this ticket (tracked separately in [`BL-LDS-007`](./BL-LDS-007.md)).
4. **✅ Definition of Done (Binary Verification Checklist)**:
   - [x] `SynchedEntityData.defineId(Player.class, ...)` and `@Inject(method = "defineSynchedData", ...)` completely removed from `PlayerMixin.java` across `26.1.2` (root), `26.2`, and `26.3`.
   - [x] Dual-Delivery implemented: optional `LevelAuraSyncPayload` (`PayloadTypeRegistry.clientboundPlay().register(...)`, `ServerPlayNetworking.canSend(...)`, `ClientPlayNetworking.registerGlobalReceiver(...)`) updates `@Unique int leveldoessomething$auraLevel` on modded clients so `LevelAuraRenderer` respects `enableClientAuraParticles`, while unmodded clients (`!ServerPlayNetworking.canSend(...)`) receive throttled `ServerLevel.sendParticles(ServerPlayer, ...)` every 4 ticks.
   - [x] `PlayerMixin#onTick` reacts immediately to runtime `RULE_ENABLE_AURA` changes (not only when `experienceLevel` changes) and guards `isSpectator() || isInvisible()`.
   - [x] `code_auditor.py --check-vanilla-compat --scan-jars` passes with `0` CRITICAL findings across `26.1.2`, `26.2`, and `26.3`.
   - [x] `./gradlew.bat test build --no-daemon --console=plain` (plus `--init-script` LWJGL fix for `26.2`) passes all unit + negative parity tests, and `verify_mods.py --mod "Level Does Something" --live` passes vanilla-client join verification.

---

## ❓ 2. Problem Statement & Player Impact

`PlayerMixin.java` (line 30 & lines 39–42 in `src/`, `Level Does Something v26.2/`, and `Level Does Something v26.3/`) defines a custom `EntityDataAccessor<Integer>`:
```java
private static final EntityDataAccessor<Integer> leveldoessomething$DATA_AURA_LEVEL =
    SynchedEntityData.defineId(Player.class, EntityDataSerializers.INT);
```
Because vanilla clients do not register this extra `EntityDataAccessor` on `Player`, any server running `level-does-something` sends an unknown/shifted `SynchedEntityData` ID during `ClientboundSetEntityDataPacket`, causing decoder mismatches or disconnects for unmodded vanilla players. Furthermore, `PlayerMixin#onTick` (lines 50–64) only checks `RULE_ENABLE_AURA` when `experienceLevel` changes, ignoring live `/gamerule` toggles.

---

## 🔬 3. Architectural Blueprint & Target Classes

- **Target Files (per anchor: `26.1.2` root, `v26.2`, `v26.3`)**:
  - `network/LevelAuraSyncPayload.java` (new `CustomPacketPayload` record `(int entityId, int auraLevel)`)
  - `network/LevelAuraNetworking.java` (new server payload registration, `ServerPlayConnectionEvents.JOIN` sync, and Dual-Delivery helper)
  - `mixin/PlayerMixin.java` (remove `DATA_AURA_LEVEL` + `defineSynchedData`; store `@Unique int leveldoessomething$auraLevel`; track `leveldoessomething$lastAuraLevel`; broadcast payload on change; emit throttled `ServerLevel.sendParticles` to `!canSend` vanilla viewers every 4 ticks)
  - `LevelDoesSomethingFabric.java` (call `LevelAuraNetworking.registerServer()`)
  - `LevelDoesSomethingFabricClient.java` (register `ClientPlayNetworking.registerGlobalReceiver(LevelAuraSyncPayload.TYPE, ...)` updating `LevelAuraTracker` on client player entity)
- **Zero-Allocation Hot-Path Guard**:
  - Vanilla-client particle loop runs only when `(player.tickCount & 3) == 0 && enableAura && player.experienceLevel >= 30 && !player.isSpectator() && !player.isInvisible()`, using `player.getRandom()` (zero `new Random()` or stream/list allocations).

---

## ⚓ 4. Multi-Era Anchor Parity Specifications

- **MC 26.1.2 (Root)**: Java 25, Loom `1.15.2`, `mod_version=1.0.10+A-26.1.2` (bumped from `1.0.9`). Requires Step 0 `archiveReleaseJar`, `CHANGELOG.md`, `RELEASE_QUEUE.md`, and `${version}` manifest parity.
- **MC 26.2**: Java 25, Loom `1.15.2`, `mod_version=1.0.10+26.2` (bumped from `1.0.9+26.2`). Uses `--init-script` LWJGL `libraries.minecraft.net` override (`ERR-20261126-001`).
- **MC 26.3**: Java 25, Loom `1.15.5`, `mod_version=1.0.10+26.3` (bumped from `1.0.9+26.3`).

---

## 🛡️ 5. Edge Cases, Collisions & Failure Modes

| Surface | Risk | Mitigation |
| :--- | :--- | :--- |
| **Vanilla Contraptions / Entities** | Mutating `Player` `SynchedEntityData` breaks other entity mixins and vanilla clients | Remove `defineId` completely; store `@Unique int` on `PlayerMixin` and use optional `CustomPacketPayload` |
| **World Saves** | N/A (`EntityDataAccessor` is transient runtime state) | Zero NBT schema change |
| **Redstone / Timing** | Unthrottled `sendParticles` every tick floods network packets | Throttle vanilla `ServerLevel.sendParticles` to every 4 ticks (`(tickCount & 3) == 0`), radius `<= 32` blocks (`1024.0` sq), and skip invisible/spectator players |
| **Multiplayer Exploits** | Fake S2C state or client-side spam; newly joined modded clients missing existing players' aura | Server computes `player.experienceLevel` and `RULE_ENABLE_AURA` authoritatively; `ServerPlayConnectionEvents.JOIN` syncs active `>= 30` players to joining modded client; broadcast when `desiredAura != lastAuraLevel` |

---

## 🚀 6. Implementation Steps (Code-Level Plan)

1. **Step 0 (Baseline Quarantine & Infrastructure Parity)**: Restore trimmed `v26.3/CHANGELOG.md` entries, commit dirty `Doc/Platform Pages/` + `BACKLOG.md` + `v26.3/build.gradle`, bring `26.1.2` root to release parity (`archiveReleaseJar`, `CHANGELOG.md`, `RELEASE_QUEUE.md`, `${version}` in `fabric.mod.json`), verify `v26.2` LWJGL init-script build, and scaffold JUnit + Pillar 5 Sidedness Contract test suites across all 3 anchors (zero SemVer bump; `compileJava test` verification).
2. **Step 1 (`BL-LDS-006` Dual-Delivery Fix & Release)**: Remove `DATA_AURA_LEVEL` + `defineSynchedData` from `PlayerMixin.java`, add `LevelAuraSyncPayload` + `LevelAuraNetworking` + client receiver + runtime `RULE_ENABLE_AURA` reactivity + negative parity tests across `26.1.2`, `26.2`, and `26.3`; bump `1.0.10+A-26.1.2` / `1.0.10+26.2` / `1.0.10+26.3`; run 5-Pillar + Suite 12 + `verify_mods.py --live`; 5-Point Distribute and lockstep git push.
3. **Step 2 (Studio Governance & Closure)**: Update `History.md`, `MASTER_RELEASE_QUEUE.md`, Clean Sweep `BL-LDS-006` out of `BACKLOG.md`, and sync `MASTER_BACKLOG_BOARD.md`, `MASTER_ANCHOR_BOARD.md`, `platform_projects.json`, and `watched_projects.json`.
4. **Step 3 (`/learn` Retrospective)**: Run telemetry report, auto-graduate Rungs 2–4, workspace sweep, and `.agents/` git push.

---

## 🏁 7. 5-Pillar Verification Checklist

- [x] **Pillar 1 (Chaos Fuzzing)**: Aura particle count & Dual-Delivery logic fuzzed across negative, zero, `29`, `30`, and `Integer.MAX_VALUE` XP levels.
- [x] **Pillar 2 (Concurrency Load)**: Stateless / thread-safe payload & `ServerLevel` particle dispatch with zero shared mutable unsynchronized collections.
- [x] **Pillar 3 (Brigadier / GameRule AST)**: GameRule `RULE_ENABLE_AURA` live toggle (`true` and `false` polarity) verified in-memory.
- [x] **Pillar 4 (Negative Parity & Live Vanilla Join)**: Unit tests assert `PlayerMixin` declares zero `SynchedEntityData.defineId` / `defineSynchedData` hooks; `verify_mods.py --mod "Level Does Something" --live` passes unmodded vanilla-client server join.
- [x] **Pillar 5 (Sidedness & Suite 12)**: `code_auditor.py --check-vanilla-compat --scan-jars` passes with `0` CRITICAL findings across `26.1.2`, `26.2`, and `26.3`, plus `*SidednessContractTest` asserts zero client imports in common bytecode.

---

## 🛡️ 8. Autonomous Goal Dependability & Self-Healing Contract

- Run all Gradle tasks with `--no-daemon --console=plain` (plus `--init-script` LWJGL `libraries.minecraft.net` override on `26.2` per `ERR-20261126-001`).
- Bump lockstep SemVer targets: `1.0.10+A-26.1.2` (Root), `1.0.10+26.2`, `1.0.10+26.3`.
- Allow 1 in-loop compile retry on minor signature shifts before reporting blockers.
