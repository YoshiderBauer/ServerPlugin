package de.yoshi.serverplugin;

import de.yoshi.serverplugin.commands.*;
import de.yoshi.serverplugin.listener.*;
import de.yoshi.serverplugin.utils.fileconfig;
import de.yoshi.serverplugin.utils.restart;
import de.yoshi.serverplugin.utils.tpsUtils;
import de.yoshi.serverplugin.utils.configUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public final class Main extends JavaPlugin {
    public static Main INSTANCE;

    public Main() {
        INSTANCE = this;
    }

    static fileconfig config;
    static fileconfig status;
    static fileconfig statusConfig;
    public long lastStart;
    public static String PREFIX; //default: §7| §f§lSurvival Server §l§7x§a
    public static String NOPERMISSION = "§cDu hast keine Berechtigung diesen Command auszuführen!";

    public static void log(String text) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + text);
    }

    @Override
    public void onEnable() {
        try {
            config = new fileconfig("config.yml");
        } catch (Exception e) {}
        try {
            status = new fileconfig("status.yml");
        } catch (Exception e) {}
        try {
            statusConfig = new fileconfig("statusConfig.yml");
        } catch (Exception e) {}
        this.setFiles();
        this.register();
        this.setCraftingRecipe();

        PREFIX = configUtils.getString(config, "PREFIX", "§7| §f§lSurvival Server §l§7x§a "); //default: §7| §f§lSurvival Server §l§7x§a

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        World world = Bukkit.getWorlds().get(0);
        if (!configUtils.getBoolean(config, "Start", false)) {
            world.setDifficulty(Difficulty.PEACEFUL);
            Bukkit.getServer().setDefaultGameMode(GameMode.ADVENTURE);
            world.getWorldBorder().setCenter(world.getSpawnLocation());
            world.getWorldBorder().setSize(config.getDouble("SpawnRadius"));
        } else {
            world.setDifficulty(Difficulty.HARD);
            Bukkit.getServer().setDefaultGameMode(GameMode.SURVIVAL);
            world.getWorldBorder().setCenter(world.getSpawnLocation());
            world.getWorldBorder().setSize(29999984.0);
        }

        for(OfflinePlayer all : Bukkit.getWhitelistedPlayers()){
            if(!status.contains(all.getName())) status.set(all.getName(), "reset");
            status.saveConfig();
        }
        lastStart = System.currentTimeMillis();

        log("Das Plugin wurde geladen.");
        if(configUtils.getString(config,"PREFIX", "§7| §f§lSurvival Server §l§7x§a ") == null) log("§c§lDer Server muss neugestartet werden!");
    }

    @Override
    public void onDisable() {
        log("§cDas Plugin wurde entladen.");
    }

    private void setCraftingRecipe() {
        if (configUtils.getBoolean(config,"BundleRecipe", true)) {
            //Bundle
            ItemStack Bundle = new ItemStack(Material.BUNDLE);
            NamespacedKey key1 = new NamespacedKey(this, "Bundle");
            ShapedRecipe BundleRecipe = new ShapedRecipe(key1, Bundle);
            BundleRecipe.shape("SHS", "H H", "HHH");
            BundleRecipe.setIngredient('S', Material.STRING);
            BundleRecipe.setIngredient('H', Material.RABBIT_HIDE);
            Bukkit.addRecipe(BundleRecipe);
        }

        if(configUtils.getBoolean(config, "LightRecipe", true)){
            //Light Block
            ItemStack LightBlock = new ItemStack(Material.LIGHT);
            LightBlock.setAmount(2);
            NamespacedKey key1 = new NamespacedKey(this, "Light");
            ShapedRecipe BundleRecipe = new ShapedRecipe(key1, LightBlock);
            BundleRecipe.shape("GGG", "GTG", "GGG");
            BundleRecipe.setIngredient('G', Material.GLASS_PANE);
            BundleRecipe.setIngredient('T', Material.TORCH);
            Bukkit.addRecipe(BundleRecipe);
        }
    }

    private void setFiles() {
        if(!config.contains("PREFIX")) config.set("PREFIX", "§7| §f§lSurvival Server §l§7x§a ");
        if(!config.contains("lobbyCommand"))config.set("lobbyCommand", false);
        if(!config.contains("tpsCommand"))config.set("tpsCommand", true);
        if(!config.contains("showPing"))config.set("showPing", false);
        if(!config.contains("Motd1"))config.set("Motd1", "Yoshi_der_Bauer ist der Beste!");
        if(!config.contains("Motd2"))config.set("Motd2", "Der hackt, der hackt, der hackt!");
        if(!config.contains("Motd3"))config.set("Motd3", "Ich mag Kakteen!");
        if(!config.contains("Motd4"))config.set("Motd4", "Ich bin schon wieder tot!");
        if(!config.contains("Motd5"))config.set("Motd5", "Sound.Player.Death");
        if(!config.contains("Motd6"))config.set("Motd6", "§eHerobrian has joined the game.");
        if(!config.contains("Motd7"))config.set("Motd7", "Tot, Halbes!");
        if(!config.contains("SpawnElytra"))config.set("SpawnElytra", true);
        if(!config.contains("SpawnElytraBoost"))config.set("SpawnElytraBoost", 5);
        if(!config.contains("SpawnRadius"))config.set("SpawnRadius", 45);
        if(!config.contains("BundleRecipe"))config.set("BundleRecipe", true);
        if(!config.contains("LightRecipe"))config.set("LightRecipe", true);
        if(!config.contains("InvisItemFrame"))config.set("InvisItemFrame", true);
        if(!config.contains("Start"))config.set("Start", false);
        if(!config.contains("CustomChat"))config.set("CustomChat", true);
        if(!config.contains("Auto-Restart delay"))config.set("Auto-Restart delay", 120);
        if(!config.contains("DeathCounter"))config.set("DeathCounter", true);
        config.saveConfig();
        if(!status.contains("Description")) status.set("Description", "Hier werden die Statusse der Spieler gespeichert.");
        status.saveConfig();
        if(!statusConfig.contains("Description")) statusConfig.set("Description", "Hier können bis zu 10 Statusse für Spieler hinzugefügt werden.");
        if(!statusConfig.contains("Status1")) statusConfig.set("Status1", "Status1");
        if(!statusConfig.contains("Status2")) statusConfig.set("Status2", "Status2");
        if(!statusConfig.contains("Status3")) statusConfig.set("Status3", "Status3");
        if(!statusConfig.contains("Status4")) statusConfig.set("Status4", "Status4");
        if(!statusConfig.contains("Status5")) statusConfig.set("Status5", "Status5");
        if(!statusConfig.contains("Status6")) statusConfig.set("Status6", "Status6");
        if(!statusConfig.contains("Status7")) statusConfig.set("Status7", "Status7");
        if(!statusConfig.contains("Status8")) statusConfig.set("Status8", "Status8");
        if(!statusConfig.contains("Status9")) statusConfig.set("Status9", "Status9");
        if(!statusConfig.contains("Status10")) statusConfig.set("Status10", "Status10");
        if(!statusConfig.contains("Status1Farbe")) statusConfig.set("Status1Farbe", "§r");
        if(!statusConfig.contains("Status2Farbe")) statusConfig.set("Status2Farbe", "§r");
        if(!statusConfig.contains("Status3Farbe")) statusConfig.set("Status3Farbe", "§r");
        if(!statusConfig.contains("Status4Farbe")) statusConfig.set("Status4Farbe", "§r");
        if(!statusConfig.contains("Status5Farbe")) statusConfig.set("Status5Farbe", "§r");
        if(!statusConfig.contains("Status6Farbe")) statusConfig.set("Status6Farbe", "§r");
        if(!statusConfig.contains("Status7Farbe")) statusConfig.set("Status7Farbe", "§r");
        if(!statusConfig.contains("Status8Farbe")) statusConfig.set("Status8Farbe", "§r");
        if(!statusConfig.contains("Status9Farbe")) statusConfig.set("Status9Farbe", "§r");
        if(!statusConfig.contains("Status10Farbe")) statusConfig.set("Status10Farbe", "§r");
        statusConfig.saveConfig();
    }

    private void register() {
        //Events:
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new joinQuitListener(), this);
        pluginManager.registerEvents(new serverPing(), this);
        pluginManager.registerEvents(new gameModeListener(this), this);
        if(configUtils.getBoolean(config,"InvisItemFrame", true)) pluginManager.registerEvents(new rightClickListener(), this);
        if(configUtils.getBoolean(config,"CustomChat", false)) pluginManager.registerEvents(new chatListener(), this);
        if(configUtils.getBoolean(config,"SpawnElytra", true)) pluginManager.registerEvents(new SpawnElytra(this), this);
        //Commands:
        Bukkit.getPluginCommand("ping").setExecutor(new pingCommand());
        Bukkit.getPluginCommand("start").setExecutor(new startCommand());
        Bukkit.getPluginCommand("zeit").setExecutor(new timeCommand());
        Bukkit.getPluginCommand("ticks").setExecutor(new tpsCommand());
        Bukkit.getPluginCommand("status").setExecutor(new statusCommand());
        Bukkit.getPluginCommand("lobby").setExecutor(new lobbyCommand());
        Bukkit.getPluginCommand("cam").setExecutor(new camCommand());
        //Scheduler:
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new tpsUtils(), 100L, 1L);
        if(configUtils.getInt(config,"Auto-Restart delay", 120) != 0) Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new restart(this), 0, 1200);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this,new PlayerList(), 0, 20);
    }

    public static void sendServer(Player player, String server) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        try {
            dataOutputStream.writeUTF("Connect");
            dataOutputStream.writeUTF(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(Main.INSTANCE, "BungeeCord", byteArrayOutputStream.toByteArray());
        log(player.getName() + " wurde zu " + server + " gesendet!");
    }

}