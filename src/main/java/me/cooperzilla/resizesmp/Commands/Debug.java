package me.cooperzilla.resizesmp.Commands;

import me.cooperzilla.resizesmp.Recepies.ReviveCraft;
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

import java.util.Collections;

public class Debug implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) { return true; }

        Player p = ((Player) commandSender).getPlayer();
        assert p != null;

        switch (args[0].toLowerCase()) {
            case "small", "shrink" -> {
                if (Utils.getLives(p) < 3) {
                    Utils.incrementLives(p);
                    Utils.updateNbt(p);
                }
            }
            case "big", "grow" -> {
                if (Utils.getLives(p) <= -3) {
                    Utils.Ban(p);
                } else {
                    Utils.decrementLives(p);
                    Utils.updateNbt(p);
                }
            }
            case "give" -> {
                switch (args[1]) {
                    case "revive" -> {
                        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
                        ItemMeta meta = totem.getItemMeta();

                        meta.setDisplayName("Revival Totem");
                        meta.setLore(Collections.singletonList("Run /revive <name>"));

                        var container = meta.getPersistentDataContainer();
                        container.set(
                                new NamespacedKey(ResizeSMP.getPl().getName(),"revive"),
                                PersistentDataType.STRING,
                                "Some Value"
                        );

                        totem.setItemMeta(meta);

                        p.getInventory().addItem(totem);
                    }
                }
            }
        }

        return true;
    }
}
