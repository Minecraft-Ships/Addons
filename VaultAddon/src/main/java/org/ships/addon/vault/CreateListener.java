package org.ships.addon.vault;

import org.core.entity.Entity;
import org.core.entity.living.human.player.LivePlayer;
import org.core.event.EventListener;
import org.core.event.events.entity.EntityEvent;
import org.ships.event.vessel.create.VesselCreateEvent;

public class CreateListener implements EventListener {

    public void onVesselCreate(final VesselCreateEvent.Pre event) {
        double price = event.getVessel().getType().getFlagValue(CreatePriceFlag.class).orElse(0.0);
        if (price == 0.0) {
            return;
        }
        if (!(event instanceof EntityEvent)) {
            return;
        }
        final Entity<?> entity = ((EntityEvent<?>)event).getEntity();
        if (!(entity instanceof LivePlayer)) {
            return;
        }
        final LivePlayer player = (LivePlayer)entity;
        if (player.hasPermission("vaultaddon.ignore.create")) {
            return;
        }
        if (!player.removeBalance(price)) {
            player.sendMessagePlain(event.getVessel().getType().getDisplayName() + " costs " + price);
            event.setCancelled(true);
        }
    }
}
