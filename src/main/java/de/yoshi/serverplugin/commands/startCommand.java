package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.fileconfig;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class startCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            Main.log(Main.NOPERMISSION); return true;}
        Player player = (Player) sender;
        if(!player.hasPermission("op")){
            Main.log(Main.NOPERMISSION); return true;}
        fileconfig config = new fileconfig("config.yml");
        if(config.getBoolean("Start")){player.sendMessage(Main.PREFIX + "§cDas Event wurde bereits gestartet."); return true;}

        config.set("Start", true);
        config.saveConfig();

        World world = Bukkit.getWorlds().get(0);
        world.getWorldBorder().setCenter(world.getSpawnLocation());
        world.getWorldBorder().setSize(29999984.0, 2000);
        world.setDifficulty(Difficulty.HARD);
        Bukkit.getServer().setDefaultGameMode(GameMode.SURVIVAL);
        for(Player player1 : Bukkit.getOnlinePlayers()){
            if(player1.getGameMode() != GameMode.SPECTATOR || player1.getGameMode() != GameMode.CREATIVE){
                player1.setGameMode(GameMode.SURVIVAL);
            }
            player1.removePotionEffect(PotionEffectType.WEAKNESS);
            player1.removePotionEffect(PotionEffectType.SATURATION);
            player1.removePotionEffect(PotionEffectType.REGENERATION);
            player1.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            player1.sendMessage(Main.PREFIX + "§aViel Spaß!");
        }
        player.sendMessage(Main.PREFIX + "§aDas Event wurde erfolgreich gestartet!");
        return true;
    }
}
