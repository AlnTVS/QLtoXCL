package ru.bellintegrator.worker;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import ru.bellintegrator.model.ConfigSQL;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InfluxDBWorker {
    private static String url = "http://localhost:8086";
    private static String username = "pc_influx_admin";
    private static String password = "password";
    private static String dbName = "Test_1";
    private static InfluxDB influxDB = null;

    private static InfluxDB getInfluxDB() {
        influxDB = InfluxDBFactory.connect(url, username, password);
        influxDB.setDatabase(dbName);
        return influxDB;
    }


    private static QueryResult query(String str) {
        initializeInfluxDB();
        QueryResult queryResult = influxDB.query(new Query(str, dbName));
        if (queryResult == null) {
            System.err.println("queryResult is null");
        }
        return queryResult;
    }

    private static void initializeInfluxDB() {
        if (influxDB == null) {
            influxDB = InfluxDBWorker.getInfluxDB();
        }
    }

    private static Map<String, List<String>> getMapQueryResultWithGroupBy(String query) {
        Map<String, List<String>> valuesMap = new LinkedHashMap<>();
        QueryResult queryResult = query(query);
        queryResult.getResults().get(0).getSeries().get(0).getColumns().forEach(c -> valuesMap.put(c, new ArrayList<>()));
        int resultSize = queryResult.getResults().get(0).getSeries().size();
        List<String> tmpListOfValues = new ArrayList<>();
        for (int i = 0; i < resultSize; i++) {
            queryResult.getResults().get(0).getSeries().get(i).getValues().get(0).forEach(v -> tmpListOfValues.add(v.toString()));
            int j = 0;
            for (Map.Entry entry : valuesMap.entrySet()) {
                ((List) entry.getValue()).add(tmpListOfValues.get(j++));
            }
            tmpListOfValues.clear();
        }
        return valuesMap;
    }

    private static Map<String, List<String>> getMapQueryKeyResult(String query) {
        Map<String, List<String>> valuesMap = new LinkedHashMap<>();
        QueryResult queryResult = query(query);
        valuesMap.put("Name",new ArrayList<>());
        queryResult.getResults().get(0).getSeries().get(0).getValues().forEach(v->valuesMap.get("Name").add(v.get(1).toString()));
        return valuesMap;
    }

    public static void getListMapsFromInfluxDB(List<Map<String,List<String>>> listMap, ConfigSQL configSQL) {
        for (List<String> list : configSQL.getSqlList()) {
            list.stream()
                    .filter(s -> s.startsWith(ConfigSQL.typeString.sqlText.toString()))
                    .forEach(s -> listMap
                            .add(InfluxDBWorker.getMapQueryResultWithGroupBy(s
                                    .substring((ConfigSQL.typeString.sqlText.toString()).length() + 1))));
            list.stream()
                    .filter(s -> s.startsWith(ConfigSQL.typeString.tagName.toString()))
                    .forEach(s -> listMap
                            .add(InfluxDBWorker.getMapQueryKeyResult(s
                                    .substring((ConfigSQL.typeString.tagName.toString()).length() + 1))));
        }
    }
}
