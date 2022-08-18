package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class r implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein.");
        } else {
            Player p = (Player) sender;
            if (!msg_cmd.lastmsg.containsKey(p.getUniqueId())) {
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cNiemand hat dir geschrieben.");
                return true;
            }
            Player target = Bukkit.getPlayer(msg_cmd.lastmsg.get(p.getUniqueId()));
            if (target == null) {
                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDer Spieler mit dem du geschrieben hattest, ist nun offline.");
                msg_cmd.lastmsg.remove(target);
                return true;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i] + " ");
            }

            String msg = sb.toString();
            p.sendMessage("§6[§cmir§6 -> §c" + target.getPlayerListName() + "§6]§f " + msg);
            target.sendMessage("§6[§c" + p.getPlayerListName() + "§6 -> §cmir§6]§f " + msg);
            for(Player all : Bukkit.getOnlinePlayers()) {
                if (socialspy.socialspy.contains(all)) {
                    all.sendMessage("§dSocialSpy §7» §6[§c" + p.getPlayerListName() + "§6 -> §c"+ target.getName() +"§6]§f " + msg);
                }
            }

            msg_cmd.lastmsg.put(target.getUniqueId(), p.getUniqueId());


        }
        return false;
    }
}
