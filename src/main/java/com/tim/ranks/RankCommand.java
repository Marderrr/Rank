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

                                player.sendMessage(ChatColor.GREEN + "You changed " + target.getName() + "'s rank to " + rank.getDisplay() + ChatColor.GREEN + "!");

                                if (target.isOnline()) {
                                    target.getPlayer().sendMessage(ChatColor.GREEN + "Your rank was set to " + rank.getDisplay() + ChatColor.GREEN + "!");

                                }

                                return false;

                            } else {
                                player.sendMessage(ChatColor.RED + "You did no specify a valid rank!");
                            }
                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "This user has never joined the server before!");
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Please use /rank <player> <rank>");
                }

            } else {
                player.sendMessage(ChatColor.RED + "You must be Operator to use this command!");
            }

        }

        return false;

    }

}
