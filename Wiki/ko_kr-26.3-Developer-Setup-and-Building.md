🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 개발자 설정 및 툴체인 가이드 (MC 26.3)

> 📌 **저장소 소스 고지사항**: 본 위키 문서는 **저장소 내의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 이전의 최신 커밋 또는 개발 중인 기능이 포함될 수 있습니다.

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
* [[Minecraft 26.3 포털로 돌아가기|ko_kr-26.3-Home]]
* [[26.3 아키텍처 및 믹스인(Mixins) 분석|ko_kr-26.3-Architecture-and-Mixins]]
* [[버전 호환성 매트릭스|ko_kr-Version-Compatibility]]
* [[중앙 포털로 돌아가기|ko_kr-Home]]
