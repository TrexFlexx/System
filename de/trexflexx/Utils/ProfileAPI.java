package de.trexflexx.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ProfileAPI {

    public static File file = new File("plugins/CitybuildSystem/Data", "Geschlecht.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);


    public static void setDivers(UUID uuid, boolean status) {
        cfg.set(uuid + ".divers", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setMen(UUID uuid, boolean status) {
        cfg.set(uuid + ".men", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setWomen(UUID uuid, boolean status) {
        cfg.set(uuid + ".women", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static boolean isWomen(UUID uuid) {
        return cfg.getBoolean( uuid + ".women");
    }
    public static boolean isMen(UUID uuid) {
        return cfg.getBoolean( uuid + ".men");
    }
    public static boolean isDivers(UUID uuid) {
        return cfg.getBoolean( uuid + ".divers");
    }
}
