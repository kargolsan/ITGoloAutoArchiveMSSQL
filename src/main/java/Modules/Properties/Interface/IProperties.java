package Modules.Properties.Interface;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public interface IProperties {

    /**
     * Get property from file with properties
     *
     * @param key of property
     * @return value of property
     */
    String getProperty(String key);

    /**
     * Get path to file with properties
     *
     * @return path to files with properties
     */
    String getPath();
}
