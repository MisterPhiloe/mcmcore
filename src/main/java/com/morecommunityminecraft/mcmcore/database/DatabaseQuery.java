package com.morecommunityminecraft.mcmcore.database;

import com.morecommunityminecraft.mcmcore.Main;

import java.sql.Connection;

public class DatabaseQuery {

    private Connection connection;

    public DatabaseQuery() {
        connection = Main.getInstance().getConnection();
    }

    public Connection getConnection() {
        return this.connection;
    }




}
