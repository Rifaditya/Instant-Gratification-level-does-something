/*
 * Standard Core v2.1
 * Verified against: ClientPacketListener.java (26.1.2+)
 */
package net.instantgratification.leveldoessomething.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.instantgratification.leveldoessomething.config.LevelDoesSomethingConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {

    @Shadow
    private ClientLevel level;

    @Shadow
    @Final
    protected Minecraft minecraft;

    @Redirect(
        method = "handleTakeItemEntity",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/multiplayer/ClientLevel;playLocalSound(DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V"
        )
    )
    private void redirectXPOrbSound(ClientLevel clientLevel, double x, double y, double z, SoundEvent sound, SoundSource source, float volume, float pitch, ClientboundTakeItemEntityPacket packet) {
        if (sound == SoundEvents.EXPERIENCE_ORB_PICKUP) {
            // Check if client-side config is enabled
            boolean clientEnableSound = LevelDoesSomethingConfig.get().enableClientSoundPitch;

            if (clientEnableSound) {
                // Find target player
                Entity playerEntity = clientLevel.getEntity(packet.getPlayerId());
                Player player = (playerEntity instanceof Player p) ? p : this.minecraft.player;

                if (player != null) {
                    int levelVal = player.experienceLevel;
                    // Pitch shifts up as the player accumulates levels
                    // Vanilla pitch is: (random.nextFloat() - random.nextFloat()) * 0.35f + 0.9f  (ranges from 0.55 to 1.25)
                    // We will add a factor based on player's level: + 0.015f * level, capped at 2.0f max pitch
                    float shift = 0.015f * levelVal;
                    float newPitch = Math.min(pitch + shift, 2.0f);
                    
                    clientLevel.playLocalSound(x, y, z, sound, source, volume, newPitch, false);
                    return;
                }
            }
        }
        
        // Fallback to default sound playing behavior
        clientLevel.playLocalSound(x, y, z, sound, source, volume, pitch, false);
    }
}
