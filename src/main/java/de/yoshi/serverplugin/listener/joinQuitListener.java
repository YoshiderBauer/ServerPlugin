package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.configUtils;
import de.yoshi.serverplugin.utils.fileconfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

public class joinQuitListener implements Listener {
    @EventHandler
    private void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setPlayerListHeader("\n            " + Main.PREFIX + "         \n");
        player.setPlayerListFooter(" ");
        fileconfig status = new fileconfig("status.yml");

        if(!status.contains(player.getName())) {
            status.set(player.getName(), "reset");
            status.saveConfig();
        }

        if(!configUtils.getString(status, player.getName(), "reset").equals("cam")){
            event.setJoinMessage(Main.PREFIX + "§a" + player.getName() + " §7hat den Server betreten.");
        } else {
            event.setJoinMessage("");
            Main.log("§a" + player.getName() + " §7hat den Server betreten.");
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
        } else if (arg.equals(Main.Status1)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status1Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status1Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status1Farbe +  "§r] " +player.getName());
        } else if (arg.equals(Main.Status2)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status2Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status2Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status2Farbe +  "§r] " +player.getName());
        } else if (arg.equals(Main.Status3)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status3Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status3Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status3Farbe +  "§r] " +player.getName());
        } else if (arg.equals(Main.Status4)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status4Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status4Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status4Farbe +  "§r] " +player.getName());
        } else if (arg.equals(Main.Status5)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status5Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status5Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status5Farbe +  "§r] " +player.getName());
        } else if (arg.equals(Main.Status6)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status6Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status6Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status6Farbe +  "§r] " +player.getName());
        } else if (arg.equals(Main.Status7)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status7Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status7Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status7Farbe +  "§r] " +player.getName());
        } else if (arg.equals(Main.Status8)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status8Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status8Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status8Farbe +  "§r] " +player.getName());
        } else if (arg.equals(Main.Status9)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status9Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status9Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status9Farbe +  "§r] " +player.getName());
        } else if (arg.equals(Main.Status10)){
            player.sendMessage(Main.PREFIX + "§rDein Status steht immernoch auf " + Main.Status10Farbe + "§r!");
            player.setPlayerListName("[" + Main.Status10Farbe + "§r] " + player.getName());
            player.setDisplayName("[" + Main.Status10Farbe +  "§r] " +player.getName());
        } else if (arg.equals("cam") && (!player.getGameMode().equals(GameMode.SPECTATOR))) {
            player.setGameMode(GameMode.SPECTATOR);
            player.setDisplayName("§r[§7CAM§r] " + player.getName());
            player.setPlayerListName("§r[§7CAM§r] " + player.getName());
        } else if (arg.equals("cam")) {
            player.setDisplayName("§r[§7CAM§r] " + player.getName());
            player.setPlayerListName("§r[§7CAM§r] " + player.getName());
        }

        fileconfig config = new fileconfig("config.yml");

        if(configUtils.getBoolean(config, "DeathCounter", true)){
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard;
            Objective objective;

            scoreboard = manager.getNewScoreboard();
            objective = scoreboard.registerNewObjective("Deaths", "deathCount");
            objective.setDisplayName("Deaths");
            objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
            Score score = objective.getScore(player);
            score.setScore(player.getStatistic(Statistic.DEATHS));
            player.setScoreboard(scoreboard);
        }

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
        fileconfig status = new fileconfig("status.yml");
        if(!configUtils.getString(status, player.getName(), "reset").equals("cam")){
            event.setQuitMessage(Main.PREFIX + "§c" + player.getName() + " §7hat den Server verlassen.");
        } else {
            event.setQuitMessage("");
            Main.log("§c" + player.getName() + " §7hat den Server verlassen.");
        }
    }
}
