package com.morecommunityminecraft.mcmcore.events;


import com.morecommunityminecraft.mcmcore.Main;
import com.morecommunityminecraft.mcmcore.database.queries.PlayerQuery;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PlayerQuery query = new PlayerQuery(Main.getInstance().getMySQL());
        Player player = e.getPlayer();
        e.setJoinMessage("");
        query.registerPlayer(player);
       }
}
