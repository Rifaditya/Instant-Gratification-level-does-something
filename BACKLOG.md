# 📌 Level Does Something Backlog

<!-- Highest Historical ID: BL-LDS-008 (Next Ticket Must Start at BL-LDS-009) -->
> **Next Available Ticket ID**: `[BL-LDS-009]`

This file tracks planned features, technical refinements, performance optimizations, and deferred bug fixes for **Level Does Something**.

> [!CAUTION]
> ### 📦 Feature-Frozen (Maintenance & Critical Compatibility Fixes Active)
> **Level Does Something** is locked in a feature-complete state: no new gameplay features or legacy anchor backports (`1.20.1`, `1.21.1`, `1.21.11`) are planned. Critical compatibility and stability bugfixes across active modern anchors (`26.1.2`, `26.2`, `26.3`) remain active as needed.


---

## 📊 Backlog Summary

| ID | Category | Title | Priority | Target Version | Status | Specification |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| [`BL-LDS-003`](tickets/BL-LDS-003.md) | `[TECH_DEBT]` | Scaffold Anchor 7: Level Does Something v26.x (rolling 26.3+) | `[MEDIUM]` | Multi-Version Parity | `📌 DEFERRED` | [View Ticket](tickets/BL-LDS-003.md) |
| [`BL-LDS-002`](tickets/BL-LDS-002.md) | `[TECH_DEBT]` | Adopt 5-Pillar Testing & Sidedness Verification Harness | `[MEDIUM]` | All Anchors | `📦 RETIRED` | [View Ticket](tickets/BL-LDS-002.md) |
| [`BL-LDS-004`](tickets/BL-LDS-004.md) | `[TECH_DEBT]` | Wiki Audit & Update to Latest Version: Level Does Something | `[MEDIUM]` | `All Anchors` | `📌 DEFERRED` | [View Ticket](tickets/BL-LDS-004.md) |
| [`BL-LDS-005`](tickets/BL-LDS-005.md) | `[TECH_DEBT]` | Adopt Per-Mod Advancement-Triggered Early Access Support Prompt (`DasikPromptRegistry`) | `[MEDIUM]` | `Opportunistic Per-Mod` | `📌 DEFERRED` | [View Ticket](tickets/BL-LDS-005.md) |
| [`BL-LDS-006`](tickets/BL-LDS-006.md) | `[BUGFIX]` | Remove `SynchedEntityData.defineId` from `PlayerMixin` for Vanilla-Client Compatibility | `[CRITICAL]` | `26.1.2, 26.2, 26.3` | `📌 DEFERRED` | [View Ticket](tickets/BL-LDS-006.md) |
| [`BL-LDS-007`](tickets/BL-LDS-007.md) | `[BUGFIX]` | Guard Optional Cloth Config Screen Factory in Root `26.1.2` `ModMenuIntegration` | `[HIGH]` | `26.1.2` | `📌 DEFERRED` | [View Ticket](tickets/BL-LDS-007.md) |
| [`BL-LDS-008`](tickets/BL-LDS-008.md) | `[TECH_DEBT]` | Annotate Client Classes (`@Environment(EnvType.CLIENT)`) & Static Mixin Refmap Hygiene | `[MEDIUM]` | `26.1.2, 26.2, 26.3` | `📌 DEFERRED` | [View Ticket](tickets/BL-LDS-008.md) |

---

## 🏷 Legend & Status Tags
- **Categories**: `[FEATURE]`, `[REFINEMENT]`, `[BUGFIX]`, `[PERF]`, `[TECH_DEBT]`
- **Priorities**: `[CRITICAL]` (Crash/desync/vanilla-join blocker), `[HIGH]` (Important logic/crash guard), `[MEDIUM]` (Quality of life), `[LOW]` (Minor polish)
- **Statuses**: `📌 DEFERRED` (Queued for future work), `🚧 IN_PROGRESS` (Active development), `✅ RESOLVED` (Implemented and verified), `📦 RETIRED` (Discontinued/archived)

---

## 📝 Detailed Backlog Entries (Modular Storage)

> [!NOTE]
> **Modular Backlog Architecture**: In accordance with Item 11 of the Studio Constitution (*The Modular Backlog Standard*), detailed specifications, implementation blueprints, and verification checklists are maintained in dedicated atomic ticket files located in [`tickets/`](tickets/README.md). Click any **View Ticket** link in the summary table above or prompt worker agents directly with `tickets/BL-LDS-XXX.md`.
