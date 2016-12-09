package com.morecommunityminecraft.mcmcore.database.Queries;

import com.morecommunityminecraft.mcmcore.database.DatabaseQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerQuery extends DatabaseQuery {

    public enum MessageType {
        MOTD, PLAYERMESSAGE
    }

    public ServerQuery() {
        super();
    }

    public String getMessage(MessageType messageType) throws SQLException {
        String query = "SELECT ? FROM server";
        PreparedStatement prepStmt = getConnection().prepareStatement(query);
        prepStmt.setString(1, messageType.name());
        ResultSet rs = prepStmt.executeQuery();
        String message = "";
        while (rs.next()) {
            message = rs.getString(messageType.name());
        }
        rs.close();
        prepStmt.close();
        return message;
    }




}
