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
    String Uhrzeit;
    String Ping;
    int ping;
    String tpsF;
    int totalRAM;
    int ramUsage;
    int ramAvailable;
    String Ram;
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()){

            Clock clock = Clock.system(ZoneId.of("Europe/Berlin"));
            LocalDateTime localDateTime = LocalDateTime.now(clock);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd.MM.uuuu");
            int TPS = (int) tpsUtils.getTPS();
            if(TPS >= 20){
                TPS = 20;
            }
            if(TPS <= 10){
                tpsF = " §c§lTPS:§r " + TPS;
            } else if (TPS > 10 && TPS < 20) {
                tpsF = " §6§lTPS:§r " + TPS;
            } else {
                tpsF = " §a§lTPS:§r " + TPS;
            }
            ping = player.getPing();
            if(ping <= 50){
                Ping = " §a§lPing:§r " + ping;
            } else if (ping > 50 && ping <= 100) {
                Ping = " §6§lPing:§r " + ping;
            } else {
                Ping = " §c§lPing:§r " + ping;
            }
            Uhrzeit = "§a§lUhrzeit: §r" + localDateTime.format(dateTimeFormatter);
            Runtime r = Runtime.getRuntime();
            totalRAM = (int) r.totalMemory() / 1024 / 1024;
            ramAvailable = (int) r.freeMemory() / 1024 / 1024;
            ramUsage = totalRAM - ramAvailable;
            Ram = "§a§lArbeitsspeicher:§r§l " + ramUsage + "§r MB von §l" + totalRAM + "§r MB verwendet";
            player.setPlayerListFooter("\n" + Uhrzeit + tpsF + Ping + /*"\n" + Ram +*/"\n");
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
