package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class teamchat implements CommandExecutor {

    ArrayList<Player> tc = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(!(sender instanceof Player)) {
                sender.sendMessage("§cDu musst ein Spieler sein.");
        } else {

        Player p = (Player) sender;
            if (p.hasPermission(Main.cfg.getString("Permissions.teamchat"))) {
                if (strings.length == 0) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/teamchat login");
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/teamchat logout");
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/teamchat <message>");
                } else {
                    if (strings.length == 1) {
                        if (strings[0].equalsIgnoreCase("login")) {
                            if (!tc.contains(p)) {
                                tc.add(p);
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aErfolgreich angemeldet!");
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    if (tc.contains(all) || sender instanceof ConsoleCommandSender) {
                                        all.sendMessage("§bTeamChat | §e" + p.getName() + " §5hat sich eingeloggt!");
                                    } else {
                                        return false;
                                    }
                                }
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu bist schon angemeldet!");
                            }
                        } else {
                            if (strings[0].equalsIgnoreCase("logout")) {
                                if (tc.contains(p)) {
                                    tc.remove(p);
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aErfolgreich abgemeldet!");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (tc.contains(all) || sender instanceof ConsoleCommandSender) {
                                            all.sendMessage("§bTeamChat | §e" + p.getName() + " §5hat sich abgemeldet!");
                                        } else {
                                            return false;
                                        }
                                    }
                                } else {
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu bist nicht angemeldet!");
                                }
                            } else {
                                String msg = "";

                                for (int i = 0; i < strings.length; i++) {
                                    msg = msg + strings[i] + " ";
                                }


                                if (tc.contains(p)) {
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (tc.contains(all) || sender instanceof ConsoleCommandSender) {
                                            all.sendMessage("§bTeamChat | §e" + p.getName() + "§7 » §a" + ChatColor.translateAlternateColorCodes('&', msg));
                                        } else {
                                            return true;
                                        }
                                    }

                                } else {
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu musst dich erst einloggen! §eMit: /teamchat login");
                                }
                            }
                        }
                    } else {
                        if(strings.length > 1) {
                            String msg = "";

                            for (int i = 0; i < strings.length; i++) {
                                msg = msg + strings[i] + " ";
                            }


                            if (tc.contains(p)) {
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    if (tc.contains(all)) {
                                        all.sendMessage("§bTeamChat | §e" + p.getName() + "§7 » §a" + ChatColor.translateAlternateColorCodes('&', msg));
                                    } else {
                                        return true;
                                    }
                                }
                            }  else {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu musst dich erst einloggen! §eMit: /teamchat login");
                            }
                        }

                    }
                }
            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }

        return false;
    }
}
