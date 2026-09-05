# 🎛️ Master Release Queue: Instant Gratification — Level Does Something

> **Mod Project Master Ground-Truth Document**  
> *Last Synchronized: 2026-09-05*  
> **Modrinth ID**: *Unregistered* | **CurseForge ID**: *Unregistered* | **Lead SemVer**: `1.0.5`

---

## 📊 Multi-Version Release Matrix & Queue Status

| Target MC | Generational Era | Live on Platforms | Next Queued Version | Status & Cadence Action | Feature Highlights / Notes |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **MC 26.2** | Modern Standard | *(Unreleased)* | `1.0.5+26.2` | 🛠️ **Local Development** | MC 26.2 alignment, YACL v3 migration, and Ko-fi button integration. |
| **MC 26.3** | Modern Lead | `None` | `1.0.5+26.3` | ⏸️ **PARITY HOLD** | 1. **Why**: Held in archive until MC 26.2 reaches 1.0.5 feature parity on Modrinth/CurseForge.<br>2. **Until When**: Lifted once 1.0.5 parity is achieved.<br>3. **Resume Action**: Flip to `- [ ]` for daily automated release hub dispatch. |

---

## 🏛️ Project Operating Rules & Architectural Invariants

1. **🔢 Universal Direct SemVer Inheritance**:
   - Modern subprojects share unified SemVer milestone lineage targeting `1.0.4`.
   - Each Minecraft version anchor manages its own organic progression to ensure 100% clean, verified parity.

2. **📅 Daily Update Guard**:
   - Strict maximum of 1 release per day per targeted Minecraft version anchor across Modrinth and CurseForge.

---

## 🛠️ CLI Publisher Commands for Level Does Something

```powershell
# 1. Check current status across all targeted Minecraft versions
python ".agents/skills/platform-publisher/scripts/platform_publisher.py" --mod "Level Does Something" --status

# 2. Publish next sequential batch across all active versions
python ".agents/skills/platform-publisher/scripts/platform_publisher.py" --mod "Level Does Something" --publish-next --yes

```
