package Application.Services;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 19.08.2016
 * Time: 18:02
 */
public class LanguageService {

    /**
     * Get resource bundle
     *
     * @param bundleResource string for localize files of resource bundle
     * @return resource bundle
     */
    public static ResourceBundle getResourceBundle(String bundleResource) {
        Locale locale = Locale.getDefault();
        return DecodeUtf8PropertiesService.getBundle(bundleResource, locale);
    }
}
