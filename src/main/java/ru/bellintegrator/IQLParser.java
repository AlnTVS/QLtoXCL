package ru.bellintegrator;

import ru.bellintegrator.model.IQLModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IQLParser {
    public static IQLModel parseToModel(String query) {
        IQLModel iqlModel = new IQLModel();
        iqlModel.setColumns(extractColumns(query));
        iqlModel.setSchema(extractSchema(query));
        return iqlModel;
    }

    private static List<String> extractColumns(String query) {
        query = query.trim();
        List<String> columnsList = new ArrayList<>();
        int startIndex = query.indexOf("SELECT");
        int endIndex = query.indexOf("FROM");
        String columns = query.substring(startIndex + 7, endIndex - 1);
        Arrays.stream(columns.split("\n")).forEach(columnsList::add);
        System.out.println(columnsList);
        return normilizeColumnsName(columnsList);
    }

    private static List<String> normilizeColumnsName(List<String> columnsList) {
        int startIndex;
        int endIndex;
        List<String> columnsName = new ArrayList<>();
        for (String str:columnsList) {
            if(str.contains(" AS ")){
                startIndex = str.indexOf(" AS ");
                columnsName.add(str.substring(startIndex+4).split("\\W",2)[0]);
            }
        }
        return columnsName;
    }

    private static String extractSchema(String query) {
        query = query.toUpperCase().trim();
        String schema;
        int startIndex = query.indexOf("FROM");
        int endIndex = query.indexOf("WHERE");
        schema = query.substring(startIndex+5,endIndex-1).trim();
        System.out.println("---" + schema + "---");
        return schema;
    }
}
