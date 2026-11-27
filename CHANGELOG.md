# Changelog: Instant Gratification — Level Does Something (MC 26.1.2)

## [1.0.10+A-26.1.2] - 2026-11-27

### Fixed
- Removed custom SynchedEntityData registration on Player so unmodded vanilla clients can join modded servers cleanly without entity tracker mismatches.
- Added Dual-Delivery aura synchronization (optional client payload for modded players plus throttled server particle fallback for nearby vanilla clients).
- Made the aura toggle GameRule take effect immediately on live servers without waiting for a player level change.

## [1.0.4+A-26.1.2] - 2026-05-22

### Added
- Initial MC 26.1.2 release baseline.
