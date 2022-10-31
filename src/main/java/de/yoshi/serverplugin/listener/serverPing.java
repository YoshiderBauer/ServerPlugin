package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.Main;
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
            event.setMotd(config.getString("Motd1"));
        } else if(Rand == 2) {
            event.setMotd(config.getString("Motd2"));
        } else if (Rand == 3) {
            event.setMotd(config.getString("Motd3"));
        } else if (Rand == 4) {
            event.setMotd(config.getString("Motd4"));
        } else if (Rand == 5) {
            event.setMotd(config.getString("Motd5"));
        } else if (Rand == 6){
            event.setMotd(config.getString("Motd6"));
        } else {
            event.setMotd(config.getString("Motd7"));
        }

        if(config.getBoolean("showPing")){
            Main.log("§cPing " + event.getAddress());
        }
    }
}
