package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.fileconfig;
import de.yoshi.serverplugin.utils.tpsUtils;
import de.yoshi.serverplugin.utils.configUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PlayerList implements Runnable{
    String Uhrzeit;
    String Ping;
    int ping;
    String tpsF;
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {

            Clock clock = Clock.system(ZoneId.of("Europe/Berlin"));
            LocalDateTime localDateTime = LocalDateTime.now(clock);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            int TPS = (int) tpsUtils.getTPS();
            if (TPS >= 20) {
                TPS = 20;
            }
            if (TPS <= 10) {
                tpsF = " §c§lTPS:§r " + TPS;
            } else if (TPS > 10 && TPS < 20) {
                tpsF = " §6§lTPS:§r " + TPS;
            } else {
                tpsF = " §a§lTPS:§r " + TPS;
            }
            ping = player.getPing();
            if (ping <= 50) {
                Ping = " §a§lPing:§r " + ping + " §a§lms";
            } else if (ping > 50 && ping <= 100) {
                Ping = " §6§lPing:§r " + ping + " §6§lms";
            } else {
                Ping = " §c§lPing:§r " + ping + " §c§lms";
            }
            Uhrzeit = "§a§lUhrzeit: §r" + localDateTime.format(dateTimeFormatter);
            player.setPlayerListFooter("\n     " + Uhrzeit + tpsF + Ping + "     \n");
            fileconfig status = new fileconfig("status.yml");
            String arg = configUtils.getString(status, player.getName(), "reset");

            if (Objects.equals(arg, "afk")) {
                player.setPlayerListName("§7[AFK] " + player.getName() + "§r");
            } else if (Objects.equals(arg, "reset")) {
                if (player.hasPermission("op")) {
                    player.setPlayerListName("[§cADMIN§f] " + player.getName());
                } else {
                    player.setPlayerListName("[§aPlayer§f] " + player.getName());
                }
            } else if (Objects.equals(arg, Main.Status1)) {
                player.setPlayerListName("[" + Main.Status1Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, Main.Status2)) {
                player.setPlayerListName("[" + Main.Status2Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, Main.Status3)) {
                player.setPlayerListName("[" + Main.Status3Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, Main.Status4)) {
                player.setPlayerListName("[" + Main.Status4Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, Main.Status5)) {
                player.setPlayerListName("[" + Main.Status5Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, Main.Status6)) {
                player.setPlayerListName("[" + Main.Status6Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, Main.Status7)) {
                player.setPlayerListName("[" + Main.Status7Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, Main.Status8)) {
                player.setPlayerListName("[" + Main.Status8Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, Main.Status9)) {
                player.setPlayerListName("[" + Main.Status9Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, Main.Status10)) {
                player.setPlayerListName("[" + Main.Status10Farbe + "§r] " + player.getName());
            } else if (Objects.equals(arg, "cam")) {
                player.setPlayerListName("§r[§7CAM§r] " + player.getName());
            } else {
                status.set(player.getName(), "reset");
                if (player.hasPermission("op")) {
                    player.setPlayerListName("[§cADMIN§f] " + player.getName());
                } else {
                    player.setPlayerListName("[§aPlayer§f] " + player.getName());
                }
            }
        }
    }
}
