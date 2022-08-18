package de.trexflexx.listener;


import de.trexflexx.Utils.PerksApi;
import de.trexflexx.Utils.tab;
import de.trexflexx.main.Main;
import de.trexflexx.main.ManagerTab;

import net.minecraft.server.v1_16_R3.EnumChatFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;


public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

            p.performCommand("spawn");

            for (Player all : Bukkit.getOnlinePlayers()) {
                Main.getInstance().getTabManager().setTablist(p);
                new tab().addPlayer(p);
                Main.getInstance().sb.setScoreboard(all);

                Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
                    Main.getInstance().sb.updateBoard(all);
                }, 0, 20);

            }

        if(PerksApi.isSpeed(p.getUniqueId())){
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000000000, 0));
        }
        if(PerksApi.isJump(p.getUniqueId())){
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 2000000000, 1));
        }
        if(PerksApi.isNight(p.getUniqueId())){
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2000000000, 1));
        }
        if(PerksApi.isWater(p.getUniqueId())){
            p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2000000000, 1));
        }
        if(PerksApi.isStrength(p.getUniqueId())){
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2000000000, 1));
        }

        if (e.getPlayer().hasPermission(Main.cfg.getString("Ranks.Owner.perm"))) {
            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.Owner.prefixTab"), EnumChatFormat.GRAY, "", "0");
        } else if (e.getPlayer().hasPermission(Main.cfg.getString("Ranks.Admin.perm"))) {
            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.Admin.prefixTab"), EnumChatFormat.GRAY, "", "1");
        } else if (e.getPlayer().hasPermission(Main.cfg.getString("Ranks.Developer.perm"))) {
            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.Developer.prefixTab"), EnumChatFormat.GRAY, "", "2");
        } else if (e.getPlayer().hasPermission(Main.cfg.getString("Ranks.Moderator.perm"))) {
            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.Moderator.prefixTab"), EnumChatFormat.GRAY, "", "3");
        } else if (e.getPlayer().hasPermission(Main.cfg.getString("Ranks.Supporter.perm"))) {
            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.Supporter.prefixTab"), EnumChatFormat.GRAY, "", "4");
        } else if (e.getPlayer().hasPermission(Main.cfg.getString("Ranks.Builder.perm"))) {
            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.Builder.prefixTab"), EnumChatFormat.GRAY, "", "5");
        } else if (e.getPlayer().hasPermission(Main.cfg.getString("Ranks.Streamer.perm"))) {
            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.Streamer.prefixTab"), EnumChatFormat.GRAY, "", "6");
        } else if (e.getPlayer().hasPermission(Main.cfg.getString("Ranks.Freund.perm"))) {
            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.Freund.prefixTab"), EnumChatFormat.GRAY, "", "7");
        } else if (e.getPlayer().hasPermission(Main.cfg.getString("Ranks.VIP.perm"))) {
            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.VIP.prefixTab"), EnumChatFormat.GRAY, "", "8");
        } else {

            ManagerTab.getInstance().registerTeam(e.getPlayer(), Main.cfg.getString("Ranks.Spieler.prefixTab"), EnumChatFormat.GRAY, "", "9");
        }
            e.setJoinMessage(null);
    }
    @EventHandler
    public void asdfiaf(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        final File Status = new File("plugins/CitybuildSystem/Data/Status.yml");
        final YamlConfiguration yStatus = YamlConfiguration.loadConfiguration(Status);
        if (yStatus.getString(p.getUniqueId().toString()) != null) {
            final String StatusS = yStatus.getString(p.getUniqueId().toString());
            Bukkit.broadcastMessage("§e§l" + p.getName() + " §d» §a" + ChatColor.translateAlternateColorCodes('&', StatusS));
        }
    }
}