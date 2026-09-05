// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.leveldoessomething.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.dasik.social.api.config.GuiHelper;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return GuiHelper.getOptionalYaclFactory(
            "leveldoessomething",
            "net.instantgratification.leveldoessomething.config.YaclScreenHelper",
            "createScreen"
        );
    }
}
