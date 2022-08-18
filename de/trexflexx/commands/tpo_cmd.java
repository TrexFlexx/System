package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
       
public class tpo_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {

            Player p = (Player) sender;

            if (!p.hasPermission(Main.cfg.getString("Permissions.tpo"))) {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
                return true;
            } else {

                if (args.length == 0) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/tpo <Spielername>");
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDieser Spieler ist nicht online!");
                        return true;
                    }
                    p.teleport((Entity) target);
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast dich zu §b" + target.getName() + "§a Teleportiert");

                } else {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benute: §b/tpo <Spielername>");
                }
            }
        }
         return false;
    }
}
