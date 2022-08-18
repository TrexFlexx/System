package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ci_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            Player p = (Player) sender;
            if (p.hasPermission(Main.cfg.getString("Permissions.ci"))) {
                if (args.length == 0) {
                    p.getInventory().clear();
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDein inventar wurde gelöscht.");
                } else {
                    if (args.length == 1) {
                        Player t = Bukkit.getPlayer(args[0]);

                        if (t != null) {
                            if (args[0].equalsIgnoreCase(t.getName())) {
                                t.getInventory().clear();
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast das Inventar von §b" + t.getName() + "§a gelöscht.");
                            }
                        } else if (t == null) {
                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDieser Spieler ist nicht online!");
                        }

                    }
                }
            } else {

                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }

        return false;
    }
}
