package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.configUtils;
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

public class joinQuitListener implements Listener {
    @EventHandler
    private void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setPlayerListHeader("\n            " + Main.PREFIX + "         \n");
        player.setPlayerListFooter(" ");

        event.setJoinMessage(Main.PREFIX + "§a" + player.getDisplayName() + " §7hat den Server betreten.");

        fileconfig status = new fileconfig("status.yml");

        if(!status.contains(player.getName())) {
            status.set(player.getName(), "reset");
            status.saveConfig();
        }

        String arg = configUtils.getString(status, player.getName(), "reset");

        if(arg.equals("afk")){
            player.sendMessage(Main.PREFIX + "§rDu bist immernoch §7§lAFK§r!");
            player.setPlayerListName("§7[AFK] " + player.getName());
            player.setDisplayName("§7[AFK§f] " + player.getName() + "§r");
        } else if (arg.equals("reset")){
            if(player.hasPermission("op")){
                player.setPlayerListName("[§cADMIN§f] " + player.getName());
                player.setDisplayName("[§cADMIN§f] " + player.getName());
            } else {
                player.setPlayerListName("[§aPlayer§f] " + player.getName());
                player.setDisplayName("[§aPlayer§f] " + player.getName());
            }
        } else if (arg.equals("Roleplay")){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf §5§lRoleplay§r!");
            player.setPlayerListName("[§5RP§f] " + player.getName());
            player.setDisplayName("[§5RP§f] " + player.getName());
        } else if (arg.equals("Redstone")){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf §c§lRedstone§r!");
            player.setPlayerListName("[§cREDSTONE§f] " + player.getName());
            player.setDisplayName("[§cREDSTONE§f] " + player.getName());
        } else if (arg.equals("pog")){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf §2§lPog§r!");
            player.setPlayerListName("[§2POG§f] " + player.getName());
            player.setDisplayName("[§2POG§f] " + player.getName());
        } else if (arg.equals("Livestream")) {
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf §9§lLivestream§r!");
            player.setPlayerListName("[§9LIVE§f] " + player.getName());
            player.setDisplayName("[§9LIVE§f] " + player.getName());
        } else if (arg.equals("Aufnahme")) {
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf §c§lAufnahme§r!");
            player.setPlayerListName("[§cREC§f] " + player.getName());
            player.setDisplayName("[§cREC§f] " + player.getName());
        } else if (arg.equals("Kingsmen")) {
            player.sendMessage(Main.PREFIX + "§rDu bist immer noch ein §6§lKingsmen§r!");
            player.setPlayerListName("[§6Kingsmen§f] " + player.getName());
            player.setDisplayName("[§6Kingsmen§f] " + player.getName());
        } else if (arg.equals("Troll")) {
            player.sendMessage(Main.PREFIX + "§rDu bist immernoch ein §d§lTroll§r!");
            player.setPlayerListName("[§dTroll§f] " + player.getName());
            player.setDisplayName("[§dTroll§f] " + player.getName());
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
