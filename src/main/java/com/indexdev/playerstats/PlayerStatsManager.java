package com.indexdev.playerstats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.*;

public class PlayerStatsManager {

    private final Map<UUID, Map<String, Integer>> stats = new HashMap<>();
    private final PlayerStatsAPI plugin;
    private MySQLStorage mysql;

    public PlayerStatsManager(PlayerStatsAPI plugin) {
        this.plugin = plugin;

        if (plugin.getConfig().getBoolean("storage.use-mysql")) {
            try {
                this.mysql = new MySQLStorage(plugin);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    load(player.getUniqueId());
                }
            } catch (SQLException e) {
                plugin.getLogger().severe("No se pudo conectar a MySQL: " + e.getMessage());
            }
        }
    }

    public void load(UUID uuid) {
        if (mysql != null) {
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try {
                    stats.put(uuid, mysql.loadStats(uuid));
                } catch (SQLException e) {
                    plugin.getLogger().severe("No se pudieron cargar estadísticas para " + uuid);
                }
            });
        } else {
            stats.putIfAbsent(uuid, new HashMap<>());
        }
    }

    public void save(UUID uuid) {
        if (!stats.containsKey(uuid)) return;
        if (mysql != null) {
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try {
                    mysql.saveStats(uuid, stats.get(uuid));
                } catch (SQLException e) {
                    plugin.getLogger().severe("No se pudieron guardar estadísticas para " + uuid);
                }
            });
        }
    }

    public int getStat(UUID uuid, String key) {
        return stats.getOrDefault(uuid, new HashMap<>()).getOrDefault(key, 0);
    }

    public void incrementStat(UUID uuid, String key, int amount) {
        stats.computeIfAbsent(uuid, k -> new HashMap<>()).merge(key, amount, Integer::sum);
    }

    public Map<String, Integer> getStats(UUID uuid) {
        return stats.getOrDefault(uuid, new HashMap<>());
    }
}
