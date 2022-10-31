package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.fileconfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class afkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            Main.log(Main.NOPERMISSION);
            return true;
        }

        Player player = (Player) sender;
        fileconfig afk = new fileconfig("afk.yml");
        if(!(afk.contains(player.getName()))){
            afk.set(player.getName(), false);
            afk.saveConfig();
        }

        if(!afk.getBoolean(player.getName())){
            player.setPlayerListName("§7[AFK] " + player.getName());
            player.sendMessage(Main.PREFIX + "§7Du bist nun AFK!");

            for(Player all : Bukkit.getOnlinePlayers()){
                if(all != player) all.sendMessage(Main.PREFIX + "§7 " + player.getName() + " ist nun AFK!");
            }
            afk.set(player.getName(), true);
            afk.saveConfig();
            return true;
        } else if (afk.getBoolean(player.getName()) && player.hasPermission("op")){
            player.setPlayerListName("[§cADMIN§f] " + player.getName());
            player.sendMessage(Main.PREFIX + "§7Du bist nun nicht mehr AFK!");
            afk.set(player.getName(), false);
            afk.saveConfig();
            return true;
        } else {
            player.setPlayerListName("[§aPlayer§f] " + player.getName());
            player.sendMessage(Main.PREFIX + "§7Du bist nun nichtmehr AFK!");
            afk.set(player.getName(), false);
            afk.saveConfig();
            return true;
        }
    }
}
