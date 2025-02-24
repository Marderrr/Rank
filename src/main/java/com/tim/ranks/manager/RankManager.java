package com.tim.ranks.manager;

import com.tim.ranks.Rank;
import com.tim.ranks.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private Ranks ranks;

    private File file;
    private YamlConfiguration config;

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public RankManager(Ranks ranks) {
        this.ranks = ranks;

        if (!ranks.getDataFolder().exists()) {
            ranks.getDataFolder().mkdir();
        }

        file = new File(ranks.getDataFolder(), "ranks.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);

    }

    public void setRank(UUID uuid, Rank rank, boolean firstJoin) {
        if (Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin) {
            Player player = Bukkit.getPlayer(uuid);

            PermissionAttachment attachment;

            if (perms.containsKey(uuid)) {
                attachment = perms.get(uuid);

            } else {
                attachment = player.addAttachment(ranks);
                perms.put(uuid, attachment);
            }

            for (String perm : getRank(uuid).getPermissions()) {
                if (player.hasPermission(perm)) {
                    attachment.unsetPermission(perm);
                }
            }

            for (String perm : rank.getPermissions()) {
                attachment.setPermission(perm, true);
            }

        }

        config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(uuid);

            ranks.getNametagManager().removeTag(player);
            ranks.getNametagManager().newTag(player);

        }

    }

    public Rank getRank(UUID uuid) {
        return Rank.valueOf(config.getString(uuid.toString()));
    }

    public HashMap<UUID, PermissionAttachment> getPerms() {return  perms;}
}
