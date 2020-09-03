package main.java.booking.utils;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    @SneakyThrows
    public static void loadConfigFile() {
        FileInputStream inputStream = new FileInputStream("src/test/config.properties");
        properties.load(inputStream);
    }

    public static String getConfig(String key) {
        return properties.getProperty(key);
    }

}
