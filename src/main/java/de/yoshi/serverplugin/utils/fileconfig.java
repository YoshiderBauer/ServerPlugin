package de.yoshi.serverplugin.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.FileNotFoundException;
import java.io.IOException;

public class fileconfig extends YamlConfiguration {

    private String path;

    public fileconfig(String folder, String fileName){
        this.path = "plugins/" + folder + "/" + fileName;

        try{
            load(this.path);
        } catch (IOException | InvalidConfigurationException ex){
            ex.printStackTrace();
        }
    }

    public fileconfig(String fileName){
        this("ServerPlugin", fileName);
    }


    public void saveConfig(){
        try{
            save(this.path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
