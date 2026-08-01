# Architecture Overview

This document describes the architectural flow and synchronization mechanisms used in **Level Does Something** to balance server authority with client performance and visual customizability.

---

## 1. System Execution Flow

The mod's logic is split into a server-side state machine that manages attribute updates, and a client-side visualization system that spawns particles based on data synchronized via Mojang's built-in `SynchedEntityData` (DataTracker).

```mermaid
sequenceDiagram
    autonumber
    actor PlayerEntity as Player
    participant ServerPlayer as Server Player Entity
    participant DataTracker as SynchedEntityData (DataTracker)
    participant ClientPlayer as Client Player Entity
    participant ClientConfig as Local Client Config

    Note over ServerPlayer: Player gains XP or enchants
    ServerPlayer->>ServerPlayer: Player.tick() runs
    ServerPlayer->>ServerPlayer: Check if level changed (experienceLevel != lastExperienceLevel)
    
    rect rgb(240, 240, 240)
        Note over ServerPlayer: Recalculate Buffs & State
        ServerPlayer->>ServerPlayer: Apply custom AttributeModifiers (Logarithmic/Exponential/Linear)
        ServerPlayer->>DataTracker: Update DATA_AURA_LEVEL tracker key
    end

    DataTracker-->>ClientPlayer: Automatic synchronization packet sent to surrounding clients
    
    rect rgb(240, 240, 240)
        Note over ClientPlayer: Visual Aura Loop (Ticking)
        ClientPlayer->>ClientConfig: Read enableClientAuraParticles
        alt enableClientAuraParticles is true
            ClientPlayer->>ClientPlayer: Spawn swirling green/gold particles locally
        else enableClientAuraParticles is false
            ClientPlayer->>ClientPlayer: Suppress particle rendering
        end
    end
```

---

## 2. Component breakdown

### A. State Tracking & Attribute Engine (Server-Side)
- **Target**: `Player.java`
- **Method Hook**: `Player.tick()`
- **Mechanism**:
  - The player's current experience level is evaluated against the `lastExperienceLevel` field.
  - When a difference is detected, the attributes are cleared of the mod's specific modifier UUIDs and replaced with freshly computed modifiers based on the active curve type.
  - Synced data is then updated to ensure surrounding clients are aware of the visual changes.

### B. Synchronization Layer (Mojang DataTracker)
- **Tracker Key**: `DATA_AURA_LEVEL` (registered via `Player.defineSynchedData`)
- **Type**: `Integer`
- **Data Flow**:
  - Automatically synced to all client players within tracking range of the entity.
  - Eliminates the need to serialize custom network packets, reducing server ticking time and mod compatibility issues.

### C. Aesthetic Particle Engine (Client-Side)
- **Ticking Hook**: Client entity ticking handler.
- **Process**:
  - The client reads the `DATA_AURA_LEVEL` tracker value from the player entity.
  - If the level is $\ge 30$, and `enableClientAuraParticles` is true in `level-does-something-client.json`, the client spawns `minecraft:happy_villager` and `minecraft:trial_spawner_detection` particles in an upward spiral centered around the player's bounding box.

### D. Audio Interceptor (Client-Side)
- **Target**: `ExperienceOrb.java`
- **Method Hook**: `playerTouch(Player)`
- **Process**:
  - Intercepts the sound trigger for experience pick-up.
  - If `enableClientSoundPitch` is true in client config, modifies the sound source pitch parameter proportionally based on the player's tracked level.
