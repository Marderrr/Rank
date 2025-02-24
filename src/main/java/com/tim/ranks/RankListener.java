package com.tim.ranks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

public class RankListener implements Listener {

    private Ranks ranks;

    public RankListener (Ranks ranks) {
        this.ranks = ranks;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (!player.hasPlayedBefore()) {
            ranks.getRankManager().setRank(player.getUniqueId(), Rank.PLAYER, true);
        }

        ranks.getNametagManager().setNametags(player);
        ranks.getNametagManager().newTag(player);

        PermissionAttachment attachment;

        if (ranks.getRankManager().getPerms().containsKey(player.getUniqueId())) {
            attachment = ranks.getRankManager().getPerms().get(player.getUniqueId());

        } else {
            attachment = player.addAttachment(ranks);
            ranks.getRankManager().getPerms().put(player.getUniqueId(), attachment);
        }

        for (String perm : ranks.getRankManager().getRank(player.getUniqueId()).getPermissions()) {
            attachment.setPermission(perm, true);
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        ranks.getNametagManager().removeTag(e.getPlayer());

    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);

        Player player = e.getPlayer();

        Bukkit.broadcastMessage(ranks.getRankManager().getRank(player.getUniqueId()).getDisplay() + " " + player.getName() + " " + ChatColor.GRAY +  e.getMessage());

    }

}
