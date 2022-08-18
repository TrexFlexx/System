package de.trexflexx.money;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class eco implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            if(args.length == 0){
                sender.sendMessage("§7Benutze: §b/eco <set/add/remove> <Player> <Amount>");
            } else {
                if(args.length > 1){
                    if (args[0].equalsIgnoreCase("set")) {
                        if(args.length == 1){
                            sender.sendMessage("§cGebe einen Spieler an");
                        } else if(args.length == 2){
                            sender.sendMessage("§cGebe einen Wert an.");
                        } else
                        if (args.length == 3) {
                            int amount = 0;
                            try {
                                amount = Integer.valueOf(args[2]);
                            } catch (IllegalArgumentException e1) {
                                sender.sendMessage("§cGebe eine gueltige Zahl an!");
                                return true;
                            }

                            Player t = Bukkit.getPlayer(args[1]);
                            DecimalFormat df = new DecimalFormat(
                                    "#,##0",
                                    new DecimalFormatSymbols(new Locale("pt", "BR")));

                            BigDecimal value = new BigDecimal(amount);
                            if (t != null) {
                                Api.setMoney(t.getUniqueId(), amount);
                                sender.sendMessage("§aDu hast das Guthaben von §b" + t.getName() + "§a auf §e" + df.format(value.floatValue()) + "§e$ §agesetzt!");
                                t.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDein Guthaben wurde von der Console auf §e" + df.format(value.floatValue()) + "§e$ §agesetzt!");
                            } else {
                                sender.sendMessage("§cDieser Spieler ist nicht online!");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("add")) {
                        int amount = 0;
                        try {
                            amount = Integer.valueOf(args[2]);
                        } catch (IllegalArgumentException e1) {
                            sender.sendMessage("§cGebe eine gueltige Zahl an!");
                            return true;
                        }
                        Player t = Bukkit.getPlayer(args[1]);
                        DecimalFormat df = new DecimalFormat(
                                "#,##0",
                                new DecimalFormatSymbols(new Locale("pt", "BR")));

                        BigDecimal value = new BigDecimal(amount);
                        if (t != null) {
                            Api.addMoney(t.getUniqueId(), amount);
                            sender.sendMessage("§aDu hast dem Guthaben von §b" + t.getName() + "§a §e" + df.format(value.floatValue()) + " §e$ §agutgeschrieben!");
                            t.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDeinem Guthaben wurden von der Console §e" + df.format(value.floatValue()) + "§e$ §agutgeschrieben!");
                        } else {
                            sender.sendMessage("§cDieser Spieler ist nicht online!");
                        }
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (args.length == 3) {
                            int amount = 0;
                            try {
                                amount = Integer.valueOf(args[2]);
                            } catch (IllegalArgumentException e1) {
                                sender.sendMessage("§cGebe eine gueltige Zahl an!");
                                return true;
                            }
                            Player t = Bukkit.getPlayer(args[1]);
                            DecimalFormat df = new DecimalFormat(
                                    "#,##0",
                                    new DecimalFormatSymbols(new Locale("pt", "BR")));

                            BigDecimal value = new BigDecimal(amount);
                            if (t != null) {
                                Api.removeMoney(t.getUniqueId(), amount);
                                sender.sendMessage("§aDu hast von dem Spieler §b" + t.getName() + "§a §e" + df.format(value.floatValue()) + " §e$ §aentfernt!");
                                t.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aVon deinem Guthaben wurden §e" + df.format(value.floatValue()) + "§e$ §aentfernt!");
                            } else {
                                sender.sendMessage("§cDieser Spieler ist nicht online!");
                            }
                        }
                    }
                }
            }


        } else {
            Player p = (Player) sender;
            if (p.hasPermission(Main.cfg.getString("Permissions.eco"))) {
                if (args.length == 0) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§7Benutze: §b/eco <add/set/remove> <Player> <Amount>");
                } else if (args.length == 1) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cGebe einen Spieler an!");
                } else if (args.length == 2) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cGebe einen Wert an!");
                } else {
                    if (args.length == 3) {
                        if (args[0].equalsIgnoreCase("add")) {
                            int amount = 0;
                            try {
                                amount = Integer.valueOf(args[2]);
                            } catch (IllegalArgumentException e1) {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cGebe eine gültige Zahl an!");
                                return true;
                            }
                            Player t = Bukkit.getPlayer(args[1]);
                            DecimalFormat df = new DecimalFormat(
                                    "#,##0",
                                    new DecimalFormatSymbols(new Locale("pt", "BR")));

                            BigDecimal value = new BigDecimal(amount);
                            if (t != null) {
                                Api.addMoney(t.getUniqueId(), amount);
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDu hast dem Guthaben von §b" + t.getName() + "§a §e" + df.format(value.floatValue()) + "§e$ §agutgeschrieben!");
                                t.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDeinem Guthaben wurden §e" + df.format(value.floatValue()) + "§e$ §agutgeschrieben!");
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDieser Spieler ist nicht online!");
                            }
                        } else {
                            if (args[0].equalsIgnoreCase("set")) {
                                if (args.length == 3) {
                                    int amount = 0;
                                    if (amount > 2000000000) {
                                        p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDer Wert muss unter §e2.000.000.000 §csein.");
                                    } else {
                                    try {
                                        amount = Integer.valueOf(args[2]);
                                    } catch (IllegalArgumentException e1) {
                                        p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cGebe eine gültige Zahl an!");
                                        return true;
                                    }
                                        Player t = Bukkit.getPlayer(args[1]);
                                        DecimalFormat df = new DecimalFormat(
                                                "#,##0",
                                                new DecimalFormatSymbols(new Locale("pt", "BR")));

                                        BigDecimal value = new BigDecimal(amount);
                                        if (t != null) {
                                            Api.setMoney(t.getUniqueId(), amount);
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDu hast das Guthaben von §b" + t.getName() + "§a auf §e" + df.format(value.floatValue()) + "§e$ §agesetzt!");
                                            t.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDein Guthaben wurde auf §e" + df.format(value.floatValue()) + "§e$ §agesetzt!");
                                        } else {
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDieser Spieler ist nicht online!");
                                        }
                                    }
                                }
                            } else {
                                if (args[0].equalsIgnoreCase("remove")) {
                                    if (args.length == 3) {
                                        int amount = 0;
                                        try {
                                            amount = Integer.valueOf(args[2]);
                                        } catch (IllegalArgumentException e1) {
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cGebe eine gültige Zahl an!");
                                            return true;
                                        }
                                        Player t = Bukkit.getPlayer(args[1]);
                                        DecimalFormat df = new DecimalFormat(
                                                "#,##0",
                                                new DecimalFormatSymbols(new Locale("pt", "BR")));

                                        BigDecimal value = new BigDecimal(amount);
                                        if (t != null) {
                                            Api.removeMoney(t.getUniqueId(), amount);
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDu hast von dem Spieler §b" + t.getName() + "§a §e" + df.format(value.floatValue()) + "§e$ §aentfernt!");
                                            t.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aVon deinem Guthaben wurden §e" + df.format(value.floatValue()) + "§e$ §aentfernt!");
                                        } else {
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDieser Spieler ist nicht online!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        return false;
    }
}
