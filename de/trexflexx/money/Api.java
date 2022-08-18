package de.trexflexx.money;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Api {



    public static File file = new File("plugins/CitybuildSystem/Data", "money.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);



    public static int getMoney(UUID uuid){
        int money = cfg.getInt(uuid + ".money");
        return money;
    }

    public static void removeMoney(UUID uuid, int amount){

        int money = cfg.getInt(uuid + ".money");
        money = money-amount;
        cfg.set(uuid + ".money", money);
        try {
            cfg.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void addMoney(UUID uuid, int amount){

        int money = cfg.getInt(uuid + ".money");
        money = money+amount;
        cfg.set(uuid + ".money", money);
        try {
            cfg.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void setMoney(UUID uuid, int amount){

        cfg.set(uuid + ".money", amount);
        try {
            cfg.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static boolean hasEnough(UUID uuid, int amount){

        int money = cfg.getInt(uuid + ".money");
        if(money >= amount){
            return true;
        } else {
            return false;
        }
    }
}
