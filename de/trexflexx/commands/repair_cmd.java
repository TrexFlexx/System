package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
       
public class repair_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            Player player = (Player) sender;
            if (player.hasPermission(Main.cfg.getString("Permissions.repair"))) {
                if (args.length == 0) {
                    try {
                        if (player.getItemInHand().getDurability() == 0) {
                            player.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDas Item ist schon repariert!");
                            return true;
                        }
                        if (!player.getInventory().getItemInMainHand().getType().isAir()) {
                            player.getItemInHand().setDurability((short) 0);
                            sender.sendMessage(Main.cfg.getString("Nachricht.prefix") + "Das Item wurde repariert!");
                            return true;
                        } else {
                            player.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cIn deiner Hand ist kein Item!");
                        }
                    } catch (Exception i) {
                        sender.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDieses Item kann nicht repariert werden!");
                    }
                } else {
                    sender.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/repair");
                }
            } else {
                sender.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        return true;
    }
}
