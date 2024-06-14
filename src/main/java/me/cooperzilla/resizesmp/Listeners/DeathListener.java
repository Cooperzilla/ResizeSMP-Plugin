package me.cooperzilla.resizesmp.Listeners;

import me.cooperzilla.resizesmp.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() == null) {
            return;
        }

        Player p = event.getEntity();
        Player pp = p.getKiller();


        if (Utils.getLives(p) <= -3) {
            Utils.Ban(p);
        } else {
            Utils.decrementLives(p);
            Utils.updateNbt(p);
        }

        if (Utils.getLives(pp) < 3) {
            Utils.incrementLives(pp);
            Utils.updateNbt(pp);
        }
    }
}
