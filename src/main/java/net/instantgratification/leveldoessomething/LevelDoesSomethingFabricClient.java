/*
 * Standard Core v2.1
 * Verified against: ClientModInitializer.java (26.1.2+)
 */
package net.instantgratification.leveldoessomething;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.instantgratification.leveldoessomething.client.LevelAuraRenderer;
import net.instantgratification.leveldoessomething.config.LevelDoesSomethingConfig;

public class LevelDoesSomethingFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Load client configuration
        LevelDoesSomethingConfig.load();

        // Register client tick handler for ambient particle rendering
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.level != null && !client.isPaused()) {
                LevelAuraRenderer.tick(client.level);
            }
        });
    }
}
