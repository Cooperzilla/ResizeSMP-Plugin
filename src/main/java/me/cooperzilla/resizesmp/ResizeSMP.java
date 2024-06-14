package me.cooperzilla.resizesmp;

import lombok.Getter;
import lombok.Setter;
import me.cooperzilla.resizesmp.Commands.Debug;
import me.cooperzilla.resizesmp.Commands.Revive;
import me.cooperzilla.resizesmp.Listeners.DeathListener;
import me.cooperzilla.resizesmp.Listeners.LoginListener;
import me.cooperzilla.resizesmp.Recepies.Recipes;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ResizeSMP extends JavaPlugin {
    @Getter
    public static Plugin pl;

    @Override
    public void onEnable() {
        pl = this;
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new LoginListener(), this);
        this.getCommand("debug").setExecutor(new Debug());
        this.getCommand("revive").setExecutor(new Revive());
        new Recipes().Register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
