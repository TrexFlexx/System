package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class playtime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            if(strings.length == 0){
                commandSender.sendMessage("§7Benutze: §b/playtime <Player>");
            } else if(strings.length == 1){
                Player t = Bukkit.getPlayer(strings[0]);
                if(t != null){
                    int hours = Main.getPlugin(Main.class).getConfig().getInt("Time." + t.getName() + ".hours");
                    int minutes = Main.getPlugin(Main.class).getConfig().getInt("Time." + t.getName() + ".minutes");
                    commandSender.sendMessage("§aDer Spieler §b" + t.getName() + "§a ist seit " + "§c" + hours + "§ch " + minutes + "§cmin §aauf dem Server.");
                } else if(t == null){
                    commandSender.sendMessage("§cDieser Spieler ist nicht online.");
                }
            }
        } else {


            Player p = (Player) commandSender;


            int hours = Main.getPlugin(Main.class).getConfig().getInt("Time." + p.getName() + ".hours");
            int minutes = Main.getPlugin(Main.class).getConfig().getInt("Time." + p.getName() + ".minutes");


            if (p.hasPermission(Main.cfg.getString("Permissions.playtime"))) {
                if (strings.length == 0) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDeine Spielzeit: §c" + hours + "§ch " + minutes + "§cmin");
                }
                if (strings.length == 1) {

                    Player target = Bukkit.getPlayer(strings[0]);
                    if (target == null) {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDieser Spieler ist nicht online!");


                    } else if (target != null) {

                        int hourss = Main.getPlugin(Main.class).getConfig().getInt("Time." + target.getName() + ".hours");
                        int minutess = Main.getPlugin(Main.class).getConfig().getInt("Time." + target.getName() + ".minutes");
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDer Spieler §b" + target.getName() + "§a ist seit " + "§c" + hourss + "§ch " + minutess + "§cmin §aauf dem Server.");
                    }
                }
            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }

        return false;
    }
}
