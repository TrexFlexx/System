package de.trexflexx.main;


import de.trexflexx.Utils.ConfigManager;
import de.trexflexx.Utils.TabManager;
import de.trexflexx.Utils.rezepte;
import de.trexflexx.anderes.tp.tpa;
import de.trexflexx.commands.SpawnCommand;
import de.trexflexx.commands.setspawn;
import de.trexflexx.listener.scoreboard;

import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class Main extends JavaPlugin{

    private static Main instance;

    public scoreboard sb = new scoreboard();

    private TabManager tabManager;

    public ConfigManager cm;

    public static File file;
    public static FileConfiguration cfg;

    private File customConfigFile;
    private FileConfiguration customConfig;


    @Override
    public void onLoad() {
        Core.setCore(new Core(this));
    }

    public void onEnable() {

        rezepte.MagmaPickaxe();

        instance = this;



        startTimer();

        Core.getCore().enableCore();

        tabManager = new TabManager();


        this.cm = new ConfigManager(this);

        this.cm.registerConfig("spawn", "spawn.yml");
        this.cm.loadAll();
        this.cm.saveAll();
        getConfig().options().copyDefaults(true);
        new SpawnCommand();
        new setspawn();
        getConfig().options().copyDefaults(true);

        createCustomConfig();

        Main.file = new File("plugins/CitybuildSystem", "config.yml");
        Main.cfg = YamlConfiguration.loadConfiguration(Main.file);

        saveDefaultConfig();
        isLicence();
    }




    public void onDisable() {

        this.cm.saveAll();
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage("§cAusgeschaltet!");
        tpa.requests.clear();

    }

    public static Main getInstance() {
        return instance;
    }

    public TabManager getTabManager() {
        return tabManager;
    }
    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "licence.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("licence.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.addDefault("licence-key", "HIER_DEINE_LICENCE");
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public void startTimer() {

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

                for(Player players : Bukkit.getOnlinePlayers()){
                    int hours = getConfig().getInt("Time." + players.getName() + ".hours");
                    int minutes = getConfig().getInt("Time." + players.getName() + ".minutes");

                    minutes++;

                    getConfig().set("Time." + players.getName() + ".minutes", minutes);
                    saveConfig();

                    if(minutes == 60){
                        getConfig().set("Time." + players.getName() + ".minutes", 0);
                        hours++;
                        getConfig().set("Time." + players.getName() + ".hours", hours);
                        saveConfig();
                    }
                }
            }
        }, 20*60 ,20*60);
    }

    public Location getSpawnLocation() {
        ConfigManager.RConfig spawnCfg = this.cm.getConfig("spawn");
        if (spawnCfg.getString("world") == null)
            return ((World)getServer().getWorlds().get(0)).getSpawnLocation();
        Location spawn = new Location(null, 0.0D, 0.0D, 0.0D);
        spawn.setX(spawnCfg.getDouble("x"));
        spawn.setY(spawnCfg.getDouble("y"));
        spawn.setZ(spawnCfg.getDouble("z"));
        spawn.setWorld(getServer().getWorld(spawnCfg.getString("world")));
        spawn.setYaw(spawnCfg.getInt("yaw"));
        spawn.setPitch(spawnCfg.getInt("pitch"));
        return spawn;
    }

    public void setSpawnLocation(String world, double x, double y, double z, float yaw, float pitch) {
        ConfigManager.RConfig spawnCfg = this.cm.getConfig("spawn");
        spawnCfg.set("world", world);
        spawnCfg.set("x", Double.valueOf(x));
        spawnCfg.set("y", Double.valueOf(y));
        spawnCfg.set("z", Double.valueOf(z));
        spawnCfg.set("yaw", Float.valueOf(yaw));
        spawnCfg.set("pitch", Float.valueOf(pitch));
        try {
            spawnCfg.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void isLicence() {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        String key = customConfig.getString("licence-key");
        try{
            String url = "https://lizenz.trexflexx.repl.co/" + key;
            URLConnection openConnection = new URL(url).openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            @SuppressWarnings("resource")
            Scanner scan = new Scanner((new InputStreamReader(openConnection.getInputStream())));
            while(scan.hasNextLine()){
                String firstline = scan.nextLine();
                if(firstline.contains("true")){
                    sender.sendMessage("§7[§dCityBuildSystem§7] §aDeine Lizenz ist gültig. Plugin wurde erfolgreich geladen.");
                    return;
                }
            }
        }catch(Exception e){

        }
        sender.sendMessage("§7[§dCityBuildSystem§7] §c§lERROR: §4Diese Lizenz ist ungueltig. Plugin wurde deaktiviert.");
        Bukkit.getPluginManager().disablePlugin(this);
        return;
    }
}