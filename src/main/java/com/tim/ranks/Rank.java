package com.tim.ranks;

import org.bukkit.ChatColor;

public enum Rank {

    OWNER(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "OWNER", new String[]{}),
    ADMIN(ChatColor.RED.toString() + ChatColor.BOLD + "ADMIN", new String[]{}),
    MEMBER(ChatColor.YELLOW.toString() + ChatColor.BOLD + "MEMBER", new String[]{"minecraft.command.seed"}),
    GUEST(ChatColor.GRAY.toString() + ChatColor.BOLD + "GUEST", new String[]{});

    private String display;
    private String[] permissions;
    private char displayOrder;

    Rank(String display, String[] permissions) {
        this.display = display;
        this.permissions = permissions;
        this.displayOrder = displayOrder;
    }

    public String getDisplay() {return display;}
    public String[] getPermissions(){return permissions;}
    public char getDisplayOrder() {return displayOrder;}

}
