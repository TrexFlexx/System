package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) {
            if(args.length == 0) {
                sender.sendMessage("§7Benutze: §b/kick <Player>");
            } else if(args.length == 1){
                Player t = Bukkit.getPlayer(args[0]);
                t.kickPlayer("§6[§cServerKick§6] §aDu wurdest von der §bConsole §avom Server gekickt!");
                sender.sendMessage("§aDu hast den Spieler §b" + t.getName() + "§a vom Server gekickt.");
            }
        } else {
        Player p = (Player) sender;


            if (p.hasPermission(Main.cfg.getString("Permissions.kick"))) {
                if (args.length == 0) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/kick <Player> <Reason>");
                } else {
                    if (args.length == 1) {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cGebe einen Grund an!");
                    } else {
                        if (args.length >= 2) {
                            String msg = "";

                            for (int i = 1; i < args.length; i++) {
                                msg = msg + args[i] + " ";
                            }

                            Player t = Bukkit.getPlayer(args[0]);

                            if (t != null) {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast den Spieler §b" + t.getName() + "§a erfolgreich gekickt!");
                                t.kickPlayer("§6[§cServerKick§6] §aDu wurdest von §b" + p.getName() + " §avom Server gekickt!" + "\n" + "§eGrund: §c" + msg);
                            } else {
                                if (t == null) {
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDieser Spieler ist nicht online!");
                                }
                            }
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
