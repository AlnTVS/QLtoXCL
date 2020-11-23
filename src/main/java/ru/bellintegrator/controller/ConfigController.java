package ru.bellintegrator.controller;

import ru.bellintegrator.model.Config;
import ru.bellintegrator.worker.XMLWorker;

public class ConfigController {
    private static String path = "Config.config";
    private static Config config = null;

    public static Config getConfig() {
        if(config==null) {
            initConfig();
        }
        return config;
    }

    private static void initConfig() {
        config = XMLWorker.readConfig(path,Config.class);
    }

    public static void setConfig(String strPath) {
        config = XMLWorker.readConfig(strPath,Config.class);
    }

    public static void setConfigSQL(String strPath) {
        config.setParameter(Config.Parameters.configSQL,strPath);
    }

    public static void setTimeInfluxDB(String argument) {
        config.setParameter(Config.Parameters.timeInfluxDB,argument);
    }

    public static void setResultXlsx(String argument) {
        config.setParameter(Config.Parameters.resultXlsx,argument);
    }

    public static void setResultCsv(String argument) {
        // code...
    }

    public static void setTemplate(String argument) {
        config.setParameter(Config.Parameters.template,argument);
    }
}
