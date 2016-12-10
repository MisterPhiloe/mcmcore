package com.morecommunityminecraft.mcmcore.events;


import com.morecommunityminecraft.mcmcore.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        Player player = e.getPlayer();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Main.getInstance().getMySQL().insertString("players", new String[]{"uuid", "name", "playTime", "dateJoined"}, new String[]{player.getUniqueId().toString(), player.getName(), "0", timeStamp}, null);
    }
}
