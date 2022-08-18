package de.trexflexx.commands;

import de.trexflexx.Utils.PerksApi;
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class cc_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            for (int i = 0; i < 105; i++) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage("");
                    all.sendMessage("");
                    all.sendMessage("");
                    all.sendMessage("");
                    all.sendMessage("");
                    all.sendMessage("");
                    all.sendMessage("");
                    all.sendMessage("");
                    all.sendMessage("");
                    all.sendMessage("");
                }
            }
            Bukkit.broadcastMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Chat wurde gelöscht von der §6Console §7gelöscht.");
        } else {
            Player p = (Player) sender;
            if (p.hasPermission(Main.cfg.getString("Permissions.clearchat")) || PerksApi.isCC(p.getUniqueId())) {
                for (int i = 0; i < 105; i++) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.sendMessage("");
                        all.sendMessage("");
                        all.sendMessage("");
                        all.sendMessage("");
                        all.sendMessage("");
                        all.sendMessage("");
                        all.sendMessage("");
                        all.sendMessage("");
                        all.sendMessage("");
                        all.sendMessage("");
                    }
                }
                Bukkit.broadcastMessage(Main.cfg.getString("Nachricht.prefix") + "Der Chat wurde gelöscht von §6" + p.getName() + "§7!");
            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        return false;
    }
}
