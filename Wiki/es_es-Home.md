# Level Does Something — Portal Oficial de Documentación

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 Selector de Versión de Minecraft

Selecciona tu versión objetivo de Minecraft para consultar la documentación aislada de mecánicas, fórmulas matemáticas y reglas de juego:

| Versión de Minecraft | Canal de lanzamiento | Archivo compilado | Acceso directo |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | Modern Standard | `level-does-something-1.0.5+26.2.jar` | [[👉 Entrar a la Wiki de MC 26.2|26.2-Home]] |
| **Minecraft 26.3** | Modern Lead | `level-does-something-1.0.5+26.3.jar` | [[👉 Entrar a la Wiki de MC 26.3|26.3-Home]] |

Para verificar la compatibilidad con Fabric Loader, Java y DasikLibrary, consulta la [[Matriz de compatibilidad de versiones|Version-Compatibility]].

---

## ⚡ Filosofía y Mecánicas Principales

**Level Does Something** sigue la filosofía de diseño **Instant Gratification (Gratificación Instantánea)**:  
> *«Respeta el tiempo del jugador, no las reglas obsoletas del juego.»*

En Minecraft vanilla, acumular niveles de experiencia más allá del nivel 30 apenas tiene utilidad práctica. Sirve únicamente como moneda consumible para encantar o reparar en yunques, y se pierde casi por completo al morir. ¡Este mod transforma la experiencia acumulada en una progresión de poder pasivo inmediata!

### 1. Escalado Dinámico de Atributos
Mantener niveles de experiencia otorga bonificaciones pasivas constantes a 5 atributos esenciales:
* **Velocidad de ruptura de bloques** (`minecraft:block_break_speed`): Mina más rápido con manos y herramientas (+1.0% por unidad de escala).
* **Daño de ataque** (`minecraft:attack_damage`): Aumenta el poder de tus golpes físicos (+0.5% por unidad de escala).
* **Velocidad de movimiento** (`minecraft:movement_speed`): Te desplazas y corres con mayor rapidez (+0.2% por unidad de escala).
* **Suerte** (`minecraft:luck`): Incrementa la probabilidad de botín valioso en pesca y cofres de estructuras (+1.0% por unidad de escala).
* **Salud máxima** (`minecraft:max_health`): Otorga corazones adicionales (+1.0% por unidad de escala) con **curación inmediata de la diferencia**, evitando contenedores de corazones vacíos.

### 2. Aura Visual y Respuesta Auditiva
* **Aura Celestial de Experiencia (Nivel 30+)**: Vórtice ascendente en espiral compuesto por destellos verdes (`minecraft:happy_villager`) y dorados (`minecraft:trial_spawner_detection`).
* **Modulación del tono de sonido**: Recoger orbes de experiencia genera tonos de sonido progresivamente más agudos (hasta 2.0x).
* **Campanadas de Hito**: Alcanzar los niveles 30, 60 y 100 emite una campana resonante de ascenso (`minecraft:ui.toast.challenge_complete`).

---

## 📚 Enlaces Rápidos
* [[⚙️ Matriz de compatibilidad de versiones|Version-Compatibility]]
* [[❓ Preguntas frecuentes y solución de problemas|Troubleshooting-and-FAQ]]
* [[🛠️ Guía de compilación Gradle para desarrolladores|Developer-Setup-and-Building]]
* [[26.2 Escalado de experiencia y curvas matemáticas|26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 Escalado de experiencia y curvas matemáticas|26.3-Experience-Scaling-and-Mathematical-Curves]]

---

## ⚖️ Licencia y Atribución
* **Autor**: **Dasik (Rifaditya)**
* **Licencia**: **GNU General Public License v3.0 (GPLv3)**
* **Repositorio**: [GitHub](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
