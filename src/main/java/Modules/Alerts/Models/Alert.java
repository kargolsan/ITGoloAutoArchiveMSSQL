package Modules.Alerts.Models;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public class Alert {

    /**
     * Show warning alert
     *
     * @param content body of alert
     * @param title of alert
     */
    public static void warning(String content, String title){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Show warning alert
     *
     * @param content body of alert
     * @param title of alert
     * @param header for content
     */
    public static void warning(String content, String title, String header){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
