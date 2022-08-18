package de.trexflexx.commands;

import de.trexflexx.Utils.ProfileAPI;
import de.trexflexx.main.ItemBuilder;
import de.trexflexx.main.Main;
import de.trexflexx.money.Api;
import de.trexflexx.money.Bank.BankAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

public class profilw implements CommandExecutor {

    public static HashMap<Player, Player> target = new HashMap<Player, Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst ein Spieler sein.");
        } else {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/profile <Player>");
            } else if (args.length == 1) {


                Player t = Bukkit.getPlayer(args[0]);
                if(p.isOnline()) {
                    if (t == null) {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDieser Spieler ist nicht online.");
                    } else if (t != null) {

                        int hours = Main.getPlugin(Main.class).getConfig().getInt("Time." + t.getName() + ".hours");

                        DecimalFormat df = new DecimalFormat(
                                "#,##0",
                                new DecimalFormatSymbols(new Locale("pt", "BR")));
                        BigDecimal value = new BigDecimal(Api.getMoney(t.getUniqueId()));
                        BigDecimal bankvalue = new BigDecimal(BankAPI.getMoney(t.getUniqueId()));
                        target.put(p, t);
                        Inventory inv = Bukkit.createInventory(null, 45, "§aProfile von §b" + t.getName());
                        ItemStack glas = new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE, 1).setName(" ").toItemStack();
                        ItemStack settings = new ItemBuilder(Material.REDSTONE_TORCH, 1).setName("§4Einstellungen").setLore("§7Klicke, um die Einstellungen zu öffnen.").toItemStack();
                        ItemStack clocj = new ItemBuilder(Material.CLOCK, 1).setName("§6Spielzeit").setLore("§7Der Spieler hat derzeit §e" + hours + " §eStunden §7auf diesem Server verbracht.").toItemStack();
                        ItemStack Paper = new ItemBuilder(Material.PAPER, 1).setName("§6Informationen").setLore("§eGeschlecht: §e" + ((ProfileAPI.isMen(t.getUniqueId()) ? "§2Männlich" : (ProfileAPI.isWomen(t.getUniqueId())) ? "§dWeiblich" : (ProfileAPI.isDivers(t.getUniqueId()) ? "§bDivers" : "§cNichts ausgewahlt.")))).addLoreLine("§eAlter: §cNoch nicht verfügbar.").toItemStack();
                        ItemStack gold = new ItemBuilder(Material.GOLD_INGOT, 1).setName("§6Guthaben").setLore("§e" + t.getName() + "§7 hat gerade §e" + df.format(value.floatValue()) + "§e€").toItemStack();
                        ItemStack dia = new ItemBuilder(Material.DIAMOND, 1).setName("§6Bankguthaben").setLore("§e" + t.getName() + "§7 hat gerade §e" + df.format(bankvalue.floatValue()) + "§e€ §7auf seiner Bank.").toItemStack();
                        ItemStack head = new ItemBuilder(Material.PLAYER_HEAD, 1).setName("§d" + t.getName()).setSkullOwner(t.getName()).setLore("§7Rang:" + (t.hasPermission(Main.cfg.getString("Ranks.Owner.perm")) ? Main.cfg.getString("Ranks.Owner.prefixScoreboard") : t.hasPermission(Main.cfg.getString("Ranks.Streamer.perm")) ? Main.cfg.getString("Ranks.Streamer.prefixScoreboard") : (t.hasPermission(Main.cfg.getString("Ranks.Admin.perm")) ? Main.cfg.getString("Ranks.Admin.prefixScoreboard") : (t.hasPermission(Main.cfg.getString("Ranks.Developer.perm")) ? Main.cfg.getString("Ranks.Developer.prefixScoreboard") : (t.hasPermission(Main.cfg.getString("Ranks.Moderator.perm")) ? Main.cfg.getString("Ranks.Moderator.prefixScoreboard") : (t.hasPermission(Main.cfg.getString("Ranks.Supporter.perm")) ? Main.cfg.getString("Ranks.Supporter.prefixScoreboard") : (t.hasPermission(Main.cfg.getString("Ranks.Builder.perm")) ? Main.cfg.getString("Ranks.Builder.prefixScoreboard") : (t.hasPermission(Main.cfg.getString("Ranks.Freund.perm")) ? Main.cfg.getString("Ranks.Freund.prefixScoreboard") : Main.cfg.getString("Ranks.Spieler.prefixScoreboard"))))))))).toItemStack();
                        ItemStack Online = new ItemBuilder(Material.REDSTONE, 1).setName("§6Letzes Online").setLore("§eOnline: §b" + (t.isOnline() ? "§aIst gerade Online." : "§cIst derzeit Offline.")).toItemStack();

                        int b = 45;
                        for (int i = 0; i != b; i++) {
                            inv.setItem(i, glas);
                        }
                        inv.setItem(10, Paper);
                        inv.setItem(13, head);
                        if (t.getName().equalsIgnoreCase(p.getName())) {
                            inv.setItem(16, settings);
                        }
                        inv.setItem(28, gold);
                        inv.setItem(29, dia);
                        inv.setItem(33, Online);
                        inv.setItem(34, clocj);
                        p.openInventory(inv);
                    }
                }
            }
        }

        return false;
    }
}
