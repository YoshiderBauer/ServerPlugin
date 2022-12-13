package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.configUtils;
import de.yoshi.serverplugin.utils.fileconfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Random;

public class serverPing implements Listener {

    @EventHandler
    private void onPingEvent(ServerListPingEvent event){
        Random random = new Random();
        int rand = random.nextInt(1000-10) + 10;
        event.setMaxPlayers(rand);
        int Rand = random.nextInt(7) + 1;

        fileconfig config = new fileconfig("config.yml");
        if(Rand == 1){
            event.setMotd(configUtils.getString(config,"Motd1", "Yoshi_der_Bauer ist der Beste!"));
        } else if(Rand == 2) {
            event.setMotd(configUtils.getString(config,"Motd2", "Der hackt, der hackt, der hackt!"));
        } else if (Rand == 3) {
            event.setMotd(configUtils.getString(config,"Motd3", "Ich mag Kakteen!"));
        } else if (Rand == 4) {
            event.setMotd(configUtils.getString(config,"Motd4", "Ich bin schon wieder tot!"));
        } else if (Rand == 5) {
            event.setMotd(configUtils.getString(config,"Motd5", "Sound.Player.Death"));
        } else if (Rand == 6){
            event.setMotd(configUtils.getString(config,"Motd6", "§eHerobrian has joined the game."));
        } else {
            event.setMotd(configUtils.getString(config,"Motd7", "Tot, Halbes!"));
        }

        if(configUtils.getBoolean(config,"showPing",false)){
            Main.log("§cPing " + event.getAddress());
        }
    }
}
