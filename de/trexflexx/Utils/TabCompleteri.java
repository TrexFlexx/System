package de.trexflexx.Utils;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompleteri implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {

            List<String> list = new ArrayList<String>();
            for (Material mat : Material.values()) {
                list.add(mat.name());
            }

            return list;
        }

        return null;
    }
}
