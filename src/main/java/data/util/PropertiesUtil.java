package data.util;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    private PropertiesUtil () {  }

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (var inputStream =
                     PropertiesUtil.class.getClassLoader().getResourceAsStream("file.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}