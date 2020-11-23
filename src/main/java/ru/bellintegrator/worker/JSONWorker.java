package ru.bellintegrator.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONWorker {

    public static <T> T readConfig(String pathStr, Class<T> objClass) {
        T obj = null;
        Path path = Paths.get(pathStr);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            obj = objectMapper.readValue(path.toFile(), objClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> void writeConfigs(String pathStr, T obj) {
        Path path = Paths.get(pathStr);
        ObjectMapper objMapper = new ObjectMapper();
        try {
            String json = objMapper.writeValueAsString(obj);
            FileOutputStream os = new FileOutputStream(path.toFile());
            os.write(json.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
