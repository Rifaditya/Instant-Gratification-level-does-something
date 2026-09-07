# Level Does Something — 公式ドキュメントポータル

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 マインクラフト バージョン選択

対象のMinecraftバージョンを選択して、バージョン専用のシステム、計算式、設定ガイドをご覧ください：

| Minecraft バージョン | チャンネル | ビルド成果物 | クイックアクセス |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | Modern Standard | `level-does-something-1.0.5+26.2.jar` | [[👉 MC 26.2 Wiki に入る|ja_jp-26.2-Home]] |
| **Minecraft 26.3** | Modern Lead | `level-does-something-1.0.5+26.3.jar` | [[👉 MC 26.3 Wiki に入る|ja_jp-26.3-Home]] |

Fabric Loader、Java実行環境、DasikLibraryの要件については、[[バージョン互換性マトリクス|ja_jp-Version-Compatibility]] をご確認ください。

---

## ⚡ 設計理念とゲームプレイ機能

**Level Does Something** は **Instant Gratification (即時充足)** のMOD設計思想に基づいています：  
> *「古いルールではなく、プレイヤーの時間を尊重せよ。」*

バニラのMinecraftでは、レベル30を超える経験値を保持していても実用的な恩恵はほとんどありません。エンチャントや金床の消費通貨としてのみ機能し、死亡すれば大半を喪失してしまいます。本MODはこの仕様を一新し、経験値を蓄積・保持することで即座に強力なパッシブ能力を獲得できます！

### 1. 動的なプレイヤー属性の強化
経験値レベルを保持することで、5つの基本能力値が永続的に強化されます：
* **ブロック採掘速度** (`minecraft:block_break_speed`)：素手および全ツールの採掘速度が向上（1単位あたり +1.0%）。
* **攻撃力** (`minecraft:attack_damage`)：物理攻撃のダメージが向上（1単位あたり +0.5%）。
* **移動速度** (`minecraft:movement_speed`)：歩行およびダッシュの速度が上昇（1単位あたり +0.2%）。
* **幸運** (`minecraft:luck`)：釣りやダンジョンのチェストからのレアアイテム入手確率が上昇（1単位あたり +1.0%）。
* **最大体力** (`minecraft:max_health`)：追加のハート（体力）を獲得（1単位あたり +1.0%）。レベルアップ時には**増加分の体力が即時回復**し、空のハート状態を防ぎます。

### 2. 視覚オーラと音響フィードバック
* **天界の経験値オーラ (レベル30以上)**：エメラルド色の星芒 (`minecraft:happy_villager`) と黄金の光彩 (`minecraft:trial_spawner_detection`) がプレイヤーの周囲を螺旋状に舞い上がります。
* **経験値回収音のピッチ変化**：経験値オーブを拾った際の音が、レベルの上昇に合わせて高音へと変化します（最大2.0倍）。
* **マイルストーン達成のチャイム**：レベル30、60、100到達時に、周囲へ響き渡る昇天の鐘の音 (`minecraft:ui.toast.challenge_complete`) が鳴り響きます。

---

## 📚 クイックリンク
* [[⚙️ バージョン互換性マトリクス|ja_jp-Version-Compatibility]]
* [[❓ トラブルシューティング & FAQ|ja_jp-Troubleshooting-and-FAQ]]
* [[🛠️ 開発者環境設定 & Gradle ビルドガイド|ja_jp-Developer-Setup-and-Building]]
* [[26.2 経験値スケーリングと計算曲線|26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 経験値スケーリングと計算曲線|26.3-Experience-Scaling-and-Mathematical-Curves]]

---

## ⚖️ ライセンスと帰属表示
* **開発者**: **Dasik (Rifaditya)**
* **ライセンス**: **GNU General Public License v3.0 (GPLv3)**
* **ソースコード**: [GitHub リポジトリ](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
