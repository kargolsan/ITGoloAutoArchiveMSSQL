package Modules.Application.Controllers;

import java.net.URL;

import Modules.Application.Interfaces.ILogService;
import Modules.Application.Models.Log;
import Modules.Application.Models.Services.LogService;
import Modules.Application.Models.TaskRow;
import Modules.DateTime.DateTime;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.apache.logging.log4j.Level;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class LogsController implements Initializable {

    @FXML private TableView<Log> tableLogs;
    @FXML private TableColumn<Log, String> itemCreatedAt;
    @FXML private TableColumn<Log, String> itemLevel;
    @FXML private TableColumn<Log, String> itemMessage;

    public static ObservableList<Log> listLogs = FXCollections.observableArrayList();

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        itemCreatedAt.setCellValueFactory(new PropertyValueFactory<Log, String>("created_at"));
        itemLevel.setCellValueFactory(new PropertyValueFactory<Log, String>("level"));
        itemMessage.setCellValueFactory(new PropertyValueFactory<Log, String>("message"));

        setFactoryOfTableLogs();

        tableLogs.setItems(listLogs);
    }

    /**
     * Clear all logs in list
     */
    @FXML
    public void clear(ActionEvent event){
        listLogs.clear();
    }

    /**
     * Set factory for row in table logs
     */
    private void setFactoryOfTableLogs(){
        tableLogs.setRowFactory(new Callback<TableView<Log>, TableRow<Log>>() {
            @Override public TableRow<Log> call(TableView<Log> param) {
                return new TableRow<Log>() {
                    @Override protected void updateItem(Log item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) return;

                        getStyleClass().remove("success");
                        getStyleClass().remove("error");
                        getStyleClass().remove("warning");
                        getStyleClass().remove("info");

                        switch(item.getLevel()){
                            case "success":
                                getStyleClass().add("success");
                                break;
                            case "error":
                                getStyleClass().add("error");
                                break;
                            case "warning":
                                getStyleClass().add("warning");
                                break;
                            case "info":
                                getStyleClass().add("info");
                                break;
                        }
                    }
                };
            }
        });
    }
}
