package ru.bellintegrator;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import ru.bellintegrator.worker.InfluxDBWorker;
import ru.bellintegrator.worker.JXLCWorker;

import java.io.*;
import java.util.*;

/**
 * Отдельно отлаживал работы с influxDB и JXLC
 * */

public class MainApp {
    public static void main(String[] args) {
//        String sttt = "SELECT\n" +
//                "Mode(\"transaction\"),\n" +
//                "min(min) as Min,\n" +
//                "mean(avg) as Average,\n" +
//                "max(max) as Max,\n" +
//                "percentile(\"pct90.0\", 90) as pct90\n" +
//                "FROM \"jmeter\"\n" +
//                "WHERE \"statut\"= 'all'\n" +
//                "AND time >= 1604414385218ms and time <= 1604418305672ms\n" +
//                "GROUP BY \"transaction\"";
//        Map<String, List<String>> valuesMap0 = InfluxDBWorker.getMapQueryResultWithGroupBy(sttt);
        String str = "SELECT\n" +
                "MIN(min) AS Min,\n" +
                "MEAN(avg) AS Average,\n" +
                "MAX(max) AS Max,\n" +
                "percentile(\"pct90.0\",90) AS pct90,\n" +
                "COUNT(count) AS Total\n" +
                "FROM \"jmeter\" \n" +
                "WHERE transaction =~/uc0[1-2]_pacing/ \n" +
                "AND time >= 1604414385218ms and time <= 1604418305672ms \n" +
                "GROUP BY transaction";

        String str2 = "SELECT\n" +
                "COUNT(count) as Error\n" +
                "FROM \"jmeter\" \n" +
                "WHERE transaction =~/uc0[1-2]_pacing/ \n" +
                "AND transaction !~ /(all|\\/)/\n" +
                "AND time >= 1604414385218ms and time <= 1604418305672ms \n" +
                "AND \"statut\"='ko'\n" +
                "GROUP BY transaction";

        String str3 = "SHOW " +
                "TAG VALUES " +
                "FROM \"jmeter\" " +
                "WITH KEY = \"transaction\" " +
                "WHERE transaction =~/uc0[1-2]_pacing/ " +
                "AND time >= 1604414385218ms and time <= 1604418305672ms";

//        Map<String, List<String>> valuesMap = InfluxDBWorker.getMapQueryResultWithGroupBy(str);
//        Map<String, List<String>> valuesMap2 = InfluxDBWorker.getMapQueryResultWithGroupBy(str2);
//        Map<String, List<String>> valuesMap3 = InfluxDBWorker.getMapQueryKeyResult(str3);
//        List<Map> mapList = new ArrayList<>();
//        mapList.add(valuesMap);
//        mapList.add(valuesMap2);
//        mapList.add(valuesMap3);
//
//        Context context = new Context();
//        for (Map map : mapList) {
//            JXLCWorker.putMapToContext(map, context);
//        }
//        String tempPath = "templates\\transaction_template.xls";
//        String outPaht = "templates\\target\\transaction_output.xls";
//        JXLCWorker.createXLC(tempPath,outPaht,context);
//    }
//
//    private static String queryTransactionName(String seriesName) {
//        return "SELECT transaction FROM " + seriesName + " WHERE transaction =~/uc0[1-2]_pacing/;";
    }
}
