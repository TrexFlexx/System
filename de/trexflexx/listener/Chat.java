package de.trexflexx.listener;

import de.trexflexx.commands.Globalmute_cmd;
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {
   @EventHandler
   public void onChat(AsyncPlayerChatEvent event) {

       if (Globalmute_cmd.globalmute == false) {
               event.setCancelled(false);

               Player player = event.getPlayer();

                   String message = event.getMessage();
                   message = message.replaceAll("&", "§");
                   if (player.hasPermission(Main.cfg.getString("Ranks.Owner.perm"))) {
                       event.setCancelled(true);
                       Bukkit.broadcastMessage("§8»");
                       Bukkit.broadcastMessage(Main.cfg.getString("Ranks.Owner.prefixChat") + "§7" + player.getName() + " §8» §a" + message);
                       Bukkit.broadcastMessage("§8»");

                   } else if (player.hasPermission(Main.cfg.getString("Ranks.Admin.perm"))) {
                       event.setFormat(Main.cfg.getString("Ranks.Admin.prefixChat") + "§7" + player.getName() + " §8» §a" + message);

                   } else if (player.hasPermission(Main.cfg.getString("Ranks.Moderator.perm"))) {
                       event.setFormat(Main.cfg.getString("Ranks.Moderator.prefixChat")+ "§7" + player.getName() + " §8» §7" + message);

                   } else if (player.hasPermission(Main.cfg.getString("Ranks.Supporter.perm"))) {
                       event.setFormat(Main.cfg.getString("Ranks.Supporter.prefixChat")+ "§7" + player.getName() + " §8» §7" + message);

                   } else if (player.hasPermission(Main.cfg.getString("Ranks.Developer.perm"))) {
                       event.setFormat(Main.cfg.getString("Ranks.Developer.prefixChat") + "§7"+ player.getName() + " §8» §7" + message);

                   } else if (player.hasPermission(Main.cfg.getString("Ranks.Builder.perm"))) {
                       event.setFormat(Main.cfg.getString("Ranks.Builder.prefixChat") + "§7"+ player.getName() + " §8» §7" + message);

                   } else if (player.hasPermission(Main.cfg.getString("Ranks.Streamer.perm"))) {
                       event.setFormat(Main.cfg.getString("Ranks.Streamer.prefixChat") + "§7"+ player.getName() + " §8» §7" + message);

                   } else if (player.hasPermission(Main.cfg.getString("Ranks.VIP.perm"))) {
                       event.setFormat(Main.cfg.getString("Ranks.VIP.prefixChat") + "§7"+ player.getName() + " §8» §7" + message);

                   } else if (player.hasPermission(Main.cfg.getString("Ranks.Freund.perm"))) {
                       event.setFormat(Main.cfg.getString("Ranks.Freund.prefixChat") + "§7"+ player.getName() + " §8» §7" + message);

                   } else {
                       event.setFormat(Main.cfg.getString("Ranks.Spieler.prefixChat") + "§7"+ player.getName() + " §8» §7" + message);
                   }
       } else {
           if(Globalmute_cmd.globalmute == true){
               event.getPlayer().sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Globalmute ist gerade an!");
               event.setCancelled(true);
           }
       }
    }
 }
