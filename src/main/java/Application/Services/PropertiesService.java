package Application.Services;

import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 26.08.2016
 * Time: 22:25
 */
public class PropertiesService {

    /**
     * Get property from files properties
     *
     * @param key in properties file
     * @return value of key
     */
    public static String get(String key, String propertiesPath) {
        Properties prop = new Properties();
        try {
            prop.load(PropertiesService.class.getClassLoader().getResourceAsStream(propertiesPath));
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            LogManager.getLogger().error(e.getMessage());
            return key;
        }
    }
}
