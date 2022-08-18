package de.trexflexx.listener;

import de.trexflexx.Utils.tab;
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

public class onQuit implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        for(Player all : Bukkit.getOnlinePlayers()) {
            new tab().removePlayer(e.getPlayer());

            Main.getInstance().sb.setScoreboard(all);

            Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
                Main.getInstance().sb.updateBoard(all);
            }, 0, 20);
        }

        e.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
        e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
        e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
        e.getPlayer().removePotionEffect(PotionEffectType.WATER_BREATHING);
        e.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        e.setQuitMessage(null);
    }
}
