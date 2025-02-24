package com.tim.ranks;

import com.tim.ranks.manager.NametagManager;
import com.tim.ranks.manager.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ranks extends JavaPlugin {

    public String serverPrefix = ChatColor.GOLD.toString() + ChatColor.BOLD + "[DC] ";

    private RankManager rankManager;
    private NametagManager nametagManager;

    @Override
    public void onEnable() {
        getCommand("rank").setExecutor(new RankCommand(this));

        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);

        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);

    }

    public RankManager getRankManager() {return rankManager;}
    public NametagManager getNametagManager() {return nametagManager;}

    @Override
    public void onDisable() {

    }
}
