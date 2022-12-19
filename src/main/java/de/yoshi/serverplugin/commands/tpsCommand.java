package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.tpsUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class tpsCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        double TPS = tpsUtils.getTPS();
        if(TPS > 20){
            TPS = 20.0;
        }
        DecimalFormat tpsFormat = new DecimalFormat("#");

        if(!(sender instanceof Player)){
            Main.log("Aktuelle TPS: " + tpsFormat.format(TPS));
            return true;
        }

        Player player = (Player) sender;

        if(TPS > 19.5){
            player.sendMessage( Main.PREFIX + "§rAktuelle TPS: §a" + tpsFormat.format(TPS));
        } else if(TPS < 19.5 && TPS > 10){
            player.sendMessage(Main.PREFIX + "§rAktuelle TPS: §e" + tpsFormat.format(TPS));
        } else if(TPS < 10){
            player.sendMessage(Main.PREFIX + "§rAktuelle TPS: §c" + tpsFormat.format(TPS));
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        return list;
    }
}
