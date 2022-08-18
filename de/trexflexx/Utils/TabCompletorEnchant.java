package de.trexflexx.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

public class TabCompletorEnchant implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {

            List<String> list = new ArrayList<String>();
            for (Enchantment enchantment : Enchantment.values()) {
                list.add(enchantment.getName());
            }

            return list;
        }

        return null;
    }
}
