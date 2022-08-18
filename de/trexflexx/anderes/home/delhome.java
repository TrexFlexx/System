package de.trexflexx.anderes.home;

import de.trexflexx.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class delhome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if(sender instanceof Player){

            final Player player = (Player)sender;
            final File file = new File("plugins/CitybuildSystem/Data", "/homes.yml");
            final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            final UUID uuid = player.getUniqueId();

            if(args.length < 1){
                player.sendMessage(Main.cfg.getString("Nachricht.prefix") + ChatColor.RED + "§7Benutze: §b/delhome <name>");
            } else {

                final String key = "players." + uuid + "." + args[0];
                final ConfigurationSection configurationSection = configuration.getConfigurationSection(key + ".");

                if(configurationSection == null){
                    player.sendMessage(Main.cfg.getString("Nachricht.prefix") + ChatColor.RED + "Dieses Home exestiert nicht.");
                } else {

                    configuration.set(key, null);
                    try {
                        configuration.save(file);
                        player.sendMessage(Main.cfg.getString("Nachricht.prefix") + ChatColor.GREEN + "Das Home §b" + args[0] + "§a wurde erfolgreich gelöscht.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return false;
    }
}