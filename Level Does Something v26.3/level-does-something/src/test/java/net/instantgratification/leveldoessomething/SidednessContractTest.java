// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.leveldoessomething;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pillar 5 Sidedness & Mixin Contract Verification Suite.
 * Scans compiled common bytecode in build/classes/java/main to guarantee
 * zero client-only references leak into dedicated-server common classes.
 */
public class SidednessContractTest {

    private static final String FORBIDDEN_CLIENT_BYTECODE =
            String.join("/", "net", "minecraft", "client") + "/";
    private static final String FORBIDDEN_BLAZE3D_BYTECODE =
            String.join("/", "com", "mojang", "blaze3d") + "/";

    @Test
    @DisplayName("Pillar 5: Common Bytecode Contains Zero Client References")
    void testCommonBytecodeHasZeroClientReferences() throws IOException {
        Path classesDir = Paths.get("build", "classes", "java", "main");
        assertTrue(Files.isDirectory(classesDir), "Compiled main classes directory must exist: " + classesDir.toAbsolutePath());

        List<Path> classFiles;
        try (Stream<Path> stream = Files.walk(classesDir)) {
            classFiles = stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".class"))
                    .toList();
        }
        assertFalse(classFiles.isEmpty(), "build/classes/java/main must contain compiled .class files");

        List<String> violations = new ArrayList<>();
        int commonScanned = 0;

        for (Path classFile : classFiles) {
            String relPath = classesDir.relativize(classFile).toString().replace('\\', '/');
            if (isClientScopedClass(relPath)) {
                continue;
            }
            commonScanned++;
            String rawBytes = new String(Files.readAllBytes(classFile), StandardCharsets.ISO_8859_1);
            if (rawBytes.contains(FORBIDDEN_CLIENT_BYTECODE)) {
                violations.add(relPath + " references " + FORBIDDEN_CLIENT_BYTECODE);
            }
            if (rawBytes.contains(FORBIDDEN_BLAZE3D_BYTECODE)) {
                violations.add(relPath + " references " + FORBIDDEN_BLAZE3D_BYTECODE);
            }
        }

        assertTrue(commonScanned >= 5, "Expected at least 5 common classes scanned, found: " + commonScanned);
        assertTrue(violations.isEmpty(), "Client bytecode leakage detected in common classes:\n" + String.join("\n", violations));
    }

    @Test
    @DisplayName("Pillar 5: Client Mixin Isolated to Client Array in mixins.json")
    void testClientMixinIsolationInManifest() throws IOException {
        Path mixinsJson = Paths.get("src", "main", "resources", "level-does-something.mixins.json");
        assertTrue(Files.exists(mixinsJson), "level-does-something.mixins.json must exist");
        String content = Files.readString(mixinsJson, StandardCharsets.UTF_8);

        int mixinsIdx = content.indexOf("\"mixins\"");
        int clientIdx = content.indexOf("\"client\"");
        assertTrue(mixinsIdx >= 0 && clientIdx > mixinsIdx, "mixins.json must declare both mixins and client sections");

        String commonSection = content.substring(mixinsIdx, clientIdx);
        assertFalse(commonSection.contains("ClientPacketListenerMixin"),
                "ClientPacketListenerMixin must not be registered in common mixins array");
    }

    private static boolean isClientScopedClass(String relPath) {
        if (relPath.contains("/client/") || relPath.startsWith("client/")) {
            return true;
        }
        String fileName = relPath.substring(relPath.lastIndexOf('/') + 1);
        String baseName = fileName.contains("$")
                ? fileName.substring(0, fileName.indexOf('$'))
                : fileName.substring(0, fileName.length() - ".class".length());

        return baseName.equals("LevelDoesSomethingFabricClient")
                || baseName.equals("ClientPacketListenerMixin")
                || baseName.equals("ModMenuIntegration")
                || baseName.equals("ClothConfigScreenHelper")
                || baseName.equals("YaclScreenHelper");
    }
}
