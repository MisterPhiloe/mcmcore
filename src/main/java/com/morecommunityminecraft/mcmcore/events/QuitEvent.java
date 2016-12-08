package com.morecommunityminecraft.mcmcore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e ){
        e.setQuitMessage("");
    }
}
