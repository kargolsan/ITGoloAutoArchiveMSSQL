package Application.Services;

import java.awt.*;
import java.net.URL;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 26.08.2016
 * Time: 22:38
 */
public class TrayService {

    /**
     * Set tray for the stage
     *
     * @param stage    of the main view
     * @param title    for view of application
     * @param iconPath for tray
     * @param iconPath for tray
     */
    public static void setTray(Stage stage, String title, String iconPath, String languagePath) {
        if (!SystemTray.isSupported()) {
            return;
        }

        ResourceBundle resources = LanguageService.getResourceBundle(languagePath);

        setApplication(stage);

        TrayIcon trayIcon = new TrayIcon(getIcon(iconPath), title, createMenuPopup(stage, resources));

        embedTray(trayIcon);
    }

    /**
     * Create menu popup
     *
     * @param stage of application
     * @param resources also bundle with language
     * @return popup menu
     */
    public static PopupMenu createMenuPopup(Stage stage, ResourceBundle resources){
        PopupMenu popup = new PopupMenu();
        MenuItem show = new MenuItem(resources.getString("tray_service.menu.show_application"));
        MenuItem exit = new MenuItem(resources.getString("tray_service.menu.close"));

        show.addActionListener(m -> {
            showApplication(stage);
        });

        exit.addActionListener(m -> {
            exitApplication(stage);
        });

        popup.add(show);
        popup.add(exit);

        return popup;
    }

    /**
     * Show application from tray
     *
     * @param stage also window
     */
    private static void showApplication(Stage stage) {
        Platform.runLater(() -> {
            stage.show();
        });
    }

    /**
     * Hide application from tray
     *
     * @param stage also window
     */
    private static void hideApplication(Stage stage) {
        Platform.runLater(() -> {
            stage.hide();
        });
    }

    /**
     * Exit application from tray
     *
     * @param stage also window
     */
    private static void exitApplication(Stage stage) {
        ApplicationService.terminate();
    }

    /**
     * Get icon for tray
     *
     * @param iconPath also string
     * @return image object for tray
     */
    private static Image getIcon(String iconPath){
        URL url = System.class.getResource(iconPath);
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    /**
     * Embed tray to system icons in tray
     *
     * @param trayIcon also string
     */
    private static void embedTray(TrayIcon trayIcon){
        trayIcon.setImageAutoSize(true);
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            LogManager.getLogger().error(e.getMessage());
        }
    }

    /**
     * Set application for tray
     *
     * @param stage of application
     */
    private static void setApplication(Stage stage){
        Platform.setImplicitExit(false);

        stage.setOnCloseRequest(m -> {
            hideApplication(stage);
        });
    }
}
