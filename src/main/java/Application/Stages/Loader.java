package Application.Stages;

import Application.Services.LanguageService;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class Loader extends Preloader {

    /**
     * Stage loader view
     */
    private static Stage stage;

    /** Path to stage view */
    private static final String VIEW = "/Application/Resources/Views/LoaderView.fxml";

    /** Path to language application*/
    private static final String LANGUAGE = "Application/Resources/Languages/application";

    /** Stage icon */
    private static final String ICON = "/Application/Resources/Assets/Images/Icons/app.png";

    /** Get stage of view */
    public static Stage getStage() {
        return stage;
    }

    /** Set stage of view */
    public static void setStage(Stage stage) {
        Loader.stage = stage;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage main of application
     */
    public void start(Stage stage) {
        setStage(stage);
        try {
            VBox page = FXMLLoader.load(getClass().getResource(VIEW), LanguageService.getResourceBundle(LANGUAGE));
            Scene scene = new Scene(page);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream(ICON)));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle stage change notification
     *
     * @param stateChangeNotification
     */
    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            //Loader.stage.hide();
        }
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
