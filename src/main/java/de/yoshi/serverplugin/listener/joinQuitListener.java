package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.fileconfig;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.FileNotFoundException;
import java.io.IOException;

public class joinQuitListener implements Listener {
    @EventHandler
    private void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setPlayerListHeader("\n            " + Main.PREFIX + "         \n");
        player.setPlayerListFooter(" ");

        event.setJoinMessage(Main.PREFIX + "§a" + player.getDisplayName() + " §7hat den Server betreten.");

        fileconfig afk = new fileconfig("afk.yml");

        if(!afk.contains(player.getName())) {
            afk.set(player.getName(), false);
            afk.saveConfig();
        }

        if(!afk.getBoolean(player.getName()) && player.hasPermission("op")){
            player.setPlayerListName("[§cADMIN§f] " + player.getName());
        } else if (afk.getBoolean(player.getName())) {
            player.setPlayerListName("§7[AFK] " + player.getName());
            player.sendMessage(Main.PREFIX + "§7Du bist noch AFK!");
        } else if (!afk.getBoolean(player.getName()) && !player.hasPermission("op")){
            player.setPlayerListName("[§aPlayer§f] " + player.getName());
        }

        fileconfig config = new fileconfig("config.yml");
        if(!config.getBoolean("Start")){
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            player.setGameMode(GameMode.ADVENTURE);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 999999, 255, true, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 999999, 255, true, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999, 255, true, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999, 255, true, false, false));
        }
    }

    @EventHandler
    private void playerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.setQuitMessage(Main.PREFIX + "§c" + player.getDisplayName() + " §7hat den Server verlassen.");
    }
}
