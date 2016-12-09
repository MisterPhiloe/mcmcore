package com.morecommunityminecraft.mcmcore.events;


import com.morecommunityminecraft.mcmcore.database.Queries.PlayerQuery;
import com.morecommunityminecraft.mcmcore.database.Queries.ServerQuery;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class JoinEvent implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.setJoinMessage("");
        Player player = e.getPlayer();
        try {
            new PlayerQuery().addPlayer(player);
            player.sendMessage(new ServerQuery().getMessage(ServerQuery.MessageType.PLAYERMESSAGE));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
