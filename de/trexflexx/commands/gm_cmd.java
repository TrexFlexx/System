package de.trexflexx.commands;
       
import de.trexflexx.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
       
public class gm_cmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if(args.length == 0){
                sender.sendMessage("§7Benute: §b/gm <0/1/2/3> <Player>");
            } else if(args.length == 1){
                sender.sendMessage("§cGebe einen GameMode an.");
            } else if(args.length == 2){
                Player t = Bukkit.getPlayer(args[1]);

                if(t != null){
                    if(args[0].equalsIgnoreCase("1")){
                        t.setGameMode(GameMode.CREATIVE);
                        t.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDie §dConsole §ahat dich in den GameMode §bKreativ §agesetzt.");
                        sender.sendMessage("§aDu hast §b" + t.getName() + "§a in den GameMode §bKreativ §agesetzt.");
                    } else if(args[0].equalsIgnoreCase("2")){
                        t.setGameMode(GameMode.ADVENTURE);
                        t.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDie §dConsole §ahat dich in den GameMode §Abenteuer §agesetzt.");
                        sender.sendMessage("§aDu hast §b" + t.getName() + "§a in den GameMode §Abenteuer §agesetzt.");
                    }else if(args[0].equalsIgnoreCase("3")){
                        t.setGameMode(GameMode.SPECTATOR);
                        t.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDie §dConsole §ahat dich in den GameMode §bSpectator §agesetzt.");
                        sender.sendMessage("§aDu hast §b" + t.getName() + "§a in den GameMode §bSpectator §agesetzt.");
                    }else if(args[0].equalsIgnoreCase("0")){
                        t.setGameMode(GameMode.SURVIVAL);
                        t.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§aDie §dConsole §ahat dich in den GameMode §bÜberleben §agesetzt.");
                        sender.sendMessage("§aDu hast §b" + t.getName() + "§a in den GameMode §bÜberleben §agesetzt.");
                    }
                } else {
                    sender.sendMessage("§cDieser Spieler ist nicht online.");
                }
            }
        } else {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("gm")) {
                if (p.hasPermission(Main.cfg.getString("Permissions.gm.use"))) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("0")) {
                            if(p.hasPermission(Main.cfg.getString("Permissions.gm.surival"))) {
                                p.setGameMode(GameMode.SURVIVAL);
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist nun im Spielmodus§a » " + p.getGameMode());
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDiesen §eSpielModus §cdarft du nicht nutzen.");
                            }
                        } else if (args[0].equalsIgnoreCase("1")) {
                            if(p.hasPermission(Main.cfg.getString("Permissions.gm.creative"))) {
                                p.setGameMode(GameMode.CREATIVE);
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist nun im Spielmodus§a » " + p.getGameMode());
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDiesen §eSpielModus §cdarft du nicht nutzen.");
                            }
                        } else if (args[0].equalsIgnoreCase("2")) {
                            if(p.hasPermission(Main.cfg.getString("Permissions.gm.adventure"))) {
                                p.setGameMode(GameMode.ADVENTURE);
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist nun im Spielmodus§a » " + p.getGameMode());
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDiesen §eSpielModus §cdarft du nicht nutzen.");
                            }
                        } else if (args[0].equalsIgnoreCase("3")) {
                            if(p.hasPermission(Main.cfg.getString("Permissions.gm.spectator"))) {
                                p.setGameMode(GameMode.SPECTATOR);
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist nun im Spielmodus§a » " + p.getGameMode());
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDiesen §eSpielModus §cdarft du nicht nutzen.");
                            }
                        } else {
                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §e/gm <0, 1, 2, 3> §f| §e/gm <0, 1, 2, 3> <Spielername>");
                        }
                    } else if (args.length == 0 || args.length == 3) {
                        p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: §e/gm <0, 1, 2, 3> §f| §e/gm <0, 1, 2, 3> <Spielername>");
                    }
                    if (args.length == 2) {
                        try {
                            Player a = Bukkit.getPlayer(args[1]);
                            if(p.hasPermission("Permissions.gm.others")) {
                                if (args[0].equalsIgnoreCase("0")) {
                                    a.setGameMode(GameMode.SURVIVAL);
                                    a.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist nun im Spielmodus§a ➢ Überleben");
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast §a••● " + a.getName() + " §fin den Spielmodus §a» Überleben§f gesetzt!");
                                } else if (args[0].equalsIgnoreCase("1")) {
                                    a.setGameMode(GameMode.CREATIVE);
                                    a.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist nun im Spielmodus§a ➢ Kreative");
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast §a••● " + a.getName() + " §fin den Spielmodus §a» Kreative§f gesetzt!");
                                } else if (args[0].equalsIgnoreCase("2")) {
                                    a.setGameMode(GameMode.ADVENTURE);
                                    a.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist nun im Spielmodus§a ➢ Abenteuer");
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast §a••● " + a.getName() + " §fin den Spielmodus §a» Abenteuer§f gesetzt!");
                                } else if (args[0].equalsIgnoreCase("3")) {
                                    a.setGameMode(GameMode.SPECTATOR);
                                    a.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du bist nun im Spielmodus§a ➢ SPECTATOR");
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du hast §a••● " + a.getName() + " §fin den Spielmodus §a» Spectator§f gesetzt!");
                                } else {
                                    p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Benutze: /gm <0, 1, 2, 3> | gm <0, 1, 2, 3> <Spielername>");
                                }
                            } else {
                                p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§cDu darfst keinen anderen Spielern in ein §eSpielModus §csetzten.");
                            }

                        } catch (NullPointerException d) {
                            p.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Dieser Spieler ist nicht online!");
                        }

                    }
                } else {

                    p.sendMessage(Main.cfg.getString("Nachricht.noPerms"));
                }
            } else {
                sender.sendMessage(Main.cfg.getString("Nachricht.prefix") + "§7Du musst ein Spieler sein um dies tuhen zu können!");
            }
        }
         return false;
         }
       }
