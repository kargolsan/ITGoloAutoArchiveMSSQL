package Modules.Translations.Models;

import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Modules.Translations.Interfaces.ITranslation;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class Translation extends Utf8ResourceBundle implements ITranslation {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * Get resource bundle
     *
     * @param bundleResource string for localize files of resource bundle
     * @return resource bundle
     */
    public ResourceBundle getResourceBundle(String bundleResource) {

        Locale locale = Locale.getDefault();
        ResourceBundle resource;

        try {
            resource = getBundle(bundleResource, locale);
            return resource;
        } catch (Exception ex)
        {
            logger.fatal(ex.getMessage());
        }

        return null;
    }
}



