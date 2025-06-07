package de.yoshi.serverplugin.commands;

import de.yoshi.serverplugin.Main;
import de.yoshi.serverplugin.utils.configUtils;
import de.yoshi.serverplugin.utils.fileconfig;
import org.bukkit.ChatColor;
import org.bukkit.Color;
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
        if (!(sender instanceof Player)) {
            Main.log(Main.NOPERMISSION);
            return true;
        }
        fileconfig status = new fileconfig("status.yml");
        Player player = (Player) sender;
        if (configUtils.getString(status, player.getName(), "reset").equals("cam")) {
            player.sendMessage(Main.PREFIX + "§cDieser Command kann nur von Spielern ausgeführt werden!");
            return true;
        }
        if (args.length == 0) {
            status.set(player.getName(), "reset");
            status.saveConfig();
            player.sendMessage(Main.PREFIX + "Dein Status wurde nun zurück gesetzt!");
            if (player.hasPermission("op")) {
                player.setPlayerListName("[§cADMIN§f] " + player.getName());
                player.setDisplayName("[§cADMIN§f] " + player.getName());
            } else {
                player.setPlayerListName("[§aPlayer§f] " + player.getName());
                player.setDisplayName("[§aPlayer§f] " + player.getName());
            }
            return true;
        }

        String arg = args[0];
        if (Objects.equals(arg, "afk")) {
            status.set(player.getName(), "afk");
            player.sendMessage(Main.PREFIX + "§rDu bist nun §7§lAFK§r!");
            player.setPlayerListName("§7[AFK] " + player.getName());
            player.setDisplayName("§7[AFK] " + player.getName() + "§r");
        } else if (Objects.equals(arg, " reset")) {
            status.set(player.getName(), "reset");
            player.sendMessage(Main.PREFIX + "Dein Status wurde nun zurück gesetzt!");
            if (player.hasPermission("op")) {
                player.setPlayerListName("[§cADMIN§f] " + player.getName());
                player.setDisplayName("[§cADMIN§f] " + player.getName());
            } else {
                player.setPlayerListName("[§aPlayer§f] " + player.getName());
                player.setDisplayName("[§aPlayer§f] " + player.getName());
            }
        } else if (Objects.equals(arg, Main.Status1)) {
            status.set(player.getName(), Main.Status1);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status1Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status1Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status1Farbe + "] " + player.getName());;
        } else if (Objects.equals(arg, Main.Status2)) {
            status.set(player.getName(), Main.Status2);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status2Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status2Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status2Farbe + "] " + player.getName());;
        } else if (Objects.equals(arg, Main.Status3)) {
            status.set(player.getName(), Main.Status3);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status3Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status3Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status3Farbe + "] " + player.getName());;
        } else if (Objects.equals(arg, Main.Status4)) {
            status.set(player.getName(), Main.Status4);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status4Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status4Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status4Farbe + "] " + player.getName());;
        } else if (Objects.equals(arg, Main.Status5)) {
            status.set(player.getName(), Main.Status5);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status5Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status5Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status5Farbe + "] " + player.getName());;
        } else if (Objects.equals(arg, Main.Status6)) {
            status.set(player.getName(), Main.Status6);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status6Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status6Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status6Farbe + "] " + player.getName());;
        } else if (Objects.equals(arg, Main.Status7)) {
            status.set(player.getName(), Main.Status7);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status7Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status7Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status7Farbe + "] " + player.getName());;
        } else if (Objects.equals(arg, Main.Status8)) {
            status.set(player.getName(), Main.Status8);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status8Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status8Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status8Farbe + "] " + player.getName());;
        } else if (Objects.equals(arg, Main.Status9)) {
            status.set(player.getName(), Main.Status9);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status9Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status9Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status9Farbe + "] " + player.getName());;
        } else if (Objects.equals(arg, Main.Status10)) {
            status.set(player.getName(), Main.Status10);
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + Main.Status10Farbe + "§r gesetzt!");
            player.setPlayerListName("[" + Main.Status10Farbe + "] " + player.getName());
            player.setDisplayName("[" + Main.Status10Farbe + "] " + player.getName());;
        } else {
            status.set(player.getName(), "reset");
            player.sendMessage(Main.PREFIX + "Dein Status wurde nun zurück gesetzt!");
            if (player.hasPermission("op")) {
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
        if (args.length == 1) {
            String a = args[0];
            statuse.add("afk");
            statuse.add("reset");
            statuse.add(Main.Status1);
            statuse.add(Main.Status2);
            statuse.add(Main.Status3);
            statuse.add(Main.Status4);
            statuse.add(Main.Status5);
            statuse.add(Main.Status6);
            statuse.add(Main.Status7);
            statuse.add(Main.Status8);
            statuse.add(Main.Status9);
            statuse.add(Main.Status10);
        } else {
            statuse.clear();
        } return statuse;
    }
}

