package ru.bellintegrator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ConfigSQL {
    private List<List<String>> sqlList = new ArrayList<>();
    public enum typeString {sqlText, tagName}

//    public ConfigSQL(){
//        sqlList.add(new ArrayList<>());
//        sqlList.add(new ArrayList<>());
//        sqlList.get(0).add(typeString.sqlText.toString() + "=SELECT " +
//                "MIN(min) AS Min, " +
//                "MEAN(avg) AS Average, " +
//                "MAX(max) AS Max," +
//                "percentile(\"pct90.0\",90) AS pct90, " +
//                "COUNT(count) AS Total " +
//                "FROM \"jmeter\" " +
//                "WHERE transaction =~/uc0[1-2]_pacing/ " +
//                "AND time >= 1604414385218ms and time <= 1604418305672ms " +
//                "GROUP BY transaction");
//        sqlList.get(0).add(typeString.sqlText.toString() + "=SELECT " +
//                "COUNT(count) as Error " +
//                "FROM \"jmeter\" " +
//                "WHERE transaction =~/uc0[1-2]_pacing/ " +
//                "AND transaction !~ /(all|\\/)/ " +
//                "AND time >= 1604414385218ms and time <= 1604418305672ms " +
//                "AND \"statut\"='ko' " +
//                "GROUP BY transaction");
//        sqlList.get(0).add(typeString.tagName.toString() + "=transaction");
//
//        sqlList.get(1).add(typeString.sqlText.toString() + "=SELECT " +
//                "MIN(min) AS Min, " +
//                "MEAN(avg) AS Average, " +
//                "MAX(max) AS Max," +
//                "percentile(\"pct90.0\",90) AS pct90, " +
//                "COUNT(count) AS Total " +
//                "FROM \"jmeter\" " +
//                "WHERE transaction =~/uc0[1-2]_pacing/ " +
//                "AND time >= 1604414385218ms and time <= 1604418305672ms " +
//                "GROUP BY transaction");
//    }
}
