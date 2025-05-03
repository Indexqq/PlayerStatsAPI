package com.indexdev.playerstats;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;

public class JoinQuitListener implements Listener {

    private final PlayerStatsManager manager;

    public JoinQuitListener(PlayerStatsManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        manager.loadStats(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        manager.saveStats(event.getPlayer());
    }
}
