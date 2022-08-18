package de.trexflexx.Utils;

import de.trexflexx.main.Main;
import org.bukkit.entity.Player;


public class TabManager {
    public void setTablist(Player player) {
        player.setPlayerListHeader("§8[ §e••● "+ Main.cfg.getString("ServerName") + "§2•§bNetzwerk §e●•• §8]");
        player.setPlayerListFooter("§8Website: §7"+ Main.cfg.getString("Website") + "\n" + "§8[ §e••● "+ Main.cfg.getString("ServerName") + "§2•§bNetzwerk §e●•• §8]");
    }
}
