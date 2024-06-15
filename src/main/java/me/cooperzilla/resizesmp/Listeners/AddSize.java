package me.cooperzilla.resizesmp.Listeners;

import me.cooperzilla.resizesmp.ResizeSMP;
import me.cooperzilla.resizesmp.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class AddSize implements Listener {
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        ItemStack item = new ItemStack(Material.RED_DYE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Congealed Size");
        meta.setCustomModelData(1);

        var container = meta.getPersistentDataContainer();
        container.set(
                new NamespacedKey(ResizeSMP.getPl().getName(),"congealed_size"),
                PersistentDataType.STRING,
                "Some Value"
        );

        item.setItemMeta(meta);

        if (
                (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                e.getItem() == item
        ) {
            e.getItem().setAmount(e.getItem().getAmount() - 1);
            Utils.incrementLives(e.getPlayer());
            Utils.updateNbt(e.getPlayer());
        }
    }
}
