package de.trexflexx.commands;

import de.trexflexx.Utils.PerksApi;
import de.trexflexx.main.ItemBuilder;
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class perks implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {



        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {

            int amount = 0;

            DecimalFormat df = new DecimalFormat(
                    "#,##0",
                    new DecimalFormatSymbols(new Locale("pt", "BR")));

            BigDecimal hungerCosts = new BigDecimal(Main.cfg.getInt("Perks.NoHunger"));
            BigDecimal NoFallCosts = new BigDecimal(Main.cfg.getInt("Perks.NoFall"));
            BigDecimal keepHotbarCosts = new BigDecimal(Main.cfg.getInt("Perks.KeepHotbar"));
            BigDecimal keepXPCosts = new BigDecimal(Main.cfg.getInt("Perks.KeepXP"));
            BigDecimal StatusCosts = new BigDecimal(Main.cfg.getInt("Perks.Status"));
            BigDecimal ClearChatCosts = new BigDecimal(Main.cfg.getInt("Perks.ClearChat"));


            Player p = (Player) sender;
            Inventory inv = Bukkit.createInventory(null, 54, "§dPerks");

            ItemStack hunger = new ItemBuilder(Material.COOKED_BEEF, 1).setName("§cKein Hunger").setLore(" ").addLoreLine("§g» §bMit diesem Perk bekommst du kein Hunger mehr.").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(hungerCosts.floatValue()) + "§6€").toItemStack();
            ItemStack hungerNoNumber = new ItemBuilder(Material.COOKED_BEEF, 1).setName("§cKein Hunger").setLore(" ").addLoreLine("§g» §bMit diesem Perk bekommst du kein Hunger mehr.").addLoreLine(" ").addLoreLine("§ePreis: §cDer Wert in der Config ist keine Zahl.").toItemStack();
            ItemStack NoFall = new ItemBuilder(Material.DIAMOND_BOOTS, 1).setName("§cKein Falldamage").setLore(" ").addLoreLine("§g» §bMit diesem Perk bekommst du kein Fallschaden").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(NoFallCosts.floatValue()) + "§6€").toItemStack();
            ItemStack Hotbar = new ItemBuilder(Material.BARRIER, 1).setName("§cKeep-Hotbar").setLore(" ").addLoreLine("§g» §bMit diesem Perk verlierßt du dein Inventar bei dem Tod nichtmehr.").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(keepHotbarCosts.floatValue()) + "§6€").toItemStack();
            ItemStack xp = new ItemBuilder(Material.EXPERIENCE_BOTTLE, 1).setName("§cKeep-XP").setLore(" ").addLoreLine("§g» §bMit diesem Perk behälts du deine XP beim sterben").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(keepXPCosts.floatValue()) + "§6€").toItemStack();
            ItemStack slowchat = new ItemBuilder(Material.BEDROCK, 1).setName("§cStatus-Perks").setLore(" ").addLoreLine("§g» §bMit diesem Perk kannst du /status benutzen.").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(StatusCosts.floatValue()) + "§6€").toItemStack();

            ItemStack nichtgekauft = new ItemBuilder(Material.RED_STAINED_GLASS_PANE, 1).setName("§eNicht Verfügbar").setLore(" ").addLoreLine("§g» §bKaufe dir als erstes dieses Perk.").toItemStack();
            ItemStack gekauft = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE, 1).setName("§aVerfügbar").setLore(" ").addLoreLine("§g» §bDu hast dieses Perk gekauft.").toItemStack();


            ItemStack clearchat = new ItemBuilder(Material.PAPER, 1).setName("§cClearChat").setLore(" ").addLoreLine("§g» §bMit diesem Perk kannst du /clearchat benutzen.").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(ClearChatCosts.floatValue()) + "§6€").toItemStack();

            ItemStack glas = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, 1).setName(" ").toItemStack();
            ItemStack glas2 = new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE, 1).setName(" ").toItemStack();
            int b = 54;
            for (int i = 0; i != b; i++) {
                inv.setItem(i, glas);
            }
            int b2 = 10;
            for (int i2 = 0; i2 != b2; i2++) {
                inv.setItem(i2, glas2);
            }
            inv.setItem(17, glas2);
            inv.setItem(18, glas2);
            inv.setItem(27, glas2);
            inv.setItem(26, glas2);
            inv.setItem(35, glas2);
            inv.setItem(36, glas2);

            int b22 = 54;
            for (int i22 = 44; i22 != b22; i22++) {
                inv.setItem(i22, glas2);
            }
            inv.setItem(10, hunger);
            inv.setItem(11, NoFall);
            inv.setItem(12, Hotbar);
            inv.setItem(14, clearchat);
            inv.setItem(15, xp);
            inv.setItem(16, slowchat);

            if (PerksApi.isHunger(p.getUniqueId())) {
                inv.setItem(19, gekauft);
            } else {
                inv.setItem(19, nichtgekauft);
            }

            if(PerksApi.isNoFall(p.getUniqueId())){
                inv.setItem(20, gekauft);
            } else {
                inv.setItem(20, nichtgekauft);
            }

            if(PerksApi.isKeepHotbar(p.getUniqueId())){
                inv.setItem(21, gekauft);
            } else {
                inv.setItem(21, nichtgekauft);
            }

            if (PerksApi.isCC(p.getUniqueId())) {
                inv.setItem(23, gekauft);
            } else {
                inv.setItem(23, nichtgekauft);
            }
            if (PerksApi.isKeepXP(p.getUniqueId())) {
                inv.setItem(24, gekauft);
            } else {
                inv.setItem(24, nichtgekauft);
            }
            if (PerksApi.isSlowChat(p.getUniqueId())) {
                inv.setItem(25, gekauft);
            } else {
                inv.setItem(25, nichtgekauft);
            }




            BigDecimal nightCosts = new BigDecimal(Main.cfg.getInt("Perks.NightVision"));
            BigDecimal effeCosts = new BigDecimal(Main.cfg.getInt("Perks.Effizienz"));
            BigDecimal speedCosts = new BigDecimal(Main.cfg.getInt("Perks.Speed"));
            BigDecimal wasserCosts = new BigDecimal(Main.cfg.getInt("Perks.WasserAtmung"));
            BigDecimal stärkeCosts = new BigDecimal(Main.cfg.getInt("Perks.Stärke"));



            ItemStack night = new ItemBuilder(Material.POTION, 1).setName("§3Nightvision-Perk").setLore("§cACHTUNG: §4Dieses Perk kann man nicht deaktiviern.").addLoreLine("§g» §bMit diesem Perk siehst du in der Dunkelheit.").addLoreLine(" ").addLoreLine("§ePreis: §6" +  df.format(nightCosts.floatValue()) + "§6€").toItemStack();
            ItemStack jump = new ItemBuilder(Material.POTION, 1).setName("§2Effizienz-Perk").setLore("§cACHTUNG: §4Dieses Perk kann man nicht deaktiviern.").addLoreLine("§g» §bMit diesem Perk baust du doppelt so schnell ab.").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(effeCosts.floatValue()) + "§6€").toItemStack();
            ItemStack speed = new ItemBuilder(Material.POTION, 1).setName("§dSpeed-Perk").setLore("§cACHTUNG: §4Dieses Perk kann man nicht deaktiviern.").addLoreLine("§g» §bMit diesem Perk läufst du doppelt so schnell.").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(speedCosts.floatValue()) + "§6€").toItemStack();
            ItemStack wasser = new ItemBuilder(Material.POTION, 1).setName("§bWasseratmungs-Perk").setLore("§cACHTUNG: §4Dieses Perk kann man nicht deaktiviern.").addLoreLine("§g» §bMit diesem Perk kannst du länger tauchen.").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(wasserCosts.floatValue()) + "§6€").toItemStack();
            ItemStack stärke = new ItemBuilder(Material.POTION, 1).setName("§cStärke-Perk").setLore("§cACHTUNG: §4Dieses Perk kann man nicht deaktiviern.").addLoreLine("§g» §bMit diesem Perk bist du doppelt so stark.").addLoreLine(" ").addLoreLine("§ePreis: §6" + df.format(stärkeCosts.floatValue()) + "§6€").toItemStack();

            inv.setItem(29, jump);
            inv.setItem(30, night);
            inv.setItem(31, speed);
            inv.setItem(32, wasser);
            inv.setItem(33, stärke);

            inv.setItem(42, nichtgekauft);

            if (PerksApi.isWater(p.getUniqueId())) {
                inv.setItem(41, gekauft);
            } else {
                inv.setItem(41, nichtgekauft);
            }
            if (PerksApi.isStrength(p.getUniqueId())) {
                inv.setItem(42, gekauft);
            } else {
                inv.setItem(42, nichtgekauft);
            }

            if (PerksApi.isJump(p.getUniqueId())) {
                inv.setItem(38, gekauft);
            } else {
                inv.setItem(38, nichtgekauft);
            }

            if(PerksApi.isNight(p.getUniqueId())){
                inv.setItem(39, gekauft);
            } else {
                inv.setItem(39, nichtgekauft);
            }

            if(PerksApi.isSpeed(p.getUniqueId())){
                inv.setItem(40, gekauft);
            } else {
                inv.setItem(40, nichtgekauft);
            }
            p.openInventory(inv);
        }

        return false;
    }
}
