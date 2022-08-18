package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Enchanzt implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player p = (Player) commandSender;

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            if (p.hasPermission(Main.cfg.getString("Permissions.enchant"))) {
                if (strings.length < 2) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/enchant <Enchantment> <Level>");
                    return true;
                }
                if (p.getItemInHand().getType() == Material.AIR) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu hast nichts in deiner Hand.");
                    return true;
                }
                ItemStack i = p.getItemInHand();
                Enchantment e = Enchantment.getByName(strings[0].toUpperCase());
                int level = 0;
                try {
                    level = Integer.parseInt(strings[1]);
                } catch (NumberFormatException e1) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§c" + strings[1] + "§cist keine Zahl.");
                    return true;
                }
                try {
                    i.addUnsafeEnchantment(e, level);
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDein Item wurde erfolgreich mit dem Enchantment §b" + e.getName() + " §aund dem Level §b" + level + "§a verzaubert!");

                } catch (IllegalArgumentException e1) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDas Enchantment §b" + strings[0] + "§c existiert nicht!");
                    return true;
                }
            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }

        return false;
    }


}
