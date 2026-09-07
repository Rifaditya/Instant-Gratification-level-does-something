# Level Does Something — 공식 문서 포털

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 마인크래프트 버전 선택 포털

대상 마인크래프트 버전을 선택하여 전용 시스템, 계산 공식 및 설정 가이드를 확인하세요:

| 마인크래프트 버전 | 출시 채널 | 빌드 아티팩트 | 바로가기 |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | Modern Standard | `level-does-something-1.0.5+26.2.jar` | [[👉 MC 26.2 위키 들어가기|ko_kr-26.2-Home]] |
| **Minecraft 26.3** | Modern Lead | `level-does-something-1.0.5+26.3.jar` | [[👉 MC 26.3 위키 들어가기|ko_kr-26.3-Home]] |

패브릭 로더(Fabric Loader), Java 런타임 및 DasikLibrary 호환성에 대해서는 [[버전 호환성 매트릭스|ko_kr-Version-Compatibility]]를 참조하십시오.

---

## ⚡ 핵심 철학 및 게임 메커니즘

**Level Does Something**은 **Instant Gratification (즉각적 만족)** 모딩 철학을 따릅니다:  
> *“구시대적인 게임 규칙이 아닌, 플레이어의 시간을 존중하라.”*

바닐라 마인크래프트에서는 30레벨을 넘어서 경험치를 축적해도 실용적인 이점이 거의 없습니다. 마법 부여와 모루 수리를 위한 소모품에 불과하며 사망 시 대부분 사라집니다. 이 모드는 경험치를 단순히 보유하는 것만으로도 즉각적인 패시브 파워를 부여합니다!

### 1. 동적 플레이어 능력치 스케일링
경험치 레벨을 유지하면 5가지 핵심 능력치가 지속적으로 강화됩니다:
* **블록 채굴 속도** (`minecraft:block_break_speed`): 맨손 및 모든 도구의 채굴 속도 증가 (스케일 단위당 +1.0%).
* **공격력** (`minecraft:attack_damage`): 물리 타격 피해량 증가 (스케일 단위당 +0.5%).
* **이동 속도** (`minecraft:movement_speed`): 걷기 및 달리기 속도 향상 (스케일 단위당 +0.2%).
* **행운** (`minecraft:luck`): 낚시 및 전리품 상자에서 희귀 아이템 획득 확률 증가 (스케일 단위당 +1.0%).
* **최대 체력** (`minecraft:max_health`): 추가 하트 획득 (스케일 단위당 +1.0%). 체력 증가 시 **차이만큼 즉시 회복**되어 빈 하트가 생기는 현상을 방지합니다.

### 2. 시각적 오라 및 음향 피드백
* **천상의 경험치 오라 (30레벨 이상)**: 플레이어 주위를 나선형으로 감싸 올라가는 에메랄드 별빛 (`minecraft:happy_villager`)과 황금빛 반짝임 (`minecraft:trial_spawner_detection`).
* **경험치 획득 피치 변조**: 경험치 구슬을 획득할 때 레벨이 높아질수록 소리 음정이 점진적으로 높아집니다 (최대 2.0배).
* **도달 마일스톤 차임**: 30, 60, 100레벨 돌파 시 축하 승천 차임 소리 (`minecraft:ui.toast.challenge_complete`)가 울립니다.

---

## 📚 빠른 탐색
* [[⚙️ 버전 호환성 매트릭스|ko_kr-Version-Compatibility]]
* [[❓ 문제 해결 및 FAQ|ko_kr-Troubleshooting-and-FAQ]]
* [[🛠️ 개발자 설정 및 Gradle 빌드 가이드|ko_kr-Developer-Setup-and-Building]]
* [[26.2 경험치 스케일링 및 수학 곡선|26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 경험치 스케일링 및 수학 곡선|26.3-Experience-Scaling-and-Mathematical-Curves]]

---

## ⚖️ 라이선스 및 크레딧
* **개발자**: **Dasik (Rifaditya)**
* **라이선스**: **GNU General Public License v3.0 (GPLv3)**
* **소스 코드**: [GitHub 저장소](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
