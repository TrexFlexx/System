package de.trexflexx.anderes.tp;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class tpa implements CommandExecutor {

    public static final Map<String, String> requests = new HashMap<>();
    public static final Map<String, BukkitTask> timeouts = new HashMap<>();

    public static void removeRequest(String source) {
        if (tpa.timeouts.containsKey(source) && !tpa.timeouts.get(source).isCancelled())
            tpa.timeouts.get(source).cancel();
        tpa.timeouts.remove(source);
        tpa.requests.remove(source);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        }
        if (args.length != 1) return false;
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer != null && targetPlayer != sender) {
            targetPlayer.sendMessage(
                    "§aDer Spieler §b" + sender.getName() + " §ahat dir eine Tpa geschickt. Mache §e/tpaccept §aum sie anzunehmen, und §e/tpdeny §aum sie abzulehnen.");
            sender.sendMessage( "§aDu hast §b" + targetPlayer.getName() + "§a eine TPA geschickt, er hat 30 Sekunden Zeit sie anzunehmen.");
            requests.put(sender.getName(), targetPlayer.getName());
            timeouts.put(sender.getName(), Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                try {
                    sender.sendMessage( "§aDeine Teleportsanfrage an §b" + targetPlayer.getName() + "§a ist abgelaufen.");
                    targetPlayer.sendMessage( "§aDie Teleportsanfrage an dich von §b" + sender.getName() + "§a ist abgelaufen.");
                } catch (Exception ignored) {
                }
                removeRequest(sender.getName());
            }, 20 * 30));
        } else if (targetPlayer == sender) {
            sender.sendMessage( "§cDu kannst dir nicht selbst eine TPA schicken.");
        } else {
            sender.sendMessage( "§cDieser Spieler ist nicht online.");
        }
        return false;
    }
}
