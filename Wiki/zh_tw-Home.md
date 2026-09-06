# Level Does Something (等級賦能) — 官方文檔中心

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 多版本導航門戶

請選擇您的目標 Minecraft 版本以獲取專屬的機制、公式及設定文檔：

| 遊戲版本 | 發布渠道 | 建置檔案 | 快速入口 |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | 現代穩定版 | `level-does-something-1.0.5+26.2.jar` | [[👉 進入 MC 26.2 文檔|26.2-Home]] |
| **Minecraft 26.3** | 現代先行版 | `level-does-something-1.0.5+26.3.jar` | [[👉 進入 MC 26.3 文檔|26.3-Home]] |

關於 Fabric Loader、Java 執行環境以及 DasikLibrary 依賴相容性，請參閱 [[版本相容性矩陣|Version-Compatibility]]。

---

## ⚡ 核心設計哲學與遊戲機制

**Level Does Something** 秉持 **Instant Gratification (即時滿足)** 模組哲學：  
> *「尊重玩家的時間，而非陳舊的規則。」*

在原生 Minecraft 中，累積超過 30 級的經驗值幾乎毫無實用價值，僅能作為附魔和鐵砧修理的消耗品，且在死亡時絕大部分都會損失。本模組徹底改變了這一循環：玩家只需累積並保留經驗等級，即可獲得即時的被動力量成長！

### 1. 動態玩家屬性強化
隨著玩家經驗等級的提升，五項核心被動屬性將獲得持續增強：
* **方塊挖掘速度** (`minecraft:block_break_speed`)：提升空手及所有工具的挖掘效率（每單位 +1.0%）。
* **物理攻擊傷害** (`minecraft:attack_damage`)：增強徒手與近戰武器攻擊力（每單位 +0.5%）。
* **移動速度** (`minecraft:movement_speed`)：大幅提升行走與衝刺疾跑速度（每單位 +0.2%）。
* **幸運值** (`minecraft:luck`)：提高釣魚、戰利品箱和擊敗生物掉落高階稀有物品的機率（每單位 +1.0%）。
* **最大生命值** (`minecraft:max_health`)：隨等級提升額外的心之容器（每單位 +1.0%），並在等級提升時**立即治癒差額生命值**，徹底消除「空心」不同步問題！

### 2. 視覺光環與音效回饋
* **天界經驗光環 (Level 30+)**：達到 30 級及以上的玩家周身將環繞翠綠星芒 (`minecraft:happy_villager`) 與黃金光塵 (`minecraft:trial_spawner_detection`) 組成的旋轉粒子螺旋。
* **經驗拾取升調音效**：拾取經驗球時的音調隨玩家等級提升而逐漸變高（最高 2.0 倍頻）。
* **里程碑飛升鐘鳴**：突破 30、60 與 100 級大關時，將向全服廣播清脆勝利鐘鳴 (`minecraft:ui.toast.challenge_complete`)。

---

## 📚 常用導航
* [[⚙️ 版本相容性矩陣|Version-Compatibility]]
* [[❓ 疑難排解與常見問題 (FAQ)|Troubleshooting-and-FAQ]]
* [[🛠️ 開發者配置與 Gradle 建置指南|Developer-Setup-and-Building]]
* [[26.2 經驗強化與數學曲線|26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 經驗強化與數學曲線|26.3-Experience-Scaling-and-Mathematical-Curves]]

---

## ⚖️ 開源協議與署名
* **作者**: **Dasik (Rifaditya)**
* **授權條款**: **GNU General Public License v3.0 (GPLv3)**
* **源代碼**: [GitHub 倉庫](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
