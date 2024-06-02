package zw.co.els.englishlearningsystem.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class Config {

    private static Config instance;
    private Properties properties = new Properties();
    public Config() {
        loadProperties();
    }
    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application-dev.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find properties");
                return;
            }
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Config getInstance() {
        if (instance == null)synchronized (Config.class) {
            if (instance == null) {
                instance = new Config();
            }
        }
        return instance;
    }
}
