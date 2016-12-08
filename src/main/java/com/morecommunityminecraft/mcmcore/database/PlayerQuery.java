package com.morecommunityminecraft.mcmcore.database;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerQuery extends DatabaseQuery{

    public PlayerQuery(){
        super();
    }

    public float getTimePlayed(Player player) throws SQLException {
        String query = "SELECT timeplayed FROM players WHERE players.id = ?";
        PreparedStatement prepStatement = getConnection().prepareStatement(query);
        prepStatement.setString(1, player.getUniqueId().toString());
        ResultSet rs = prepStatement.executeQuery();
        float hours = 0f;
        while(rs.next()){
            hours = rs.getFloat("timeplayed");
        }
        return hours;
    }

    public void addTimePlayed(Player player, float hours) throws SQLException {
        String query = "UPDATE players SET timeplayed = timeplayed + ? WHERE players.id = ?";
        PreparedStatement prepStatement = getConnection().prepareStatement(query);
        prepStatement.setFloat(1, hours);
        prepStatement.setString(2, player.getUniqueId().toString());
        prepStatement.executeUpdate();
    }

}
