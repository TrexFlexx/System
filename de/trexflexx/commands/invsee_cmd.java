package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class invsee_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
           Player p = (Player)sender;
           if (p.hasPermission(Main.cfg.getString("Permissions.invsee"))) {
             if (args.length == 1) {
               Player open_inv = Bukkit.getPlayerExact(args[0]);
               if (open_inv != null) {
                 p.openInventory((Inventory)open_inv.getInventory());
                 } else {
                 p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Spieler ist offline...");
                 } 
               } else {
               p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/invsee <Player>");
               } 
             } else {
             p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
             } 
        }
        return true;
    }
}
