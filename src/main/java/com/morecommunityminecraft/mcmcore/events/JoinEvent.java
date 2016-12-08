package com.morecommunityminecraft.mcmcore.events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.setJoinMessage("");
        Player player = e.getPlayer();
        player.sendMessage("Welcome to the server " + player.getDisplayName() + "!");
    }
}
