🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# Matriz de Compatibilidad y Ciclo de Vida

> 📌 **Descargo de responsabilidad de fuente de repositorio**: Esta documentación de wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes no publicadas o características en desarrollo antes de los lanzamientos públicos en CurseForge y Modrinth.

---

## 📊 Matriz Completa de Compatibilidad del Motor

**Level Does Something** adheres strictly to the **1 Jar 1 Version Policy** for active Minecraft release anchors, ensuring pristine bytecode compatibility without runtime reflection hacks or bloated multi-version shims.

| Minecraft Version | Release Channel | Mod SemVer | Java Runtime | Fabric Loader | Fabric API | Parchment Mappings | DasikLibrary Bounds | Archive Binary |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | Modern Standard | `1.0.5+26.2` | `Java >= 25` | `>= 0.19.3` | `0.149.0+26.2` | `26.2:2026.02.15` | `>= 1.8.38` | `level-does-something-1.0.5+26.2.jar` |
| **Minecraft 26.3** | Modern Lead | `1.0.5+26.3` | `Java >= 25` | `>= 0.19.3` | `0.156.1+26.3` | `26.3-snapshot-6:2026.01.22` | `>= 1.8.38` | `level-does-something-1.0.5+26.3.jar` |

---

## 🏛️ Invariantes Arquitectónicos Fundamentales

### 1. The "1 Jar 1 Version" Policy
Each major Minecraft release anchor receives its own dedicated, discrete compilation target within the repository collection:
- `Level Does Something v26.2/level-does-something`
- `Level Does Something v26.3/level-does-something`

This architectural separation guarantees that version-specific API shifts, packet serialization schemas, and Mojang mapping discrepancies are addressed directly at compile-time with zero runtime reflection overhead.

### 2. Universal Standalone Library Invariant
`DasikLibrary` serves as the foundational shared architecture across all mods developed by Dasik (Rifaditya).
- **Universal Boundary**: `dasik-library >= 1.8.38`
- **Dynamic Registry Lifecycle**: Provides namespaced Dynamic GameRule registration, runtime category injection, and client GUI integration facades.
- **Hard Runtime Verification**: During mod initialization, `LevelDoesSomethingFabric` performs an explicit classpath validation check:
  ```java
  if (!FabricLoader.getInstance().isModLoaded("dasik-library")) {
      throw new RuntimeException("Level Does Something requires DasikLibrary to function. Please install it.");
  }
  ```

---

## 🔌 Dependencias Opcionales de GUI del Cliente

While **Level Does Something** operates seamlessly as a pure vanilla-compatible mod without client dependencies, installing the following optional client mods unlocks the full visual configuration interface:

| Mod / Library | Namespace ID | Target Scope | Functional Purpose |
| :--- | :--- | :--- | :--- |
| **YetAnotherConfigLib v3** | `yet_another_config_lib_v3` | Client-side only | Powers the in-game configuration screen with tooltips and hot-reloading. |
| **ModMenu** | `modmenu` | Client-side only | Integrates the mod configuration button directly into the pause and title menus. |
| **Cloth Config** | `cloth-config` | Client-side only | Fallback configuration screen provider for legacy setups. |

### 🛡️ Classloader Safety & Side-Separation
The configuration GUI is dynamically instantiated using `net.dasik.social.api.config.GuiHelper`:
```java
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return GuiHelper.getOptionalYaclFactory(
            "leveldoessomething",
            "net.instantgratification.leveldoessomething.config.YaclScreenHelper",
            "createScreen"
        );
    }
}
```

---

## 🔗 Dedicated Version Trees
* [[26.2 Portal General|es_es-26.2-Home]]
* [[26.3 Portal General|es_es-26.3-Home]]
* [[Volver al Portal Principal|es_es-Home]]
