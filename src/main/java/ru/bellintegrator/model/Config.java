package ru.bellintegrator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
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

    public void setParameter(Parameters param, String value) {
        config.put(param, value);
    }

    public String getParameter(Parameters param) {
        return config.get(param);
    }
}
