package org.ships.addon.vault;

import org.core.adventureText.AText;
import org.core.adventureText.format.NamedTextColours;
import org.core.entity.Entity;
import org.core.entity.living.human.player.LivePlayer;
import org.core.event.EventListener;
import org.core.event.HEvent;
import org.core.event.events.entity.EntityEvent;
import org.ships.event.vessel.create.VesselCreateEvent;

public class CreateListener implements EventListener {

    @HEvent
    public void onVesselCreate(final VesselCreateEvent.Pre event) {
        double price = event.getVessel().getType().getFlagValue(CreatePriceFlag.class).orElse(0.0);
        if (price == 0.0) {
            return;
        }
        if (!(event instanceof EntityEvent)) {
            return;
        }
        final Entity<?> entity = ((EntityEvent<?>) event).getEntity();
        if (!(entity instanceof LivePlayer)) {
            return;
        }
        final LivePlayer player = (LivePlayer) entity;
        if (player.hasPermission("vaultaddon.ignore.create")) {
            return;
        }
        try {
            player.removeBalance(price);
        }catch (IllegalStateException e){
            AText message = AText.ofPlain(e.getLocalizedMessage()).withColour(NamedTextColours.RED);
            player.sendMessage(message);
            event.setCancelled(true);
        }
    }
}
