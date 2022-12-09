package com.github.xrozl.game.oneshotgg;

import org.bukkit.plugin.java.JavaPlugin;

public final class OneShotGG extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new OneShotListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
