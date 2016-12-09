package com.morecommunityminecraft.mcmcore.database;


import com.morecommunityminecraft.mcmcore.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection connection;

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;

    public DatabaseConnection(String hostname, String port, String username, String password, String database) {
        this.host = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
    }

    public void openConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }

            synchronized (this) {
                if (connection != null && !connection.isClosed()) {
                    return;
                }
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Main.getInstance().getLogger().info(Main.getInstance().getName() + "'s Database connection has successfully been closed!");
        }
    }

}
