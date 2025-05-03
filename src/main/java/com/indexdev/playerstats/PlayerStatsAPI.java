package com.indexdev.playerstats;

import com.indexdev.playerstats.util.SingletonTrait;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerStatsAPI extends JavaPlugin implements SingletonTrait<PlayerStatsAPI> {

    private static PlayerStatsAPI instance;
    private PlayerStatsManager statsManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        statsManager = new PlayerStatsManager(this);
        getServer().getPluginManager().registerEvents(new JoinQuitListener(statsManager), this);
        getCommand("playerstats").setExecutor(new com.indexdev.playerstats.commands.StatsCommand());
        getLogger().info("PlayerStatsAPI habilitado correctamente.");
    }

    @Override
    public PlayerStatsAPI getInstance() {
        return instance;
    }

    public PlayerStatsManager getStatsManager() {
        return statsManager;
    }
}
