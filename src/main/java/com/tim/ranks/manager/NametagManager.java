package com.tim.ranks.manager;

import com.tim.ranks.Rank;
import com.tim.ranks.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    private Ranks ranks;
    public NametagManager(Ranks ranks) {
        this.ranks = ranks;
    }

    public void setNametags(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for (Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.getDisplayOrder() + rank.name());
            team.setPrefix(rank.getDisplay());
        }

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (player.getUniqueId() != target.getUniqueId()) {
                player.getScoreboard().getTeam(ranks.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());

            }

        }

    }

    public void newTag(Player player) {
        Rank rank = ranks.getRankManager().getRank(player.getUniqueId());

        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getTeam(rank.getDisplayOrder() + rank.name()).addEntry(player.getName());
        }

    }

    public void removeTag(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }

    }

}
