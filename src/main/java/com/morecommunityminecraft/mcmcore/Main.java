package com.morecommunityminecraft.mcmcore;

import com.morecommunityminecraft.mcmcore.commands.Commands;
import com.morecommunityminecraft.mcmcore.database.MySQL;
import com.morecommunityminecraft.mcmcore.events.JoinEvent;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private final Logger log = Logger.getLogger("Minecraft");
    private static Main main;
    private WorldGuardPlugin worldGuardPlugin;
    private Economy econ = null;
    private Permission perms = null;
    private Chat chat = null;

    private String[] dbKeys = new String[5];
    private MySQL sql = null;

    @Override
    public void onEnable() {
        main = this;
        setupConfig();
        setupMySQL();
        log.info(this.getName() + " has been enabled!");
        registerCommands();
        registerEvents();
        //setupWorldGuard();
    }


    @Override
    public void onDisable() {
        main = null;
        log.info(this.getName() + " has been disabled!");
    }

    public static Main getInstance() {
        return main;
    }


    private void setupMySQL() {
        this.sql = new MySQL(dbKeys[0], dbKeys[1], dbKeys[2], dbKeys[3], dbKeys[4]);
        getMySQL().createTable("Players", new String[]{"uuid", "name", "playTime", "dateJoined"}, new String[]{"VARCHAR(36)", "VARCHAR(36)", "FLOAT(8,4)", "DATETIME"}, "PRIMARY KEY (uuid)");
    }

    private void setupWorldGuard() {
        worldGuardPlugin = WGBukkit.getPlugin();
        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
    }

    private void setupConfig() {
        String[] stray = {"hostname", "port", "username", "password", "database"};
        List<String> l = new ArrayList<>();
        File file = new File(getDataFolder(), "database.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        for (int i = 0; i < stray.length; i++) {
            String s = stray[i];
            if (yaml.getString(s) == null || yaml.getString(s).equalsIgnoreCase("")) {
                yaml.set(s, s);
            } else {
                dbKeys[i] = yaml.getString(s);
            }
        }
        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands() {
        getCommand("help").setExecutor(new Commands());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public Permission getPermission() {
        return perms;
    }

    public Economy getEconomy() {
        return econ;
    }

    public Chat getChat() {
        return chat;
    }

    public WorldGuardPlugin getWorldGuard() {
        return this.worldGuardPlugin;
    }

    public Logger getMinecraftLogger() {
        return this.log;
    }

    public MySQL getMySQL() {
        return this.sql;
    }


}
