package me.cooperzilla.resizesmp.Commands;

import me.cooperzilla.resizesmp.ResizeSMP;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class Revive implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) { return true; }

        Player p = ((Player) commandSender).getPlayer();
        assert p != null;

        if (args.length == 0) {
            p.sendMessage("Enter a name dumbass");
            return true;
        }

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

        if (!(p.getInventory().getItemInMainHand().equals(totem))) {
            return true;
        } else {
            p.getInventory().getItemInMainHand().setAmount(0);
        }

        var ban = Bukkit.getServer().getBanList(BanList.Type.PROFILE).getBanEntry(args[0]);

        if (ban.getReason() == "Death Banned") {
            Bukkit.getServer().getBanList(BanList.Type.PROFILE).pardon(args[0]);
        }

        return true;
    }
}
