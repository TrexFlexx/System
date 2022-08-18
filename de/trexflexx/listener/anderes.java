package de.trexflexx.listener;

import de.trexflexx.bann.BanCMD;
import de.trexflexx.commands.VanishCMD;
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class anderes implements Listener {
    @EventHandler
    public void onBan(final PlayerLoginEvent e) {
        final Player p = e.getPlayer();
        final String UUID = String.valueOf(p.getUniqueId());


        if (BanCMD.Banned_cfg.get(UUID) != null) {
            if (BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Laenge").equalsIgnoreCase("Permanent")) {
                BanCMD.Banned_cfg.set(String.valueOf(UUID) + ".Name", (Object)p.getName());
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§8[§4Spielverbot§8] §7Du wurdest vom Spiel §4ausgeschlossen.\n\n§eGrund: §7" + BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Grund") + "\n" + "\n" + "§eEnde des Bans: §4§lPERMANENT" + "\n" + "\n" + "§7             Einen Entbannantrag kannst du unter" + "\n" + "§e" + Main.cfg.getString("Nachricht.unbanIP") + "\n" + "§7stellen.");
                try {
                    BanCMD.Banned_cfg.save(BanCMD.Banned);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            else if (BanCMD.Banned_cfg.get(String.valueOf(UUID) + ".Laenge") != null) {
                if (BanCMD.Banned_cfg.getLong(String.valueOf(UUID) + ".Laenge") < System.currentTimeMillis()) {
                    BanCMD.Banned_cfg.set(UUID, (Object)null);
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("system.bansystem.unban.see")) {
                            all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + p.getName() + " §7wurde von der §b§lAutomatischen Cloud §7entbannt!");
                        }
                    }
                    try {
                        BanCMD.Banned_cfg.save(BanCMD.Banned);
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                else {
                    final Date date = new Date(BanCMD.Banned_cfg.getLong(String.valueOf(UUID) + ".Laenge"));
                    final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
                    final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
                    BanCMD.Banned_cfg.set(String.valueOf(UUID) + ".Name", (Object)p.getName());
                    e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§8[§4Spielverbot§8] §7Du wurdest vom Spiel §4ausgeschlossen.\n\n§eGrund: §7" + BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Grund") + "\n" + "\n" + "§eEnde des Bans: §7" + mm_dd_yyyy + " um " + hour_min_sec + " Uhr" + "\n" + "\n" + "§7Einen Entbannantrag kannst du unter" + "\n" + "§e" + Main.cfg.getString("Nachricht.unbanIP") + "\n" + "§7stellen.");
                    try {
                        BanCMD.Banned_cfg.save(BanCMD.Banned);
                    }
                    catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
        }
    }
    @EventHandler
    public void onMute(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String UUID = String.valueOf(p.getUniqueId());
        if (BanCMD.Mutet_cfg.get(String.valueOf(UUID) + ".Laenge") != null) {
            if (BanCMD.Mutet_cfg.getLong(String.valueOf(UUID) + ".Laenge") < System.currentTimeMillis()) {
                BanCMD.Mutet_cfg.set(UUID, (Object)null);
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("system.bansystem.unmute.see")) {
                        all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + p.getName() + " §7wurde von der §b§lAutomatischen Cloud §7entmutet!");
                    }
                }
                try {
                    BanCMD.Mutet_cfg.save(BanCMD.Mutet);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            else {
                e.setCancelled(true);
                final Date date = new Date(BanCMD.Mutet_cfg.getLong(String.valueOf(UUID) + ".Laenge"));
                final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
                final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist bis zum §e" + mm_dd_yyyy + " um " + hour_min_sec + " §7gemutet.");
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Grund: §e" + BanCMD.Mutet_cfg.getString(String.valueOf(UUID) + ".Grund"));
            }
        }
    }

    @EventHandler
    public void onMuteCMD(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        final String UUID = String.valueOf(p.getUniqueId());
        final String[] cmd = e.getMessage().substring(1).split(" ");
        if ((cmd[0].equalsIgnoreCase("msg") || cmd[0].equalsIgnoreCase("r")) && BanCMD.Mutet_cfg.get(String.valueOf(UUID) + ".Laenge") != null) {
            if (BanCMD.Mutet_cfg.getLong(String.valueOf(UUID) + ".Laenge") < System.currentTimeMillis()) {
                BanCMD.Mutet_cfg.set(UUID, (Object)null);
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("system.bansystem.unmute.see")) {
                        all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + p.getName() + " §7wurde von der §b§lAutomatischen Cloud §7entmutet!");
                    }
                }
                try {
                    BanCMD.Mutet_cfg.save(BanCMD.Mutet);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            else {
                e.setCancelled(true);
                final Date date = new Date(BanCMD.Mutet_cfg.getLong(String.valueOf(UUID) + ".Laenge"));
                final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
                final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist bis zum §e" + mm_dd_yyyy + " um " + hour_min_sec + " §7gemutet.");
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Grund: §e" + BanCMD.Mutet_cfg.getString(String.valueOf(UUID) + ".Grund"));
            }
        }
    }
    @EventHandler
    public void onVanish(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (VanishCMD.yVanish.contains(p.getName()) && VanishCMD.yVanish.getString(String.valueOf(p.getName()) + ".Vanish").contains("true")) {
            e.setJoinMessage((String)null);
            for (final Player all : Bukkit.getOnlinePlayers()) {
                if (!all.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                    all.hidePlayer(p);
                }
                else {
                    all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + e.getPlayer().getName() + " §7ist gejoint und ist noch im Vanish.");
                }
            }
        }
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (VanishCMD.yVanish.contains(all.getName()) && VanishCMD.yVanish.getString(String.valueOf(all.getName()) + ".Vanish").contains("true")) {
                if (!p.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                    p.hidePlayer(all);
                }
                else {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + p.getName() + " §7ist im Vanish!");
                }
            }
        }
    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event){
        Player p = event.getPlayer();

        if(event.isSneaking()){
            new scoreboard().setScoreboard(p);
        }
    }
    @EventHandler
    public void onSneasdfak(PlayerToggleSprintEvent event){
        Player p = event.getPlayer();

        if(event.isSprinting()){
            new scoreboard().setScoreboard(p);
        }
    }
}
