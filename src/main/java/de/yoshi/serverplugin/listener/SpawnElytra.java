package de.yoshi.serverplugin.listener;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.fileconfig;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SpawnElytra implements Listener {
    private final int multiplyValue;
    private final int spawnRadius;
    private final List<Player> flying = new ArrayList<>();
    private final List<Player> boosted = new ArrayList<>();

    public SpawnElytra(Plugin plugin){
        fileconfig config = new fileconfig("config.yml");
        this.multiplyValue = config.getInt("SpawnElytraBoost");
        this.spawnRadius = config.getInt("SpawnRadius");

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            Bukkit.getWorld("world").getPlayers().forEach(player -> {
                if(player.getGameMode() != GameMode.SURVIVAL && player.getGameMode() != GameMode.ADVENTURE) return;
                player.setAllowFlight(isInSpawnRadius(player));
                if(flying.contains(player) && !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()){
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.setGliding(false);
                    boosted.remove(player);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        flying.remove(player);
                    },5);
                }
            });
        }, 0,3);
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent event){
        Player player = event.getPlayer();
        if(player.getGameMode() != GameMode.SURVIVAL && player.getGameMode() != GameMode.ADVENTURE) return;
        if(!isInSpawnRadius(player)) return;
        event.setCancelled(true);
        player.setGliding(true);
        flying.add(player);
    }

    @EventHandler
    public void onDamageEvent(EntityDamageEvent event){
        Entity player = event.getEntity();
        if(event.getEntityType() == EntityType.PLAYER
        && (event.getCause() == EntityDamageEvent.DamageCause.FALL
        || event.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL)
        && flying.contains(player)) event.setCancelled(true);

    }

    @EventHandler
    public void onSwapItemEvent(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();
        if(boosted.contains(player) || !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir() || !player.isGliding() || !flying.contains(player)) return;
        event.setCancelled(true);
        boosted.add(player);

        player.setVelocity(player.getLocation().getDirection().multiply(multiplyValue));
    }

    @EventHandler
    public void onPlayerToggleGlideEvent(EntityToggleGlideEvent event){
        Entity player = event.getEntity();
        if(event.getEntityType() == EntityType.PLAYER && flying.contains(player)) event.setCancelled(true);

    }

    private boolean isInSpawnRadius(Player player){
        if(!player.getWorld().getName().equals("world")) return false;
        return player.getWorld().getSpawnLocation().distance(player.getLocation()) <= spawnRadius;
    }

}
