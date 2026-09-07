🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 经验强化与数学曲线 (MC 26.2)

> 📌 **代码仓库来源免责声明**：本维基文档反映了**代码仓库中的当前源码状态**，可能包含领先于 CurseForge 和 Modrinth 平台公开发布版本的最新未发布提交或开发中功能。

---

## 📋 官方技术信息

| Property | Value |
| :--- | :--- |
| **Target Minecraft Version** | `26.2` |
| **Source Implementation** | [`PlayerLevelAttributesHandler.java`](https://github.com/Rifaditya/Instant-Gratification-level-does-something) |
| **Attribute Modifier Operation** | `AttributeModifier.Operation.ADD_MULTIPLIED_BASE` |
| **Controlling GameRules** | `leveldoessomething:levelPowerCurveType`, `leveldoessomething:levelPowerBaseMultiplier`, `leveldoessomething:levelPowerMaxTier` |
| **Trigger Hook** | `PlayerMixin.tick()` (evaluated upon `lastExperienceLevel != player.experienceLevel`) |
| **Cleanup Hook** | `PlayerMixin.remove()` invoking `PlayerLevelAttributesHandler.removePlayerAttributes()` |
| **Immediate Heart Compensation** | Active ($\Delta H = H_{\text{new}} - H_{\text{prev}}$, healing player if $\Delta H > 0$) |

---

## 🔄 生存阶段进阶流程

```
       [ PLAYER EARNS EXPERIENCE ]
                  │
                  ▼
       [ Integer Level Increases ]
                  │
                  ▼ (Player.tick() detects delta)
       ┌─────────────────────────────────────────┐
       │ PlayerLevelAttributesHandler Evaluates: │
       │ 1. Current GameRule Curve Type (0, 1, 2)│
       │ 2. Calculates Scale Factor S            │
       │ 3. Computes Global Multiplier M_global  │
       └─────────────────────────────────────────┘
                  │
                  ▼
       ┌─────────────────────────────────────────┐
       │ Updates 5 Vanilla AttributeModifiers:   │
       │ • BLOCK_BREAK_SPEED (+1.0% × S × M)     │
       │ • ATTACK_DAMAGE     (+0.5% × S × M)     │
       │ • MOVEMENT_SPEED    (+0.2% × S × M)     │
       │ • LUCK              (+1.0% × S × M)     │
       │ • MAX_HEALTH        (+1.0% × S × M)     │
       └─────────────────────────────────────────┘
                  │
                  ▼
       ┌─────────────────────────────────────────┐
       │ Extra Hearts Immediately Healed!        │
       │ DATA_AURA_LEVEL synced to clients       │
       └─────────────────────────────────────────┘
```

1. **Experience Accumulation**: As the player defeats mobs, mines ores, smelts items, or breeds animals, integer experience level increases.
2. **Atomic Change Detection**: During `Player.tick()`, the server checks whether `lastExperienceLevel != player.experienceLevel`.
3. **Transient Modifier Refresh**: Existing mod modifiers are safely cleared by Identifier, and freshly computed `AttributeModifier` instances are attached.
4. **Heart Population**: If `MAX_HEALTH` increases, the difference in maximum health is computed immediately and the player is healed by $\Delta H$.
5. **Death & Cleanup**: If the player dies, all five modifiers are stripped at `Player.remove()`.

---

## 🧮 数学曲线与计算模型

### Curve Type 0: Exponential Tier Progression
Steps every 10 levels:
$$T = \min\left(\left\lfloor \frac{L}{10} \right\rfloor, \text{maxTier}\right)$$
$$S_{\text{tier}} = 2^T - 1$$

### Curve Type 1: Smooth Logarithmic Curve (Default)
Continuous progression with diminishing returns:
$$S_{\text{log}} = \ln(1.0 + 0.1 \times L)$$

### Curve Type 2: Simple Linear Scaling
Direct linear scaling:
$$S_{\text{lin}} = L$$

---

## 🔗 Related Documentation
* [[返回 Minecraft 26.2 门户|zh_cn-26.2-Home]]
* [[26.2 视觉光环与音效反馈|zh_cn-26.2-Visual-Aura-and-Auditory-Feedback]]
* [[26.2 配置与游戏规则矩阵|zh_cn-26.2-Configuration-and-GameRules]]
* [[26.2 架构设计与 Mixin 解析|zh_cn-26.2-Architecture-and-Mixins]]
* [[返回中央门户|zh_cn-Home]]
