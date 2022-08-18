package de.trexflexx.main;


import de.trexflexx.Utils.TabCompleteri;
import de.trexflexx.Utils.TabCompletorEnchant;
import de.trexflexx.anderes.home.delhome;
import de.trexflexx.anderes.home.home;
import de.trexflexx.anderes.home.sethome;
import de.trexflexx.anderes.tp.tpa;
import de.trexflexx.anderes.tp.tpaccept;
import de.trexflexx.anderes.tp.tpdeny;
import de.trexflexx.bann.BanCMD;
import de.trexflexx.bann.UnMuteCMD;
import de.trexflexx.bann.UnbanCMD;
import de.trexflexx.commands.*;
import de.trexflexx.listener.*;

import de.trexflexx.money.Bank.BankCMD;
import de.trexflexx.money.Bank.BankTabCompletor;
import de.trexflexx.money.EcoTabCompleter;
import de.trexflexx.money.MoneyCMD;
import de.trexflexx.money.eco;
import de.trexflexx.money.pay;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Manager {

    private Main main;
    private PluginManager pluginManager;

    public Manager() {
        main = Core.getCore().getMain();
        pluginManager = Bukkit.getPluginManager();
    }

    public void registerCommands() {
        main.getCommand("bc").setExecutor(new bc_cmd());
        main.getCommand("belohnung").setExecutor(new belohnung_cmd());
        main.getCommand("cc").setExecutor(new cc_cmd());
        main.getCommand("craft").setExecutor(new craft_cmd());
        main.getCommand("ci").setExecutor(new ci_cmd());
        main.getCommand("day").setExecutor(new day_cmd());
        main.getCommand("ec").setExecutor(new ec_cmd());
        main.getCommand("fly").setExecutor(new fly_cmd());
        main.getCommand("globalmute").setExecutor(new Globalmute_cmd());
        main.getCommand("gm").setExecutor(new gm_cmd());
        main.getCommand("heal").setExecutor(new heal_cmd());
        main.getCommand("invsee").setExecutor(new invsee_cmd());
        main.getCommand("kopf").setExecutor(new kopf_cmd());
        main.getCommand("msg").setExecutor(new msg_cmd());
        main.getCommand("night").setExecutor(new night_cmd());
        main.getCommand("repair").setExecutor(new repair_cmd());
        main.getCommand("tpo").setExecutor(new tpo_cmd());
        main.getCommand("tpohere").setExecutor(new tpohere_cmd());
        main.getCommand("v").setExecutor(new VanishCMD());
        main.getCommand("vanish").setExecutor(new VanishCMD());
        main.getCommand("ConfigReload").setExecutor(new reloadSys());
        main.getCommand("Live").setExecutor(new live());
        main.getCommand("hat").setExecutor(new hat());
        main.getCommand("sign").setExecutor(new sign());
        main.getCommand("speed").setExecutor(new speed());
        main.getCommand("tc").setExecutor(new teamchat());
        main.getCommand("teamchat").setExecutor(new teamchat());
        main.getCommand("kick").setExecutor(new kick());
        main.getCommand("setspawn").setExecutor(new setspawn());
        main.getCommand("spawn").setExecutor(new SpawnCommand());
        main.getCommand("eco").setExecutor(new eco());
        main.getCommand("pay").setExecutor(new pay());
        main.getCommand("eco").setTabCompleter(new EcoTabCompleter());
        main.getCommand("money").setExecutor(new MoneyCMD());
        main.getCommand("enchant").setExecutor(new Enchanzt());
        main.getCommand("enchant").setTabCompleter(new TabCompletorEnchant());
        main.getCommand("i").setExecutor(new i());
        main.getCommand("i").setTabCompleter(new TabCompleteri());
        main.getCommand("bank").setExecutor(new BankCMD());
        main.getCommand("bank").setTabCompleter(new BankTabCompletor());
        main.getCommand("ptime").setExecutor(new Ptime());
        main.getCommand("home").setExecutor(new home());
        main.getCommand("sethome").setExecutor(new sethome());
        main.getCommand("delhome").setExecutor(new delhome());
        main.getCommand("playtime").setExecutor(new playtime());
        main.getCommand("status").setExecutor(new status());
        main.getCommand("tpdeny").setExecutor(new tpdeny());
        main.getCommand("tpaccept").setExecutor(new tpaccept());
        main.getCommand("tpa").setExecutor(new tpa());
        main.getCommand("perks").setExecutor(new perks());
        main.getCommand("unmute").setExecutor(new UnMuteCMD());
        main.getCommand("unban").setExecutor(new UnbanCMD());
        main.getCommand("ban").setExecutor(new BanCMD());
        main.getCommand("say").setExecutor(new ConsoleSay());
        main.getCommand("r").setExecutor(new r());
        main.getCommand("profile").setExecutor(new profilw());
        main.getCommand("socialspy").setExecutor(new socialspy());
    }

    public void registerListeners() {
        pluginManager.registerEvents(new Chat(), main);
        pluginManager.registerEvents(new anderes(), main);
        pluginManager.registerEvents(new Join(), main);
        pluginManager.registerEvents(new onQuit(), main);
        pluginManager.registerEvents(new ClickListener(), main);
        pluginManager.registerEvents(new die(), main);
        pluginManager.registerEvents(new AutoSmelt(), main);
        pluginManager.registerEvents(new PerkListener(), main);
    }
}