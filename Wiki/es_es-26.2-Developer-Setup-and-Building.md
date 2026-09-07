🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# Guía de Herramientas y Configuración (MC 26.2)

> 📌 **Descargo de responsabilidad de fuente de repositorio**: Esta documentación de wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes no publicadas o características en desarrollo antes de los lanzamientos públicos en CurseForge y Modrinth.

---

## 🛠️ Workstation Setup for Minecraft 26.2

### Subproject Location
The source code for Minecraft 26.2 resides at:
`Level Does Something v26.2/level-does-something`

### Toolchain Dependencies
```properties
minecraft_version=26.2
parchment_minecraft_version=26.2
parchment_version=2026.02.15

fabric_version=0.149.0+26.2
fabric_loader_version=0.19.3
dasik_library_version=1.8.38
```

---

## 🔨 Build & Run Commands

```powershell
cd "Level Does Something v26.2/level-does-something"
./gradlew build --no-daemon
./gradlew runClient
```

---

## 🔗 Related Documentation
* [[Volver al Portal de Minecraft 26.2|es_es-26.2-Home]]
* [[26.2 Arquitectura y Mixins|es_es-26.2-Architecture-and-Mixins]]
* [[Matriz de Compatibilidad de Versiones|es_es-Version-Compatibility]]
* [[Volver al Portal Principal|es_es-Home]]
