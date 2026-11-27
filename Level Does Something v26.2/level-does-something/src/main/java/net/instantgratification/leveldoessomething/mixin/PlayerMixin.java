/*
 * Standard Core v2.1
 * Verified against: Player.java, Entity.java (26.1.2+)
 */
package net.instantgratification.leveldoessomething.mixin;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.leveldoessomething.LevelDoesSomethingFabric;
import net.instantgratification.leveldoessomething.network.LevelAuraNetworking;
import net.instantgratification.leveldoessomething.util.LevelAuraTracker;
import net.instantgratification.leveldoessomething.util.PlayerLevelAttributesHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements LevelAuraTracker {

    @Unique
    private int leveldoessomething$lastExperienceLevel = -1;

    @Unique
    private int leveldoessomething$auraLevel = 0;

    @Unique
    private int leveldoessomething$lastBroadcastAuraLevel = -1;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        Player player = (Player) (Object) this;

        if (!this.level().isClientSide()) {
            // Server side tracking
            if (this.leveldoessomething$lastExperienceLevel != player.experienceLevel) {
                int oldLevel = this.leveldoessomething$lastExperienceLevel;
                this.leveldoessomething$lastExperienceLevel = player.experienceLevel;
                PlayerLevelAttributesHandler.updatePlayerAttributes(player);

                if (oldLevel != -1 && player.experienceLevel > oldLevel) {
                    boolean enableSound = DynamicGameRuleManager.getBoolean(this.level(), LevelDoesSomethingFabric.RULE_ENABLE_SOUND_PITCH);
                    if (enableSound) {
                        leveldoessomething$checkAndPlayMilestoneChime(oldLevel, player.experienceLevel);
                    }
                }
            }

            boolean enableAura = DynamicGameRuleManager.getBoolean(this.level(), LevelDoesSomethingFabric.RULE_ENABLE_AURA);
            int desiredAura = LevelAuraNetworking.computeDesiredAuraLevel(enableAura, player.experienceLevel);
            this.leveldoessomething$auraLevel = desiredAura;

            if (player instanceof ServerPlayer serverPlayer) {
                if (this.leveldoessomething$lastBroadcastAuraLevel != desiredAura) {
                    this.leveldoessomething$lastBroadcastAuraLevel = desiredAura;
                    LevelAuraNetworking.syncAuraChange(serverPlayer, desiredAura);
                }

                if ((player.tickCount & 3) == 0 && desiredAura >= 30) {
                    LevelAuraNetworking.emitVanillaFallbackParticles(serverPlayer, desiredAura);
                }
            }
        } else {
            // Client side tracking (only keeping local client's track level in sync)
            if (player.isLocalPlayer()) {
                this.leveldoessomething$lastExperienceLevel = player.experienceLevel;
            }
        }
    }

    @Unique
    private void leveldoessomething$checkAndPlayMilestoneChime(int oldLevel, int newLevel) {
        int[] milestones = {30, 60, 100};
        for (int milestone : milestones) {
            if (oldLevel < milestone && newLevel >= milestone) {
                // Play triumphant milestone chime sound server-side to player and surrounding players
                this.level().playSound(
                    null,
                    this.getX(), this.getY(), this.getZ(),
                    SoundEvents.UI_TOAST_CHALLENGE_COMPLETE,
                    SoundSource.PLAYERS,
                    0.8f,
                    1.2f
                );
                break; // Only play one chime even if multiple crossed
            }
        }
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void onRemove(Entity.RemovalReason reason, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        if (!player.level().isClientSide()) {
            PlayerLevelAttributesHandler.removePlayerAttributes(player);
        }
    }

    @Override
    public int getAuraLevel() {
        return this.leveldoessomething$auraLevel;
    }

    @Override
    public void setAuraLevel(int level) {
        this.leveldoessomething$auraLevel = Math.max(0, level);
    }
}
