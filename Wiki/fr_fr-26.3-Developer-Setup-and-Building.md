🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# Configuration Développeur & Chaîne d'Outils (MC 26.3)

> 📌 **Avertissement relatif à la source du dépôt** : Cette documentation wiki reflète **l'état actuel du code source dans le dépôt**, qui peut inclure les derniers commits non publiés ou des fonctionnalités en cours de développement avant les versions publiques sur CurseForge et Modrinth.

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
* [[Retour au Portail Minecraft 26.3|fr_fr-26.3-Home]]
* [[26.3 Architecture & Mixins|fr_fr-26.3-Architecture-and-Mixins]]
* [[Matrice de Compatibilité|fr_fr-Version-Compatibility]]
* [[Retour au Portail Principal|fr_fr-Home]]
