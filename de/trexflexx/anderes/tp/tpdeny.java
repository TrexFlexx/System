package de.trexflexx.anderes.tp;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tpdeny implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        List<String> sources = new ArrayList<>();
        if (args.length == 0) {
            for (String source : tpa.requests.keySet()) {
                if (tpa.requests.get(source).equals(sender.getName()))
                    sources.add(source);
            }
        } else {
            for (String arg : args) {
                if (!tpa.requests.containsKey(arg) || !tpa.requests.get(arg).equals(sender.getName())) {
                    sender.sendMessage( "§cDer Spieler §b'" + arg + "'§c hat dir keine Teleportsanfrage geschickt.");
                    return true;
                }
                sources.add(arg);
            }
        }
        if (sources.size() == 0) {
            sender.sendMessage( "§cEs gibt keine Tpa zum ablehnen.");
        } else {
            for (String source : sources) {
                Player sourcePlayer = Bukkit.getServer().getPlayer(source);

                tpa.removeRequest(source);

                if (sourcePlayer != null) {
                    sourcePlayer.sendMessage( "§cDer Spieler §b" + sender.getName() + " §chat deine Teleportsanfrage abgelehnt.");
                }
            }
        }
        return false;
    }
}
