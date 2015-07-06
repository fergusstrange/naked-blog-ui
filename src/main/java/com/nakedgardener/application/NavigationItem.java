package com.nakedgardener.application;

public class NavigationItem {

    private final String href;
    private final String name;
    private final boolean active;

    public NavigationItem(String href, String name, boolean active) {
        this.href = href;
        this.name = name;
        this.active = active;
    }

    public String getHref() {
        return href;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }
}
