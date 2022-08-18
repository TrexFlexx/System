package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
       
       
       
public class night_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
        Player p = (Player)sender;
            if (p.hasPermission(Main.cfg.getString("Permissions.night"))) {
                p.getLocation().getWorld().setTime(13000L);
            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        return false;
    }
}
