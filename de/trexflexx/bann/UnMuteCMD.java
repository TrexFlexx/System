package de.trexflexx.bann;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class UnMuteCMD implements CommandExecutor
{


    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission(Main.cfg.getString("Permissions.ban.unmute.use"))) {
                if (args.length == 1) {
                    final String target = args[0];
                    final String UUID = String.valueOf(Bukkit.getOfflinePlayer(target).getUniqueId());
                    if (BanCMD.Mutet_cfg.getString(String.valueOf(UUID) + ".Laenge") != null) {
                        BanCMD.Mutet_cfg.set(UUID, (Object)null);
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission(Main.cfg.getString("Permissions.ban.unmute.see"))) {
                                all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + target + " §7wurde von §a" + p.getName() + " §7entmutet!");
                            }
                        }
                        try {
                            BanCMD.Mutet_cfg.save(BanCMD.Mutet);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Spieler ist nicht gemutet.");
                    }
                }
                else {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cBenutze: §a/Unmute <Spieler>");
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
                final String UUID = String.valueOf(Bukkit.getOfflinePlayer(target).getUniqueId());
                if (BanCMD.Mutet_cfg.getString(String.valueOf(UUID) + ".Laenge") != null) {
                    BanCMD.Mutet_cfg.set(UUID, (Object)null);
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission(Main.cfg.getString("Permissions.ban.unmute.see"))) {
                            all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + target + " §7wurde von §a" + p2.getName() + " §7entmutet!");
                        }
                    }
                    p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + target + " §7wurde von §a" + p2.getName() + " §7entmutet!");
                    try {
                        BanCMD.Mutet_cfg.save(BanCMD.Mutet);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Spieler ist nicht gemutet.");
                }
            }
            else {
                p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cBenutze: §a/Unmute <Spieler>");
            }
        }
        return true;
    }
}

