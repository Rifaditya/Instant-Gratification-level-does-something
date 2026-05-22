/*
 * Standard Core v2.1
 * Verified against: ConfigBuilder.java (Cloth Config 15+)
 */
package net.instantgratification.leveldoessomething.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClothConfigScreenHelper {
    public static Screen createConfigScreen(Screen parent) {
        LevelDoesSomethingConfig config = LevelDoesSomethingConfig.get();

        ConfigBuilder builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Component.translatable("text.leveldoessomething.config.title"))
            .setSavingRunnable(LevelDoesSomethingConfig::save);

        ConfigCategory general = builder.getOrCreateCategory(
            Component.translatable("text.leveldoessomething.config.category.general")
        );
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(
                Component.translatable("text.leveldoessomething.config.option.enableClientAuraParticles"),
                config.enableClientAuraParticles
            )
            .setDefaultValue(true)
            .setTooltip(Component.translatable("text.leveldoessomething.config.option.enableClientAuraParticles.tooltip"))
            .setSaveConsumer(val -> config.enableClientAuraParticles = val)
            .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle(
                Component.translatable("text.leveldoessomething.config.option.enableClientSoundPitch"),
                config.enableClientSoundPitch
            )
            .setDefaultValue(true)
            .setTooltip(Component.translatable("text.leveldoessomething.config.option.enableClientSoundPitch.tooltip"))
            .setSaveConsumer(val -> config.enableClientSoundPitch = val)
            .build()
        );

        return builder.build();
    }
}
