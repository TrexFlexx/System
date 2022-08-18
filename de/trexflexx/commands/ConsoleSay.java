package de.trexflexx.commands;

import de.trexflexx.main.Main;
import javafx.print.PageLayout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConsoleSay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){

            if(args.length == 0){
                sender.sendMessage("§7Benutze: §b/say <Nachricht>");
            } else {
                if(args.length >= 1){
                    String msg = "";

                    for (int i = 0; i < args.length; i++) {
                        msg = msg + args[i] + " ";
                    }

                    Bukkit.broadcastMessage("§7»");
                    Bukkit.broadcastMessage("§4§lConsole §e§l| §a§l" + msg);
                    Bukkit.broadcastMessage("§7»");
                }
            }
        } else {
            Player p = (Player) sender;
            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu musst eine Console sein.");
        }

        return false;
    }
}
