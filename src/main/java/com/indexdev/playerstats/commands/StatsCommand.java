package com.indexdev.playerstats.commands;

import com.indexdev.playerstats.PlayerStatsAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Este comando solo puede ser usado por jugadores.");
            return true;
        }

        var stats = PlayerStatsAPI.getInstance().getStatsManager().getStats(player.getUniqueId());
        player.sendMessage(ChatColor.GREEN + "Tus estadísticas:");
        stats.forEach((k, v) -> player.sendMessage(ChatColor.YELLOW + k + ": " + ChatColor.AQUA + v));
        return true;
    }
}
