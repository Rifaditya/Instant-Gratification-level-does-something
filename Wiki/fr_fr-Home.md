# Level Does Something — Portail Officiel de Documentation

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 Portail de Sélection de Version

Sélectionnez votre version ciblée de Minecraft pour accéder à la documentation dédiée des mécaniques et configurations :

| Version Minecraft | Canal | Fichier binaire | Navigation rapide |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | Modern Standard | `level-does-something-1.0.5+26.2.jar` | [[👉 Entrer dans le Wiki MC 26.2|26.2-Home]] |
| **Minecraft 26.3** | Modern Lead | `level-does-something-1.0.5+26.3.jar` | [[👉 Entrer dans le Wiki MC 26.3|26.3-Home]] |

Pour vérifier la compatibilité avec Fabric Loader, Java et DasikLibrary, consultez la [[Matrice de compatibilité|Version-Compatibility]].

---

## ⚡ Philosophie et Mécanismes Fondamentaux

**Level Does Something** applique la philosophie **Instant Gratification (Gratification Immédiate)** :  
> *« Respectez le temps du joueur, pas les règles dépassées du jeu. »*

Dans Minecraft vanilla, accumuler de l'expérience au-delà du niveau 30 n'apporte presque aucun avantage concret. Elle ne sert que de monnaie consommable pour les enchantements et les réparations d'enclume, monnaie anéantie à la mort. Ce mod fait de l'accumulation d'expérience une source permanente de puissance passive !

### 1. Progression Dynamique des Attributs
Conserver des niveaux d'expérience augmente 5 attributs majeurs :
* **Vitesse de minage** (`minecraft:block_break_speed`) : Brisez les blocs plus rapidement (+1.0% par unité d'échelle).
* **Dégâts d'attaque** (`minecraft:attack_damage`) : Augmente vos dégâts physiques (+0.5% par unité d'échelle).
* **Vitesse de déplacement** (`minecraft:movement_speed`) : Déplacez-vous et sprintez plus vite (+0.2% par unité d'échelle).
* **Chance** (`minecraft:luck`) : Accroît la probabilité d'obtenir des butins rares (+1.0% par unité d'échelle).
* **Santé maximale** (`minecraft:max_health`) : Ajoute des cœurs supplémentaires (+1.0% par unité d'échelle) avec **guérison instantanée de la différence**, évitant les cœurs vides.

### 2. Aura Visuelle & Retour Sonore
* **Aura Céleste (Niveau 30+)** : Un vortex en spirale de particules d'étoiles vertes (`minecraft:happy_villager`) et d'étincelles dorées (`minecraft:trial_spawner_detection`).
* **Hauteur sonore modulée** : Le ramassage des orbes d'expérience produit des sons de plus en plus aigus (jusqu'à 2.0x).
* **Carillon d'Ascension** : Franchir les niveaux 30, 60 et 100 déclenche un carillon solennel (`minecraft:ui.toast.challenge_complete`).

---

## 📚 Liens Rapides
* [[⚙️ Matrice de compatibilité|Version-Compatibility]]
* [[❓ Dépannage et FAQ|Troubleshooting-and-FAQ]]
* [[🛠️ Guide de configuration & Compilation Gradle|Developer-Setup-and-Building]]
* [[26.2 Échelonnage d'expérience et courbes|26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 Échelonnage d'expérience et courbes|26.3-Experience-Scaling-and-Mathematical-Curves]]

---

## ⚖️ Licence et Attribution
* **Développeur** : **Dasik (Rifaditya)**
* **Licence** : **GNU General Public License v3.0 (GPLv3)**
* **Dépôt** : [GitHub](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
