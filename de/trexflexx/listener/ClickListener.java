package de.trexflexx.listener;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.trexflexx.Utils.PerksApi;
import de.trexflexx.Utils.ProfileAPI;
import de.trexflexx.main.ItemBuilder;
import de.trexflexx.main.Main;
import de.trexflexx.money.Api;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class ClickListener implements Listener {


    public static HashMap<String, Long> tagcooldown = new HashMap<>();

    public static ArrayList<Player> cc = new ArrayList<>();

    @EventHandler
    public void InvClickBelohnung(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase("§aTägliche Belohnung")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aBonus")) {


                long jetzt = System.currentTimeMillis();


                if (tagcooldown.containsKey(p.getName())) {

                    long be = ((Long) tagcooldown.get(p.getName())).longValue();

                    int rest = (int) (be + 86400000L - jetzt);

                    if (rest > 0) {

                        int stunde = rest / 1000 / 60 / 60;
                        rest -= stunde * 1000 * 60 * 60;
                        int minute = rest / 1000 / 60;


                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") +  "Du musst noch §c" + stunde + ((stunde == 1) ? "Stunde" : " Stunden ") + "§7und §c" + minute + ((minute == 1) ? "Minute" : " Minuten") + "§7 warten!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 1.0F, 1.0F);
                        p.closeInventory();

                        return;
                    }
                }
                ItemStack brot = new ItemStack(Material.BREAD, 32);

                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0F, 100.0F);
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast deine Tägliche Belohnung abgeholt!");

                Bukkit.broadcastMessage(Main.cfg.getString("Nachricht.prefix") + "§3Der Spieler §a" + p.getName() + "§3 hat seine Tägliche belohnung abgeholt! §7(§a/belohnung§7)");

                p.getInventory().addItem(new ItemStack[]{brot});
                Api.addMoney(p.getUniqueId(), Main.cfg.getInt("BelohnungsMoney"));

                tagcooldown.put(p.getName(), Long.valueOf(jetzt));
                p.closeInventory();
            }
        }
    }
    @EventHandler
    public void InvClickBelasdfohnung(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();


        int amount = 0;

        if (e.getView().getTitle().equalsIgnoreCase("§dPerks")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case COOKED_BEEF:
                    try{
                        amount = Main.cfg.getInt("Perks.NoHunger");
                    } catch (IllegalArgumentException e1){
                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cError: §4Der Wert in der Config ist keine Zahl.");
                    }
                    if(Api.hasEnough(e.getWhoClicked().getUniqueId(), amount)) {
                        if(!(PerksApi.isHunger(p.getUniqueId()))) {
                            PerksApi.hunger(e.getWhoClicked().getUniqueId(), true);
                            Api.removeMoney(p.getUniqueId(), amount);
                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§aDu hast dir das §dNoHunger-Perk §agekauft.");
                            p.updateInventory();
                        } else {
                            p.closeInventory();
                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast dieses Perk schon.");
                        }
                    } else {
                        p.closeInventory();
                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast nicht genug Guthaben.");
                    }
                    break;

                case DIAMOND_BOOTS:
                    try{
                        amount = Main.cfg.getInt("Perks.NoFall");
                    } catch (IllegalArgumentException e1){
                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cError: §4Der Wert in der Config ist keine Zahl.");
                    }
                    if(Api.hasEnough(e.getWhoClicked().getUniqueId(), amount)) {
                        if(!(PerksApi.isNoFall(p.getUniqueId()))) {
                            PerksApi.nofall(e.getWhoClicked().getUniqueId(), true);
                            Api.removeMoney(p.getUniqueId(), amount);
                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§aDu hast dir das §dNoFall-Perk §agekauft.");
                            p.updateInventory();
                        } else {
                            p.closeInventory();
                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast dieses Perk schon.");
                        }
                    } else {
                        p.closeInventory();
                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast nicht genug Guthaben.");
                    }
                    break;
                case BARRIER:
                    try{
                        amount = Main.cfg.getInt("Perks.KeepHotbar");
                    } catch (IllegalArgumentException e1){
                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cError: §4Der Wert in der Config ist keine Zahl.");
                    }
                    if(Api.hasEnough(e.getWhoClicked().getUniqueId(), amount)) {
                        if(!(PerksApi.isKeepHotbar(p.getUniqueId()))) {
                            PerksApi.keepHotbar(e.getWhoClicked().getUniqueId(), true);
                            Api.removeMoney(p.getUniqueId(), amount);
                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§aDu hast dir das §dKeepHotbar-Perk §agekauft.");
                            p.updateInventory();
                        } else {
                            p.closeInventory();
                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast dieses Perk schon.");
                        }
                    } else {
                        p.closeInventory();
                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast nicht genug Guthaben.");
                    }
                    break;
                case POTION:
                    if(e.getCurrentItem().getItemMeta().getLore().contains("§g» §bMit diesem Perk läufst du doppelt so schnell.")){
                        if(Api.hasEnough(p.getUniqueId(),Main.cfg.getInt("Perks.Speed"))){
                            if(PerksApi.isSpeed(p.getUniqueId())){
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast dieses Perk schon.");
                                p.closeInventory();
                            } else if(!(PerksApi.isSpeed(p.getUniqueId()))){
                                PerksApi.speed(p.getUniqueId(), true);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000000000, 0));
                                Api.removeMoney(p.getUniqueId(), Main.cfg.getInt("Perks.Speed"));
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§aDu hast dir das §dSpeed-Perk §agekauft.");
                                p.updateInventory();
                            }
                        } else {
                            p.closeInventory();
                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast nicht genug Guthaben.");
                        }
                    } else {

                        if(e.getCurrentItem().getItemMeta().getLore().contains("§g» §bMit diesem Perk baust du doppelt so schnell ab.")){
                            if(Api.hasEnough(p.getUniqueId(),Main.cfg.getInt("Perks.Effizienz"))){
                                if(PerksApi.isJump(p.getUniqueId())){
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast dieses Perk schon.");
                                    p.closeInventory();
                                } else if(!(PerksApi.isJump(p.getUniqueId()))){
                                    PerksApi.jumpboost(p.getUniqueId(), true);
                                    Api.removeMoney(p.getUniqueId(), Main.cfg.getInt("Perks.Effizienz"));
                                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 2000000000, 1));
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§aDu hast dir das §dEffizienz-Perk §agekauft.");
                                    p.updateInventory();
                                }
                            } else {
                                p.closeInventory();
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast nicht genug Guthaben.");
                            }
                        } else {

                            if(e.getCurrentItem().getItemMeta().getLore().contains("§g» §bMit diesem Perk siehst du in der Dunkelheit.")) {
                                if (Api.hasEnough(p.getUniqueId(), Main.cfg.getInt("Perks.NightVision"))) {
                                    if (PerksApi.isNight(p.getUniqueId())) {
                                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast dieses Perk schon.");
                                        p.closeInventory();
                                    } else if (!(PerksApi.isNight(p.getUniqueId()))) {
                                        PerksApi.night(p.getUniqueId(), true);
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2000000000, 1));
                                        Api.removeMoney(p.getUniqueId(), Main.cfg.getInt("Perks.NightVision"));
                                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§aDu hast dir das §dNightVision-Perk §agekauft.");
                                        p.updateInventory();
                                    }
                                } else {
                                    p.closeInventory();
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast nicht genug Guthaben.");
                                }
                            } else {
                                if(e.getCurrentItem().getItemMeta().getLore().contains("§g» §bMit diesem Perk kannst du länger tauchen.")) {
                                    if (Api.hasEnough(p.getUniqueId(), Main.cfg.getInt("Perks.WasserAtmung"))) {
                                        if (PerksApi.isWater(p.getUniqueId())) {
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast dieses Perk schon.");
                                            p.closeInventory();
                                        } else if (!(PerksApi.isWater(p.getUniqueId()))) {
                                            PerksApi.setWater(p.getUniqueId(), true);
                                            p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2000000000, 1));
                                            Api.removeMoney(p.getUniqueId(), Main.cfg.getInt("Perks.WasserAtmung"));
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§aDu hast dir das §dWasserAtmungs-Perk §agekauft.");
                                            p.updateInventory();
                                        }
                                    } else {
                                        p.closeInventory();
                                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast nicht genug Guthaben.");
                                    }
                                } else {
                                    if(e.getCurrentItem().getItemMeta().getLore().contains("§g» §bMit diesem Perk bist du doppelt so stark.")) {
                                        if (Api.hasEnough(p.getUniqueId(), Main.cfg.getInt("Perks.Stärke"))) {
                                            if (PerksApi.isStrength(p.getUniqueId())) {
                                                p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast dieses Perk schon.");
                                                p.closeInventory();
                                            } else if (!(PerksApi.isStrength(p.getUniqueId()))) {
                                                PerksApi.setStrenght(p.getUniqueId(), true);
                                                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2000000000, 1));
                                                Api.removeMoney(p.getUniqueId(), Main.cfg.getInt("Perks.Stärke"));
                                                p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§aDu hast dir das §dStärke-Perk §agekauft.");
                                                p.updateInventory();
                                            }
                                        } else {
                                            p.closeInventory();
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast nicht genug Guthaben.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case PAPER:
                    try{
                        amount = Main.cfg.getInt("Perks.ClearChat");
                    } catch (IllegalArgumentException e1){
                        p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cError: §4Der Wert in der Config ist keine Zahl.");
                    }
                        if (!(PerksApi.isCC(p.getUniqueId()))) {
                            if (Api.hasEnough(p.getUniqueId(), amount)) {
                                PerksApi.setCC(p.getUniqueId(), true);
                                cc.add(p);
                                Api.removeMoney(p.getUniqueId(), Main.cfg.getInt("Perks.ClearChat"));
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§aDu hast dir das §dClearChat-Perk §agekauft.");
                                p.updateInventory();
                            } else {
                                p.closeInventory();
                                p.sendMessage(Main.cfg.getString("Nachricht.prefixPerks") + "§cDu hast nicht genug Guthaben.");
                            }
                        }
                    break;
                case EXPERIENCE_BOTTLE:
                    try{
                        amount = Main.cfg.getInt("Perks.KeepXP");
                    } catch (IllegalArgumentException e1){
                        p.sendMessage("§cError: §4Der Wert in der Config ist keine Zahl.");
                    }
                    if(Api.hasEnough(p.getUniqueId(), amount)){
                        if(!(PerksApi.isKeepXP(p.getUniqueId()))){
                            PerksApi.keepXP(p.getUniqueId(), true);
                            Api.removeMoney(p.getUniqueId(), Main.cfg.getInt("Perks.KeepXP"));
                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast dir das §dKeepXP-Perk §agekauft.");
                            p.updateInventory();
                        } else {
                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu hast dieses Perk schon.");
                            p.closeInventory();
                        }
                    } else {
                        p.closeInventory();
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu hast nicht genug Guthaben.");
                    }
                    break;
                case BEDROCK:
                    try{
                        amount = Main.cfg.getInt("Perks.Status");
                    } catch (IllegalArgumentException e1){
                        p.sendMessage("§cError: §4Der Wert in der Config ist keine Zahl.");
                    }
                    if(Api.hasEnough(p.getUniqueId(), amount)){
                    if(!(PerksApi.isSlowChat(p.getUniqueId()))){
                        PerksApi.slowchat(p.getUniqueId(), true);
                        Api.removeMoney(p.getUniqueId(), Main.cfg.getInt("Perks.Status"));
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast dir das §dStatus-Perk §agekauft.");
                        p.updateInventory();
                    } else {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu hast dieses Perk schon.");
                        p.closeInventory();
                    }
            } else {
                p.closeInventory();
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu hast nicht genug Guthaben.");
            }
            break;
                default:
                    break;
            }
        } else if (e.getView().getTitle().contains("§aProfile von §b")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {

                case REDSTONE_TORCH:
                    ItemStack glas = new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE, 1).setName(" ").toItemStack();
                    ItemStack white = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE, 1).setName(" ").toItemStack();
                    ItemStack entfernw = new ItemBuilder(Material.RED_DYE, 1).setName("§cGeschlecht zurücksetzen").setLore("§7Klicke, um das dein §eGeschlecht §7zurück zu setzen.").toItemStack();
                    ItemStack männlich = new ItemBuilder(Material.CYAN_CONCRETE, 1).setName("§2Männlich").setLore("§7Klicke, um das Geschlecht §eMännlich §7zu aktivieren.").toItemStack();
                    ItemStack weiblich = new ItemBuilder(Material.PINK_CONCRETE, 1).setName("§dWeiblich").setLore("§7Klicke, um das Geschlecht §eWeiblich §7zu aktivieren.").toItemStack();
                    ItemStack divers = new ItemBuilder(Material.YELLOW_CONCRETE, 1).setName("§bDivers").setLore("§7Klicke, um das Geschlecht §eDivers §7zu aktivieren.").toItemStack();
                    Inventory inv = Bukkit.createInventory(null, 45, "§eProfile §4Einstellungen");

                    int b = 45;
                    for(int i = 0;i!=b; i++){
                        inv.setItem(i, glas);
                    }
                    inv.setItem(1, white);
                    inv.setItem(0, white);
                    inv.setItem(9, white);

                    inv.setItem(7, white);
                    inv.setItem(8, white);
                    inv.setItem(17, white);

                    inv.setItem(27, white);
                    inv.setItem(35, white);
                    inv.setItem(36, white);
                    inv.setItem(37, white);
                    inv.setItem(43, white);
                    inv.setItem(44, white);

                    inv.setItem(13, weiblich);
                    inv.setItem(11, männlich);
                    inv.setItem(15, divers);
                    inv.setItem(31, entfernw);

                    p.openInventory(inv);
                    break;
                default:
                    break;
            }
        }else if (e.getView().getTitle().contains("§eProfile §4Einstellungen")){
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case YELLOW_CONCRETE:
                    if(!ProfileAPI.isDivers(p.getUniqueId())){
                        ProfileAPI.setDivers(p.getUniqueId(), true);
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast das §eDiverse §7Geschlecht ausgewählt.");
                        p.closeInventory();
                        ProfileAPI.setWomen(p.getUniqueId(), false);
                        ProfileAPI.setMen(p.getUniqueId(), false);
                    } else {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast schon dieses Geschlecht gewählt.");
                        p.closeInventory();
                    }
                    break;
                case CYAN_CONCRETE:
                    if(!ProfileAPI.isMen(p.getUniqueId())){
                        ProfileAPI.setMen(p.getUniqueId(), true);
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast das §eMännliche §7Geschlecht ausgewählt.");
                        p.closeInventory();
                        ProfileAPI.setWomen(p.getUniqueId(), false);
                        ProfileAPI.setDivers(p.getUniqueId(), false);
                    } else {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast schon dieses Geschlecht gewählt.");
                        p.closeInventory();
                    }
                    break;
                case PINK_CONCRETE:
                    if(!ProfileAPI.isWomen(p.getUniqueId())){
                        ProfileAPI.setWomen(p.getUniqueId(), true);
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast das §eWeibliche §7Geschlecht ausgewählt.");
                        p.closeInventory();
                        ProfileAPI.setMen(p.getUniqueId(), false);
                        ProfileAPI.setDivers(p.getUniqueId(), false);
                    } else {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast schon dieses Geschlecht gewählt.");
                        p.closeInventory();
                    }
                    break;
                case RED_DYE:
                    ProfileAPI.setMen(p.getUniqueId(), false);
                    ProfileAPI.setWomen(p.getUniqueId(), false);
                    ProfileAPI.setDivers(p.getUniqueId(), false);
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast dein Geschlecht zurückgesetzt.");
                    break;
            }
        }
    }
}





