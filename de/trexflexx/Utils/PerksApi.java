package de.trexflexx.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PerksApi {

    public static File file = new File("plugins/CitybuildSystem/Data", "perks.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void hunger(UUID uuid, boolean status) {
        cfg.set(uuid + ".hunger", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void nofall(UUID uuid, boolean status) {
        cfg.set(uuid + ".nofall", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void keepHotbar(UUID uuid, boolean status) {
        cfg.set(uuid + ".keepHotbar", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void keepXP(UUID uuid, boolean status) {
        cfg.set(uuid + ".keepXP", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void slowchat(UUID uuid, boolean status) {
        cfg.set(uuid + ".slowChat", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean isKeepHotbar(UUID uuid) {
        return cfg.getBoolean( uuid + ".keepHotbar");
    }
    public static boolean isSlowChat(UUID uuid){
        return cfg.getBoolean(uuid + ".slowChat");
    }
    public static boolean isKeepXP(UUID uuid) {
        return cfg.getBoolean( uuid + ".keepXP");
    }
    public static boolean isNoFall(UUID uuid) {
        return cfg.getBoolean( uuid + ".nofall");
    }
    public static boolean isHunger(UUID uuid) {
        return cfg.getBoolean( uuid + ".hunger");
    }






    public static void night(UUID uuid, boolean status) {
        cfg.set(uuid + ".nightvision", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void speed(UUID uuid, boolean status) {
        cfg.set(uuid + ".speed", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void jumpboost(UUID uuid, boolean status) {
        cfg.set(uuid + ".jumpboost", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setCC(UUID uuid, boolean status) {
        cfg.set(uuid + ".clearchat", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setWater(UUID uuid, boolean status) {
        cfg.set(uuid + ".water", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setStrenght(UUID uuid, boolean status) {
        cfg.set(uuid + ".staerke", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isStrength(UUID uuid) {
        return cfg.getBoolean( uuid + ".staerke");
    }
    public static boolean isWater(UUID uuid) {
        return cfg.getBoolean(uuid + ".water");
    }
    public static boolean isCC(UUID uuid) {
        return cfg.getBoolean( uuid + ".clearchat");
    }
    public static boolean isSpeed(UUID uuid) {
        return cfg.getBoolean( uuid + ".speed");
    }
    public static boolean isJump(UUID uuid) {
        return cfg.getBoolean( uuid + ".jumpboost");
    }
    public static boolean isNight(UUID uuid) {
        return cfg.getBoolean( uuid + ".nightvision");
    }
}
