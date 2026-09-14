// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.leveldoessomething.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.dasik.social.api.config.DasikSupportHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class YaclScreenHelper {
    public static ConfigScreenFactory<?> createScreen() {
        return YaclScreenHelper::buildScreen;
    }

    private static Screen buildScreen(Screen parent) {
        LevelDoesSomethingConfig config = LevelDoesSomethingConfig.get();

        var generalGroup = OptionGroup.createBuilder()
            .name(Component.translatable("text.leveldoessomething.config.category.general"));

        Option<?> supportButton = (Option<?>) DasikSupportHelper.createYaclButton();
        if (supportButton != null) {
            generalGroup.option(supportButton);
        }

        generalGroup
            .option(Option.<Boolean>createBuilder()
                .name(Component.translatable("text.leveldoessomething.config.option.enableClientAuraParticles"))
                .description(OptionDescription.of(Component.translatable("text.leveldoessomething.config.option.enableClientAuraParticles.tooltip")))
                .binding(
                    true,
                    () -> config.enableClientAuraParticles,
                    val -> config.enableClientAuraParticles = val
                )
                .controller(BooleanControllerBuilder::create)
                .build())
            .option(Option.<Boolean>createBuilder()
                .name(Component.translatable("text.leveldoessomething.config.option.enableClientSoundPitch"))
                .description(OptionDescription.of(Component.translatable("text.leveldoessomething.config.option.enableClientSoundPitch.tooltip")))
                .binding(
                    true,
                    () -> config.enableClientSoundPitch,
                    val -> config.enableClientSoundPitch = val
                )
                .controller(BooleanControllerBuilder::create)
                .build());

        return YetAnotherConfigLib.createBuilder()
            .title(Component.translatable("text.leveldoessomething.config.title"))
            .category(ConfigCategory.createBuilder()
                .name(Component.translatable("text.leveldoessomething.config.category.general"))
                .group(generalGroup.build())
                .build())
            .save(LevelDoesSomethingConfig::save)
            .build()
            .generateScreen(parent);
    }
}
