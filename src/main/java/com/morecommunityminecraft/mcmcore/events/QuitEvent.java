package com.morecommunityminecraft.mcmcore.events;

import com.morecommunityminecraft.mcmcore.Main;
import com.morecommunityminecraft.mcmcore.database.queries.PlayerQuery;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        PlayerQuery pq = new PlayerQuery(Main.getInstance().getMySQL());
        e.setQuitMessage("");
        pq.addPlayTime(e.getPlayer());
        pq.removePlayTimer(e.getPlayer());
    }
}
