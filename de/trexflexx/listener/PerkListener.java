package de.trexflexx.listener;

import de.trexflexx.Utils.PerksApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PerkListener implements Listener {
    @EventHandler
    public void erstes(FoodLevelChangeEvent e){
        if(PerksApi.isHunger(e.getEntity().getUniqueId())){
            e.setCancelled(true);
        } else {
            return;
        }

    }
    @EventHandler
    public void Fall(EntityDamageEvent e){
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
            if(PerksApi.isNoFall(e.getEntity().getUniqueId())){
                e.setCancelled(true);
            } else {
                return;
            }
        }
    }
    @EventHandler
    public void hotnar(PlayerDeathEvent e){
        if(PerksApi.isKeepHotbar(e.getEntity().getUniqueId())){
            e.setKeepInventory(true);
            e.getDrops().clear();
        } else {
            return;
        }
        if(PerksApi.isKeepXP(e.getEntity().getUniqueId())){
            e.setKeepLevel(true);
            e.setDroppedExp(0);
        } else {
            return;
        }
    }
}
