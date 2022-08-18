package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class socialspy implements CommandExecutor {

    public static ArrayList<Player> socialspy = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst ein Spieler sein.");
        } else {
            Player p = (Player) sender;

            if(p.hasPermission(Main.cfg.getString("Permissions.socialspy"))){
                if(!(socialspy.contains(p))){
                    socialspy.add(p);
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast den §6SocialSpy §aerfolgreich aktiviert.");
                } else if(socialspy.contains(p)){
                    socialspy.remove(p);
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast den §6SocialSpy §aerfolgreich deaktiviert.");
                }
            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }

        }

        return false;
    }
}
