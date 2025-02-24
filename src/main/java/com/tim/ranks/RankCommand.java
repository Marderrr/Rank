package com.tim.ranks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RankCommand implements CommandExecutor {

    private Ranks ranks;

    public RankCommand(Ranks ranks) {
        this.ranks = ranks;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.isOp()) {
                if (args.length == 2) {
                    if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                        for (Rank rank : Rank.values()) {
                            if (rank.name().equalsIgnoreCase(args[1])) {
                                ranks.getRankManager().setRank(target.getUniqueId(), rank, false);

                                player.sendMessage(ranks.serverPrefix + ChatColor.GREEN + "Du hast den Rang von " + target.getName() + " auf " + rank.getDisplay() + ChatColor.GREEN + " gesetzt!");

                                if (target.isOnline()) {
                                    target.getPlayer().sendMessage(ranks.serverPrefix + ChatColor.GREEN + "Dein Rang wurde auf " + rank.getDisplay() + ChatColor.GREEN + " gesetzt!");

                                }

                                return false;

                            } else {
                                player.sendMessage(ranks.serverPrefix + ChatColor.RED + "Kein valider Rang!");
                            }
                        }

                    } else {
                        player.sendMessage(ranks.serverPrefix + ChatColor.RED + "Dieser Spieler war noch nie auf dem Server!");
                    }

                } else {
                    player.sendMessage(ranks.serverPrefix + ChatColor.RED + "Bitte benutze /rank <player> <rank>");
                }

            } else {
                player.sendMessage(ranks.serverPrefix + ChatColor.RED + "Du hast keine Rechte dazu!");
            }

        }

        return false;

    }

}
