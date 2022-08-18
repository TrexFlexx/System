package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bc_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Main.cfg.getString("Permissions.broadcast"))) {
                if (args.length >= 1) {
                    String msg = "";
                    for (int i = 0; i < args.length; i++) {
                        msg = msg + args[i] + " ";
                    }

                    Bukkit.broadcastMessage("§8»");
                    Bukkit.broadcastMessage("§eBroadcast §3┃ §a§l" + msg.replaceAll("&", "&"));
                    Bukkit.broadcastMessage("§8»");
                } else {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/bc <nachricht>");
                }
            } else {

                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        } else {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        }

        return false;
    }
}
