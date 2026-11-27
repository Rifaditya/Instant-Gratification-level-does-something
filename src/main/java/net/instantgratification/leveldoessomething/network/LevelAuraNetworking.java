// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.leveldoessomething.network;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.instantgratification.leveldoessomething.LevelDoesSomethingFabric;
import net.instantgratification.leveldoessomething.util.LevelAuraTracker;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;

/**
 * Dual-Delivery networking and vanilla-fallback particle emitter for player aura sync.
 * Strictly common/server-safe with zero client-only references.
 */
public final class LevelAuraNetworking {

    private static final double VANILLA_PARTICLE_RADIUS_SQ = 1024.0;

    private LevelAuraNetworking() {
    }

    /**
     * Registers the optional S2C aura sync payload and join-time sync callback.
     */
    public static void registerServer() {
        PayloadTypeRegistry.clientboundPlay().register(LevelAuraSyncPayload.TYPE, LevelAuraSyncPayload.CODEC);
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> syncOnJoin(handler.getPlayer()));
    }

    /**
     * Computes the authoritative aura level from GameRule polarity and player XP level.
     */
    public static int computeDesiredAuraLevel(boolean enableAura, int experienceLevel) {
        return enableAura ? Math.max(0, experienceLevel) : 0;
    }

    /**
     * Computes the bounded particle count per burst for a given aura level.
     */
    public static int computeParticleCount(int auraLevel) {
        return auraLevel < 30 ? 0 : Math.clamp((auraLevel - 20) / 15, 1, 5);
    }

    /**
     * Broadcasts an aura level update to modded viewers in the same dimension.
     */
    public static void syncAuraChange(ServerPlayer source, int auraLevel) {
        if (source == null) {
            return;
        }
        LevelAuraSyncPayload payload = new LevelAuraSyncPayload(source.getId(), auraLevel);
        for (ServerPlayer viewer : source.level().players()) {
            if (ServerPlayNetworking.canSend(viewer, LevelAuraSyncPayload.TYPE)) {
                ServerPlayNetworking.send(viewer, payload);
            }
        }
    }

    /**
     * Synchronizes active aura states on player join for modded clients.
     */
    public static void syncOnJoin(ServerPlayer joining) {
        if (joining == null) {
            return;
        }
        ServerLevel serverLevel = joining.level();
        boolean enableAura = DynamicGameRuleManager.getBoolean(serverLevel, LevelDoesSomethingFabric.RULE_ENABLE_AURA);
        int joiningAura = computeDesiredAuraLevel(enableAura, joining.experienceLevel);
        if (joining instanceof LevelAuraTracker tracker) {
            tracker.setAuraLevel(joiningAura);
        }

        if (ServerPlayNetworking.canSend(joining, LevelAuraSyncPayload.TYPE)) {
            for (ServerPlayer online : serverLevel.players()) {
                int onlineAura = online instanceof LevelAuraTracker tracker
                        ? tracker.getAuraLevel()
                        : computeDesiredAuraLevel(enableAura, online.experienceLevel);
                if (online == joining || onlineAura >= 30) {
                    ServerPlayNetworking.send(joining, new LevelAuraSyncPayload(online.getId(), onlineAura));
                }
            }
        }

        if (joiningAura >= 30) {
            LevelAuraSyncPayload joiningPayload = new LevelAuraSyncPayload(joining.getId(), joiningAura);
            for (ServerPlayer viewer : serverLevel.players()) {
                if (viewer != joining && ServerPlayNetworking.canSend(viewer, LevelAuraSyncPayload.TYPE)) {
                    ServerPlayNetworking.send(viewer, joiningPayload);
                }
            }
        }
    }

    /**
     * Emits server-driven aura particles only to nearby unmodded vanilla viewers.
     */
    public static void emitVanillaFallbackParticles(ServerPlayer source, int auraLevel) {
        if (source == null || auraLevel < 30 || source.isSpectator() || source.isInvisible()) {
            return;
        }
        int particleCount = computeParticleCount(auraLevel);
        if (particleCount <= 0) {
            return;
        }

        ServerLevel serverLevel = source.level();
        RandomSource random = source.getRandom();
        double baseX = source.getX();
        double baseY = source.getY();
        double baseZ = source.getZ();

        for (ServerPlayer viewer : serverLevel.players()) {
            if (ServerPlayNetworking.canSend(viewer, LevelAuraSyncPayload.TYPE)) {
                continue;
            }
            if (viewer.distanceToSqr(source) > VANILLA_PARTICLE_RADIUS_SQ) {
                continue;
            }
            for (int i = 0; i < particleCount; i++) {
                double height = random.nextDouble() * 2.2;
                double angle = height * 2.5 + source.tickCount * 0.15 + random.nextDouble() * 0.5;
                double radius = 0.55 + random.nextGaussian() * 0.05;
                double px = baseX + radius * Math.cos(angle);
                double py = baseY + height;
                double pz = baseZ + radius * Math.sin(angle);
                SimpleParticleType particle = random.nextBoolean()
                        ? ParticleTypes.HAPPY_VILLAGER
                        : ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER;
                serverLevel.sendParticles(viewer, particle, false, false, px, py, pz, 1, 0.0, 0.04, 0.0, 0.02);
            }
        }
    }
}
