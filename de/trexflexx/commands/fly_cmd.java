package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fly_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            if (p.hasPermission(Main.cfg.getString("Permissions.fly"))) {
                if (args.length == 0) {
                    if (!p.getAllowFlight()) {
                        p.setAllowFlight(true);
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu kannst nun fliegen.");
                    } else {
                        p.setAllowFlight(false);
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu kannst nun§c nicht§a mehr fliegen.");
                    }
                } else {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (args[0].equalsIgnoreCase(target.getName())) {
                            if (target.getAllowFlight()) {
                                target.setAllowFlight(false);
                                target.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu kannst nun §cnicht §amehr fliegen.");
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDer Spieler §b" + target.getName() + "§a kann nun nicht mehr fliegen.");
                            } else {
                                target.setAllowFlight(true);
                                target.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu kannst nun fliegen.");
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDer Spieler §b" + target.getName() + "§a kann nun fliegen.");
                            }
                        }
                    } else {
                        p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
                    }
                }
            }
           }
        return false;
    }
}
