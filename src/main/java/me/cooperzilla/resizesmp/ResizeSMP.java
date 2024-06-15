package me.cooperzilla.resizesmp;

import lombok.Getter;
import me.cooperzilla.resizesmp.Commands.*;
import me.cooperzilla.resizesmp.Listeners.*;
import me.cooperzilla.resizesmp.Recepies.*;
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
        this.getCommand("withdraw").setExecutor(new Withdraw());
        WebsCraft.register();
        ReviveCraft.register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
