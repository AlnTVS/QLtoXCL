package ru.bellintegrator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class Config {
    private Map<Parameters, String> config = new LinkedHashMap<>();

    public enum Parameters {
        timeInfluxDB,
        timeFormat,
        dbType,
        isWriteResultHead,
        isUseTemplate,
        template,
        configSQL,
        resultXlsx,
        resultCsv
    }

    public Config() {
        config.put(Parameters.timeInfluxDB, "");
        config.put(Parameters.timeFormat, "");
        config.put(Parameters.dbType, "");
        config.put(Parameters.isWriteResultHead, "");
        config.put(Parameters.isUseTemplate, "");
        config.put(Parameters.template, "");
        config.put(Parameters.configSQL, "");
        config.put(Parameters.resultXlsx, "");
        config.put(Parameters.resultCsv, "");
    }

    public void setParameter(Parameters param, String value) {
        config.put(param, value);
    }

    public String getParameter(Parameters param) {
        return config.get(param);
    }
}
