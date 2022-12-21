package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.configUtils;
import de.yoshi.serverplugin.utils.fileconfig;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class camCommand implements CommandExecutor, TabCompleter {
    Player player;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            player = (Player) sender;
        }

        if(args.length < 1){
            if(sender instanceof Player){
                player.sendMessage(Main.PREFIX + "§cDu musst noch einen Spieler angeben!");
            } else {
                Main.log("§cDu musst noch einen Spieler angeben!");
            }

            return true;
        }

        boolean matched = false;
        for (Player player1 : Bukkit.getOnlinePlayers()){
            if(args[0].equals(player1.getName())){
                matched = true;
            }
        }
        if(!matched){
            if(sender instanceof Player){
                player.sendMessage(Main.PREFIX + "§cDieser Spieler ist nicht auf diesem Server!");
            } else {
                Main.log("§cDieser Spieler ist nicht auf diesem Server!");
            }
            return true;
        }
        Player cam = Bukkit.getPlayer(args[0]);
        fileconfig status = new fileconfig("status.yml");
        if(!configUtils.getString(status, args[0], "reset").equals("cam")){
            status.set(args[0], "cam");

            cam.setGameMode(GameMode.SPECTATOR);
            cam.sendMessage(Main.PREFIX + "Du bist jetzt als Cam registriert!");
            if(sender instanceof Player){
                player.sendMessage(Main.PREFIX + cam.getName() + " ist jetzt als Cam registriert!");
            } else {
                Main.log(cam.getName() + " ist jetzt als Cam registriert!");
            }
            cam.setDisplayName("§r[§7CAM§r] " + cam.getName());
            cam.setPlayerListName("§r[§7CAM§r] " + cam.getName());
        } else if (configUtils.getString(status, cam.getName(), "reset").equals("cam")){
            status.set(args[0], "reset");
            cam.setGameMode(GameMode.SURVIVAL);
            cam.setNoDamageTicks(200);
            cam.sendMessage(Main.PREFIX + "§cDu bist jetzt nichtmehr als Cam registriert!");
            if(sender instanceof Player){
                player.sendMessage(Main.PREFIX + "§c" + cam.getName() + " ist jetzt nichtmehr als Cam registriert!");
            } else {
                Main.log("§c" + cam.getName() + " ist jetzt nichtmehr als Cam registriert!");
            }

            if(cam.hasPermission("op")){
                cam.setPlayerListName("[§cADMIN§f] " + cam.getName());
                cam.setDisplayName("[§cADMIN§f] " + cam.getName());
            } else {
                cam.setPlayerListName("[§aPlayer§f] " + cam.getName());
                cam.setDisplayName("[§aPlayer§f] " + cam.getName());
            }
        }
        status.saveConfig();
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList <String> onlinePlayers = new ArrayList<>();

        if(args.length > 1){
            return onlinePlayers;
        }

        for (Player player : Bukkit.getOnlinePlayers()){
            onlinePlayers.add(player.getName());
        }
        return onlinePlayers;
    }
}
