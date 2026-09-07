🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 開発者向けセットアップとツールチェーン (MC 26.3)

> 📌 **リポジトリソースに関する免責事項**: このWikiドキュメントは**リポジトリ内の現在のソースコード状態**を反映しており、CurseForgeおよびModrinthでの公開リリース前の最新の未リリースコミットや開発中の機能が含まれている場合があります。

---

## 🛠️ Workstation Setup for Minecraft 26.3

### Subproject Location
The source code for Minecraft 26.3 resides at:
`Level Does Something v26.3/level-does-something`

### Toolchain Dependencies
```properties
minecraft_version=26.3
parchment_minecraft_version=26.3
parchment_version=26.3-snapshot-6:2026.01.22

fabric_version=0.156.1+26.3
fabric_loader_version=0.19.3
dasik_library_version=1.8.38
```

---

## 🔨 Build & Run Commands

```powershell
cd "Level Does Something v26.3/level-does-something"
./gradlew build --no-daemon
./gradlew runClient
```

---

## 🔗 Related Documentation
* [[Minecraft 26.3 ポータルに戻る|ja_jp-26.3-Home]]
* [[26.3 アーキテクチャと Mixin 解析|ja_jp-26.3-Architecture-and-Mixins]]
* [[バージョン互換性マトリクス|ja_jp-Version-Compatibility]]
* [[グローバルホームに戻る|ja_jp-Home]]
