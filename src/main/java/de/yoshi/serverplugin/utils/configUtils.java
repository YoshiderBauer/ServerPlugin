package de.yoshi.serverplugin.utils;

public class configUtils {
    public static boolean getBoolean(fileconfig fileconfig, String path, boolean standart){
        try {
            return fileconfig.getBoolean(path);
        } catch (Exception e){
            fileconfig.set(path, standart);
            fileconfig.saveConfig();
            return fileconfig.getBoolean(path);
        }
    }

    public static int getInt(fileconfig fileconfig, String path, int standart){
        try {
            return fileconfig.getInt(path);
        } catch (Exception e){
            fileconfig.set(path, standart);
            fileconfig.saveConfig();
            return fileconfig.getInt(path);
        }
    }

    public static double getDouble(fileconfig fileconfig, String path, double standart){
        try {
            return fileconfig.getDouble(path);
        } catch (Exception e){
            fileconfig.set(path, standart);
            fileconfig.saveConfig();
            return fileconfig.getDouble(path);
        }
    }

    public static long getLong(fileconfig fileconfig, String path, long standart){
        try {
            return fileconfig.getLong(path);
        } catch (Exception e){
            fileconfig.set(path, standart);
            fileconfig.saveConfig();
            return fileconfig.getLong(path);
        }
    }

    public static String getString(fileconfig fileconfig, String path, String standart){
        try {
            return fileconfig.getString(path);
        } catch (Exception e){
            fileconfig.set(path, standart);
            fileconfig.saveConfig();
            return fileconfig.getString(path);
        }
    }
}
