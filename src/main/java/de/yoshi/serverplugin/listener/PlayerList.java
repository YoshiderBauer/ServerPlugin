package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.commands.timeCommand;
import de.yoshi.serverplugin.utils.fileconfig;
import de.yoshi.serverplugin.utils.tpsUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PlayerList implements Runnable{
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()){

            Clock clock = Clock.system(ZoneId.of("Europe/Berlin"));
            LocalDateTime localDateTime = LocalDateTime.now(clock);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd.MM.uuuu");
            int TPS = (int) tpsUtils.getTPS();
            player.setPlayerListFooter("§a§lUhrzeit: §r" + localDateTime.format(dateTimeFormatter) + " §a§lTPS:§r " + TPS);
            fileconfig afk = new fileconfig("afk.yml");
            if(afk.getBoolean(player.getName())){
                player.setPlayerListName("§7[AFK] " + player.getName());
                return;
            } else if (player.isOp() && !(afk.getBoolean(player.getName()))){
                player.setPlayerListName("[§cADMIN§f] " + player.getName());
                return;
            } else {
                player.setPlayerListName("[§aPlayer§f] " + player.getName());
            }
        }
    }
}
