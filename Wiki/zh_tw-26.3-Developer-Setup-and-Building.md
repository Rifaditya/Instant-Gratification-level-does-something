🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 開發者配置與工具鏈指南 (MC 26.3)

> 📌 **代碼倉庫來源免責聲明**：本維基文件反映了**代碼倉庫中的當前源碼狀態**，可能包含領先於 CurseForge 和 Modrinth 平台公開發布版本的最新未發布提交或開發中功能。

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
* [[返回 Minecraft 26.3 門戶|zh_tw-26.3-Home]]
* [[26.3 架構設計與 Mixin 解析|zh_tw-26.3-Architecture-and-Mixins]]
* [[版本相容性矩陣|zh_tw-Version-Compatibility]]
* [[返回中央門戶|zh_tw-Home]]
