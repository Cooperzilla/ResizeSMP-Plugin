package me.cooperzilla.resizesmp.Listeners;

import me.cooperzilla.resizesmp.ResizeSMP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class LoginListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player p = event.getPlayer();

        if (!(p.hasMetadata("lives"))) {
            p.setMetadata("lives", new FixedMetadataValue(ResizeSMP.getPl(), 0));
        }
    }
}
