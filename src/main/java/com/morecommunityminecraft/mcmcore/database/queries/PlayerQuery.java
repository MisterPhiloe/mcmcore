package com.morecommunityminecraft.mcmcore.database.queries;

import com.morecommunityminecraft.mcmcore.Main;
import com.morecommunityminecraft.mcmcore.database.MySQL;
import org.bukkit.entity.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlayerQuery {

    private MySQL sql;

    public PlayerQuery(MySQL mySQL) {
        this.sql = mySQL;
    }

    public MySQL getMySQL() {
        return sql;
    }

    public void registerPlayer(Player player) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        getMySQL().insertObject("Players", new String[]{"uuid", "name", "playTime", "dateJoined"},
                new String[]{player.getUniqueId().toString(), player.getName(), "0", timeStamp}, null);
        getMySQL().insertObject("Timer", new String[]{"uuid", "joined"},
                new String[]{player.getUniqueId().toString(), timeStamp}, null);
    }

    public void addPlayTime(Player player) {
        String uuid = player.getUniqueId().toString();
        String[] v = Main.getInstance().getMySQL().getObject("Timer", new String[]{"joined", "left"}, new String[]{"uuid",uuid});
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long d1= 0;
        long d2= 0;
        try {
            d1=format.parse(v[0]).getTime();
            d2 = format.parse(v[1]).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        float hours = Math.abs((d1-d2)/(1000*60*60));
        Main.getInstance().getMySQL().updateObject("Players", new String[]{"playTime"}, new String[]{"playtime + " + hours}, new String[]{"uuid", uuid});
    }

    public void removePlayTimer(Player player){
        String uuid = player.getUniqueId().toString();
        Main.getInstance().getMySQL().removeObject("Timer", new String[]{"uuid"}, new String[]{uuid});
    }
}
