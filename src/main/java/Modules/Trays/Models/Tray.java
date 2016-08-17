package Modules.Trays.Models;

import java.awt.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import java.awt.event.ActionListener;
import Modules.Trays.Interfaces.ITray;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Modules.Translations.Models.Translation;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public class Tray implements ITray {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /** path to tray icon */
    private String pathTrayIcon;

    /** title of tray */
    private String title;

    /** translation */
    ResourceBundle trans;

    /**
     * Constructor of class
     */
    public Tray(){
        trans = new Translation().getResourceBundle("Modules/Trays/Resources/Languages/tray");
    }

    /**
     * Add tray minimize to main application view
     *
     * @param applicationStage
     */
    public void include(Stage applicationStage)
    {
        if (SystemTray.isSupported()) {
            Platform.setImplicitExit(false);
        } else {
            return;
        }

        SystemTray sTray = SystemTray.getSystemTray();

        ActionListener listenerShow = method -> {
            Platform.runLater(new Runnable() {
                public void run() {
                    applicationStage.show();
                }
            });
        };

        ActionListener listenerClose = metohd -> {
            System.exit(0);
        };

        applicationStage.setOnCloseRequest(method -> {
            applicationStage.hide();
        });

        PopupMenu popup = new PopupMenu();
        MenuItem showItem = new MenuItem(trans.getString("tray.menu.show_application"));
        MenuItem exitItem = new MenuItem(trans.getString("tray.menu.close"));

        showItem.addActionListener(listenerShow);
        exitItem.addActionListener(listenerClose);

        popup.add(showItem);
        popup.add(exitItem);

        URL url = System.class.getResource(pathTrayIcon);
        Image img = Toolkit.getDefaultToolkit().getImage(url);

        TrayIcon icon = new TrayIcon(img, title, popup);
        icon.setImageAutoSize(true);
        try {
            sTray.add(icon);
        }
        catch (AWTException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Set icon for tray
     *
     * @param pathIcon (example: src/main/java/Modules/.../)
     */
    public void setIcon(String pathIcon) {
        this.pathTrayIcon = pathIcon;
    }

    /**
     * Set title of tray
     *
     * @param title of tray
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
