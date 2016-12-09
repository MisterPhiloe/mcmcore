package com.morecommunityminecraft.mcmcore.database.Queries;

import com.morecommunityminecraft.mcmcore.database.DatabaseQuery;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerQuery extends DatabaseQuery {

    public PlayerQuery(){
        super();
    }

    public void addPlayer(Player player) throws SQLException {
        String query = "INSERT IGNORE INTO players (players.id, players.name) VALUES (?, ?) ";
        PreparedStatement prepStatement = getConnection().prepareStatement(query);
        prepStatement.setString(1, player.getUniqueId().toString());
        prepStatement.setString(2, player.getName());
        prepStatement.executeUpdate();
        prepStatement.close();
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
        rs.close();
        prepStatement.close();
        return hours;
    }

    public void addTimePlayed(Player player, float hours) throws SQLException {
        String query = "UPDATE players SET timeplayed = timeplayed + ? WHERE players.id = ?";
        PreparedStatement prepStatement = getConnection().prepareStatement(query);
        prepStatement.setFloat(1, hours);
        prepStatement.setString(2, player.getUniqueId().toString());
        prepStatement.executeUpdate();
        prepStatement.close();
    }

}
