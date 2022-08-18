package de.trexflexx.money.Bank;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BankAPI {



    public static File file = new File("plugins/CitybuildSystem/Data", "bank.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);


    public static Integer getMoney(UUID uuid){
        int money = cfg.getInt(uuid + ".bank.money");
        return money;
    }

    public static void removeMoney(UUID uuid, int amount){

        int money = cfg.getInt(uuid + ".bank.money");
        money = money-amount;
        cfg.set(uuid + ".bank.money", money);
        try {
            cfg.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void addMoney(UUID uuid, int amount){

        int money = cfg.getInt(uuid + ".bank.money");
        money = money+amount;
        cfg.set(uuid + ".bank.money", money);
        try {
            cfg.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void setMoney(UUID uuid, int amount){

        cfg.set(uuid + ".bank.money", amount);
        try {
            cfg.save(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void updateBankFreeze(UUID uuid, boolean status) {
        cfg.set(uuid + ".bank.frozen", Boolean.valueOf(status));
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean hasEnough(UUID uuid, int amount){

        int money = cfg.getInt(uuid + ".bank.money");
        if(money >= amount){
            return true;
        } else {
            return false;
        }
    }
    public static boolean isBankAccountFrozen(UUID uuid) {
        return cfg.getBoolean( uuid + ".bank.frozen");
    }
}
