/*
 * Standard Core v2.1
 * Verified against: ClientLevel.java, ParticleTypes.java (26.1.2+)
 */
package net.instantgratification.leveldoessomething.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.instantgratification.leveldoessomething.config.LevelDoesSomethingConfig;
import net.instantgratification.leveldoessomething.util.LevelAuraTracker;

import java.util.Random;

public class LevelAuraRenderer {
    private static final Random RANDOM = new Random();

    public static void tick(ClientLevel level) {
        // Dual-layer client-side visual toggle
        if (!LevelDoesSomethingConfig.get().enableClientAuraParticles) {
            return;
        }

        for (Player player : level.players()) {
            if (player.isSpectator() || player.isInvisible()) {
                continue;
            }

            // Sync level check from tracker
            int auraLevel = ((LevelAuraTracker) player).getAuraLevel();
            if (auraLevel >= 30) {
                spawnAuraParticles(level, player, auraLevel);
            }
        }
    }

    private static void spawnAuraParticles(ClientLevel level, Player player, int auraLevel) {
        // Math clamp to cap particle density based on player level
        int particleCount = Math.clamp((auraLevel - 20) / 15, 1, 5);

        for (int i = 0; i < particleCount; i++) {
            // Spiral height offset (0.0 to 2.2 meters)
            double height = RANDOM.nextDouble() * 2.2;
            
            // Swirling angle calculation based on player tickCount and height
            double angle = height * 2.5 + player.tickCount * 0.15 + RANDOM.nextDouble() * 0.5;
            double radius = 0.55 + RANDOM.nextGaussian() * 0.05;

            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);

            double px = player.getX() + xOffset;
            double py = player.getY() + height;
            double pz = player.getZ() + zOffset;

            // Gentle upward drift velocities
            double vx = (RANDOM.nextDouble() - 0.5) * 0.02;
            double vy = 0.04 + RANDOM.nextDouble() * 0.02;
            double vz = (RANDOM.nextDouble() - 0.5) * 0.02;

            // Alternate between happy_villager (green sparks) and trial_spawner_detection (gold sparkles)
            if (RANDOM.nextBoolean()) {
                level.addParticle(ParticleTypes.HAPPY_VILLAGER, px, py, pz, vx, vy, vz);
            } else {
                level.addParticle(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER, px, py, pz, vx, vy, vz);
            }
        }
    }
}
