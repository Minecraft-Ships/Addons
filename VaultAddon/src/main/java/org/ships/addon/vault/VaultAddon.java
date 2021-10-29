package org.ships.addon.vault;

import org.core.platform.plugin.*;
import org.core.platform.plugin.details.PluginVersion;
import org.core.TranslateCore;
import org.core.command.CommandRegister;
import org.core.config.ConfigurationFormat;
import org.core.config.ConfigurationStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Optional;

public class VaultAddon implements Plugin {
    static BukkitVaultAddon plugin;

    public String getPluginName() {
        return "VaultAddon";
    }

    public String getPluginId() {
        return "vaultaddon";
    }

    public PluginVersion getPluginVersion() {
        return new VaultAddonVersion(0, 2);
    }

    public void registerCommands(final CommandRegister register) {
    }

    public BukkitVaultAddon getLauncher() {
        return VaultAddon.plugin;
    }

    public Optional<ConfigurationStream.ConfigurationFile> createConfig(final String configName, final File file) {
        final InputStream stream = this.getLauncher().getResource(configName);
        if (stream == null) {
            System.err.println("Request for '" + configName + "' could not be found");
            return Optional.empty();
        }
        try {
            file.getParentFile().mkdirs();
            Files.copy(stream, file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(TranslateCore.createConfigurationFile(file, ConfigurationFormat.FORMAT_YAML));
    }

    public static BukkitVaultAddon getInstance() {
        return VaultAddon.plugin;
    }

    public BukkitVaultAddon getPlatformLauncher() {
        return this.getLauncher();
    }
}