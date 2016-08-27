package Application.Stages;

import Application.Services.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Properties;
import javafx.stage.StageStyle;
import java.util.ResourceBundle;
import Application.Classes.Async;
import Modules.Trays.Models.Tray;
import Modules.Trays.Interfaces.ITray;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Modules.Translations.Models.Translation;
import Modules.MSSQL.Interfaces.IBackupDBController;
import Modules.MSSQL.Controllers.BackupDBController;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: Aug 17, 2016
 * Time: 11:15:10 AM
 */
public class Main extends javafx.application.Application {

    /** Stage icon */
    private static final String ICON = "/Application/Resources/Assets/Images/Icons/app.png";

    /** Path to stage view */
    private static final String VIEW = "/Modules/Application/Resources/Views/ApplicationView.fxml";

    /** Path to language application*/
    private static final String LANGUAGE = "Modules/Application/Resources/Languages/application";

    /** Path to properties application */
    private static final String PROPERTIES = "Application/Resources/Properties/application.properties";

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Async().run(()->{
            LoaderService.load();
        },()->{
            createStage(stage);
            IBackupDBController backupDB = new BackupDBController();
            backupDB.run();
        });
    }

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     */
    @Override
    public void stop() throws Exception {
        ApplicationService.exit();
    }

    /**
     * Create stage of main
     *
     * @param stage of main
     */
    public void createStage(Stage stage){
        String title = PropertiesService.get("title", PROPERTIES);
        TrayService.setTray(stage, title, ICON, "Application/Resources/Languages/application");
        stage.setTitle(String.format("%1$s - v%2$s", title, PropertiesService.get("version", PROPERTIES)));
        StageService.show(stage, VIEW, LANGUAGE, StageStyle.DECORATED, ICON);
    }
}
