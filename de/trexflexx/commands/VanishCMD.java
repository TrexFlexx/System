package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.Bukkit;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

import org.bukkit.command.CommandExecutor;

public class VanishCMD implements CommandExecutor {

       public static File Vanish;
       public static YamlConfiguration yVanish;

       static {
              VanishCMD.Vanish = new File("plugins/CitybuildSystem/Data/Vanish.yml");
              VanishCMD.yVanish = YamlConfiguration.loadConfiguration(VanishCMD.Vanish);
       }


       public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
              if (sender instanceof Player) {
                     final Player p = (Player)sender;
                     if (p.hasPermission(Main.cfg.getString("Permissions.vanish.use"))) {
                            if (args.length == 0) {
                                   if (VanishCMD.yVanish.getString(String.valueOf(p.getName()) + ".Vanish") == null) {
                                          p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§2§lDu bist nun unsichtbar!");
                                          VanishCMD.yVanish.set(String.valueOf(p.getName()) + ".Vanish", (Object)"true");
                                          try {
                                                 VanishCMD.yVanish.save(VanishCMD.Vanish);
                                          }
                                          catch (IOException e) {
                                                 e.printStackTrace();
                                          }
                                          for (final Player all : Bukkit.getOnlinePlayers()) {
                                                 if (!all.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                                                        all.hidePlayer(p);
                                                 }
                                                 else {
                                                        all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + p.getName() + " §7ist in den Vanish gegangen.");
                                                 }
                                          }
                                   }
                                   else if (VanishCMD.yVanish.getString(String.valueOf(p.getName()) + ".Vanish").contains("false")) {
                                          p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§2§lDu bist nun unsichtbar!");
                                          VanishCMD.yVanish.set(String.valueOf(p.getName()) + ".Vanish", (Object)"true");
                                          try {
                                                 VanishCMD.yVanish.save(VanishCMD.Vanish);
                                          }
                                          catch (IOException e) {
                                                 e.printStackTrace();
                                          }
                                          for (final Player all : Bukkit.getOnlinePlayers()) {
                                                 if (!all.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                                                        all.hidePlayer(p);
                                                 }
                                                 else {
                                                        all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + p.getName() + " §7ist in den Vanish gegangen.");
                                                 }
                                          }
                                   }
                                   else if (VanishCMD.yVanish.getString(String.valueOf(p.getName()) + ".Vanish").contains("true")) {
                                          p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§4§lDu bist nun sichtbar!");
                                          VanishCMD.yVanish.set(String.valueOf(p.getName()) + ".Vanish", (Object)"false");
                                          try {
                                                 VanishCMD.yVanish.save(VanishCMD.Vanish);
                                          }
                                          catch (IOException e) {
                                                 e.printStackTrace();
                                          }
                                          for (final Player all : Bukkit.getOnlinePlayers()) {
                                                 all.showPlayer(p);
                                                 if (all.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                                                        all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + p.getName() + " §7ist aus den Vanish gegangen.");
                                                 }
                                          }
                                   }
                            }
                            else if (args.length == 1) {
                                   final String target = args[0];
                                   final Player tar = Bukkit.getPlayer(target);
                                   if (tar != null) {
                                          if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish") == null) {
                                                 tar.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§2§lDu bist nun unsichtbar!");
                                                 p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §c" + tar.getName() + " §7ist nun §2§lunsichtbar!");
                                                 VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"true");
                                                 try {
                                                        VanishCMD.yVanish.save(VanishCMD.Vanish);
                                                 }
                                                 catch (IOException e2) {
                                                        e2.printStackTrace();
                                                 }
                                                 for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                                        if (!all2.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                                                               all2.hidePlayer(p);
                                                        }
                                                        else {
                                                               all2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + tar.getName() + " §7wurde in den Vanish gesetzt.");
                                                        }
                                                 }
                                          }
                                          else if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish").contains("false")) {
                                                 tar.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§2§lDu bist nun unsichtbar!");
                                                 p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §c" + tar.getName() + " §7ist nun §2§lunsichtbar!");
                                                 VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"true");
                                                 try {
                                                        VanishCMD.yVanish.save(VanishCMD.Vanish);
                                                 }
                                                 catch (IOException e2) {
                                                        e2.printStackTrace();
                                                 }
                                                 for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                                        if (!all2.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                                                               all2.hidePlayer(p);
                                                        }
                                                        else {
                                                               all2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + tar.getName() + " §7wurde in den Vanish gesetzt.");
                                                        }
                                                 }
                                          }
                                          else if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish").contains("true")) {
                                                 tar.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§4§lDu bist nun sichtbar!");
                                                 p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §c" + tar.getName() + " §7ist nun §4§lsichtbar!");
                                                 VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"false");
                                                 try {
                                                        VanishCMD.yVanish.save(VanishCMD.Vanish);
                                                 }
                                                 catch (IOException e2) {
                                                        e2.printStackTrace();
                                                 }
                                                 for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                                        all2.showPlayer(tar);
                                                        if (all2.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                                                               all2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + tar.getName() + " §7wurde aus den Vanish gesetzt.");
                                                        }
                                                 }
                                          }
                                   }
                                   else {
                                          p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                                   }
                            }
                            else {
                                   p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cBenutze: §a/Vanish (<Spieler>)");
                            }
                     }
                     else {
                            p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
                     }
              }
              else {
                     final ConsoleCommandSender p2 = Bukkit.getConsoleSender();
                     if (args.length == 1) {
                            final String target = args[0];
                            final Player tar = Bukkit.getPlayer(target);
                            if (tar != null) {
                                   if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish") == null) {
                                          tar.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§2§lDu bist nun unsichtbar!");
                                          p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §c" + tar.getName() + " §7ist nun §2§lunsichtbar!");
                                          VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"true");
                                          try {
                                                 VanishCMD.yVanish.save(VanishCMD.Vanish);
                                          }
                                          catch (IOException e2) {
                                                 e2.printStackTrace();
                                          }
                                          for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                                 if (!all2.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                                                        all2.hidePlayer(tar);
                                                 }
                                                 else {
                                                        all2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + tar.getName() + " §7wurde in den Vanish gesetzt.");
                                                 }
                                          }
                                   }
                                   else if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish").contains("false")) {
                                          tar.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§2§lDu bist nun unsichtbar!");
                                          p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §c" + tar.getName() + " §7ist nun §2§lunsichtbar!");
                                          VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"true");
                                          try {
                                                 VanishCMD.yVanish.save(VanishCMD.Vanish);
                                          }
                                          catch (IOException e2) {
                                                 e2.printStackTrace();
                                          }
                                          for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                                 if (!all2.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                                                        all2.hidePlayer(tar);
                                                 }
                                                 else {
                                                        all2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + tar.getName() + " §7wurde in den Vanish gesetzt.");
                                                 }
                                          }
                                   }
                                   else if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish").contains("true")) {
                                          tar.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§4§lDu bist nun sichtbar!");
                                          p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §c" + tar.getName() + " §7ist nun §4§lsichtbar!");
                                          VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"false");
                                          try {
                                                 VanishCMD.yVanish.save(VanishCMD.Vanish);
                                          }
                                          catch (IOException e2) {
                                                 e2.printStackTrace();
                                          }
                                          for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                                 all2.showPlayer(tar);
                                                 if (all2.hasPermission(Main.cfg.getString("Permissions.vanish.see"))) {
                                                        all2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + tar.getName() + " §7wurde aus den Vanish gesetzt.");
                                                 }
                                          }
                                   }
                            }
                            else {
                                   p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                            }
                     }
                     else {
                            p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cBenutze: §a/Vanish <Spieler>");
                     }
              }
              return true;
       }
}