package de.trexflexx.listener;

import de.trexflexx.main.Main;
import de.trexflexx.money.Api;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class scoreboard {

    String uuid;




    public void setScoreboard(Player p){

        int hours = Main.getPlugin(Main.class).getConfig().getInt("Time." + p.getName() + ".hours");
        int minutes = Main.getPlugin(Main.class).getConfig().getInt("Time." + p.getName() + ".minutes");

        DecimalFormat df = new DecimalFormat(
                "#,##0",
                new DecimalFormatSymbols(new Locale("pt", "BR")));

        BigDecimal value = new BigDecimal(Api.getMoney(p.getUniqueId()));

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("stats", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§e••● "+ Main.cfg.getString("ServerName") + " §e●•• ");

        obj.getScore("§3§lDein Rang:").setScore(13);
        obj.getScore("§7x" + (p.hasPermission(Main.cfg.getString("Ranks.Owner.perm")) ? Main.cfg.getString("Ranks.Owner.prefixScoreboard") : p.hasPermission(Main.cfg.getString("Ranks.Streamer.perm")) ? Main.cfg.getString("Ranks.Streamer.prefixScoreboard") : (p.hasPermission(Main.cfg.getString("Ranks.Admin.perm")) ? Main.cfg.getString("Ranks.Admin.prefixScoreboard") : (p.hasPermission(Main.cfg.getString("Ranks.Developer.perm")) ? Main.cfg.getString("Ranks.Developer.prefixScoreboard") : (p.hasPermission(Main.cfg.getString("Ranks.Moderator.perm")) ? Main.cfg.getString("Ranks.Moderator.prefixScoreboard") : (p.hasPermission(Main.cfg.getString("Ranks.Supporter.perm")) ? Main.cfg.getString("Ranks.Supporter.prefixScoreboard") : (p.hasPermission(Main.cfg.getString("Ranks.Builder.perm")) ? Main.cfg.getString("Ranks.Builder.prefixScoreboard") : (p.hasPermission(Main.cfg.getString("Ranks.Freund.perm")) ? Main.cfg.getString("Ranks.Freund.prefixScoreboard") : Main.cfg.getString("Ranks.Spieler.prefixScoreboard"))))))))).setScore(12);
        obj.getScore(ChatColor.values()[0].toString()).setScore(11);
        obj.getScore("§3§lMoney").setScore(10);
        obj.getScore("§7x §e" + df.format(value.floatValue()) + "§e$").setScore(9);
        obj.getScore(ChatColor.values()[1].toString()).setScore(8);
        obj.getScore("§3§lSpielzeit:").setScore(7);
        obj.getScore("§7x §b" + hours + " Stunden").setScore(6);
        obj.getScore(ChatColor.values()[2].toString()).setScore(5);
        obj.getScore("§3§lOnline:").setScore(4);
        obj.getScore("§7x §b" + Bukkit.getOnlinePlayers().size()).setScore(3);
        obj.getScore("§e ").setScore(2);
        obj.getScore("§8----------------- ").setScore(1);

        p.setScoreboard(board);
    }

    public void updateBoard(Player p){

        Scoreboard board = p.getScoreboard();
        Bukkit.getOnlinePlayers();


    }

}
