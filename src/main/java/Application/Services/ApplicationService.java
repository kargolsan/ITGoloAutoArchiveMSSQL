package Application.Services;

import javafx.application.Platform;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 26.08.2016
 * Time: 16:27
 */
public class ApplicationService {

    /**
     * Terminate application
     */
    public static void terminate(){
        Platform.exit();
        System.exit(0);
    }
}
