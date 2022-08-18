package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class kopf_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            Player p = (Player) sender;

            if (p.hasPermission(Main.cfg.getString("Permissions.kopf"))) {

                if (args.length == 1) {
                    ItemStack is = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
                    SkullMeta im = (SkullMeta) is.getItemMeta();
                    im.setOwner(args[0]);
                    im.setDisplayName(args[0]);
                    is.setItemMeta((ItemMeta) im);
                    p.getInventory().addItem(new ItemStack[]{is});
                    p.updateInventory();
                    return true;
                }

                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benute: §b/kopf <Spieler>");
            } else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        return false;
    }
}
