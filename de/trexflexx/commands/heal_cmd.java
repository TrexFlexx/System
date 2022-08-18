package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
       
public class heal_cmd implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            if(args.length == 0){
                sender.sendMessage("§7Benutze: §b/heal <Player>");
            } else if(args.length == 1) {
                Player t = Bukkit.getPlayer(args[0]);
                if(t != null){
                t.setHealth(20.0D);
                sender.sendMessage("§aDu hast den Spieler erfolgreich geheilt.");
                t.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aErfolgreich regeneriert!");
                if (t.getFoodLevel() > 1) {
                    t.setFoodLevel(30);
                }
                } else if(t == null){
                    sender.sendMessage("§cDieser Spieler ist nicht online.");
                }
            }
        } else {
            Player p = (Player) sender;

            if (p.hasPermission(Main.cfg.getString("Permissions.heal"))) {
                p.setHealth(20.0D);
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aErfolgreich regeneriert!");
                if (p.getFoodLevel() > 1) {
                    p.setFoodLevel(30);

                }

            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        return false;
    }
}

