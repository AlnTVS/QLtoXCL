package ru.bellintegrator.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XMLWorker {

    public static <T> T readConfig(String pathStr, Class<T> objClass) {
        T obj = null;
        Path path = Paths.get(pathStr);
        if(!FileWorker.checkIsExistFile(path)&&!FileWorker.checkFileExtension(path,"config")) return obj;
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            obj = xmlMapper.readValue(path.toFile(), objClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static <T> void writeConfigs(String pathStr, T obj) {
        Path path = Paths.get(pathStr);
        if(!FileWorker.checkIsExistFile(path)) return;

        ObjectMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(path.toFile(),obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
