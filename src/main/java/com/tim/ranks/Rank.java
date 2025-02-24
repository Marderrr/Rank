package com.tim.ranks;

import org.bukkit.ChatColor;

public enum Rank {

    OWNER(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "OWNER ", new String[]{}, 'A'),
    ADMIN(ChatColor.RED.toString() + ChatColor.BOLD + "ADMIN ", new String[]{}, 'B'),
    MOD(ChatColor.DARK_BLUE.toString() + ChatColor.BOLD + "MOD ", new String[]{}, 'C'),
    DEV(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "DEV ", new String[]{}, 'D'),
    BUILDER(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "BUILDER ", new String[]{}, 'E'),
    VIP(ChatColor.GOLD.toString() + ChatColor.BOLD + "VIP ", new String[]{}, 'F'),
    PLAYER("", new String[]{}, 'G');

    private String display;
    private String[] permissions;
    private char displayOrder;

    Rank(String display, String[] permissions, char displayOrder) {
        this.display = display;
        this.permissions = permissions;
        this.displayOrder = displayOrder;
    }

    public String getDisplay() {return display;}
    public String[] getPermissions(){return permissions;}
    public char getDisplayOrder() {return displayOrder;}

}
