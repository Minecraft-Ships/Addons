package org.ships.addon.vault;

import org.core.platform.plugin.details.PluginVersion;
/**
 * Gets the plugin version in the form of major.minor
 */
public class VaultAddonVersion implements PluginVersion {

    final int major;
    final int minor;

    public VaultAddonVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    @Override
    public String asString() {
        return getMajor() + "." + getMinor();
    }
}
