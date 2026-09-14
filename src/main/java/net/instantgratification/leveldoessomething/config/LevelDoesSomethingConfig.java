/*
 * Standard Core v2.1
 * Verified against: FabricLoader.java (26.1.2+)
 */
package net.instantgratification.leveldoessomething.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class LevelDoesSomethingConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger("leveldoessomething-config");
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("level-does-something-client.json");
    private static final Path BACKUP_PATH = FabricLoader.getInstance().getConfigDir().resolve("level-does-something-client.json.bak");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final long MAX_FILE_SIZE = 50 * 1024; // 50 KB safety limit

    private static LevelDoesSomethingConfig INSTANCE = new LevelDoesSomethingConfig();

    public boolean enableClientAuraParticles = true;
    public boolean enableClientSoundPitch = true;

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                long size = Files.size(CONFIG_PATH);
                if (size > MAX_FILE_SIZE) {
                    LOGGER.warn("Config file size is too large ({} bytes). Resetting to defaults.", size);
                    INSTANCE = new LevelDoesSomethingConfig();
                    save();
                    return;
                }

                try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                    LevelDoesSomethingConfig loaded = GSON.fromJson(reader, LevelDoesSomethingConfig.class);
                    if (loaded != null) {
                        INSTANCE = loaded;
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Failed to parse config, attempting to restore from backup", e);
                restoreFromBackup();
            }
        }
        // Save to ensure all fields are written and file is formatted properly
        save();
    }

    public static void save() {
        try {
            Path tempPath = CONFIG_PATH.resolveSibling("level-does-something-client.json.tmp");
            
            // Create directories if they don't exist
            Files.createDirectories(CONFIG_PATH.getParent());
            
            // Write to temporary file
            try (Writer writer = Files.newBufferedWriter(tempPath)) {
                GSON.toJson(INSTANCE, writer);
            }

            // Create backup of current file
            if (Files.exists(CONFIG_PATH)) {
                Files.copy(CONFIG_PATH, BACKUP_PATH, StandardCopyOption.REPLACE_EXISTING);
            }

            // Atomically replace target config file
            Files.move(tempPath, CONFIG_PATH, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            LOGGER.error("Failed to save config atomically", e);
        }
    }

    private static void restoreFromBackup() {
        if (Files.exists(BACKUP_PATH)) {
            try (Reader reader = Files.newBufferedReader(BACKUP_PATH)) {
                LevelDoesSomethingConfig loaded = GSON.fromJson(reader, LevelDoesSomethingConfig.class);
                if (loaded != null) {
                    INSTANCE = loaded;
                    LOGGER.info("Config successfully restored from backup.");
                    save(); // Save restored config
                    return;
                }
            } catch (Exception e) {
                LOGGER.error("Failed to load from backup config", e);
            }
        }
        LOGGER.warn("Backup config unavailable or corrupted. Resetting to defaults.");
        INSTANCE = new LevelDoesSomethingConfig();
        save();
    }

    public static LevelDoesSomethingConfig get() {
        return INSTANCE;
    }
}
