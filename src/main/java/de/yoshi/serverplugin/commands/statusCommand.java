package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
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
            return  false;
        }
        fileconfig status = new fileconfig("status.yml");
        Player player = (Player) sender;

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
        } else if (Objects.equals(arg, "Roleplay")){
            status.set(player.getName(), "Roleplay");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §5§lRoleplay§r gesetzt!");
            player.setPlayerListName("[§5RP§f] " + player.getName());
            player.setDisplayName("[§5RP§f] " + player.getName());
        } else if (Objects.equals(arg, "Redstone")){
            status.set(player.getName(), "Redstone");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §c§lRedstone§r gesetzt!");
            player.setDisplayName("[§cREDSTONE§f] " + player.getName());
            player.setPlayerListName("[§cREDSTONE§f] " + player.getName());
        } else if (Objects.equals(arg, "pog")){
            status.set(player.getName(), "pog");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §2§lPog§r gesetzt!");
            player.setPlayerListName("[§2POG§f] " + player.getName());
            player.setDisplayName("[§2POG§f] " + player.getName());
        } else if (Objects.equals(arg, "Livestream")) {
            status.set(player.getName(), "Livestream");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §9§lLivestream§r gesetzt!");
            player.setPlayerListName("[§9LIVE§f] " + player.getName());
            player.setDisplayName("[§9LIVE§f] " + player.getName());
        } else if (Objects.equals(arg, "Aufnahme")) {
            status.set(player.getName(), "Aufnahme");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §c§lAufnahme§r gesetzt!");
            player.setPlayerListName("[§cREC§f] " + player.getName());
            player.setDisplayName("[§cREC§f] " + player.getName());
        } else if (Objects.equals(arg, "Kingsmen")) {
            status.set(player.getName(), "Kingsmen");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §6§lKingsmen§r gesetzt!");
            player.setPlayerListName("[§6Kingsmen§f] " + player.getName());
            player.setDisplayName("[§6Kingsmen§f] " + player.getName());
        } else if (Objects.equals(arg, "Troll")) {
            status.set(player.getName(), "Troll");
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf §d§lTroll§r gesetzt!");
            player.setPlayerListName("[§dTroll§f] " + player.getName());
            player.setDisplayName("[§dTroll§f] " + player.getName());
        } else {
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
                statuse.add("Aufnahme");
            } else if (a.startsWith("k") || a.startsWith("K")) {
                statuse.add("Kingsmen");
            } else if (a.startsWith("p")  || a.startsWith("P")) {
                statuse.add("pog");
            } else if (a.startsWith("r") || a.startsWith("R")) {
                statuse.add("Redstone");
                statuse.add("reset");
                statuse.add("Roleplay");
            } else if (a.startsWith("t") || a.startsWith("T")){
                statuse.add("Troll");
            } else if (a.startsWith("l") || a.startsWith("L")) {
                statuse.add("Livestream");
            } else if (a.isEmpty()){
                statuse.add("afk");
                statuse.add("reset");
                statuse.add("Livestream");
                statuse.add("Aufnahme");
                statuse.add("Roleplay");
                statuse.add("Redstone");
                statuse.add("pog");
                statuse.add("Kingsmen");
                statuse.add("Troll");
            } else {
                statuse.clear();
            }
        } else {
            statuse.clear();
        }
        return statuse;
    }
}
