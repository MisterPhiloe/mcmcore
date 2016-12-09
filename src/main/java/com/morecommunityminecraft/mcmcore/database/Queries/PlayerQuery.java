package com.morecommunityminecraft.mcmcore.database.Queries;

import com.morecommunityminecraft.mcmcore.database.DatabaseQuery;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class PlayerQuery extends DatabaseQuery {

    public PlayerQuery() {
        super();
    }

    public void addPlayer(Player player) {
        String query = "INSERT IGNORE INTO players (players.id, players.name) VALUES (?, ?) ";
        try {
            setPreparedStatement(getConnection().prepareStatement(query));
            getPreparedStatement().setString(1, player.getUniqueId().toString());
            getPreparedStatement().setString(2, player.getName());
            getPreparedStatement().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getPreparedStatement().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public float getTimePlayed(Player player) {
        float hours = 0f;
        String query = "SELECT timeplayed FROM players WHERE players.id = ?";
        try {
            setPreparedStatement(getConnection().prepareStatement(query));
            getPreparedStatement().setString(1, player.getUniqueId().toString());
            setResultSet(getPreparedStatement().executeQuery());

            while (getResultSet().next()) {
                hours = getResultSet().getFloat("timeplayed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getResultSet().close();
                getPreparedStatement().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return hours;
    }

    public void addTimePlayed(Player player, float hours) {
        String query = "UPDATE players SET timeplayed = timeplayed + ? WHERE players.id = ?";
        try {
            setPreparedStatement(getConnection().prepareStatement(query));
            getPreparedStatement().setFloat(1, hours);
            getPreparedStatement().setString(2, player.getUniqueId().toString());
            getPreparedStatement().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getResultSet().close();
                getPreparedStatement().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
