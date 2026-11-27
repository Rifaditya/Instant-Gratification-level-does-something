# 🎫 [BL-LDS-008] Annotate Client Classes (`@Environment(EnvType.CLIENT)`) & Static Mixin Refmap Hygiene

- **Ticket ID**: `[BL-LDS-008]`
- **Category**: `[TECH_DEBT]`
- **Priority**: `[MEDIUM]`
- **Scope**: `MC 26.1.2 (Root), MC 26.2, MC 26.3`
- **Status**: `📌 DEFERRED`
- **Target Repository / Root**: `Instant Gratification Collection/Level Does Something`
- **Discovered By**: Step 0 `code_auditor.py` Baseline Audit (`BL-LDS-006` pipeline)
- **Grilling Status**: `✅ PASSED` (Verified against `ClientPacketListenerMixin.java`, `ClothConfigScreenHelper.java`, `YaclScreenHelper.java`, `level-does-something.mixins.json`, and `v26.3/gradle.properties`)

---

## 🎯 1. Goal & Definition of Done (The 4-Pillar Objective Architecture)

1. **North Star Objective**: Add `@Environment(EnvType.CLIENT)` annotations to client-importing classes outside `client/` (`ClientPacketListenerMixin.java`, `ClothConfigScreenHelper.java`, `YaclScreenHelper.java`), supply static `src/main/resources/level-does-something-refmap.json` (`ERR-20261030-002`), and clean stale `parchment_minecraft_version=26.3-snapshot-6` from `v26.3/gradle.properties` so `code_auditor.py` Suites 2 and 4 pass with `0` CRITICAL findings.
2. **In-Game Experience (Before vs. After)**:
   - *Before*: `code_auditor.py` flags 4 Suite 2 CRITICAL errors per anchor and 1 Suite 4 CRITICAL refmap error per anchor + snapshot warning on `v26.3`.
   - *After*: All client-only classes outside `client/` are annotated `@Environment(EnvType.CLIENT)` and packaged JARs include `level-does-something-refmap.json` (`Suites 1–13` clean).
3. **🚫 Anti-Goals (Off-Limits / Do Not Touch)**:
   - ❌ Do not modify `ClientPacketListenerMixin` sound pitch math.
   - ❌ Do not alter `PlayerMixin` networking or attribute scaling.
   - ❌ Do not migrate `26.1.2` from Cloth Config to YACL.
4. **✅ Definition of Done (Binary Verification Checklist)**:
   - [ ] `@Environment(EnvType.CLIENT)` added to `ClientPacketListenerMixin.java` (`26.1.2`, `26.2`, `26.3`), `ClothConfigScreenHelper.java` (`26.1.2`), and `YaclScreenHelper.java` (`26.2`, `26.3`).
   - [ ] Static empty-mapping `src/main/resources/level-does-something-refmap.json` added across all 3 anchors (`ERR-20261030-002`), and `parchment_minecraft_version` removed/aligned in `v26.3/gradle.properties`.
   - [ ] `code_auditor.py --path "Instant Gratification Collection/Level Does Something"` passes Suites 2 and 4 with `0` CRITICAL findings.

---

## 🔬 2. Architectural Blueprint & Blast Radius

| Surface | Risk | Mitigation |
| :--- | :--- | :--- |
| **Vanilla Contraptions / Entities** | None | Annotation and refmap metadata only |
| **World Saves** | None | Zero NBT/GameRule change |
| **Redstone / Timing** | None | Zero tick logic change |
| **Multiplayer Exploits** | Prevents accidental dedicated-server loading of client screen/listener classes | `@Environment(EnvType.CLIENT)` strips client-only classes on dedicated server |
