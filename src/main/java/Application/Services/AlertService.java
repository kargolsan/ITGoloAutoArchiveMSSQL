package Application.Services;

import java.io.PrintWriter;
import javafx.stage.Stage;
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
     * Show alert error with details of exception
     *
     * @param title alert
     * @param header alert
     * @param content alert
     * @param ex exception for alert
     * @param resources with bundle
     * @param iconPath alert dialog
     */
    public static void errorException(String title, String header, String content, Exception ex, ResourceBundle resources, String iconPath){
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
        stage.getIcons().add(new Image(AlertService.class.getResourceAsStream(iconPath)));

        alert.showAndWait();
    }
}
