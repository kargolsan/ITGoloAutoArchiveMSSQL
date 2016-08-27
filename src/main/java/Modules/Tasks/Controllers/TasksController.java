package Modules.Tasks.Controllers;

import java.util.*;
import java.net.URL;

import Application.Services.LanguageService;
import Modules.Tasks.Services.PasswordLabelCellService;
import Modules.Tasks.Repositories.TaskRepository;
import Modules.Tasks.Services.TaskEntityService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import Modules.Tasks.Models.TaskRow;
import Modules.Translations.Models.Translation;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class TasksController implements Initializable {

    @FXML private TableView<TaskRow> tableTasks;
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
    @FXML private MenuItem ctxMenuDeleteTask;

    public static ObservableList<TaskRow> list;

    /** Translations */
    ResourceBundle resources;

    /** Path to language tray*/
    private static final String LANGUAGE = "Application/Resources/Languages/application";

    /**
     * Constructor of class
     */
    public TasksController() {

        //load translations
        resources = LanguageService.getResourceBundle(LANGUAGE);

        TaskRepository taskFactory = new TaskRepository();
        list = new TaskEntityService().entitiesToRows(taskFactory.getAll());
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
        password.setCellFactory(param -> new PasswordLabelCellService());
        password.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("password"));
        savePath.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("savePath"));
        savePathReserve.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("savePathReserve"));
        daysOfWeek.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("daysOfWeek"));
        hours.setCellValueFactory(new PropertyValueFactory<TaskRow, String>("hours"));

        tableTasks.setItems(list);
    }

    /**
     * Set items of menu with showing context menu
     *
     * @param e
     */
    @FXML
    protected void showingContextMenu(WindowEvent e) {
        TaskRow taskRow = tableTasks.getSelectionModel().getSelectedItem();
        ctxMenuDeleteTask.setDisable(taskRow == null);
    }

    /**
     * Remove task from table and database
     */
    @FXML
    private void deleteTask()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(resources.getString("view.application.tabs.tasks.alert.confirmation"));
        alert.setHeaderText(resources.getString("view.application.tabs.tasks.alert.confirmation"));
        alert.setContentText(resources.getString("view.application.tabs.tasks.alert.do_you_delete_task"));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            TaskRow taskRow = tableTasks.getSelectionModel().getSelectedItem();
            list.remove(taskRow);
            TaskRepository taskFactory = new TaskRepository();
            taskFactory.delete(taskRow.getId());
        }
    }
}

