package zw.co.els.englishlearningsystem.utils;

import java.io.InputStream;
import java.util.Properties;
public class Config {

    private final Properties properties = new Properties();
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

    private static final class InstanceHolder {
        private static final Config instance = new Config();
    }

    public static Config getInstance() {
        return InstanceHolder.instance;
    }
}
