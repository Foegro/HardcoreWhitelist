package de.foegro.hardcoreWhitelist.commands;

import de.foegro.hardcoreWhitelist.HardcoreWhitelist;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImmortalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command c, @NotNull String l, @NotNull String @NotNull [] args) {
        if (s instanceof Player plr) {
            Player target;
            if (args.length == 0) {
                if (plr.hasPermission("HardcoreWhitelist.Immortal.Self")) {
                    target = plr;
                } else {
                    plr.sendMessage("§cYou don't have permission to make yourself immortal");
                    return false;
                }
            } else if (args.length == 1) {
                if (plr.hasPermission("HardcoreWhitelist.Immortal.Others")) {
                    target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        plr.sendMessage("§cTarget not found");
                        return false;
                    }
                } else {
                    plr.sendMessage("§cYou don't have permission to make others immortal");
                    return false;
                }
            } else {
                plr.sendMessage("§cInvalid number of arguments. Use /immortal (Player)");
                return false;
            }
            FileConfiguration config = HardcoreWhitelist.getPlugin().getConfig();
            List<String> plrs = config.getStringList("ImmortalPlayers");
            String uuid = String.valueOf(target.getUniqueId());
            if (plrs.contains(uuid)) {
                plrs.remove(uuid);
                plr.sendMessage("§a"+target.getName()+" is now §cmortal");
            } else {
                plrs.add(uuid);
                plr.sendMessage("§a"+target.getName()+" is now §6immortal");
            }
            config.set("ImmortalPlayers",plrs);
            HardcoreWhitelist.getPlugin().saveConfig();
            return true;
        }
        if (s instanceof ConsoleCommandSender) {
            Player target;
            if (args.length == 0) {
                System.out.println("I guess the console is immortal now?");
                return true;
            } else if (args.length == 1) {
                target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                     System.out.println("Target not found");
                     return false;
                }
            } else {
                System.out.println("Invalid number of arguments. Use /immortal (Player)");
                return false;
            }
            FileConfiguration config = HardcoreWhitelist.getPlugin().getConfig();
            List<String> plrs = config.getStringList("ImmortalPlayers");
            String uuid = String.valueOf(target.getUniqueId());
            if (plrs.contains(uuid)) {
                plrs.remove(uuid);
                System.out.println(target.getName()+" is now mortal");
            } else {
                plrs.add(uuid);
                System.out.println(target.getName()+" is now immortal");
            }
            config.set("ImmortalPlayers",plrs);
            HardcoreWhitelist.getPlugin().saveConfig();
            return true;
        }
        return false;
    }
}
