package de.trexflexx.listener;

import de.trexflexx.Utils.PerksApi;
import de.trexflexx.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class die implements Listener {
    @EventHandler
    public void onDie(PlayerDeathEvent e){

        Player p = e.getEntity();

        e.setDeathMessage(null);
        if(PerksApi.isNight(p.getUniqueId()) || PerksApi.isJump(p.getUniqueId()) || PerksApi.isSpeed(p.getUniqueId()))
        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDa du gestorben bist wuden die effekte deiner §dPerks §cgelöscht. §aBitte rejoine.");
    }
}
