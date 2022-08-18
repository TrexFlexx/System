package de.trexflexx.bann;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class UnbanCMD implements CommandExecutor
{


    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission(Main.cfg.getString("Permissions.ban.unban.use"))) {
                if (args.length == 1) {
                    final String target = args[0];
                    final String UUID = String.valueOf(Bukkit.getOfflinePlayer(target).getUniqueId());
                    if (BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Laenge") != null) {
                        BanCMD.Banned_cfg.set(UUID, (Object)null);
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission(Main.cfg.getString("Permissions.ban.unban.see"))) {
                                all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + target + " §7wurde von §a" + p.getName() + " §7entbannt!");
                            }
                        }
                        try {
                            BanCMD.Banned_cfg.save(BanCMD.Banned);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Spieler ist nicht gebannt.");
                    }
                }
                else {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cBenutze: §a/Unban <Spieler>");
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
                if (BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Laenge") != null) {
                    BanCMD.Banned_cfg.set(UUID, (Object)null);
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission(Main.cfg.getString("Permissions.ban.unban.see"))) {
                            all.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + target + " §7wurde von §a" + p2.getName() + " §7entbannt!");
                        }
                    }
                    p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Der Spieler §a" + target + " §7wurde von §a" + p2.getName() + " §7entbannt!");
                    try {
                        BanCMD.Banned_cfg.save(BanCMD.Banned);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Spieler ist nicht gebannt.");
                }
            }
            else {
                p2.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cBenutze: §a/Unban <Spieler>");
            }
        }
        return true;
    }
}
