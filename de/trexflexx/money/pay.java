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

public class pay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        if(!(sender instanceof Player)){
            p.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            if (args.length == 0) {
                p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§7Benutze: §b/pay <Player> <Amount>");
            } else {
                if (args.length == 1) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cGebe einen Wert an!");
                } else {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("*")) {
                            if (p.hasPermission(Main.cfg.getString("Permissions.payall"))) {
                                int amount = 0;
                                try {
                                    amount = Integer.valueOf(args[1]);
                                } catch (IllegalArgumentException e1) {
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cGebe eine gültige Zahl an!");
                                    return true;
                                }
                                DecimalFormat df = new DecimalFormat(
                                        "#,##0",
                                        new DecimalFormatSymbols(new Locale("pt", "BR")));

                                BigDecimal value = new BigDecimal(amount);
                                if (Api.hasEnough(p.getUniqueId(), amount * Bukkit.getOnlinePlayers().size())) {
                                    Api.removeMoney(p.getUniqueId(), amount * Bukkit.getOnlinePlayers().size());
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDu hast dem ganzem Server §e" + df.format(value.floatValue()) + "§e$ §agegeben!");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        Api.addMoney(all.getUniqueId(), amount);
                                        all.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDer Spieler §b" + p.getName() + "§a hat dem Server §e" + df.format(value.floatValue()) + "§e$ §agegeben.");
                                    }
                                } else if (!Api.hasEnough(p.getUniqueId(), amount * Bukkit.getOnlinePlayers().size())) {
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDu hast nicht genug Guthaben!");
                                }
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
                            }
                        } else {
                            int amount = 0;
                            try {
                                amount = Integer.valueOf(args[1]);
                            } catch (IllegalArgumentException e1) {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cGebe eine gültige Zahl an!");
                                return true;
                            }
                            DecimalFormat df = new DecimalFormat(
                                    "#,##0",
                                    new DecimalFormatSymbols(new Locale("pt", "BR")));

                            BigDecimal value = new BigDecimal(amount);
                            if(!(amount >= 1)){
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDer Wert darf nicht unter §b1 §csein.");
                            } else {
                                Player t = Bukkit.getPlayer(args[0]);
                                if (t == null) {
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDieser Spieler ist nicht online!");
                                } else {
                                    if (t != null) {
                                        if (Api.hasEnough(p.getUniqueId(), amount)) {

                                            Api.removeMoney(p.getUniqueId(), amount);
                                            Api.addMoney(t.getUniqueId(), amount);
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDu hast dem Spieler §b" + t.getName() + "§e " + df.format(value.floatValue()) + "§e$ §agegeben.");
                                            t.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDer Spieler §b" + p.getName() + "§a hat dir§e " + df.format(value.floatValue()) + "§e$ §agegeben.");
                                        } else if (!Api.hasEnough(p.getUniqueId(), amount)) {
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDu hast nicht genug Guthaben!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
