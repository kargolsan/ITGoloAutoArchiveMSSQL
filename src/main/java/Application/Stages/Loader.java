package Application.Stages;

import Application.Services.StageService;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.application.Preloader;
import Application.Services.LanguageService;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class Loader extends Preloader {

    /** Stage icon */
    private static final String ICON = "/Application/Resources/Assets/Images/Icons/app.png";

    /** Path to stage view */
    private static final String VIEW = "/Application/Resources/Views/LoaderView.fxml";

    /** Path to language application*/
    private static final String LANGUAGE = "Application/Resources/Languages/application";

    /** Stage */
    private static Stage stage;

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage main of application
     */
    @Override
    public void start(Stage stage) {
        this.stage = StageService.show(stage, VIEW, LANGUAGE, StageStyle.UNDECORATED, ICON);
    }

    /**
     * Get stage
     *
     * @return stage
     */
    public static void hide() {
        stage.hide();
    }


}
