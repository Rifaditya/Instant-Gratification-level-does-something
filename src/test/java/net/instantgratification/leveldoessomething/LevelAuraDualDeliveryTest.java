// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.leveldoessomething;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.leveldoessomething.network.LevelAuraNetworking;
import net.instantgratification.leveldoessomething.network.LevelAuraSyncPayload;
import net.minecraft.SharedConstants;
import net.minecraft.resources.Identifier;
import net.minecraft.server.Bootstrap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;
import net.minecraft.world.level.gamerules.GameRules;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Dual-Delivery Aura Sync & Vanilla-Compat Verification Suite (BL-LDS-006).
 * Covers Pillar 1 Chaos Fuzzing, Pillar 2 Concurrency, Pillar 3 GameRule Dual-Polarity,
 * and Pillar 4 Negative Bytecode Parity.
 */
public class LevelAuraDualDeliveryTest {

    @BeforeAll
    static void setupMinecraftAndGameRules() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();

        if (LevelDoesSomethingFabric.LEVEL_POWER_CATEGORY == null) {
            LevelDoesSomethingFabric.LEVEL_POWER_CATEGORY = DynamicGameRuleManager.registerCategory(
                    Identifier.fromNamespaceAndPath(LevelDoesSomethingFabric.MOD_ID, "level_power")
            );
        }
        if (LevelDoesSomethingFabric.RULE_ENABLE_AURA == null) {
            try {
                LevelDoesSomethingFabric.RULE_ENABLE_AURA = DynamicGameRuleManager.booleanRule(
                        LevelDoesSomethingFabric.MOD_ID + ":level_power_enable_aura",
                        LevelDoesSomethingFabric.LEVEL_POWER_CATEGORY,
                        true
                ).register();
            } catch (Throwable ignored) {
            }
        }
        if (LevelDoesSomethingFabric.RULE_ENABLE_AURA == null) {
            LevelDoesSomethingFabric.RULE_ENABLE_AURA = new GameRule<>(
                    LevelDoesSomethingFabric.LEVEL_POWER_CATEGORY,
                    GameRuleType.BOOL,
                    BoolArgumentType.bool(),
                    GameRuleTypeVisitor::visitBoolean,
                    Codec.BOOL,
                    b -> b ? 1 : 0,
                    true,
                    FeatureFlagSet.of()
            );
        }
        assertNotNull(LevelDoesSomethingFabric.RULE_ENABLE_AURA, "RULE_ENABLE_AURA must be initialized");
    }

    @Test
    @DisplayName("Pillar 1: Chaos Bounds for Aura Level & Particle Count")
    void testChaosBounds() {
        int[] extremeLevels = {
                Integer.MIN_VALUE, -1, 0, 29, 30, 100, Integer.MAX_VALUE
        };
        int[] expectedEnabledAura = {
                0, 0, 0, 29, 30, 100, Integer.MAX_VALUE
        };
        int[] expectedParticleCounts = {
                0, 0, 0, 0, 1, 5, 5
        };

        for (int i = 0; i < extremeLevels.length; i++) {
            int xpLevel = extremeLevels[i];
            int enabledAura = LevelAuraNetworking.computeDesiredAuraLevel(true, xpLevel);
            int disabledAura = LevelAuraNetworking.computeDesiredAuraLevel(false, xpLevel);

            assertEquals(expectedEnabledAura[i], enabledAura, "Enabled aura mismatch at xp=" + xpLevel);
            assertEquals(0, disabledAura, "Disabled aura must always be 0 at xp=" + xpLevel);
            assertEquals(expectedParticleCounts[i], LevelAuraNetworking.computeParticleCount(enabledAura),
                    "Particle count mismatch at aura=" + enabledAura);
            assertEquals(0, LevelAuraNetworking.computeParticleCount(xpLevel < 30 ? xpLevel : -1));

            LevelAuraSyncPayload payload = new LevelAuraSyncPayload(i + 1, enabledAura);
            assertEquals(i + 1, payload.entityId());
            assertEquals(enabledAura, payload.auraLevel());
            assertEquals(LevelAuraSyncPayload.TYPE, payload.type());
        }
    }

    @Test
    @DisplayName("Pillar 2: Concurrency Stress on Pure Dual-Delivery Calculations")
    void testConcurrentCalculations() throws Exception {
        int threads = 8;
        int iterationsPerThread = 250;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        CountDownLatch startLatch = new CountDownLatch(1);
        List<Future<?>> futures = new ArrayList<>();

        int[] sampleLevels = {Integer.MIN_VALUE, -1, 0, 29, 30, 60, 100, Integer.MAX_VALUE};

        try {
            for (int t = 0; t < threads; t++) {
                final int workerId = t;
                futures.add(pool.submit(() -> {
                    startLatch.await();
                    for (int iter = 0; iter < iterationsPerThread; iter++) {
                        int xp = sampleLevels[(workerId + iter) % sampleLevels.length];
                        boolean enabled = ((workerId + iter) & 1) == 0;
                        int aura = LevelAuraNetworking.computeDesiredAuraLevel(enabled, xp);
                        int particles = LevelAuraNetworking.computeParticleCount(aura);
                        LevelAuraSyncPayload payload = new LevelAuraSyncPayload(workerId, aura);

                        assertTrue(aura >= 0);
                        assertTrue(particles >= 0 && particles <= 5);
                        assertEquals(aura, payload.auraLevel());
                    }
                    return null;
                }));
            }
            startLatch.countDown();
            for (Future<?> future : futures) {
                future.get();
            }
        } finally {
            pool.shutdownNow();
        }
    }

    @Test
    @DisplayName("Pillar 3: Non-Null ServerLevel & GameRules Dual-Polarity Evaluation")
    void testGameRuleDualPolarityOnServerLevel() throws Exception {
        GameRules gameRules = new GameRules(List.of(LevelDoesSomethingFabric.RULE_ENABLE_AURA));
        ServerLevel serverLevel = createStubServerLevel(gameRules);
        assertNotNull(serverLevel, "Stub ServerLevel must be non-null");

        // Polarity 1: RULE_ENABLE_AURA = true
        gameRules.set(LevelDoesSomethingFabric.RULE_ENABLE_AURA, true, null);
        boolean enabledState = DynamicGameRuleManager.getBoolean(serverLevel, LevelDoesSomethingFabric.RULE_ENABLE_AURA);
        assertTrue(enabledState, "DynamicGameRuleManager must read true when RULE_ENABLE_AURA is enabled");
        assertEquals(45, LevelAuraNetworking.computeDesiredAuraLevel(enabledState, 45));
        assertEquals(1, LevelAuraNetworking.computeParticleCount(LevelAuraNetworking.computeDesiredAuraLevel(enabledState, 30)));

        // Polarity 2: RULE_ENABLE_AURA = false (proves live ServerLevel.getGameRules lookup, not defaultValue fallback)
        gameRules.set(LevelDoesSomethingFabric.RULE_ENABLE_AURA, false, null);
        boolean disabledState = DynamicGameRuleManager.getBoolean(serverLevel, LevelDoesSomethingFabric.RULE_ENABLE_AURA);
        assertFalse(disabledState, "DynamicGameRuleManager must read false when RULE_ENABLE_AURA is disabled");
        assertEquals(0, LevelAuraNetworking.computeDesiredAuraLevel(disabledState, 45));
        assertEquals(0, LevelAuraNetworking.computeDesiredAuraLevel(disabledState, Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Pillar 4: Negative Bytecode Parity on PlayerMixin & Common Network Classes")
    void testNegativeBytecodeParity() throws IOException {
        Path playerMixinClass = Paths.get(
                "build", "classes", "java", "main",
                "net", "instantgratification", "leveldoessomething", "mixin", "PlayerMixin.class"
        );
        assertTrue(Files.exists(playerMixinClass), "Compiled PlayerMixin.class must exist: " + playerMixinClass.toAbsolutePath());

        String playerMixinBytes = new String(Files.readAllBytes(playerMixinClass), StandardCharsets.ISO_8859_1);
        assertFalse(playerMixinBytes.contains("SynchedEntityData"), "PlayerMixin must not reference SynchedEntityData");
        assertFalse(playerMixinBytes.contains("defineId"), "PlayerMixin must not call defineId");
        assertFalse(playerMixinBytes.contains("DATA_AURA_LEVEL"), "PlayerMixin must not retain DATA_AURA_LEVEL");
        assertFalse(playerMixinBytes.contains("defineSynchedData"), "PlayerMixin must not inject defineSynchedData");

        Path payloadClass = Paths.get(
                "build", "classes", "java", "main",
                "net", "instantgratification", "leveldoessomething", "network", "LevelAuraSyncPayload.class"
        );
        Path networkingClass = Paths.get(
                "build", "classes", "java", "main",
                "net", "instantgratification", "leveldoessomething", "network", "LevelAuraNetworking.class"
        );
        assertTrue(Files.exists(payloadClass), "Compiled LevelAuraSyncPayload.class must exist");
        assertTrue(Files.exists(networkingClass), "Compiled LevelAuraNetworking.class must exist");

        for (Path netClass : List.of(payloadClass, networkingClass)) {
            String raw = new String(Files.readAllBytes(netClass), StandardCharsets.ISO_8859_1);
            assertFalse(raw.contains("net/minecraft/client/"), netClass.getFileName() + " must not reference client classes");
            assertFalse(raw.contains("ClientPlayNetworking"), netClass.getFileName() + " must not reference ClientPlayNetworking");
        }
    }

    private static ServerLevel createStubServerLevel(GameRules gameRules) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        StubServerLevel level = (StubServerLevel) unsafe.allocateInstance(StubServerLevel.class);
        level.stubGameRules = gameRules;
        return level;
    }

    private static final class StubServerLevel extends ServerLevel {
        private GameRules stubGameRules;

        private StubServerLevel() {
            super(null, null, null, null, null, null, false, 0L, null, false);
        }

        @Override
        public GameRules getGameRules() {
            return this.stubGameRules;
        }
    }
}
