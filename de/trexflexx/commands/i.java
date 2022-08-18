package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class i implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
            if(p.hasPermission(Main.cfg.getString("Permissions.i"))){
                if(args.length == 0){
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/i <Item> (<Amount>)");
                } else {
                    if (args.length == 1) {
                        Material e = Material.getMaterial(args[0].toUpperCase());

                        try {
                            ItemStack is = new ItemStack(e);
                            p.getInventory().addItem(is);
                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast das Item §b" + args[0] + " §abekommen.");
                        } catch (IllegalArgumentException e1) {
                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDas Item §b" + args[0] + "§c existiert nicht.");
                            return true;
                        }

                    } else {
                        if(args.length == 2){

                            int level = 1;

                            Material e = Material.getMaterial(args[0].toUpperCase());
                            try {
                                level = Integer.parseInt(args[1]);
                            } catch (NumberFormatException e1) {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§b" + args[1] + " §cist keine Zahl.");
                                return true;
                            }

                            try {
                                ItemStack is = new ItemStack(e, level);
                                p.getInventory().addItem(is);
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast das Item §b" + args[0] + "§e " + level + " §emal §abekommen.");
                            } catch (IllegalArgumentException e1) {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDas Item §b" + args[0] + " §cexistiert nicht.");
                                return true;
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
