package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class reloadSys implements CommandExecutor {

    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("CitybuildSystem");
    final FileConfiguration config = plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            Player p = (Player) sender;


            if (p.hasPermission(Main.cfg.getString("Permissions.pluginreload"))) {
                plugin.getPluginLoader().disablePlugin(plugin);
                plugin.getPluginLoader().enablePlugin(plugin);
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDie §eConfig §ades Plugins wurde erfolgreich neu geladen!");
            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        return false;
    }
}
