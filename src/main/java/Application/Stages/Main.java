package Application.Stages;

import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import java.util.ResourceBundle;
import Modules.Trays.Models.Tray;
import Modules.Trays.Interfaces.ITray;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Modules.Database.Models.SessionService;
import Modules.Translations.Models.Translation;
import Modules.MSSQL.Interfaces.IBackupDBController;
import Modules.MSSQL.Controllers.BackupDBController;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class Main extends javafx.application.Application {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /** properties of build */
    Properties propertiesBuild;

    /** Resource bundle of translations */
    ResourceBundle trans;

    /**
     * Constructor of class
     */
    public Main(){
        propertiesBuild = new Properties();
        try {
            propertiesBuild.load(getClass().getClassLoader().getResourceAsStream("Application/Resources/Properties/application.properties"));
        } catch (IOException ex) {
            logger.fatal(ex.getMessage());
        }

         trans = new Translation().getResourceBundle("Modules/Application/Resources/Languages/application");
    }

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
            VBox page = FXMLLoader.load(getClass().getResource("/Modules/Application/Resources/Views/ApplicationView.fxml"), trans);
            Scene scene = new Scene(page);
            setTray(primaryStage, propertiesBuild.getProperty("title"));
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle(String.format("%1$s - v%2$s", propertiesBuild.getProperty("title"), propertiesBuild.getProperty("version")));
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Modules/Application/Resources/Assets/Images/app_icon.png")));
            primaryStage.show();
            rubAutoBackupDB();
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
        stopAutoBackupDB();
        SessionService.close();
    }

    /**
     * Run auto archive for database
     */
    private void rubAutoBackupDB(){
        IBackupDBController backupDB = new BackupDBController();
        backupDB.run();
    }

    /**
     * Stop auto archive for database
     */
    private void stopAutoBackupDB(){
        IBackupDBController backupDB = new BackupDBController();
        backupDB.stop();
    }

    /**
     * Set tray for the application
     *
     * @param primaryStage of the main view
     * @param title for view of application
     */
    private void setTray(Stage primaryStage, String title)
    {
        ITray tray = new Tray();
        tray.setIcon("/Modules/Application/Resources/Assets/Images/app_icon.png");
        tray.setTitle(title);
        tray.include(primaryStage);
    }
}
