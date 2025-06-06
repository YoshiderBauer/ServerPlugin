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
        if (!(sender instanceof Player)) {
            Main.log(Main.NOPERMISSION);
            return true;
        }
        fileconfig status = new fileconfig("status.yml");
        fileconfig statusConfig = new fileconfig("statusConfig.yml");
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
        } else if (Objects.equals(arg, statusConfig.getString("Status1"))) {
            status.set(player.getName(), statusConfig.getString("Status1"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status1Farbe") + "§l" +statusConfig.getString("Status1") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status1Farbe") +statusConfig.getString("Status1") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status1Farbe") +statusConfig.getString("Status1") + "§r] " + player.getName());
        } else if (Objects.equals(arg, statusConfig.getString("Status2"))) {
            status.set(player.getName(), statusConfig.getString("Status2"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status2Farbe") + "§l"+statusConfig.getString("Status2") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status2Farbe") +statusConfig.getString("Status2") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status2Farbe") +statusConfig.getString("Status2") + "§r] " + player.getName());
        } else if (Objects.equals(arg, statusConfig.getString("Status3"))) {
            status.set(player.getName(), statusConfig.getString("Status3"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status3Farbe") + "§l"+statusConfig.getString("Status3") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status3Farbe") +statusConfig.getString("Status3") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status3Farbe") +statusConfig.getString("Status3") + "§r] " + player.getName());
        } else if (Objects.equals(arg, statusConfig.getString("Status4"))) {
            status.set(player.getName(), statusConfig.getString("Status4"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status4Farbe") + "§l"+statusConfig.getString("Status4") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status4Farbe") +statusConfig.getString("Status4") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status4Farbe") +statusConfig.getString("Status4") + "§r] " + player.getName());
        } else if (Objects.equals(arg, statusConfig.getString("Status5"))) {
            status.set(player.getName(), statusConfig.getString("Status5"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status5Farbe") + "§l"+statusConfig.getString("Status5") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status5Farbe") +statusConfig.getString("Status5") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status5Farbe") +statusConfig.getString("Status5") + "§r] " + player.getName());
        } else if (Objects.equals(arg, statusConfig.getString("Status6"))) {
            status.set(player.getName(), statusConfig.getString("Status6"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status6Farbe") + "§l"+statusConfig.getString("Status6") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status6Farbe") +statusConfig.getString("Status6") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status6Farbe") +statusConfig.getString("Status6") + "§r] " + player.getName());
        } else if (Objects.equals(arg, statusConfig.getString("Status7"))) {
            status.set(player.getName(), statusConfig.getString("Status7"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status7Farbe") + "§l"+ statusConfig.getString("Status7") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status7Farbe") +statusConfig.getString("Status7") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status7Farbe") +statusConfig.getString("Status7") + "§r] " + player.getName());
        } else if (Objects.equals(arg, statusConfig.getString("Status8"))) {
            status.set(player.getName(), statusConfig.getString("Status8"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status8Farbe") + "§l"+statusConfig.getString("Status8") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status8Farbe") +statusConfig.getString("Status8") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status8Farbe") +statusConfig.getString("Status8") + "§r] " + player.getName());
        } else if (Objects.equals(arg, statusConfig.getString("Status9"))) {
            status.set(player.getName(), statusConfig.getString("Status9"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status9Farbe") + "§l"+statusConfig.getString("Status9") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status9Farbe") +statusConfig.getString("Status9") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status9Farbe") +statusConfig.getString("Status9") + "§r] " + player.getName());
        } else if (Objects.equals(arg, statusConfig.getString("Status10"))) {
            status.set(player.getName(), statusConfig.getString("Status10"));
            player.sendMessage(Main.PREFIX + "§rDein Status wurde auf " + statusConfig.getString("Status10Farbe") + "§l"+statusConfig.getString("Status10") + "§r gesetzt!");
            player.setPlayerListName("[" + statusConfig.getString("Status10Farbe") +statusConfig.getString("Status10") + "§r] " + player.getName());
            player.setDisplayName("[" + statusConfig.getString("Status10Farbe") +statusConfig.getString("Status10") + "§r] " + player.getName());
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
        fileconfig statusConfig = new fileconfig("statusConfig.yml");
        if (args.length == 1) {
            String a = args[0];
                statuse.add("afk");
                statuse.add("reset");
                statuse.add(statusConfig.getString("Status1"));
                statuse.add(statusConfig.getString("Status2"));
                statuse.add(statusConfig.getString("Status3"));
                statuse.add(statusConfig.getString("Status4"));
                statuse.add(statusConfig.getString("Status5"));
                statuse.add(statusConfig.getString("Status6"));
                statuse.add(statusConfig.getString("Status7"));
                statuse.add(statusConfig.getString("Status8"));
                statuse.add(statusConfig.getString("Status9"));
                statuse.add(statusConfig.getString("Status10"));
        } else {
            statuse.clear();
        } return statuse;
    }
}
