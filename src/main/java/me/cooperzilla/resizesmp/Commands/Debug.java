package me.cooperzilla.resizesmp.Commands;

import me.cooperzilla.resizesmp.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        }

        return true;
    }
}
