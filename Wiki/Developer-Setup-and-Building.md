# Developer Setup & Gradle Toolchain Guide

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🛠️ Environment Prerequisites

To compile and contribute to **Level Does Something**, ensure your local development workstation satisfies the following requirements:

- **Java Development Kit (JDK)**: **JDK 25** (64-bit). Configure your `JAVA_HOME` or specify `org.gradle.java.home=E:/JDK25` in `gradle.properties`.
- **Build System**: **Gradle 9.3+** (invoked via the standard `./gradlew` wrapper).
- **Modding Toolchain**: **Fabric Loom** with Minecraft `26.2` or `26.3-snapshot-6`.
- **IDE**: IntelliJ IDEA (recommended with Minecraft Development plugin) or Visual Studio Code.

---

## 📂 Multi-Version Repository Architecture

The root repository contains subproject directories organized by Minecraft version anchor:

```
Level Does Something/
├── Archive Jar of all versions/
│   ├── MC 26.2/
│   │   └── level-does-something-1.0.5+26.2.jar
│   └── MC 26.3/
│       └── level-does-something-1.0.5+26.3.jar
├── Level Does Something v26.2/
│   └── level-does-something/
│       ├── build.gradle
│       ├── gradle.properties
│       └── src/
└── Level Does Something v26.3/
    └── level-does-something/
        ├── build.gradle
        ├── gradle.properties
        └── src/
```

---

## ⚙️ Gradle Toolchain Configuration

### `gradle.properties` (Minecraft 26.2 Anchor)
```properties
org.gradle.parallel=false

mod_name=Level Does Something
mod_version=1.0.5+26.2
maven_group=net.instantgratification
archives_base_name=level-does-something

minecraft_version=26.2
parchment_minecraft_version=26.2
parchment_version=2026.02.15

fabric_version=0.149.0+26.2
fabric_loader_version=0.19.3

fabric.loom.suppressJavaCompatibilityChecks=true
loom.suppressJavaCompatibilityChecks=true

dasik_library_version=1.8.38
```

### `gradle.properties` (Minecraft 26.3 Anchor)
```properties
org.gradle.parallel=false
org.gradle.java.home=E:/JDK25

mod_name=Level Does Something
mod_version=1.0.5+26.3
maven_group=net.instantgratification
archives_base_name=level-does-something

minecraft_version=26.3-snapshot-6
parchment_minecraft_version=26.3-snapshot-6
parchment_version=2026.01.22

fabric_version=0.156.1+26.3
fabric_loader_version=0.19.3

fabric.loom.suppressJavaCompatibilityChecks=true
loom.suppressJavaCompatibilityChecks=true

dasik_library_version=1.8.38
```

---

## 🔨 Building & Verification Commands

Always use the `--no-daemon` flag when executing builds on Windows environments to prevent locked memory-mapped `.jar` or `.class` file locks:

```powershell
# Navigate to the target version subproject
cd "Level Does Something v26.2/level-does-something"

# 1. Clean the build directory
./gradlew clean --no-daemon

# 2. Compile and assemble production binary
./gradlew build --no-daemon

# 3. Launch development client with hot reload
./gradlew runClient

# 4. Launch development dedicated server
./gradlew runServer
```

Compiled production binaries are generated at:
`build/libs/level-does-something-<version>.jar`

---

## 🧩 Key Loom Dependencies & Repositories

Inside `build.gradle`, verify that `DasikLibrary` and optional GUI libraries are resolved:

```groovy
dependencies {
    minecraft "com.mojang:minecraft:${project.minecraft_version}"
    mappings loom.layered() {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${project.parchment_minecraft_version}:${project.parchment_version}@zip")
    }
    modImplementation "net.fabricmc:fabric-loader:${project.fabric_loader_version}"
    modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"

    // Required Core Architecture
    modImplementation "net.dasik:dasik-library:${project.dasik_library_version}"

    // Optional Client Configuration
    modCompileOnly "dev.isxander:yet-another-config-lib:3.6.8+1.21.4-fabric"
    modCompileOnly "com.terraformersmc:modmenu:13.0.0-beta.1"
}
```

---

## 🔗 Dedicated Subproject Guides
* [[MC 26.2 Developer Setup|26.2-Developer-Setup-and-Building]]
* [[MC 26.3 Developer Setup|26.3-Developer-Setup-and-Building]]
* [[MC 26.2 Architecture & Mixins|26.2-Architecture-and-Mixins]]
* [[MC 26.3 Architecture & Mixins|26.3-Architecture-and-Mixins]]
