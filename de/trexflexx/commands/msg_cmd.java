package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class msg_cmd implements CommandExecutor {

    public static HashMap<UUID, UUID> lastmsg = new HashMap<UUID, UUID>();

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if(!(sender instanceof Player)) {
        String msg = "";

        for (int i = 1; i < args.length; i++) {
            msg = msg + " " + args[i];
        }

        if (args.length == 1) {
            sender.sendMessage("§7Benutze: §b/msg <spieler> <nachricht>");
            return true;
        }

        if (args.length >= 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t == null) {
                sender.sendMessage("§cDieser Spieler wurde nicht gefunden!");
                return true;
            }

            if (t instanceof Player) {

                sender.sendMessage("§6[§cmir§6 -> §c" + t.getPlayerListName() + "§6]§f" + msg);
                t.sendMessage("§6[§cCONSOLE§6 -> §cmir§6]§f" + msg);
            } else {
                sender.sendMessage("§cDiser Spieler ist nicht Online!");
            }
        } else {
            sender.sendMessage("§7Benutze: /msg <spieler> <nachricht>");
        }
    } else {

    if (cmd.getName().equalsIgnoreCase("msg")) {
        Player p = (Player)sender;
        String msg = "";
               
        for (int i = 1; i < args.length; i++) {
          msg = msg + " " + args[i];
        }
               
        if (args.length == 1) {
          p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/msg <spieler> <nachricht>");
          return true;
        }
               
        if (args.length >= 1) {
          Player t = Bukkit.getPlayer(args[0]);
          if (t == null) {
            p.sendMessage("§cDieser Spieler wurde nicht gefunden!");
            return true;
          }
                 
          if (t instanceof Player) {

              lastmsg.put(p.getUniqueId(), t.getUniqueId());
              p.sendMessage("§6[§cmir§6 -> §c" + t.getPlayerListName() + "§6]§f" + msg);
              t.sendMessage("§6[§c" + p.getPlayerListName() + "§6 -> §cmir§6]§f" + msg);

              for(Player all : Bukkit.getOnlinePlayers()){
                  if(socialspy.socialspy.contains(all)){
                      all.sendMessage("§dSocialSpy §7» §6[§c" + p.getPlayerListName() + "§6 -> §c"+ t.getName() +"§6]§f" + msg);
                  }
              }
          } else {
            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDiser Spieler ist nicht Online!");
          }
        } else {
          p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/msg <spieler> <nachricht>");
        }
      }
    }

    return false;
  }
}
