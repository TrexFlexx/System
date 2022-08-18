package de.trexflexx.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class belohnung_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            Player p = (Player) sender;

            Inventory inv = Bukkit.createInventory(null, 27, "§aTägliche Belohnung");

            ItemStack bonus = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta belo = (SkullMeta) bonus.getItemMeta();
            belo.setOwner(p.getName());
            belo.setDisplayName("§aBonus");
            bonus.setItemMeta((ItemMeta) belo);

            inv.setItem(13, bonus);

            p.openInventory(inv);
        }

        return false;
    }
}
