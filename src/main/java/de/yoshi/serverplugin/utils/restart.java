package de.yoshi.serverplugin.utils;

import de.yoshi.serverplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.atomic.AtomicInteger;

public class restart implements Runnable{
    private long currentMillies;
    private long uptime;
    private long seconds;
    private long minutes;
    private double autoRestartDelay;
    private Plugin plugin;
    private fileconfig config = new fileconfig("config.yml");

    public restart(Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run() {
        currentMillies = System.currentTimeMillis();
        autoRestartDelay = config.getInt("Auto-Restart delay");
        uptime = currentMillies - Main.INSTANCE.lastStart;
        seconds = uptime / 1000;
        minutes = seconds / 60;
        if(minutes < (autoRestartDelay - 5)){
            return;
        } else if (minutes > (autoRestartDelay - 5) && minutes < (autoRestartDelay - 4.5)){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(Main.PREFIX + "Der Server wird in 5 Minuten neugestartet!");
            }
            return;
        } else if (minutes > (autoRestartDelay - 4) && minutes < (autoRestartDelay - 3.5)){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(Main.PREFIX + "Der Server wird in 4 Minuten neugestartet!");
            }
            return;
        } else if (minutes > (autoRestartDelay - 3) && minutes < (autoRestartDelay - 2.5)){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(Main.PREFIX + "Der Server wird in 3 Minuten neugestartet!");
            }
            return;
        } else if (minutes > (autoRestartDelay - 2) && minutes < (autoRestartDelay - 1.5)){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(Main.PREFIX + "Der Server wird in 2 Minuten neugestartet!");
            }
            return;
        } else if (minutes > (autoRestartDelay - 1) && minutes < (autoRestartDelay - 0.5)){
            for(Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(Main.PREFIX + "Der Server wird in einer Minute neugestartet!");
            }
            return;
        } else if(minutes > (autoRestartDelay - 0.5) && minutes < autoRestartDelay){
            AtomicInteger counter = new AtomicInteger(30);
            Bukkit.getScheduler().runTaskTimer(plugin,() -> {

                for(Player player : Bukkit.getOnlinePlayers()){
                    player.sendMessage(Main.PREFIX + "Der Server wird in " + counter + " Sekunden neugestartet!");
                }
                counter.getAndDecrement();

                if(counter.get() == 0){
                    Bukkit.getServer().spigot().restart();
                }
            },0,20);
        }

    }
}
