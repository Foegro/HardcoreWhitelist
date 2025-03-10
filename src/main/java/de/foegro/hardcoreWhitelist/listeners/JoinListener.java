package de.foegro.hardcoreWhitelist.listeners;

import de.foegro.hardcoreWhitelist.HardcoreWhitelist;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player plr = e.getPlayer();
        FileConfiguration config = HardcoreWhitelist.getPlugin().getConfig();
        List<String> bannedPlrs = config.getStringList("BannedPlayers");
        String uuid = String.valueOf(plr.getUniqueId());
        if (bannedPlrs.contains(uuid)) {
            plr.kickPlayer("You are dead");
        }
    }
}
