# 🎫 [BL-LDS-007] Guard Optional Cloth Config Screen Factory in Root `26.1.2` `ModMenuIntegration`

- **Ticket ID**: `[BL-LDS-007]`
- **Category**: `[BUGFIX]`
- **Priority**: `[HIGH]`
- **Scope**: `MC 26.1.2 (Root)` (`26.2` and `26.3` already safe via `GuiHelper.getOptionalYaclFactory`)
- **Status**: `📌 DEFERRED`
- **Target Repository / Root**: `Instant Gratification Collection/Level Does Something`
- **Discovered By**: Stage 1 `/code-auditor` Mode 1 Baseline Audit (`BL-LDS-006` pipeline)
- **Grilling Status**: `✅ PASSED` (Verified against live `ModMenuIntegration.java` and `fabric.mod.json` in root `26.1.2` vs `v26.2`/`v26.3`)

---

## 🎯 1. Goal & Definition of Done (The 4-Pillar Objective Architecture)

1. **North Star Objective**: Guard `ModMenuIntegration#getModConfigScreenFactory()` in root `26.1.2` with `FabricLoader.getInstance().isModLoaded("cloth-config")` (returning `parent -> null` when absent) so players running ModMenu without optional Cloth Config do not crash when opening the ModMenu screen.
2. **In-Game Experience (Before vs. After)**:
   - *Before*: `ModMenuIntegration.java:L13` returns `ClothConfigScreenHelper::createConfigScreen` unconditionally; clicking config or loading ModMenu without `cloth-config` throws `NoClassDefFoundError: me/shedaniel/clothconfig2/api/ConfigBuilder`.
   - *After*: Safely checks `FabricLoader.getInstance().isModLoaded("cloth-config")` before referencing `ClothConfigScreenHelper`, matching the optional `"suggests"` contract in `fabric.mod.json`.
3. **🚫 Anti-Goals (Off-Limits / Do Not Touch)**:
   - ❌ Do not migrate `26.1.2` from Cloth Config to YACL in this bugfix ticket.
   - ❌ Do not modify `v26.2` or `v26.3` `ModMenuIntegration.java` (already guarded via `GuiHelper.getOptionalYaclFactory`).
   - ❌ Do not alter `LevelDoesSomethingConfig` JSON schema or field names.
4. **✅ Definition of Done (Binary Verification Checklist)**:
   - [ ] `ModMenuIntegration.java` in root `26.1.2` guards `cloth-config` with `FabricLoader.getInstance().isModLoaded("cloth-config")`.
   - [ ] Automated test simulates `ModMenuIntegration` invocation without `cloth-config` loaded (zero `NoClassDefFoundError`).
   - [ ] `./gradlew.bat test build --no-daemon --console=plain` passes on `26.1.2` with `0` Suite 1–13 findings.

---

## 🔬 2. Architectural Blueprint & Blast Radius

- **Target File**: `src/main/java/net/instantgratification/leveldoessomething/config/ModMenuIntegration.java` (lines 10–15)
- **Blast Radius**:

| Surface | Risk | Mitigation |
| :--- | :--- | :--- |
| **Vanilla Contraptions / Entities** | None (client ModMenu entrypoint only) | Isolated to `config/ModMenuIntegration.java` |
| **World Saves** | None | Zero NBT/save impact |
| **Redstone / Timing** | None | Client GUI callback only |
| **Multiplayer Exploits** | None | Client-only ModMenu screen factory |
