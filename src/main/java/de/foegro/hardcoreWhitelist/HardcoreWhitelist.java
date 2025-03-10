package de.foegro.hardcoreWhitelist;

import de.foegro.hardcoreWhitelist.commands.ImmortalCommand;
import de.foegro.hardcoreWhitelist.commands.ReviveCommand;
import de.foegro.hardcoreWhitelist.listeners.DeathListener;
import de.foegro.hardcoreWhitelist.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class HardcoreWhitelist extends JavaPlugin {
    public static HardcoreWhitelist plugin;
    @Override
    public void onEnable() {
        plugin = this;
        Objects.requireNonNull(getCommand("revive")).setExecutor(new ReviveCommand());
        Objects.requireNonNull(getCommand("immortal")).setExecutor(new ImmortalCommand());
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
    }

    public static HardcoreWhitelist getPlugin() {
        return plugin;
    }
}
