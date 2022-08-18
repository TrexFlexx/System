package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setspawn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {

        Player p = (Player) sender;


            if (p.hasPermission(Main.cfg.getString("Permissions.setspawn"))) {

                Player player = (Player) sender;
                player.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast den §6Spawn §aerfolgreich gesetzt!");
                Location l = player.getLocation();
                Main.getPlugin(Main.class).setSpawnLocation(l.getWorld().getName(), l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch());

            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        return true;
    }
}

