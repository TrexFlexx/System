package de.trexflexx.commands;

import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class speed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDu musst ein Spieler sein!");
        } else {

        Player p = (Player) sender;


            if (p.hasPermission(Main.cfg.getString("Permissions.speed"))) {
                if (args.length == 0) {
                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §b/speed <1-10>");

                } else {
                    if (args.length == 1) {
                        double speed = Double.parseDouble(args[0]);
                        if (speed >= 0 && speed <= 10) {
                            speed = speed / 10;

                            if (p.isFlying()) {
                                p.setFlySpeed((float) speed);
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast dein Fly-Speed erfolgreich geändert.");
                            } else {
                                if (p.isOnGround()) {
                                    p.setWalkSpeed((float) speed);
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast dein Walk-Speed erfolgreich geändert.");
                                }
                            }
                        }
                    } else {
                        if (args.length == 2) {
                            double speed2 = Double.parseDouble(args[0]);
                            if (speed2 >= 0 && speed2 <= 10) {
                                speed2 = speed2 / 10;

                                Player target = Bukkit.getPlayer(args[1]);
                                if (args[1].equalsIgnoreCase(target.getName())) {
                                    if (target.isFlying()) {
                                        target.setFlySpeed((float) speed2);
                                        target.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDein Fly-Speed wurde versetzt.");
                                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast das Fly-Speed von §b" + target.getName() + "§a geändert.");
                                    } else {
                                        if (target.isOnGround()) {
                                            target.setWalkSpeed((float) speed2);
                                            target.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDein Walk-Speed wurde versetzt.");
                                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDu hast das Walk-Speed von §b" + target.getName() + "§a geändert.");
                                        }
                                    }
                                }
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze:§b /speed 1-10");
                            }
                        }
                    }
                }
            }  else {
                p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
            }
        }
        return false;
    }
}
