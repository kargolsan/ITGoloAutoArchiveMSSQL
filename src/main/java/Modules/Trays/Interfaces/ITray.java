package Modules.Trays.Interfaces;

import javafx.stage.Stage;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public interface ITray {

    /**
     * Add tray minimize to main application view
     *
     * @param applicationStage
     */
    void include(Stage applicationStage);

    /**
     * Set icon for tray
     *
     * @param pathIcon (example: src/main/java/Modules/.../)
     */
    void setIcon(String pathIcon);

    /**
     * Set title of tray
     *
     * @param title of tray
     */
    void setTitle(String title);
}
