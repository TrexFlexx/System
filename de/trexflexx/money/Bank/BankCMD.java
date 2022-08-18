package de.trexflexx.money.Bank;

import de.trexflexx.main.Main;
import de.trexflexx.money.Api;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BankCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst ein Spieler sein.");
        } else {

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§7Benutze: §b/bank einzahlen <amount>");
            p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§7Benutze: §b/bank auszahlen <amount>");
            p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§7Benutze: §b/bank guthaben");
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("guthaben")) {
                    DecimalFormat df = new DecimalFormat(
                            "#,##0",
                            new DecimalFormatSymbols(new Locale("pt", "BR")));

                    BigDecimal value = new BigDecimal(BankAPI.getMoney(p.getUniqueId()));
                    p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§aDein Bank Guthaben beträgt §e" + df.format(value.floatValue()) + "§e$");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("guthaben")) {
                    if (!(BankAPI.isBankAccountFrozen(p.getUniqueId()))) {
                        Player t = Bukkit.getPlayer(args[1]);

                        DecimalFormat df = new DecimalFormat(
                                "#,##0",
                                new DecimalFormatSymbols(new Locale("pt", "BR")));

                        BigDecimal value = new BigDecimal(BankAPI.getMoney(t.getUniqueId()));
                        if (args[1].equalsIgnoreCase(t.getName())) {
                            if (t != null) {
                                p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§aDer Spieler §b" + t.getDisplayName() + "§a hat auf seiner Bank §e" + df.format(value.floatValue()) + "§e$");
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§cDieser Spieler ist nicht online!");
                            }
                        }
                    } else if (BankAPI.isBankAccountFrozen(p.getUniqueId())) {
                        p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§cDein Bank-Account ist derzeit gesperrt!");
                    }
                } else if (args[0].equalsIgnoreCase("einzahlen")) {
                    if (!(BankAPI.isBankAccountFrozen(p.getUniqueId()))) {
                        int amount = 0;
                        try {
                            amount = Integer.valueOf(args[1]);
                        } catch (IllegalArgumentException e1) {
                            p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§cGebe eine gültige Zahl an!");
                            return true;
                        }
                        DecimalFormat df = new DecimalFormat(
                                "#,##0",
                                new DecimalFormatSymbols(new Locale("pt", "BR")));

                        BigDecimal value = new BigDecimal(amount);

                        if (Api.hasEnough(p.getUniqueId(), amount)) {
                            BankAPI.addMoney(p.getUniqueId(), amount);
                            Api.removeMoney(p.getUniqueId(), amount);
                            p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§aDu hast §e" + df.format(value.floatValue()) + "§e$ §aauf dein Konto eingezahlt.");
                        } else if (!(Api.hasEnough(p.getUniqueId(), amount))) {
                            p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§cDu hast nicht genug Guthaben.");
                        }
                    } else if (BankAPI.isBankAccountFrozen(p.getUniqueId())) {
                        p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§cDein Bank-Account ist derzeit gesperrt!");
                    }


                } else if (args[0].equalsIgnoreCase("abheben")) {
                    if (!(BankAPI.isBankAccountFrozen(p.getUniqueId()))) {
                        int amount = 0;
                        try {
                            amount = Integer.valueOf(args[1]);
                        } catch (IllegalArgumentException e1) {
                            p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§cGebe eine gültige Zahl an!");
                            return true;
                        }
                        DecimalFormat df = new DecimalFormat(
                                "#,##0",
                                new DecimalFormatSymbols(new Locale("pt", "BR")));

                        BigDecimal value = new BigDecimal(amount);
                        if (BankAPI.hasEnough(p.getUniqueId(), amount)) {
                            Api.addMoney(p.getUniqueId(), amount);
                            BankAPI.removeMoney(p.getUniqueId(), amount);
                            p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§aDu hast §e" + df.format(value.floatValue()) + "§e$ §avon deiner Bank abgehoben.");
                        } else if (!(BankAPI.hasEnough(p.getUniqueId(), amount))) {
                            p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§cDu hast nicht genug Guthaben.");
                        }
                    } else if (BankAPI.isBankAccountFrozen(p.getUniqueId())) {
                        p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§cDein Bank-Account ist derzeit gesperrt!");
                    }
                } else {
                    if (p.hasPermission(Main.cfg.getString("Permissions.Bank"))) {

                        if (args[0].equalsIgnoreCase("freeze")) {
                            if (args.length == 2) {
                                Player t = Bukkit.getPlayer(args[1]);
                                if (t == null) {
                                    p.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§cDieser Spieler ist nicht online!");
                                } else if (t != null) {
                                    if (BankAPI.isBankAccountFrozen(t.getUniqueId())) {
                                        BankAPI.updateBankFreeze(t.getUniqueId(), false);
                                        sender.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§aDu hast die Bank von §b" + t.getName() + "§a entsperrt.");
                                        t.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§aDeine Bank wurde von §b" + sender.getName() + "§a entsperrt!");
                                    } else {
                                        BankAPI.updateBankFreeze(t.getUniqueId(), true);
                                        sender.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§aDu hast die Bank von §b" + t.getName() + "§a gesperrt.");
                                        t.sendMessage(Main.cfg.getString("Nachricht.BankPrefix") + "§aDeine Bank wurde von §b" + p.getName() + "§a gesperrt.");
                                    }
                                }
                            }
                        } else {
                            if (args[0].equalsIgnoreCase("set")) {
                                if (args.length == 3) {

                                    int amount = 0;
                                    try {
                                        amount = Integer.valueOf(args[2]);
                                    } catch (IllegalArgumentException e1) {
                                        p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cGebe eine gültige Zahl an!");
                                    }
                                    Player t = Bukkit.getPlayer(args[1]);
                                    DecimalFormat df = new DecimalFormat(
                                            "#,##0",
                                            new DecimalFormatSymbols(new Locale("pt", "BR")));

                                    BigDecimal value = new BigDecimal(amount);
                                    if (t != null) {
                                        BankAPI.setMoney(t.getUniqueId(), amount);
                                        p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDu hast das Guthaben von §b" + t.getName() + "§a auf §e" + df.format(value.floatValue()) + "§e$ §agesetzt!");
                                        t.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§aDein Guthaben wurde auf §e" + df.format(value.floatValue()) + "§e$ §agesetzt!");
                                    } else {
                                        p.sendMessage(Main.cfg.getString("Nachricht.prefixEco") + "§cDieser Spieler ist nicht online!");
                                    }
                                }
                            }
                        }
                    } else {
                        p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
                    }
                }
            }
        }
        }
        return false;
    }
}
