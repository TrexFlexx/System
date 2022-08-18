package de.trexflexx.anderes.home;

import de.trexflexx.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class sethome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        final String prefix = Main.cfg.getString("Nachricht.prefix");

        if(sender instanceof Player) {

            final Player player = (Player)sender;
            final File file = new File("plugins/CitybuildSystem/Data", "/homes.yml");
            final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            final UUID uuid = player.getUniqueId();
            final Location location = player.getLocation();
            if(player.hasPermission(Main.cfg.getString("Permissions.sethome"))) {
                if (args.length < 1) {
                    player.sendMessage(Main.cfg.getString("Nachricht.prefix") + ChatColor.RED + "§7Benute: §b/sethome <name>");
                } else {

                    final String key = "players." + uuid + "." + args[0] + ".";
                    final ConfigurationSection configurationSection = configuration.getConfigurationSection(key);

                    if (configurationSection == null) {

                        configuration.set(key + ".world", location.getWorld().getName());
                        configuration.set(key + ".x", location.getX());
                        configuration.set(key + ".y", location.getY());
                        configuration.set(key + ".z", location.getZ());
                        configuration.set(key + ".yaw", location.getYaw());
                        configuration.set(key + ".pitch", location.getPitch());

                        try {
                            configuration.save(file);
                            player.sendMessage(Main.cfg.getString("Nachricht.prefix") + ChatColor.GREEN + "§aDein Home §b" + args[0] + "§a wurde erfolgreich gesetzt.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        player.sendMessage(Main.cfg.getString("Nachricht.prefix") + ChatColor.RED + "Dieses Home exestierts bereits.");
                    }
                }
            }
        }

        return false;
    }
}
