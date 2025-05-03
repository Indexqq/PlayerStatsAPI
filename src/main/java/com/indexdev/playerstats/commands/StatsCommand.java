package com.indexdev.playerstats.commands;

import com.indexdev.playerstats.PlayerStatsAPI;
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

        int kills = PlayerStatsAPI.getInstance().getStatsManager().getKills(player.getUniqueId());
        player.sendMessage("§aTienes §e" + kills + "§a kills.");
        return true;
    }
}
