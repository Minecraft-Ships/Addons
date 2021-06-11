package org.ships.addon.vault;

        import org.core.config.ConfigurationStream;
        import org.core.config.parser.Parser;
        import org.core.platform.Plugin;
        import org.core.event.EventListener;
        import org.core.CorePlugin;
        import org.ships.config.Config;
        import org.ships.config.parsers.ShipsParsers;
        import org.ships.vessel.common.flag.VesselFlag;
        import org.core.config.ConfigurationNode;
        import org.ships.vessel.common.assits.shiptype.SerializableShipType;
        import org.ships.vessel.common.types.ShipType;
        import org.ships.plugin.ShipsPlugin;
        import net.milkbowl.vault.economy.Economy;
        import org.bukkit.plugin.java.JavaPlugin;

public class BukkitVaultAddon extends JavaPlugin
{
    private VaultAddon addon;
    private Economy eco;

    public static final ConfigurationNode.KnownParser.SingleKnown<Double> PRICE = new ConfigurationNode.KnownParser.SingleKnown<>(Parser.STRING_TO_DOUBLE, "flag", "vaultaddon", "price");

    public void onEnable() {
        this.addon = new VaultAddon();
        VaultAddon.plugin = this;
        ShipsPlugin.getPlugin().getAll(ShipType.class).forEach(type -> {
            if(!(type instanceof SerializableShipType)){
                return;
            }
            SerializableShipType<?> serializableShipType = (SerializableShipType<?>) type;
            ConfigurationStream.ConfigurationFile file = type.getFile();
            double price = file.getDouble(PRICE, 0);
            serializableShipType.register(new CreatePriceFlag(price));
        });
        CorePlugin.getEventManager().register(this.addon, new CreateListener());
    }

    public VaultAddon getCoreAddon() {
        return this.addon;
    }
}
