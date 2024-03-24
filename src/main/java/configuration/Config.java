package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("Configuration file 'config.properties' not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration file", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
    public static String getToken() {
        return properties.getProperty("token");
    }
    public static String getApiKey() {
        return properties.getProperty("apiKey");
    }
}