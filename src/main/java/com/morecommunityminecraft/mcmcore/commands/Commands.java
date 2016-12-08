package com.morecommunityminecraft.mcmcore.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("help")) {
            if (player.hasPermission("Some.Permission")) {
                switch (args.length) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        }
        return false;
    }
}