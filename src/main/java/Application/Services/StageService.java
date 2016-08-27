package Application.Services;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 26.08.2016
 * Time: 21:53
 */
public class StageService {

    /**
     * Create and show window stage
     *
     * @param stage object
     * @param viewPath stage
     * @param languagePath of resource bundle
     * @param style for window stage
     * @param iconPath for window stage
     * @return created and configuration stage
     */
    public static Stage show(Stage stage, String viewPath, String
                             languagePath, StageStyle style, String iconPath){
        try {
            VBox page = FXMLLoader.load(StageService.class.getResource(viewPath),
                    LanguageService.getResourceBundle(languagePath));
            Scene scene = new Scene(page);
            stage.initStyle(style);
            stage.setScene(scene);
            stage.getIcons().add(new Image(StageService.class.getResourceAsStream(iconPath)));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            LogManager.getLogger().error(e.getMessage());
        }
        return stage;
    }
}
