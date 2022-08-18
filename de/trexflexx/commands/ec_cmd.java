package de.trexflexx.commands;

import java.util.ArrayList;
import java.util.UUID;
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class ec_cmd implements CommandExecutor {
public static ArrayList<UUID> enderchest = new ArrayList<>();


public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
        sender.sendMessage("§cDazu musst du ein Spieler sein!");
        return true;
        }

        Player p = (Player)sender;

        if (args.length == 0) {
        p.openInventory(p.getEnderChest());
        p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 1.0F);

        }
        else if (args.length == 1) {
        if (!p.hasPermission(Main.cfg.getString("Permissions.ec-other"))) {
        p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
        return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Spieler ist nicht online!");
        return true;
        }
        p.openInventory(target.getEnderChest());
        p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 1.0F);
        enderchest.contains(p.getUniqueId());
        } else {
        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/ec <Spieler>");
        }
        return false;
        }
        }
