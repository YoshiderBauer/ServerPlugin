package de.yoshi.serverplugin.listener;

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
            fileconfig statusConfig = new fileconfig("statusConfig.yml");
            String arg = configUtils.getString(status, player.getName(), "reset");

            if (Objects.equals(arg, "afk")) {
                player.setPlayerListName("§7[AFK] " + player.getName() + "§r");
            } else if (Objects.equals(arg, "reset")) {
                if (player.hasPermission("op")) {
                    player.setPlayerListName("[§cADMIN§f] " + player.getName());
                } else {
                    player.setPlayerListName("[§aPlayer§f] " + player.getName());
                }
            } else if (Objects.equals(arg, statusConfig.getString("Status1"))) {
                player.setPlayerListName("[" + statusConfig.getString("Status1Farbe") + statusConfig.getString("Status1") + "§r] " + player.getName());
            } else if (Objects.equals(arg, statusConfig.getString("Status2"))) {
                player.setPlayerListName("[" + statusConfig.getString("Status2Farbe") +statusConfig.getString("Status2") + "§r] " + player.getName());
            } else if (Objects.equals(arg, statusConfig.getString("Status3"))) {
                player.setPlayerListName("[" + statusConfig.getString("Status3Farbe") +statusConfig.getString("Status3") + "§r] " + player.getName());
            } else if (Objects.equals(arg, statusConfig.getString("Status4"))) {
                player.setPlayerListName("[" + statusConfig.getString("Status4Farbe") +statusConfig.getString("Status4") + "§r] " + player.getName());
            } else if (Objects.equals(arg, statusConfig.getString("Status5"))) {
                player.setPlayerListName("[" + statusConfig.getString("Status5Farbe") +statusConfig.getString("Status5") + "§r] " + player.getName());
            } else if (Objects.equals(arg, statusConfig.getString("Status6"))) {
                player.setPlayerListName("[" + statusConfig.getString("Status6Farbe") +statusConfig.getString("Status6") + "§r] " + player.getName());
            } else if (Objects.equals(arg, statusConfig.getString("Status7"))) {
                player.setPlayerListName("[" + statusConfig.getString("Status7Farbe") +statusConfig.getString("Status7") + "§r] " + player.getName());
            } else if (Objects.equals(arg, statusConfig.getString("Status8"))) {
                player.setPlayerListName("[" + statusConfig.getString("Status8Farbe") +statusConfig.getString("Status8") + "§r] " + player.getName());
            } else if (Objects.equals(arg, statusConfig.getString("Status9"))){
                player.setPlayerListName("[" + statusConfig.getString("Status9Farbe") +statusConfig.getString("Status9")+ "§r] " + player.getName());
            } else if (Objects.equals(arg, statusConfig.getString("Status10"))){
                player.setPlayerListName("[" + statusConfig.getString("Status10Farbe") +statusConfig.getString("Status10")+ "§r] " + player.getName());
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
