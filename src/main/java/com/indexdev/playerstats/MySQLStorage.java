package com.indexdev.playerstats;

import java.sql.*;
import java.util.*;

public class MySQLStorage {

    private final Connection connection;

    public MySQLStorage(PlayerStatsAPI plugin) throws SQLException {
        var cfg = plugin.getConfig();
        String url = "jdbc:mysql://" + cfg.getString("storage.mysql.host") + ":" +
                     cfg.getInt("storage.mysql.port") + "/" +
                     cfg.getString("storage.mysql.database") + "?useSSL=false";

        connection = DriverManager.getConnection(url, cfg.getString("storage.mysql.user"), cfg.getString("storage.mysql.password"));

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS player_stats (
                    uuid VARCHAR(36),
                    stat VARCHAR(64),
                    value INT,
                    PRIMARY KEY(uuid, stat)
                );
            """);
        }
    }

    public Map<String, Integer> loadStats(UUID uuid) throws SQLException {
        Map<String, Integer> stats = new HashMap<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT stat, value FROM player_stats WHERE uuid = ?");
        stmt.setString(1, uuid.toString());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            stats.put(rs.getString("stat"), rs.getInt("value"));
        }
        return stats;
    }

    public void saveStats(UUID uuid, Map<String, Integer> stats) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
            "REPLACE INTO player_stats (uuid, stat, value) VALUES (?, ?, ?)");
        for (var entry : stats.entrySet()) {
            stmt.setString(1, uuid.toString());
            stmt.setString(2, entry.getKey());
            stmt.setInt(3, entry.getValue());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }

    public void close() throws SQLException {
        connection.close();
    }
}
