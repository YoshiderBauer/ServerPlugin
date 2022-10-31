package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            Main.log(Main.NOPERMISSION); return true;}

        Player player = (Player) sender;
        player.sendMessage(Main.PREFIX + "Dein Ping liegt bei: " + player.getPing() + " ms.");
        return true;
    }
}
