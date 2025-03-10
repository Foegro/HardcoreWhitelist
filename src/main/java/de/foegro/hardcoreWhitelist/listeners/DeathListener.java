package de.foegro.hardcoreWhitelist.listeners;

import de.foegro.hardcoreWhitelist.HardcoreWhitelist;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeathListener implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player plr) {
            FileConfiguration config = HardcoreWhitelist.getPlugin().getConfig();
            List<String> iPlrs = config.getStringList("ImmortalPlayers");
            if (!iPlrs.contains(String.valueOf(plr.getUniqueId()))) {
                List<String> plrs = config.getStringList("BannedPlayers");
                plrs.add(String.valueOf(plr.getUniqueId()));
                config.set("BannedPlayers", plrs);
                HardcoreWhitelist.getPlugin().saveConfig();
                plr.kickPlayer("You are dead");
            }
        }
    }
}
