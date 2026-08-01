/*
 * Standard Core v2.1
 * Verified against: AttributeInstance.java, Attributes.java (26.1.2+)
 */
package net.instantgratification.leveldoessomething.util;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.instantgratification.leveldoessomething.LevelDoesSomethingFabric;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;

public class PlayerLevelAttributesHandler {
    public static final Identifier BREAK_SPEED_ID = Identifier.fromNamespaceAndPath(LevelDoesSomethingFabric.MOD_ID, "level_break_speed");
    public static final Identifier ATTACK_DAMAGE_ID = Identifier.fromNamespaceAndPath(LevelDoesSomethingFabric.MOD_ID, "level_attack_damage");
    public static final Identifier MOVEMENT_SPEED_ID = Identifier.fromNamespaceAndPath(LevelDoesSomethingFabric.MOD_ID, "level_movement_speed");
    public static final Identifier LUCK_ID = Identifier.fromNamespaceAndPath(LevelDoesSomethingFabric.MOD_ID, "level_luck");
    public static final Identifier MAX_HEALTH_ID = Identifier.fromNamespaceAndPath(LevelDoesSomethingFabric.MOD_ID, "level_max_health");

    /**
     * Recalculates and updates all level-based attributes for the player.
     * Must only be run on the server side.
     */
    public static void updatePlayerAttributes(Player player) {
        if (player.level().isClientSide()) {
            return;
        }

        int level = player.experienceLevel;

        // Retrieve server GameRules safely
        int curveType = DynamicGameRuleManager.getInt(player.level(), LevelDoesSomethingFabric.RULE_CURVE_TYPE);
        int baseMultBP = DynamicGameRuleManager.getInt(player.level(), LevelDoesSomethingFabric.RULE_BASE_MULTIPLIER);
        int maxTier = DynamicGameRuleManager.getInt(player.level(), LevelDoesSomethingFabric.RULE_MAX_TIER);

        double scaleFactor = calculateScaleFactor(level, curveType, maxTier);
        double globalMult = baseMultBP / 10.0; // 10 basis points = 1.0 multiplier ratio (e.g. 0.001 per scale unit)

        // Apply attribute updates
        applyModifier(player, Attributes.BLOCK_BREAK_SPEED, BREAK_SPEED_ID, 0.01 * scaleFactor * globalMult);
        applyModifier(player, Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_ID, 0.005 * scaleFactor * globalMult);
        applyModifier(player, Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED_ID, 0.002 * scaleFactor * globalMult);
        applyModifier(player, Attributes.LUCK, LUCK_ID, 0.01 * scaleFactor * globalMult);
        applyMaxHealthModifier(player, MAX_HEALTH_ID, 0.01 * scaleFactor * globalMult);
    }

    /**
     * Clears all level-based attributes from the player.
     */
    public static void removePlayerAttributes(Player player) {
        if (player.level().isClientSide()) {
            return;
        }
        removeModifier(player, Attributes.BLOCK_BREAK_SPEED, BREAK_SPEED_ID);
        removeModifier(player, Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_ID);
        removeModifier(player, Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED_ID);
        removeModifier(player, Attributes.LUCK, LUCK_ID);
        removeMaxHealthModifier(player, MAX_HEALTH_ID);
    }

    private static double calculateScaleFactor(int level, int curveType, int maxTier) {
        return switch (curveType) {
            case 0 -> { // Exponential Tier Steps
                if (level < 2) yield 0.0;
                int tier = Math.min(maxTier, (int) (Math.log(level) / Math.log(2)));
                yield Math.pow(2, tier);
            }
            case 2 -> // Linear Scaling
                level;
            default -> // Smooth Logarithmic Curve (Default)
                Math.log(level + 1);
        };
    }

    private static void applyModifier(Player player, Holder<Attribute> attribute, Identifier id, double amount) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance != null) {
            instance.removeModifier(id);
            if (amount > 0.0) {
                AttributeModifier modifier = new AttributeModifier(id, amount, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                instance.addOrUpdateTransientModifier(modifier);
            }
        }
    }

    private static void removeModifier(Player player, Holder<Attribute> attribute, Identifier id) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance != null) {
            instance.removeModifier(id);
        }
    }

    private static void applyMaxHealthModifier(Player player, Identifier id, double amount) {
        AttributeInstance instance = player.getAttribute(Attributes.MAX_HEALTH);
        if (instance != null) {
            double previousMaxHealth = player.getMaxHealth();
            instance.removeModifier(id);
            if (amount > 0.0) {
                AttributeModifier modifier = new AttributeModifier(id, amount, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
                instance.addOrUpdateTransientModifier(modifier);
                
                // Heal player by the difference if max health increased to populate new hearts immediately
                double newMaxHealth = player.getMaxHealth();
                double healthDiff = newMaxHealth - previousMaxHealth;
                if (healthDiff > 0) {
                    player.heal((float) healthDiff);
                }
            }
        }
    }

    private static void removeMaxHealthModifier(Player player, Identifier id) {
        AttributeInstance instance = player.getAttribute(Attributes.MAX_HEALTH);
        if (instance != null) {
            instance.removeModifier(id);
        }
    }
}
