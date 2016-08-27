package Application.Services;

import javafx.stage.Stage;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.image.Image;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 26.08.2016
 * Time: 10:36
 */
public class AlertService {

    /**
     * Icon for alert error
     */
    private static final String ICON_ERROR = "/Application/Resources/Assets/Images/Icons/error_20.png";

    /**
     * Icon for alert warning
     */
    private static final String ICON_WARNING = "/Application/Resources/Assets/Images/Icons/warning_20.png";

    /**
     * Icon for alert info
     */
    private static final String ICON_INFO = "/Application/Resources/Assets/Images/Icons/info_20.png";

    /**
     * Show alert error with details of exception
     *
     * @param title alert
     * @param header alert
     * @param content alert
     * @param ex exception for alert
     * @param resources with bundle
     */
    public static void errorException(String title, String header, String content, Exception ex, ResourceBundle resources){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label(resources.getString("alert_service.the_exception_stacktrace_was"));

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertService.class.getResourceAsStream(ICON_ERROR)));

        alert.showAndWait();
    }

    /**
     * Show warning alert
     *
     * @param content body of alert
     * @param header for content
     * @param title of alert
     */
    public static void warning(String content, String header, String title){
        Alert alert = new javafx.scene.control.Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertService.class.getResourceAsStream(ICON_WARNING)));
        alert.showAndWait();
    }

    /**
     * Show information alert
     *
     * @param content body of alert
     * @param header for content
     * @param title of alert
     */
    public static void info(String content, String header, String title){
        Alert alert = new javafx.scene.control.Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AlertService.class.getResourceAsStream(ICON_INFO)));
        alert.showAndWait();
    }
}
