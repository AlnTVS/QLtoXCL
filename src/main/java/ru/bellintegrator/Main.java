package ru.bellintegrator;

import org.jxls.common.Context;
import ru.bellintegrator.controller.ConfigController;
import ru.bellintegrator.model.Config;
import ru.bellintegrator.model.ConfigSQL;
import ru.bellintegrator.worker.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Основная программа
 * */

public class Main {

    public static void main(String[] args) {
        CommonCliWorker.start(args);
        if(!CommonCliWorker.isHelp()) {
            Config config = ConfigController.getConfig();
            if(config.getParameter(Config.Parameters.dbType).equals("influxDB")){
                // не десериализуется
                ConfigSQL configSQL = ConfigWorker.readConfig(config.getParameter(Config.Parameters.configSQL),ConfigSQL.class);
                List<Map> listMap = new ArrayList<>();
                for (List<String> list:configSQL.getSqlList()) {
                    list.stream()
                            .filter(s->s.startsWith(ConfigSQL.typeString.sqlText.toString()))
                            .forEach(s -> listMap
                                    .add(InfluxDBWorker.getMapQueryResultWithGroupBy(s
                                            .substring((ConfigSQL.typeString.sqlText.toString()).length()+1))));
                    list.stream()
                            .filter(s->s.startsWith(ConfigSQL.typeString.tagName.toString()))
                            .forEach(s -> listMap
                                    .add(InfluxDBWorker.getMapQueryKeyResult(s
                                            .substring((ConfigSQL.typeString.tagName.toString()).length()+1))));
                }
                Context context = new Context();
                listMap.stream().forEach(m->JXLCWorker.putMapToContext(m,context));
                JXLCWorker.createXLC(
                        config.getParameter(Config.Parameters.template),
                        config.getParameter(Config.Parameters.resultXlsx),
                        context);
            }
        }
    }
}
