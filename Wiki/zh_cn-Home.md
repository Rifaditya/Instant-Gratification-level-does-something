# Level Does Something (等级赋能) — 官方文档中心

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 多版本导航门户

请选择您的目标 Minecraft 版本以获取专属的机制、公式及配置文档：

| 游戏版本 | 发布渠道 | 构建文件 | 快速入口 |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | 现代稳定版 | `level-does-something-1.0.5+26.2.jar` | [[👉 进入 MC 26.2 文档|zh_cn-26.2-Home]] |
| **Minecraft 26.3** | 现代先行版 | `level-does-something-1.0.5+26.3.jar` | [[👉 进入 MC 26.3 文档|zh_cn-26.3-Home]] |

关于 Fabric Loader、Java 运行环境以及 DasikLibrary 依赖兼容性，请参阅 [[版本兼容性矩阵|zh_cn-Version-Compatibility]]。

---

## ⚡ 核心设计哲学与玩法机制

**Level Does Something** 秉持 **Instant Gratification (即时满足)** 模组哲学：  
> *“尊重玩家的时间，而非陈旧的规则。”*

在原生 Minecraft 中，积累超过 30 级的经验值几乎毫无实用价值，仅能作为附魔和铁砧修理的消耗品，且在死亡时绝大部分都会损失。本模组彻底改变了这一循环：玩家只需积累并保留经验等级，即可获得即时的被动力量成长！

### 1. 动态玩家属性强化
随着玩家经验等级的提升，五项核心被动属性将获得持续增强：
* **方块挖掘速度** (`minecraft:block_break_speed`)：提升空手及所有工具的挖掘效率（每单位 +1.0%）。
* **物理攻击伤害** (`minecraft:attack_damage`)：增强徒手与近战武器攻击力（每单位 +0.5%）。
* **移动速度** (`minecraft:movement_speed`)：大幅提升行走与奔跑疾跑速度（每单位 +0.2%）。
* **幸运值** (`minecraft:luck`)：提高钓鱼、战利品箱和刷怪掉落高阶稀有物品的几率（每单位 +1.0%）。
* **最大生命值** (`minecraft:max_health`)：随等级提升额外的心之容器（每单位 +1.0%），并在等级提升时**立即治愈差额生命值**，彻底消除“空心”不同步问题！

### 2. 视觉光环与音效反馈
* **天界经验光环 (Level 30+)**：达到 30 级及以上的玩家周身将环绕翠绿星芒 (`minecraft:happy_villager`) 与黄金光尘 (`minecraft:trial_spawner_detection`) 组成的旋转粒子螺旋。
* **经验拾取升调音效**：拾取经验球时的音调随玩家等级提升而逐渐变高（最高 2.0 倍频）。
* **里程碑飞升钟鸣**：突破 30、60 与 100 级大关时，将向全服广播清脆胜利钟鸣 (`minecraft:ui.toast.challenge_complete`)。

### 3. 双层权限与客户端自由
* **服务端绝对主权**：服务器管理员可通过 `/gamerule` 实时调节 `leveldoessomething:level_power` 下的 5 项游戏规则。
* **客户端独立开关**：感到掉帧的玩家可在客户端配置或 YACL 界面中关闭光环粒子，完全不影响服务端属性生效与他人游玩。

---

## 📚 常用导航
* [[⚙️ 版本兼容性矩阵|zh_cn-Version-Compatibility]]
* [[❓ 故障排除与常见问题 (FAQ)|zh_cn-Troubleshooting-and-FAQ]]
* [[🛠️ 开发者配置与 Gradle 构建指南|zh_cn-Developer-Setup-and-Building]]
* [[26.2 经验强化与数学曲线|26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 经验强化与数学曲线|26.3-Experience-Scaling-and-Mathematical-Curves]]

---

## ⚖️ 开源协议与署名
* **作者**: **Dasik (Rifaditya)**
* **许可证**: **GNU General Public License v3.0 (GPLv3)**
* **源代码**: [GitHub 仓库](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
