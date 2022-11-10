package de.yoshi.serverplugin.utils;

import de.yoshi.serverplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.checkerframework.checker.units.qual.A;

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
            AtomicInteger counterMinutes = new AtomicInteger(5);
            Bukkit.getScheduler().runTaskTimer(plugin,() -> {
                if(counterMinutes.get() > 1){
                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(Main.PREFIX + "Der Server wird in " + counterMinutes.get() + " Minuten neugestartet!");
                    }
                    Main.log("Der Server wird in " + counterMinutes.get() + " Minuten neugestartet!");
                } else {
                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(Main.PREFIX + "Der Server wird in einer Minute neugestartet!");
                    }
                    Main.log("Der Server wird in einer Minute neugestartet!");
                }

                if(minutes < (autoRestartDelay - 0.5)){
                    AtomicInteger counterSeconds = new AtomicInteger(30);
                    Bukkit.getScheduler().runTaskTimer(plugin,() -> {

                        for(Player player : Bukkit.getOnlinePlayers()){
                            player.sendMessage(Main.PREFIX + "Der Server wird in " + counterSeconds.get() + " Sekunden neugestartet!");
                        }
                        Main.log("Der Server wird in " + counterSeconds.get() + " Sekunden neugestartet!");
                        counterSeconds.getAndDecrement();

                        if(counterSeconds.get() == 0){
                            Bukkit.getServer().spigot().restart();
                        }
                    },0,20);
                }
            }, 0, 1200);
        }
    }
}
