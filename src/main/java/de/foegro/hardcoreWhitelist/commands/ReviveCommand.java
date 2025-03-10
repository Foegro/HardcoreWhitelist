package de.foegro.hardcoreWhitelist.commands;

import de.foegro.hardcoreWhitelist.HardcoreWhitelist;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReviveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender s, org.bukkit.command.@NotNull Command c, @NotNull String l, String @NotNull [] args) {
        if (s instanceof Player p) {
            if (!p.hasPermission("HardcoreWhitelist.Revive")) {
                p.sendMessage("§cYou don't have the permission to use this command.");
                return false;
            }
            if (args.length != 1) {
                p.sendMessage("§cInvalid amount of arguments. Correct usage: /revive [Player]");
                return false;
            }
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                p.sendMessage("§cTarget doesn't exist");
                return false;
            }
            String uuid = String.valueOf(t.getUniqueId());
            FileConfiguration config = HardcoreWhitelist.getPlugin().getConfig();
            List<String> bannedPlrs = config.getStringList("BannedPlayers");
            if (!bannedPlrs.contains(uuid)) {
                p.sendMessage("§cPlayer isn't dead");
                return false;
            }
            bannedPlrs.remove(uuid);
            config.set("BannedPlayers",bannedPlrs);
            HardcoreWhitelist.getPlugin().saveConfig();
            p.sendMessage("$a"+t.getName()+" got revived");
            return true;
        }
        if (s instanceof ConsoleCommandSender) {
            if (args.length != 1) {
                System.out.println("§cInvalid amount of arguments. Correct usage: /revive [Player]");
                return false;
            }
            OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
            String uuid = String.valueOf(t.getUniqueId());
            FileConfiguration config = HardcoreWhitelist.getPlugin().getConfig();
            List<String> bannedPlrs = config.getStringList("BannedPlayers");
            if (!bannedPlrs.contains(uuid)) {
                System.out.println("§cPlayer isn't dead");
                return false;
            }
            bannedPlrs.remove(uuid);
            config.set("BannedPlayers",bannedPlrs);
            HardcoreWhitelist.getPlugin().saveConfig();
            System.out.println(t.getName()+" got revived");
            return true;
        }
        return false;
    }
}
