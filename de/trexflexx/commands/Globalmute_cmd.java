package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
       import org.bukkit.Bukkit;
       import org.bukkit.command.Command;
       import org.bukkit.command.CommandExecutor;
       import org.bukkit.command.CommandSender;
       import org.bukkit.entity.Player;

public class Globalmute_cmd implements CommandExecutor {
    public static boolean globalmute = false;
         
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (globalmute) {
                globalmute = false;
                Bukkit.broadcastMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Globale Chat wurde von der §dConsole §c§ldeaktiviert");
            } else {
                globalmute = true;
                Bukkit.broadcastMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Globale Chat wurde von der §dConsole §a§lAktiviert");
            }
        } else {
           Player p = (Player)sender;
             
           if (!p.hasPermission(Main.cfg.getString("Permissions.GlobalMute"))) {
             p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
             return true;
             } 
           if (globalmute) {
             globalmute = false;
             Bukkit.broadcastMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Globale Chat wurde von §b" + sender.getName() + " §c§ldeaktiviert");
             } else {
             globalmute = true;
             Bukkit.broadcastMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Globale Chat wurde von §b" + sender.getName() + " §a§lAktiviert");
           }
        }
        return true;
    }
}
