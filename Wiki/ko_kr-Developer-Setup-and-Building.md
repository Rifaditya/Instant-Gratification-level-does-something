🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# 🛠️ 개발자 환경 설정 및 빌드 가이드

> 📌 **저장소 소스 고지사항**: 본 위키 문서는 **저장소 내의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 이전의 최신 커밋 또는 개발 중인 기능이 포함될 수 있습니다.

This unified guide explains workstation requirements, repository architecture, and commands for building all targets of **Level Does Something**.

---

## 💻 Workstation Prerequisites
- **Java Development Kit**: JDK 25 (`release = 25`).
- **Gradle**: 9.3+ (via `./gradlew` wrapper).
- **Fabric Loom**: 1.15+.
- **DasikLibrary Dependency**: `>= 1.8.38`.

---

## 📂 Repository Layout
```
Level Does Something/
├── Level Does Something v26.2/
│   └── level-does-something/        # Minecraft 26.2 Project Root
└── Level Does Something v26.3/
    └── level-does-something/        # Minecraft 26.3 Project Root
```

---

## 🔨 Build Commands

### Minecraft 26.2
```bash
cd "Level Does Something v26.2/level-does-something"
./gradlew build --no-daemon
```

### Minecraft 26.3
```bash
cd "Level Does Something v26.3/level-does-something"
./gradlew build --no-daemon
```

---

## ⚖️ 라이선스 및 제작진
* **Developer**: **Dasik (Rifaditya)**
* **License**: **GNU General Public License v3.0 (GPLv3)**
* **Repository**: [Instant-Gratification-level-does-something](https://github.com/Rifaditya/Instant-Gratification-level-does-something)

> 📌 **저장소 소스 고지사항**: 본 위키 문서는 **저장소 내의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 이전의 최신 커밋 또는 개발 중인 기능이 포함될 수 있습니다.
