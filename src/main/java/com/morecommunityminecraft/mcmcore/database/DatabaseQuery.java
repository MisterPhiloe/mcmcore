package com.morecommunityminecraft.mcmcore.database;

import com.morecommunityminecraft.mcmcore.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseQuery {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public DatabaseQuery() {
        connection = Main.getInstance().getConnection();
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setPreparedStatement(PreparedStatement ps) {
        this.preparedStatement = ps;
    }

    public PreparedStatement getPreparedStatement() {
        if (this.preparedStatement == null) {
            return null;
        }
        return this.preparedStatement;
    }

    public ResultSet getResultSet() {
        if (this.resultSet == null) {
            return null;
        }
        return this.resultSet;
    }

    public void setResultSet(ResultSet rs) {
        this.resultSet = rs;
    }


}
