import Application.Stages.Main;
import Application.Stages.Loader;
import com.sun.javafx.application.LauncherImpl;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class start {

    /**
     * Main function for application
     *
     * @param args
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, Loader.class, args);
    }
}
