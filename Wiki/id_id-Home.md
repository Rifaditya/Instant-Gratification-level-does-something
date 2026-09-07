# Level Does Something — Portal Dokumentasi Resmi

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🧭 Portal Pemilihan Versi Minecraft

Pilih versi target Minecraft Anda untuk mengakses dokumentasi mekanik, kurva matematika, dan panduan konfigurasi yang terisolasi:

| Versi Minecraft | Saluran Rilis | Berkas Biner | Navigasi Cepat |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | Modern Standard | `level-does-something-1.0.5+26.2.jar` | [[👉 Masuk ke Wiki MC 26.2|id_id-26.2-Home]] |
| **Minecraft 26.3** | Modern Lead | `level-does-something-1.0.5+26.3.jar` | [[👉 Masuk ke Wiki MC 26.3|id_id-26.3-Home]] |

Untuk kompatibilitas Fabric Loader, runtime Java, dan DasikLibrary, silakan periksa [[Matriks Kompatibilitas Versi|id_id-Version-Compatibility]].

---

## ⚡ Filosofi Desain & Mekanik Inti

**Level Does Something** dibangun di atas filosofi modifikasi **Instant Gratification (Kepuasan Instan)**:  
> *“Hargai waktu pemain, bukan aturan usang permainan.”*

Dalam Minecraft vanilla, mengumpulkan level pengalaman (XP) di atas level 30 hampir tidak memberikan manfaat praktis. Pengalaman hanya digunakan sebagai mata uang habis pakai untuk meja sihir dan paron, yang sebagian besar lenyap seketika saat pemain mati. Mod ini mengubah pengalaman menjadi sumber kekuatan pasif langsung!

### 1. Penskalaan Atribut Pemain Dinamis
Menyimpan level pengalaman memberikan peningkatan pasif berkelanjutan ke 5 atribut inti:
* **Kecepatan Hancur Balok** (`minecraft:block_break_speed`): Menambang lebih cepat dengan tangan dan peralatan (+1.0% per unit skala).
* **Kerusakan Serangan** (`minecraft:attack_damage`): Meningkatkan kekuatan pukulan fisik (+0.5% per unit skala).
* **Kecepatan Gerak** (`minecraft:movement_speed`): Berjalan dan berlari lebih lincah (+0.2% per unit skala).
* **Keberuntungan** (`minecraft:luck`): Memperbesar peluang jarahan langka dari memancing dan peti harta (+1.0% per unit skala).
* **Kesehatan Maksimum** (`minecraft:max_health`): Menambah jumlah wadah hati (+1.0% per unit skala) dengan **penyembuhan instan atas selisih darah baru**, mencegah hati kosong!

### 2. Aura Visual & Respon Suara
* **Aura Pengalaman Surgawi (Level 30+)**: Pusaran partikel hijau zamrud (`minecraft:happy_villager`) dan kilau emas (`minecraft:trial_spawner_detection`) yang berputar naik mengelilingi pemain.
* **Modulasi Nada Suara XP**: Mengambil bola pengalaman menghasilkan nada suara yang kian meninggi seiring bertambahnya level (hingga 2.0x).
* **Lonceng Tonggak Kenaikan**: Menembus level 30, 60, dan 100 membunyikan lonceng perayaan kemegahan (`minecraft:ui.toast.challenge_complete`).

---

## 📚 Bagian Penting
* [[⚙️ Matriks Kompatibilitas Versi|id_id-Version-Compatibility]]
* [[❓ Pemecahan Masalah & FAQ|id_id-Troubleshooting-and-FAQ]]
* [[🛠️ Panduan Pengembang & Kompilasi Gradle|id_id-Developer-Setup-and-Building]]
* [[26.2 Penskalaan XP & Kurva Matematika|26.2-Experience-Scaling-and-Mathematical-Curves]]
* [[26.3 Penskalaan XP & Kurva Matematika|26.3-Experience-Scaling-and-Mathematical-Curves]]

---

## ⚖️ Lisensi & Hak Cipta
* **Pengembang**: **Dasik (Rifaditya)**
* **Lisensi**: **GNU General Public License v3.0 (GPLv3)**
* **Repositori**: [GitHub](https://github.com/Rifaditya/Instant-Gratification-level-does-something)
