package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class hat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {
        Player p = (Player)sender;

            ItemStack i = p.getInventory().getItemInMainHand();

            if(p.getInventory().getItemInMainHand().getType().isAir()){
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu musst ein Item/Block in deiner Hand halten.");
                return true;
            } else {
                p.getInventory().setHelmet(i);

                p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 64);
            }
        }

        return false;
    }
}
