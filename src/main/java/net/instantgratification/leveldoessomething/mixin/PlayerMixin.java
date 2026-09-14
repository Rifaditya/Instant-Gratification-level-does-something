/*
 * Standard Core v2.1
 * Verified against: Player.java, Entity.java (26.1.2+)
 */
package net.instantgratification.leveldoessomething.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.instantgratification.leveldoessomething.LevelDoesSomethingFabric;
import net.instantgratification.leveldoessomething.util.LevelAuraTracker;
import net.instantgratification.leveldoessomething.util.PlayerLevelAttributesHandler;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements LevelAuraTracker {

    @Shadow public abstract SynchedEntityData getEntityData();

    @Unique
    private static final EntityDataAccessor<Integer> leveldoessomething$DATA_AURA_LEVEL = SynchedEntityData.defineId(Player.class, EntityDataSerializers.INT);

    @Unique
    private int leveldoessomething$lastExperienceLevel = -1;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void onDefineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(leveldoessomething$DATA_AURA_LEVEL, 0);
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
                
                boolean enableAura = DynamicGameRuleManager.getBoolean(this.level(), LevelDoesSomethingFabric.RULE_ENABLE_AURA);
                this.getEntityData().set(leveldoessomething$DATA_AURA_LEVEL, enableAura ? player.experienceLevel : 0);

                if (oldLevel != -1 && player.experienceLevel > oldLevel) {
                    boolean enableSound = DynamicGameRuleManager.getBoolean(this.level(), LevelDoesSomethingFabric.RULE_ENABLE_SOUND_PITCH);
                    if (enableSound) {
                        leveldoessomething$checkAndPlayMilestoneChime(oldLevel, player.experienceLevel);
                    }
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
    private void onRemove(net.minecraft.world.entity.Entity.RemovalReason reason, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        if (!player.level().isClientSide()) {
            PlayerLevelAttributesHandler.removePlayerAttributes(player);
        }
    }

    @Override
    public int getAuraLevel() {
        return this.getEntityData().get(leveldoessomething$DATA_AURA_LEVEL);
    }

    @Override
    public void setAuraLevel(int level) {
        this.getEntityData().set(leveldoessomething$DATA_AURA_LEVEL, level);
    }
}
