package Modules.Application.Models;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.util.ResourceBundle;
import Modules.Trays.Models.Tray;
import Modules.Trays.Interfaces.ITray;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Modules.Database.Models.SessionFactory;
import Modules.Translations.Models.Translation;
import Modules.MSSQL.Interfaces.IBackupDBController;
import Modules.MSSQL.Controllers.BackupDBController;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class Application extends javafx.application.Application {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    public void start(Stage primaryStage) throws Exception {
        try {
            String title = "SUR AutoArchive MSSQL";
            ResourceBundle resources = new Translation().getResourceBundle("Modules/Application/Resources/Languages/application");

            VBox page = FXMLLoader.load(getClass().getResource("/Modules/Application/Resources/Views/ApplicationView.fxml"), resources);
            Scene scene = new Scene(page);
            ITray tray = new Tray();
            tray.setIcon("src/main/java/Modules/Application/Resources/Assets/Images/app_icon.png");
            tray.setTitle(title);
            tray.include(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle(title);
            primaryStage.show();
            IBackupDBController backupDB = new BackupDBController();
            backupDB.run();
        } catch (Exception ex) {
            logger.fatal(ex);
        }
    }

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     *
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     */
    public void stop() throws Exception {
        IBackupDBController backupDB = new BackupDBController();
        backupDB.stop();
        new SessionFactory().shutdown();
    }
}
