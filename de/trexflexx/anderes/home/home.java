package de.trexflexx.anderes.home;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

public class home implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(sender instanceof Player){

            final Player player = (Player)sender;
            final File file = new File("plugins/CitybuildSystem/Data", "/homes.yml");
            final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            final UUID uuid = player.getUniqueId();

            if(args.length < 1){
                player.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/home <name>");
            } else {

                final String key = "players." + uuid + "." + args[0] + ".";
                final ConfigurationSection configurationSection = configuration.getConfigurationSection(key);

                if(configurationSection == null){
                    player.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDein Home exestiert nicht.");
                } else {

                    final World world = Bukkit.getWorld(configurationSection.getString("world"));
                    final double x = configurationSection.getDouble("x");
                    final double y = configurationSection.getDouble("y");
                    final double z = configurationSection.getDouble("z");
                    final float yaw = (float)configurationSection.getDouble("yaw");
                    final float pitch = (float)configurationSection.getDouble("pitch");
                    final Location location = new Location(world, x, y, z, yaw, pitch);

                    player.teleport(location);
                    player.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aErfolgreich telepotiert zu deinem Home §b" + args[0]);

                }
            }
        }

        return false;
    }
}

