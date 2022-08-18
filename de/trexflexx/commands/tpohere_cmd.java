package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class tpohere_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {

            Player p = (Player) sender;

            if (!p.hasPermission(Main.cfg.getString("Permissions.tpohere"))) {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
                return true;
            }

            if (args.length == 0) {
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/tpohere <Spielername/*>");
            } else if (args.length == 1) {
                if (!p.hasPermission("system.tpohere")) {
                    p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("*")) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.teleport(p);
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast nun alle zu dir telepotiert.");
                    }
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDieser Spieler ist nicht online.");
                        return true;
                    }
                    target.teleport((Entity) p);
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§b" + target.getName() + "§a wurde zu dir Teleportiert!");
                }

            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/tpohere <Spielername/*>");
            }
        }
        return false;
    }
}
