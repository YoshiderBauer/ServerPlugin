package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.Main;
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
    long totalRAM;
    long ramUsage;
    long ramAvailable;
    String Ram;
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()){

            Clock clock = Clock.system(ZoneId.of("Europe/Berlin"));
            LocalDateTime localDateTime = LocalDateTime.now(clock);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            //LocalDateTime localDateTime1 = LocalDateTime.of(localDateTime.getYear(),localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), 17, 22);
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
                Ping = " §a§lPing:§r " + ping + " §a§lms";
            } else if (ping > 50 && ping <= 100) {
                Ping = " §6§lPing:§r " + ping + " §6§lms";
            } else {
                Ping = " §c§lPing:§r " + ping + " §c§lms";
            }
            Uhrzeit = "§a§lUhrzeit: §r" + localDateTime.format(dateTimeFormatter);
            //Runtime r = Runtime.getRuntime();
            //totalRAM = r.maxMemory() / 1048576;
            //ramAvailable = r.freeMemory() / 1048576;
            //ramUsage = ramAvailable / totalRAM;
            //Main.log(ramUsage +" " + totalRAM + " " + ramAvailable);
            //Ram = "§a§lArbeitsspeicher:§r§l " + ramUsage + "§r MB von §l" + totalRAM + "§r MB verwendet";
            //if(ramUsage <= 0.7) {
            //    Ram = " §a§lRAM: §r" + (ramUsage * 100) + " %";
            //} else if (ramUsage > 0.7 && ramUsage <= 0.9) {
            //    Ram = " §6§lRAM: §r" + (ramUsage * 100) + " %";
            //} else {
             //   Ram = " §c§lRAM: §r" + (ramUsage * 100) + " %";
            //}
            player.setPlayerListFooter("\n" + Uhrzeit + tpsF + Ping + "\n" /*+ Ram + "\n"*/);
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
