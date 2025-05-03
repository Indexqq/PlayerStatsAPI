package com.indexdev.playerstats;

import org.bukkit.plugin.java.JavaPlugin;

public class PlayerStatsAPI extends JavaPlugin {

    private static PlayerStatsAPI instance;
    private PlayerStatsManager statsManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        this.statsManager = new PlayerStatsManager(this);
        getServer().getPluginManager().registerEvents(new JoinQuitListener(statsManager), this);
        getCommand("playerstats").setExecutor(new com.indexdev.playerstats.commands.StatsCommand());
        getLogger().info("PlayerStatsAPI habilitado correctamente.");
    }

    public static PlayerStatsAPI getInstance() {
        return instance;
    }

    public PlayerStatsManager getStatsManager() {
        return statsManager;
    }
}
