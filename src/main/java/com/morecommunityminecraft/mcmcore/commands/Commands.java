package com.morecommunityminecraft.mcmcore.commands;


import com.morecommunityminecraft.mcmcore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("help")) {
            if (Main.getInstance().getPermission().has(player, "mcmcommand.help") || player.isOp()) {
                switch (args.length) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        }

        if (label.equalsIgnoreCase("mcmadmin")) {
            if (Main.getInstance().getPermission().has(player, "") || player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage("Invalid input! Please specify your argument(s).");
                } else {
                    switch (args[0]) {
                        case "help":
                            player.sendMessage("Help dialog... TODO");
                            break;
                        case "database":
                            switch (args[1]) {
                                case "createTable":
                                    for (int i = 0; i < 4; i++) {
                                        if (args[i] != null && args[i].equalsIgnoreCase("")) {

                                        } else {
                                            player.sendMessage("Invalid arguments for " + args[1]);
                                            return false;
                                        }
                                    }
                                    break;
                                default:
                                    player.sendMessage("Database admin command dialog... TODO");
                                    break;
                            }
                            break;
                        default:
                            player.sendMessage("Invalid argument!");
                            break;
                    }
                }
            }
        }
        return false;
    }
}
