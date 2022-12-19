package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class timeCommand implements CommandExecutor, TabCompleter {
    public static String Zeit;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Clock clock = Clock.system(ZoneId.of("Europe/Berlin"));
        LocalDateTime localDateTime = LocalDateTime.now(clock);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        if(!(sender instanceof Player)){
            Main.log("Es ist " + localDateTime.format(dateTimeFormatter) + " Uhr am " + localDateTime.format(dateTimeFormatter1) + "!");
            return true;
        }
        Player player = (Player) sender;
        player.sendMessage(Main.PREFIX + "Es ist " + localDateTime.format(dateTimeFormatter) + " Uhr am " + localDateTime.format(dateTimeFormatter1) + "!");
        Zeit = localDateTime.format(dateTimeFormatter) + " Uhr";
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        return list;
    }
}
