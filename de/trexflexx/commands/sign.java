package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class sign implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission(Main.cfg.getString("Permissions.sign"))) {
                if (args.length >= 1) {
                    if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR) {
                        final ItemStack itemnew = p.getItemInHand();
                        final ItemMeta itemnewmeta = itemnew.getItemMeta();
                        try {
                            if (itemnewmeta.hasLore()) {
                                if (!itemnewmeta.getLore().get(2).contains("§7Signiert")) {
                                    String Message = "";
                                    for (int i = 0; i < args.length; ++i) {
                                        Message = String.valueOf(Message) + args[i] + " ";
                                    }
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDein Item wurde signiert.");
                                    final Date date = new Date(System.currentTimeMillis());
                                    final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                    itemnewmeta.setLore((List) Arrays.asList(" ", Message.replaceAll("&", "§"), "§7Signiert von §a" + p.getName() + " §7am §e" + mm_dd_yyyy));
                                    itemnew.setItemMeta(itemnewmeta);
                                }
                                else {
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDas Item ist bereits signiert.");
                                }
                            }
                            else {
                                String Message = "";
                                for (int i = 0; i < args.length; ++i) {
                                    Message = String.valueOf(Message) + args[i] + " ";
                                }
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDein Item wurde signiert.");
                                final Date date = new Date(System.currentTimeMillis());
                                final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                itemnewmeta.setLore((List)Arrays.asList(" ", Message.replaceAll("&", "§"), "§7Signiert von §a" + p.getName() + " §7am §e" + mm_dd_yyyy));
                                itemnew.setItemMeta(itemnewmeta);
                                p.setItemInHand(itemnew);
                            }
                        }
                        catch (Exception ex) {}
                    }
                    else {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu musst ein Item in der Hand haben!");
                    }
                }
                else {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/Sign <Signierung>");
                }
            }
            else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        else {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        }
        return true;
    }
}
