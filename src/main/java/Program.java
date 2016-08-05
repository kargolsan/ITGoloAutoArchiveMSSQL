import Modules.Application.Models.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class Program {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * Main function for application
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Application.class, args);
    }
}
