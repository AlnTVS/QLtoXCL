package ru.bellintegrator;

import org.jxls.common.Context;
import ru.bellintegrator.controller.ConfigController;
import ru.bellintegrator.model.Config;
import ru.bellintegrator.model.ConfigSQL;
import ru.bellintegrator.worker.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.bellintegrator.worker.InfluxDBWorker.getListMapsFromInfluxDB;

/**
 * Основная программа
 */

public class Main {

    public static void main(String[] args) {
        CommonCliWorker.start(args);
        if (!CommonCliWorker.isHelp()) {
            Config config = ConfigController.getConfig();
            // не десериализуется
            ConfigSQL configSQL = ConfigWorker.readConfig(config.getParameter(Config.Parameters.configSQL), ConfigSQL.class);
            List<Map<String,List<String>>> listMap = new ArrayList<>();
            dbSelector(config,listMap,configSQL);
            writerTypeSelector(config,listMap);
        }
    }

    private static void writerTypeSelector(Config config, List<Map<String, List<String>>> listMap) {
        if(Boolean.parseBoolean(config.getParameter(Config.Parameters.isUseTemplate))) {
            Context context = new Context();
            listMap.stream().forEach(m -> JXLCWorker.putMapToContext(m, context));
            JXLCWorker.createXLC(
                    config.getParameter(Config.Parameters.template),
                    config.getParameter(Config.Parameters.resultXlsx),
                    context);
        } else {
            ApachePOISimpleWorker.createXlsByMap(listMap,config.getParameter(Config.Parameters.resultXlsx));
        }
    }

    private static void dbSelector(Config config, List<Map<String,List<String>>> listMap, ConfigSQL configSQL) {
        if (config.getParameter(Config.Parameters.dbType).equals("influxDB")) {
            getListMapsFromInfluxDB(listMap, configSQL);
        } else if (config.getParameter(Config.Parameters.dbType).equals("NOTinfluxDB")) {

        } else
            System.err.println("Config file incorrect: dbType=" + config.getParameter(Config.Parameters.dbType) + " doesn't support");
    }


}
