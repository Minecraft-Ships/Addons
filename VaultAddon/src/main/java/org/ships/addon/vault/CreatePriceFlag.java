package org.ships.addon.vault;

import org.core.config.parser.Parser;
import org.core.config.parser.StringParser;
import org.ships.vessel.common.flag.VesselFlag;

import java.util.Optional;

public class CreatePriceFlag implements VesselFlag.Serializable<Double> {
    private double price;

    public CreatePriceFlag() {
        this(0.0);
    }

    public CreatePriceFlag(final double price) {
        this.price = price;
    }

    @Override
    public String serialize() {
        return this.price + "";
    }

    @Override
    public VesselFlag.Serializable<Double> deserialize(final String idWithValue) {
        try {
            this.price = Double.parseDouble(idWithValue);
        } catch (NumberFormatException ex) {
        }
        return this;
    }

    @Override
    public boolean isDeserializable(final String idWithValue) {
        try {
            Double.parseDouble(idWithValue);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Optional<Double> getValue() {
        return Optional.of(this.price);
    }

    @Override
    public void setValue(final Double value) {
        this.price = value;
    }

    @Override
    public StringParser<Double> getParser() {
        return Parser.STRING_TO_DOUBLE;
    }

    @Override
    public Builder toBuilder() {
        return new Builder();
    }

    @Override
    public String getId() {
        return VaultAddon.getInstance().getCoreAddon().getPluginId() + ":price";
    }

    @Override
    public String getName() {
        return "Price";
    }

    public static class Builder extends VesselFlag.Builder<Double, CreatePriceFlag> {
        @Override
        protected CreatePriceFlag buildEmpty() {
            return new CreatePriceFlag();
        }
    }
}