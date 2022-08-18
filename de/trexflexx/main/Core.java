package de.trexflexx.main;

public class Core {

    public static Core core;
    private Main main;
    private Manager manager;

    public Core(Main main) {
        core = this;
        this.main = main;
        this.manager = new Manager();
    }

    public void enableCore() {
        manager.registerListeners();
        manager.registerCommands();
    }


    public Main getMain() {
        return main;
    }

    public Manager getManager() {
        return manager;
    }

    public static Core getCore() {
        return core;
    }

    public static void setCore(Core core) {
        Core.core = core;
    }
}