package com.morecommunityminecraft.mcmcore;

import com.morecommunityminecraft.mcmcore.Database.DatabaseConnection;
import com.morecommunityminecraft.mcmcore.commands.Commands;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Main main;
    private Economy econ = null;
    private Permission perms = null;
    private Chat chat = null;
    private Connection connection;

    @Override
    public void onEnable() {
        main = this;
        log.info(this.getName() + " has been enabled!");

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();

        registerCommands();

    }

    @Override
    public void onDisable() {
        main = null;
        log.info(this.getName() + " has been disabled!");
    }

    public static Main getInstance(){
        return main;
    }

    /* Vault setup */

    private void registerCommands(){
        getCommand("help").setExecutor(new Commands());
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

    public Permission getPermission(){
        return perms;
    }

    public Economy getEconomy(){
        return econ;
    }

    public Chat getChat(){
        return chat;
    }

    /* Database.yml setup */
    private void setupDatabase(){
        String[] stray = {"hostname", "port", "username", "password", "database"};
        File file = new File(getDataFolder(), "database.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        for (String s : stray) {
            if (yaml.getString(s) == null || yaml.getString(s).equalsIgnoreCase("")) {
                yaml.set(s, s);
            }
        }
        connection = new DatabaseConnection(
                yaml.getString(stray[0]),
                yaml.getString(stray[1]),
                yaml.getString(stray[2]),
                yaml.getString(stray[3]),
                yaml.getString(stray[4])).getConnection();
    }

    public Connection getConnection() {
        if(connection == null) {
            setupDatabase();
        }
        return connection;
    }
}
