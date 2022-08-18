package de.trexflexx.money;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MoneyCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            if(args.length == 0) {
                sender.sendMessage("§7Benutze: §b/money <Player>");
            } else if(args.length == 1){

                Player t = Bukkit.getPlayer(args[0]);
                if(t == null){
                    sender.sendMessage("§cDieser Spieler ist nicht online.");
                } else if(t != null){
                    sender.sendMessage("§aDas Guthaben des Spielers §b" + t.getName() + "§a beträgt: §e" + Api.getMoney(t.getUniqueId()) + "§e$");
                }
            }
        } else {
        Player p = (Player) sender;
        if(args.length == 0){
            DecimalFormat df = new DecimalFormat(
                    "#,##0",
                    new DecimalFormatSymbols(new Locale("pt", "BR")));

            BigDecimal value = new BigDecimal(Api.getMoney(p.getUniqueId()));

            p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDein Guthaben beträgt: §e" + df.format(value.floatValue()) + "§e$");
        } else {
            if (args.length == 1) {
                Player t = Bukkit.getPlayer(args[0]);

                DecimalFormat df = new DecimalFormat(
                        "#,##0",
                        new DecimalFormatSymbols(new Locale("pt", "BR")));


                if (t == null) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDieser Spieler ist nicht online!");
                } else if(t != null){
                    BigDecimal value = new BigDecimal(Api.getMoney(t.getUniqueId()));
                    if (args[0].equalsIgnoreCase(t.getName())) {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDas Guthaben des Spielers §b" + t.getName() + "§a beträgt: §e" + df.format(value.floatValue()) + "§e$");
                    }
                }
            }
        }
        }
        return false;
    }
}
