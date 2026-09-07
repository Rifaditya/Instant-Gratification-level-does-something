🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

# Ambiente de Desenvolvimento e Toolchain (MC 26.2)

> 📌 **Aviso Legal da Fonte do Repositório**: Esta documentação wiki reflete o **estado atual do código-fonte no repositório**, podendo incluir os commits mais recentes não lançados ou recursos em desenvolvimento antes dos lançamentos públicos no CurseForge e Modrinth.

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
* [[Voltar ao Portal do Minecraft 26.2|pt_br-26.2-Home]]
* [[26.2 Arquitetura & Mixins|pt_br-26.2-Architecture-and-Mixins]]
* [[Matriz de Compatibilidade|pt_br-Version-Compatibility]]
* [[Voltar ao Portal Principal|pt_br-Home]]
