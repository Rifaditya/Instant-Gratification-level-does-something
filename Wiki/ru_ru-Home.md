# Level Does Something — Официальный портал документации

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 Выбор версии Minecraft

Выберите вашу целевую версию Minecraft для доступа к документации по механикам, математическим кривым и настройкам:

| Версия Minecraft | Канал | Бинарный артефакт | Быстрый переход |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | Modern Standard | `level-does-something-1.0.5+26.2.jar` | [[👉 Открыть вики MC 26.2|26.2-Home]] |
| **Minecraft 26.3** | Modern Lead | `level-does-something-1.0.5+26.3.jar` | [[👉 Открыть вики MC 26.3|26.3-Home]] |

Для проверки требований к Fabric Loader, Java и библиотеке DasikLibrary ознакомьтесь с [[Матрицей совместимости версий|Version-Compatibility]].

---

## ⚡ Философия и ключевые механики

Мод **Level Does Something** разработан в соответствии с философией **Instant Gratification (Мгновенное вознаграждение)**:  
> *«Уважайте время игрока, а не искусственные игровые ограничения.»*

В ванильном Minecraft накопление уровней опыта выше 30-го практически бесполезно: опыт расходуется только на наковальню и зачарование, а при смерти теряется. Этот мод превращает опыт в источник постоянного пассивного могущества!

### 1. Динамическое усиление характеристик
Удержание уровней опыта непрерывно усиливает 5 ключевых атрибутов персонажа:
* **Скорость копания блоков** (`minecraft:block_break_speed`): ускоряет добычу блоков руками и инструментами (+1.0% за единицу).
* **Урон от атак** (`minecraft:attack_damage`): увеличивает физический урон (+0.5% за единицу).
* **Скорость передвижения** (`minecraft:movement_speed`): ускоряет ходьбу и бег (+0.2% за единицу).
* **Удача** (`minecraft:luck`): повышает шансы на редкую добычу на рыбалке и в сундуках сокровищниц (+1.0% за единицу).
* **Максимальное здоровье** (`minecraft:max_health`): добавляет дополнительные сердца (+1.0% за единицу) с **мгновенным исцелением разницы**, устраняя баг «пустых сердец».

### 2. Визуальная аура и звуковая отдача
* **Небесная аура опыта (30+ уровень)**: спиральный вихрь изумрудных (`minecraft:happy_villager`) и золотых (`minecraft:trial_spawner_detection`) искр.
* **Повышение тональности сбора опыта**: звук поднятия сфер опыта звучит всё выше по мере роста уровня игрока (до 2.0x).
* **Триумфальный перезвон вех**: при достижении 30, 60 и 100 уровней воспроизводится торжественный звук восхождения (`minecraft:ui.toast.challenge_complete`).

---

## 📚 Основные разделы
* [[⚙️ Матрица совместимости версий|Version-Compatibility]]
* [[❓ Устранение неполадок и FAQ|Troubleshooting-and-FAQ]]
* [[🛠️ Руководство по сборке Gradle для разработчиков|Developer-Setup-and-Building]]
* [[26.2 Математические кривые масштабирования|26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 Математические кривые масштабирования|26.3-Experience-Scaling-and-Mathematical-Curves]]

---

## ⚖️ Лицензия и авторство
* **Автор**: **Dasik (Rifaditya)**
* **Лицензия**: **GNU General Public License v3.0 (GPLv3)**
* **Исходный код**: [Репозиторий GitHub](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
