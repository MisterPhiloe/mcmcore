package com.morecommunityminecraft.mcmcore.database;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

    private HikariDataSource ds = null;

    public MySQL(String host, String port, String username, String password, String database) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        this.ds = new HikariDataSource(config);
    }

    private Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (getConnection() != null)
                getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createTable(String tableName, String[] cN, String[] cT, @Nullable String addition) {
        if (cN.length != cT.length) return;
        PreparedStatement ps = null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cN.length; i++) {
            sb.append(cN[i]).append(" " + cT[i]);
            if(i+1 < cN.length)
                sb.append(", ");
        }
        String query = "CREATE TABLE IF NOT EXISTS" + tableName + "(" + sb.toString() + ") " + addition;
        try {
            if(getConnection() != null) {
                ps = getConnection().prepareStatement(query);
                ps.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ps != null)
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertString(String tableName, String[] fields, String[] values, @Nullable String addition){
        if(fields.length != values.length) return;
        PreparedStatement ps = null;
        StringBuilder sb = new StringBuilder();

        String query = "INSERT IGNORE INTO" + tableName + "";
    }
}

