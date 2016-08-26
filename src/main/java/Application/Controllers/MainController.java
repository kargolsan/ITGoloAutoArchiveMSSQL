package Application.Controllers;

import java.net.URL;

import Application.Stages.Loader;
import Application.Stages.Main;
import com.sun.javafx.application.LauncherImpl;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class MainController implements Initializable {

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        Loader.hide();
    }

    /**
     * Launch main stage with loader view
     *
     * @param args arguments when the program starts
     */
    public static void launch(String[] args){
        LauncherImpl.launchApplication(Main.class, Loader.class, args);
    }
}
