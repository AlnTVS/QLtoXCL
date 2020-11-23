package ru.bellintegrator.worker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApachePOISimpleWorker {
    public static void createXlsByMap(List<Map<String, List<String>>> listMap) {
//        String path = "out/" + new Date().toString() + ".xls";
//        createXlsByMap(map, path);
    }

    public static void createXlsByMap(List<Map<String, List<String>>> listMap, String path) {
        short rownum = 0;
        short cellnum = 0;
        try (FileOutputStream out = new FileOutputStream(path)) {
            Workbook wb = new HSSFWorkbook();
            Sheet s = wb.createSheet();
            Row r = null;
            Cell c = null;
            int listSize = listMap.get(0).entrySet().iterator().next().getValue().size();
            for(int i = 0; i <= listSize; i++) s.createRow(i);
            for (Map<String,List<String>> map:listMap) {
//                for (Map.Entry set:map.entrySet()) {
//                    r = s.createRow(rownum++);
//                    r.createCell(cellnum++).setCellValue(set.getKey().toString());
//                    for (String value : (List<String>) set.getValue()) {
//                        r.createCell(cellnum++).setCellValue(value);
//                    }
//                    cellnum = 0;
//                }
                for (Map.Entry set:map.entrySet()) {
                    s.getRow(0).createCell(cellnum).setCellValue(set.getKey().toString());
                    List<String> list = (List<String>)set.getValue();
                    for (int i = 0; i < listSize; i++) {
                        s.getRow(i+1).createCell(cellnum).setCellValue(list.get(i));
                    }
                    cellnum++;
                }
            }
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}