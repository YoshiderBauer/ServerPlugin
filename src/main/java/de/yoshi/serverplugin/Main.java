package de.yoshi.serverplugin;

import com.google.common.collect.Multimap;
import de.yoshi.serverplugin.commands.*;
import de.yoshi.serverplugin.listener.*;
import de.yoshi.serverplugin.utils.ItemBuilder;
import de.yoshi.serverplugin.utils.fileconfig;
import de.yoshi.serverplugin.utils.restart;
import de.yoshi.serverplugin.utils.tpsUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.item.Items;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.material.MaterialData;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Main extends JavaPlugin {
    public static Main INSTANCE;

    public Main() {
        INSTANCE = this;
    }

    static fileconfig config = new fileconfig("config.yml");
    static fileconfig afk = new fileconfig("afk.yml");
    public long lastStart;
    public static String PREFIX; //default: §7| §f§lSurvival Server §l§7x§a
    public static String NOPERMISSION = "§cDu hast keine Berechtigung diesen Command auszuführen!";

    public static void log(String text) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + text);
    }

    @Override
    public void onEnable() {
        this.setFiles();
        this.register();
        this.setCraftingRecipe();
        PREFIX = config.getString("PREFIX"); //default: §7| §f§lSurvival Server §l§7x§a

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        World world = Bukkit.getWorlds().get(0);
        if (!config.getBoolean("Start")) {
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
            if(!afk.contains(all.getName())) afk.set(all.getName(), false);
            afk.saveConfig();
        }
        lastStart = System.currentTimeMillis();

        log("Das Plugin wurde geladen.");
        if(config.getString("PREFIX") == null) log("§c§lDer Server muss neugestartet werden!");
    }

    @Override
    public void onDisable() {
        log("§cDas Plugin wurde entladen.");
    }

    private void setCraftingRecipe() {
        if (config.getBoolean("BundleRecipe")) {
            //Bundle
            ItemStack Bundle = new ItemStack(Material.BUNDLE);
            NamespacedKey key1 = new NamespacedKey(this, "Bundle");
            ShapedRecipe BundleRecipe = new ShapedRecipe(key1, Bundle);
            BundleRecipe.shape("SHS", "H H", "HHH");
            BundleRecipe.setIngredient('S', Material.STRING);
            BundleRecipe.setIngredient('H', Material.RABBIT_HIDE);
            Bukkit.addRecipe(BundleRecipe);
        }

        if(config.getBoolean("LightRecipe")){
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
        config.saveConfig();
        if(!afk.contains("Description"))afk.set("Description", "Hier werden die AFK Spieler gespeichert.");
        afk.saveConfig();
    }

    private void register() {
        //Events:
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new joinQuitListener(), this);
        pluginManager.registerEvents(new serverPing(), this);
        pluginManager.registerEvents(new gameModeListener(this), this);
        if(config.getBoolean("InvisItemFrame")) pluginManager.registerEvents(new rightClickListener(), this);
        if(config.getBoolean("CustomChat")) pluginManager.registerEvents(new chatListener(), this);
        if(config.getBoolean("SpawnElytra")) pluginManager.registerEvents(new SpawnElytra(this), this);
        //Commands:
        Bukkit.getPluginCommand("afk").setExecutor(new afkCommand());
        Bukkit.getPluginCommand("ping").setExecutor(new pingCommand());
        Bukkit.getPluginCommand("start").setExecutor(new startCommand());
        Bukkit.getPluginCommand("zeit").setExecutor(new timeCommand());
        Bukkit.getPluginCommand("ticks").setExecutor(new tpsCommand());
        if (config.getBoolean("lobby")) Bukkit.getPluginCommand("lobby").setExecutor(new lobbyCommand());
        //Scheduler:
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new tpsUtils(), 100L, 1L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new restart(this), 0, 1200);
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