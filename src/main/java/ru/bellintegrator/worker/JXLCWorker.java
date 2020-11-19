package ru.bellintegrator.worker;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.util.List;
import java.util.Map;

public class JXLCWorker {

    public static void putMapToContext(Map<String, List<String>> map, Context context) {
        for (Map.Entry entry : map.entrySet()) {
            context.putVar(entry.getKey().toString(), entry.getValue());
        }
    }

    public static void createXLC(String templatePath, String outputPath, Context context) {
        try (InputStream is = new FileInputStream(templatePath)) {
            try (OutputStream os = new FileOutputStream(outputPath)) {
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
