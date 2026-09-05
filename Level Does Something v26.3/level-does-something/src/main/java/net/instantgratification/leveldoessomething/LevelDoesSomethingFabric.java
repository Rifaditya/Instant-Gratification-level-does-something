/*
 * Standard Core v2.1
 * Verified against: ModInitializer.java (26.1.2+)
 */
package net.instantgratification.leveldoessomething;

import net.fabricmc.api.ModInitializer;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LevelDoesSomethingFabric implements ModInitializer {
    public static final String MOD_ID = "leveldoessomething";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // GameRules category and rules
    public static GameRuleCategory LEVEL_POWER_CATEGORY;
    
    public static GameRule<Integer> RULE_CURVE_TYPE;
    public static GameRule<Integer> RULE_BASE_MULTIPLIER;
    public static GameRule<Integer> RULE_MAX_TIER;
    public static GameRule<Boolean> RULE_ENABLE_AURA;
    public static GameRule<Boolean> RULE_ENABLE_SOUND_PITCH;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Level Does Something! [Standard Core v2.1]");

        // Hard Dependency Enforcement
        if (!net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("dasik-library")) {
            throw new RuntimeException("Level Does Something requires DasikLibrary to function. Please install it.");
        }

        // Register custom GameRules category (Verified using Identifier.fromNamespaceAndPath)
        LEVEL_POWER_CATEGORY = DynamicGameRuleManager.registerCategory(
            Identifier.fromNamespaceAndPath(MOD_ID, "level_power")
        );

        // Register GameRules using DynamicGameRuleManager
        RULE_CURVE_TYPE = DynamicGameRuleManager.integerRule(
            MOD_ID + ":levelPowerCurveType", LEVEL_POWER_CATEGORY, 1
        )
        .min(0).max(2)
        .name("Level Power Curve Type")
        .description("The mathematical curve used to calculate attribute scaling. 0 = Exponential Tier steps, 1 = Smooth Logarithmic curve, 2 = Simple Linear scaling. Default: 1.")
        .register();

        // 10 basis points = 0.001 (0.1% boost per level/tier)
        RULE_BASE_MULTIPLIER = DynamicGameRuleManager.integerRule(
            MOD_ID + ":levelPowerBaseMultiplier", LEVEL_POWER_CATEGORY, 10
        )
        .min(0).max(1000)
        .name("Level Power Base Multiplier")
        .description("The base multiplier applied per level in basis points (1/10000). A value of 10 represents 0.001 (0.1% increase). Default: 10.")
        .register();

        RULE_MAX_TIER = DynamicGameRuleManager.integerRule(
            MOD_ID + ":levelPowerMaxTier", LEVEL_POWER_CATEGORY, 10
        )
        .min(1).max(30)
        .name("Level Power Max Tier")
        .description("The maximum tier multiplier allowed when using the exponential tier system. Default: 10.")
        .register();

        RULE_ENABLE_AURA = DynamicGameRuleManager.booleanRule(
            MOD_ID + ":levelPowerEnableAura", LEVEL_POWER_CATEGORY, true
        )
        .name("Level Power Enable Aura")
        .description("When true, players at or above level 30 will spawn ambient experience-themed particles visible to all surrounding players. Default: true.")
        .register();

        RULE_ENABLE_SOUND_PITCH = DynamicGameRuleManager.booleanRule(
            MOD_ID + ":levelPowerEnableSoundPitch", LEVEL_POWER_CATEGORY, true
        )
        .name("Level Power Enable Sound Pitch")
        .description("When true, experience collection sounds will be pitch-shifted higher as the player's level increases, providing auditory feedback. Default: true.")
        .register();

        LOGGER.info("Level Does Something! GameRules successfully registered.");
    }
}
