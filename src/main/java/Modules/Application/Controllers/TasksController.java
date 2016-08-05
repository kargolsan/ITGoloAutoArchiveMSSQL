package Modules.Application.Controllers;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import Modules.Application.Models.TaskRow;
import Modules.Tasks.Models.TaskEntityToRow;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class TasksController implements Initializable {

    @FXML
    private TableView<TaskRow> tableTasks;
    @FXML private TableColumn<TaskRow, Long> id;
    @FXML private TableColumn<TaskRow, String> server;
    @FXML private TableColumn<TaskRow, String> instance;
    @FXML private TableColumn<TaskRow, String> database;
    @FXML private TableColumn<TaskRow, String> userName;
    @FXML private TableColumn<TaskRow, String> password;
    @FXML private TableColumn<TaskRow, String> savePath;
    @FXML private TableColumn<TaskRow, String> savePathReserve;
    @FXML private TableColumn<TaskRow, String> daysOfWeek;
    @FXML private TableColumn<TaskRow, String> hours;

    public ObservableList<TaskRow> list;

    /**
     * Constructor of class
     */
    public TasksController() {
        list = new TaskEntityToRow().getAll();
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<TaskRow, Long>("id"));
        server.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("server"));
        instance.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("instance"));
        database.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("database"));
        userName.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("userName"));
        password.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("password"));
        savePath.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("savePath"));
        savePathReserve.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("savePathReserve"));
        daysOfWeek.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("daysOfWeek"));
        hours.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("hours"));

        tableTasks.setItems(list);
    }
}
