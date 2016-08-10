package Modules.Application.Controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class ApplicationController implements Initializable {

    @FXML
    private Button button;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void helloClicked() {
        System.out.println("Hello!");
    }
}
