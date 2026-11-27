# Changelog: Instant Gratification — Level Does Something (MC 26.3)

## [1.0.10+26.3] - 2026-11-27

### Fixed
- Removed custom SynchedEntityData registration on Player so unmodded vanilla clients can join modded servers cleanly without entity tracker mismatches.
- Added Dual-Delivery aura synchronization (optional client payload for modded players plus throttled server particle fallback for nearby vanilla clients).
- Made the aura toggle GameRule take effect immediately on live servers without waiting for a player level change.

## [1.0.9+26.3] - 2026-09-16

### Fixed
- Fixed @Redirect method signature in ClientPacketListenerMixin to include boolean distanceDelay parameter.

## [1.0.8+26.3] - 2026-09-28

### Changed
- Removed superclass `@Shadow` in `ClientPacketListenerMixin` and aligned with standard Java inheritance.

## [1.0.7+26.3] - 2026-09-14

### Changed
- GameRule snake_case naming standardization and config bounds validation.

## [1.0.6+26.3] - 2026-09-14

### Changed
- Toolchain modernization and DasikLibrary alignment.

## [1.0.5+26.3] - 2026-09-05

### Added
- Migrated configuration GUI to YetAnotherConfigLib v3 (YACL) with top-pinned Ko-fi support button.

## [1.0.4+26.3] - 2026-09-05

### Added
- Initial port and release targeting Minecraft 26.3.
