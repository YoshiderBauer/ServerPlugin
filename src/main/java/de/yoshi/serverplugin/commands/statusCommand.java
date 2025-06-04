package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.configUtils;
import de.yoshi.serverplugin.utils.fileconfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class statusCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            Main.log(Main.NOPERMISSION);
            return  true;
        }
        fileconfig status = new fileconfig("status.yml");
        Player player = (Player) sender;
        if(configUtils.getString(status, player.getName(), "reset").equals("cam")){
            player.sendMessage(Main.PREFIX + "§cDieser Command kann nur von Spielern ausgeführt werden!");
            return true;
        }
        if(args.length == 0){
            status.set(player.getName(), "reset");
            player.sendMessage(Main.PREFIX + "Dein Status wurde nun zurück gesetzt!");
            if(player.hasPermission("op")){
                player.setPlayerListName("[§cADMIN§f] " + player.getName());
                player.setDisplayName("[§cADMIN§f] " + player.getName());
            } else {
                player.setPlayerListName("[§aPlayer§f] " + player.getName());
                player.setDisplayName("[§aPlayer§f] " + player.getName());
            }
            return true;
        }

        String arg = args[0];
        if(Objects.equals(arg, "afk")){
            status.set(player.getName(), "afk");
            player.sendMessage(Main.PREFIX + "§rDu bist nun §7§lAFK§r!");
            player.setPlayerListName("§7[AFK] " + player.getName());
            player.setDisplayName("§7[AFK] " + player.getName() + "§r");
        } else if (Objects.equals(arg, " reset")){
            status.set(player.getName(), "reset");
            player.sendMessage(Main.PREFIX + "Dein Status wurde nun zurück gesetzt!");
            if(player.hasPermission("op")){
                player.setPlayerListName("[§cADMIN§f] " + player.getName());
                player.setDisplayName("[§cADMIN§f] " + player.getName());
            } else {
                player.setPlayerListName("[§aPlayer§f] " + player.getName());
                player.setDisplayName("[§aPlayer§f] " + player.getName());
            }
        } else if (Objects.equals(arg, "Rockstars")){
            status.set(player.getName(), "Rockstars");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §6§lRockstars§r gesetzt!");
            player.setPlayerListName("[§6Rockstars§f] " + player.getName());
            player.setDisplayName("[§6Rockstars§f] " + player.getName());
        } else if (Objects.equals(arg, "Regio")){
            status.set(player.getName(), "Regio");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §c§lRegio§r gesetzt!");
            player.setDisplayName("[§cRegio§f] " + player.getName());
            player.setPlayerListName("[§cRegio§f] " + player.getName());
        } else if (Objects.equals(arg, "fv")){
            status.set(player.getName(), "fv");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §7§lFernverkehr§r gesetzt!");
            player.setPlayerListName("[§7Fernverkehr§f] " + player.getName());
            player.setDisplayName("[§7Fernverkehr§f] " + player.getName());
        } else if (Objects.equals(arg, "Cargo")) {
            status.set(player.getName(), "Cargo");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §4§lCargo§r gesetzt!");
            player.setPlayerListName("[§4Cargo§f] " + player.getName());
            player.setDisplayName("[§4Cargo§f] " + player.getName());
        } else if (Objects.equals(arg, "SBB")) {
            status.set(player.getName(), "SBB");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §8§lSBB§r gesetzt!");
            player.setPlayerListName("[§8SBB§f] " + player.getName());
            player.setDisplayName("[§8SBB§f] " + player.getName());
        } else if (Objects.equals(arg, "AVG")) {
            status.set(player.getName(), "AVG");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §2§lAVG§r gesetzt!");
            player.setPlayerListName("[§2AVG§f] " + player.getName());
            player.setDisplayName("[§2AVG§f] " + player.getName());
        } else if (Objects.equals(arg, "Bundesbahn")) {
            status.set(player.getName(), "Bundesbahn");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §f§lBundesbahn§r gesetzt!");
            player.setPlayerListName("[§fBundesbahn§f] " + player.getName());
            player.setDisplayName("[§fBundesbahn§f] " + player.getName());
        } else if (Objects.equals(arg, "Reichsbahn")) {
            status.set(player.getName(), "Reichsbahn");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §a§lReichsbahn§r gesetzt!");
            player.setPlayerListName("[§aReichsbahn§f] " + player.getName());
            player.setDisplayName("[§aReichsbahn§f] " + player.getName());
        }  else {
            status.set(player.getName(), "reset");
            player.sendMessage(Main.PREFIX + "Dein Status wurde nun zurück gesetzt!");
            if(player.hasPermission("op")){
                player.setPlayerListName("[§cADMIN§f] " + player.getName());
                player.setDisplayName("[§cADMIN§f] " + player.getName());
            } else {
                player.setPlayerListName("[§aPlayer§f] " + player.getName());
                player.setDisplayName("[§aPlayer§f] " + player.getName());
            }
        }
        status.saveConfig();
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> statuse = new ArrayList<>();
        if(args.length == 1){
            String a = args[0];
            if(a.startsWith("a") || a.startsWith("A")) {
                statuse.add("afk");
                statuse.add("AVG");
            } else if (a.startsWith("f")  || a.startsWith("F")) {
                statuse.add("fv");
            } else if (a.startsWith("r") || a.startsWith("R")) {
                statuse.add("Regio");
                statuse.add("reset");
                statuse.add("Rockstars");
                statuse.add("Reichsbahn");
            } else if (a.startsWith("b") || a.startsWith("B")){
                statuse.add("Bundesbahn");
            } else if (a.startsWith("c") || a.startsWith("C")) {
                statuse.add("Cargo");
            } else if (a.startsWith("s") || a.startsWith("S")) {
            statuse.add("SBB");
            } else if (a.isEmpty()){
                statuse.add("afk");
                statuse.add("AVG");
                statuse.add("Bundesbahn");
                statuse.add("Cargo");
                statuse.add("fv");
                statuse.add("Regio");
                statuse.add("Reichsbahn");
                statuse.add("reset");
                statuse.add("Rockstars");
                statuse.add("SBB");
            } else {
                statuse.clear();
            }
        } else {
            statuse.clear();
        }
        return statuse;
    }
}
