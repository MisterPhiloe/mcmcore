package com.morecommunityminecraft.mcmcore;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger log = getLogger();
        log.info(this.getName() + " has been enabled!");

    }

    @Override
    public void onDisable() {
        Logger log = getLogger();
        log.info(this.getName() + " has been disabled!");
    }

    private void registerCommands(){

    }
}
