package me.cooperzilla.resizesmp.Commands;

import me.cooperzilla.resizesmp.ResizeSMP;
import me.cooperzilla.resizesmp.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class Withdraw implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) { return true; }

        Player p = ((Player) commandSender).getPlayer();
        assert p != null;

        if (Utils.getLives(p) <= 0) { return true; }

        if (args[0] == null) {

            Utils.decrementLives(p);

            ItemStack item = new ItemStack(Material.RED_DYE);
            ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(1);

            meta.setDisplayName("Congealed Size");

            var container = meta.getPersistentDataContainer();
            container.set(
                    new NamespacedKey(ResizeSMP.getPl().getName(),"congealed_size"),
                    PersistentDataType.STRING,
                    "Some Value"
            );

            item.setItemMeta(meta);

            p.getInventory().addItem(item);

        } else {

            if (Utils.getLives(p) < Integer.parseInt(args[0])) {
                return true;
            }

            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                Utils.decrementLives(p);
                ItemStack item = new ItemStack(Material.RED_DYE);
                ItemMeta meta = item.getItemMeta();

                meta.setDisplayName("Congealed Size");
                meta.setCustomModelData(1);

                var container = meta.getPersistentDataContainer();
                container.set(
                        new NamespacedKey(ResizeSMP.getPl().getName(), "congealed_size"),
                        PersistentDataType.STRING,
                        "Some Value"
                );

                item.setItemMeta(meta);

                p.getInventory().addItem(item);
            }
        }

        Utils.updateNbt(p);
        return true;
    }
}
