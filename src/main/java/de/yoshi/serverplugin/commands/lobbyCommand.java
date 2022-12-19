package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class lobbyCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            Main.log(Main.NOPERMISSION); return true;}
        Player player = (Player) sender;

        player.sendMessage(Main.PREFIX + "Du wirst nun weitergeleitet. ");
        player.playSound(player.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 0.2f, 1.2f);
        Main.sendServer(player, "Lobby");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        return list;
    }
}
