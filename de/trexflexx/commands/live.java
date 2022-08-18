package de.trexflexx.commands;

import de.trexflexx.main.Main;
import de.trexflexx.main.ManagerTab;
import net.minecraft.server.v1_16_R3.EnumChatFormat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class live implements CommandExecutor {

    public static List<Player> Live = new ArrayList<>();
    public static List<Player> NotLive = new ArrayList<>();



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            Player p = (Player) sender;

            Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
            Team t = board.getTeam(p.getDisplayName());


            if (p.hasPermission(Main.cfg.getString("Permissions.live"))) {

                if (strings.length == 0) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze /live (on/off)");
                } else {

                    if (strings[0].equalsIgnoreCase("on")) {
                        if (Live.contains(p)) {
                            p.sendMessage("Du bist schon Live");
                            return false;
                        } else {

                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu bist nun §cLIVE!");
                            Bukkit.broadcastMessage("§7»");
                            Bukkit.broadcastMessage("§5§lStreamer: §c" + p.getPlayerListName() + " §aist nun §cLive!");
                            Bukkit.broadcastMessage("§7»");

                            Live.add(p);
                            NotLive.remove(p);

                            if (p.hasPermission(Main.cfg.getString("Ranks.Owner.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Owner.prefixTab"), EnumChatFormat.DARK_RED, " §6[§2LIVE§6]", "0");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Admin.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Admin.prefixTab"), EnumChatFormat.RED, "§6[§2LIVE§6]", "1");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Developer.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Developer.prefixTab"), EnumChatFormat.AQUA, "§6[§2LIVE§6]", "2");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Moderator.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Moderator.prefixTab"), EnumChatFormat.DARK_BLUE, "§6[§2LIVE§6]", "3");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Supporter.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Supporter.prefixTab"), EnumChatFormat.GOLD, "§6[§2LIVE§6]", "4");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Builder.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Builder.prefixTab"), EnumChatFormat.DARK_GREEN, "§6[§2LIVE§6]", "5");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Streamer.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Streamer.prefixTab"), EnumChatFormat.DARK_PURPLE, "§6[§2LIVE§6]", "6");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Freund.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Freund.prefixTab"), EnumChatFormat.AQUA, "§6[§2LIVE§6]", "7");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.VIP.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.VIP.prefixTab"), EnumChatFormat.GOLD, "§6[§2LIVE§6]", "8");
                            } else {

                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Spieler.prefixTab"), EnumChatFormat.GRAY, "", "9");
                            }


                        }

                    } else if (strings[0].equalsIgnoreCase("off")) {
                        if (NotLive.contains(p)) {
                            p.sendMessage("Du bist nicht Live");
                            return false;
                        } else {
                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu bist nun nicht mehr §cLIVE!");
                            Live.remove(p);
                            NotLive.add(p);

                            if (p.hasPermission(Main.cfg.getString("Ranks.Owner.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Owner.prefixTab"), EnumChatFormat.DARK_RED, "", "0");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Admin.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Admin.prefixTab"), EnumChatFormat.RED, "", "1");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Developer.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Developer.prefixTab"), EnumChatFormat.AQUA, "", "2");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Moderator.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Moderator.prefixTab"), EnumChatFormat.DARK_BLUE, "", "3");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Supporter.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Supporter.prefixTab"), EnumChatFormat.GOLD, "", "4");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Builder.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Builder.prefixTab"), EnumChatFormat.DARK_GREEN, "", "5");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Streamer.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Streamer.prefixTab"), EnumChatFormat.DARK_PURPLE, "", "6");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.Freund.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Freund.prefixTab"), EnumChatFormat.AQUA, "", "7");
                            } else if (p.hasPermission(Main.cfg.getString("Ranks.VIP.perm"))) {
                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.VIP.prefixTab"), EnumChatFormat.GOLD, "", "8");
                            } else {

                                ManagerTab.getInstance().registerTeam(p, Main.cfg.getString("Ranks.Spieler.prefixTab"), EnumChatFormat.GRAY, "", "9");
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
