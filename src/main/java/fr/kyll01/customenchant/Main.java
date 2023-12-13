package fr.kyll01.customenchant;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(), this);
        getCommand("ce").setExecutor(new Commands());
        getCommand("celist").setExecutor(new Commands());
        getCommand("cegive").setExecutor(new Commands());
        Utils.initEnchants();
        getLogger().info("Plugin démarré");

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin éteint");
    }

}


