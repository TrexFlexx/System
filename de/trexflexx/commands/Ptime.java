package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ptime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;
        if(args.length == 0){
            p.sendMessage("§7Benutze: §b/ptime <day/night>");
        }
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("day")) {
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast deine SpielZeit auf §6Tag §7gesetzt.");
                p.setPlayerTime(1000L, true);
            } else if(args[0].equalsIgnoreCase("night")){
                p.setPlayerTime(13000L, true);
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast deine SpielZeit auf §6Nacht §7gesetzt.");
            }
        }
        return false;
    }
}
