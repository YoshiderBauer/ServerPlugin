package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.Main;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.plugin.Plugin;

public class gameModeListener implements Listener {
    private Plugin plugin;
    public gameModeListener(Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    private void onChangeGamemode(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        GameMode gameMode = event.getNewGameMode();
        player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 1.2f);
        player.sendMessage(Main.PREFIX + "§fDein Spielmodus wurde zu §c§l" + gameMode + "§f§r geändert!");
    }
}
