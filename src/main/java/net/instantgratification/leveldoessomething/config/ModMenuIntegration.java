/*
 * Standard Core v2.1
 * Verified against: ModMenuApi.java (ModMenu 18+)
 */
package net.instantgratification.leveldoessomething.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ClothConfigScreenHelper::createConfigScreen;
    }
}
