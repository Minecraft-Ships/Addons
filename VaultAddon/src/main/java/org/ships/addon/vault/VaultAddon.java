package org.ships.addon.vault;

import org.core.CorePlugin;
import org.core.command.CommandRegister;
import org.core.config.ConfigurationFormat;
import org.core.config.ConfigurationStream;
import org.core.platform.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.Optional;

public class VaultAddon implements Plugin
{
    static BukkitVaultAddon plugin;

    public String getPluginName() {
        return "VaultAddon";
    }

    public String getPluginId() {
        return "vaultaddon";
    }

    public String getPluginVersion() {
        return "1.0 SNAPSHOT";
    }

    public void registerCommands(final CommandRegister register) {
    }

    @Override
    public void registerPlugin() {

    }

    @Override
    public void registerReady() {

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
        }
        catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(CorePlugin.createConfigurationFile(file, ConfigurationFormat.FORMAT_YAML));
    }

    public static BukkitVaultAddon getInstance() {
        return VaultAddon.plugin;
    }
}