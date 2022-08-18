package de.trexflexx.commands;

import de.trexflexx.Utils.PerksApi;
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class status implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            final File Status = new File("plugins/CitybuildSystem/Data/Status.yml");
            final YamlConfiguration yStatus = YamlConfiguration.loadConfiguration(Status);
            if (p.hasPermission(Main.cfg.getString("Permissions.status")) || PerksApi.isSlowChat(p.getUniqueId())) {
                if (args.length >= 1) {
                    if (!args[0].equalsIgnoreCase("delete")) {
                        String Message = "";
                        for (int i = 0; i < args.length; ++i) {
                            Message = String.valueOf(Message) + args[i] + " ";
                        }
                        yStatus.set(p.getUniqueId().toString(), (Object)Message);
                        try {
                            yStatus.save(Status);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast dein Status zu: §a" + Message + "§7geändert!");
                    }
                    else {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast dein Status gelöscht!");
                        yStatus.set(p.getUniqueId().toString(), (Object)null);
                        try {
                            yStatus.save(Status);
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                else {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §c/Status <delete>/<Nachricht>");
                }
            }
            else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        else {
            final File Status = new File("plugins/CitybuildSystem/Data/Status.yml");
            final YamlConfiguration yStatus = YamlConfiguration.loadConfiguration(Status);
            if(args.length == 0){
                sender.sendMessage("§7Benutze: §b/status <delete>/<Player>");
            } else if(args.length == 1){
                sender.sendMessage("§cGebe einen Spieler an.");
            } else if(args.length == 2){
                Player t = Bukkit.getPlayer(args[1]);

                if(args[0].equalsIgnoreCase("delete")){
                    if(t == null){
                        sender.sendMessage("§cDieser Spieler ist nicht online.");
                    } else if(t != null){
                        t.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDein Status wurde von der §cCONSOLE §agelöscht.");
                        sender.sendMessage("§aDu hast den Status von §b" + t.getName() + "§a gelöscht.");
                        yStatus.set(t.getUniqueId().toString(), (Object)null);
                        try {
                            yStatus.save(Status);
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
        return true;
    }
}
