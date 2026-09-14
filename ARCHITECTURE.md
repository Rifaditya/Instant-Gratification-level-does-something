# Architecture & Symbol Index: Level Does Something

## 1. Mod Metadata & Entrypoint
- **Mod ID**: `leveldoessomething`
- **Main Entrypoint**: `net.instantgratification.leveldoessomething.LevelDoesSomethingFabric` (`net.fabricmc.api.ModInitializer`)
- **Client Entrypoint**: `net.instantgratification.leveldoessomething.LevelDoesSomethingFabricClient`

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Purpose |
| :--- | :--- | :--- |
| `Vanilla Class` | `net.instantgratification.leveldoessomething.mixin.PlayerMixin` | Core mixin hook |

## 3. Core Mechanics & Subsystems
- **Source Root**: `src/main/java/`
- **Resource Root**: `src/main/resources/`

## 4. Dynamic GameRules & Commands
- **GameRules / Commands**: Configured dynamically via namespaced keys (`leveldoessomething:*`).

## 5. Configuration & Sidedness Isolation
- **Sidedness**: Server-safe logic in main, client isolated in `src/client/java` or client entrypoint.
