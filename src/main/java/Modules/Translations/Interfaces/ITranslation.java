package Modules.Translations.Interfaces;

import java.util.ResourceBundle;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public interface ITranslation {
    /**
     * Get resource bundle
     *
     * @param bundleResource string for localize files of resource bundle
     * @return resource bundle
     */
    ResourceBundle getResourceBundle(String bundleResource);
}
