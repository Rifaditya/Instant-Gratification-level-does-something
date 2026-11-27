/*
 * Standard Core v2.1
 * Verified against: ClientModInitializer.java (26.1.2+)
 */
package net.instantgratification.leveldoessomething;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.instantgratification.leveldoessomething.client.LevelAuraRenderer;
import net.instantgratification.leveldoessomething.config.LevelDoesSomethingConfig;
import net.instantgratification.leveldoessomething.network.LevelAuraSyncPayload;
import net.instantgratification.leveldoessomething.util.LevelAuraTracker;

public class LevelDoesSomethingFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Load client configuration
        LevelDoesSomethingConfig.load();

        // Register optional S2C aura sync receiver for modded clients
        ClientPlayNetworking.registerGlobalReceiver(LevelAuraSyncPayload.TYPE, (payload, context) ->
            context.client().execute(() -> {
                if (context.client().level != null
                        && context.client().level.getEntity(payload.entityId()) instanceof LevelAuraTracker tracker) {
                    tracker.setAuraLevel(payload.auraLevel());
                }
            })
        );

        // Register client tick handler for ambient particle rendering
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.level != null && !client.isPaused()) {
                LevelAuraRenderer.tick(client.level);
            }
        });
    }
}
