package de.trexflexx.main;


import java.util.HashMap;
import java.util.UUID;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EnumChatFormat;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_16_R3.Scoreboard;
import net.minecraft.server.v1_16_R3.ScoreboardTeam;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ManagerTab {
    private static ManagerTab instance;

    private Scoreboard scoreboard = new Scoreboard();

    private HashMap<UUID, String> teams = new HashMap<>();

    public void registerTeam(Player player, String prefix, EnumChatFormat color, String suffix, String level) {
        String s = level + player.getUniqueId().toString().substring(1, 6);
        if (this.scoreboard.getTeam(s) != null)
            this.scoreboard.removeTeam(this.scoreboard.getTeam(s));
        ScoreboardTeam team = this.scoreboard.createTeam(s);
        team.setColor(color);
        team.setPrefix((IChatBaseComponent)new ChatComponentText(prefix));
        team.setSuffix((IChatBaseComponent)new ChatComponentText(suffix));
        this.teams.put(player.getUniqueId(), s);
        update();
    }

    public void update() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!this.scoreboard.getTeam(this.teams.get(player.getUniqueId())).getPlayerNameSet().contains(player.getName()))
                this.scoreboard.getTeam(this.teams.get(player.getUniqueId())).getPlayerNameSet().add(player.getName());
            sendPacket((Packet<?>)new PacketPlayOutScoreboardTeam(this.scoreboard.getTeam(this.teams.get(player.getUniqueId())), 1));
            sendPacket((Packet<?>)new PacketPlayOutScoreboardTeam(this.scoreboard.getTeam(this.teams.get(player.getUniqueId())), 0));
        }
    }

    private void sendPacket(Packet<?> packet) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            CraftPlayer player = (CraftPlayer)onlinePlayer;
            (player.getHandle()).playerConnection.sendPacket(packet);
        }
    }

    public static ManagerTab getInstance() {
        if (instance == null)
            instance = new ManagerTab();
        return instance;
    }
}
