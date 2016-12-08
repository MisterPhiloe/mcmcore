package com.morecommunityminecraft.mcmcore.database;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerQuery extends DatabaseQuery{

    public PlayerQuery(){
        super();
    }

    public void getTimePlayed(Player player) throws SQLException {
        String query = "SELECT ";
        PreparedStatement prepStatement = getConnection().prepareStatement(query);
        ResultSet rs = prepStatement.executeQuery();
        while(rs.next()){

        }
    }

}
