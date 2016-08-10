package Modules.Application.Controllers;

import java.net.URL;
import java.util.Set;
import javafx.fxml.FXML;
import Modules.Tasks.Models.*;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import Modules.DateTime.DateTime;
import javafx.fxml.Initializable;
import Modules.Alerts.Models.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Modules.Application.Models.TaskRow;
import Modules.Tasks.Interfaces.ITaskFactory;
import Modules.Tasks.Interfaces.IHourConverter;
import Modules.Translations.Models.Translation;
import Modules.Tasks.Interfaces.IDayOfWeekConverter;

/**
 * Created by Karol Golec on 06.08.2016.
 */
public class AddTaskController implements Initializable {

    @FXML private TextField txtServer;
    @FXML private TextField txtInstance;
    @FXML private TextField txtDatabase;
    @FXML private TextField txtUserName;
    @FXML private TextField txtPassword;
    @FXML private TextField txtSavePath;
    @FXML private TextField txtSavePathReserve;
    @FXML private ListView<String> lsvDays;
    @FXML private ListView<String> lsvHours;
    @FXML private ComboBox<String> cmbDays;
    @FXML private ComboBox<String> cmbHours;

    /** Observable list for days */
    public ObservableList<String> listDays;

    /** Observable list for hours */
    public ObservableList<String> listHours;

    /** Observable list for full name days */
    public ObservableList<String> listNameDays;

    /** Observable list for all hours in day */
    public ObservableList<String> listAllHours;

    /** Translations */
    ResourceBundle resources;


    /**
     * Constructor of this class
     */
    public AddTaskController(){

        //load translations
        resources = new Translation().getResourceBundle("Modules/Application/Resources/Languages/application");;

        listNameDays = FXCollections.observableArrayList();
        listAllHours = FXCollections.observableArrayList();

        listNameDays.addAll(DateTime.getFullNameDays());
        listAllHours.addAll(DateTime.getAllHoursInDay());
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
        listDays = FXCollections.observableArrayList();
        lsvDays.setItems(listDays);

        listHours = FXCollections.observableArrayList();
        lsvHours.setItems(listHours);

        cmbDays.setItems(listNameDays);
        cmbHours.setItems(listAllHours);
    }

    /**
     * Add task to database
     *
     * @param event from button in fxml view
     */
    @FXML
    protected void addTask(ActionEvent event) {

        if (!validate()) {
            Alert.warning(resources.getString("view.appliaction.tabs.add_task.alert.content.fill_all_required_fields"),
                    resources.getString("view.appliaction.tabs.add_task.alert.title.warning"));
            return;
        }

        IDayOfWeekConverter daysConverter = new DayOfWeekConverter();
        Set<DayOfWeekEntity> daysOfWeekOfTask = daysConverter.listToEntities(listDays);

        IHourConverter hoursConverter = new HourConverter();
        Set<HourEntity> hoursOfTask = hoursConverter.listToEntities(listHours);

        ITaskFactory taskFactory = new TaskFactory();

        TaskEntity task = new TaskEntity();
        task.setServer(txtServer.getText());
        task.setInstance(txtInstance.getText());
        task.setDatabase(txtDatabase.getText());
        task.setUserName(txtUserName.getText());
        task.setPassword(txtPassword.getText());
        task.setSavePath(txtSavePath.getText());
        task.setSavePathReserve(txtSavePathReserve.getText());

        task.setDaysOfWeek(daysOfWeekOfTask);
        task.setHours(hoursOfTask);

        taskFactory.add(task);


        TaskRow taskRow = new TaskRow(
                task.getId(),
                task.getServer(),
                task.getInstance(),
                task.getDatabase(),
                task.getUserName(),
                task.getPassword(),
                task.getSavePath(),
                task.getSavePathReserve(),
                TaskEntityConverter.daysOfWeekToString(task.getDaysOfWeek()),
                TaskEntityConverter.hoursToString(task.getHours())
                );

        TasksController.list.add(taskRow);
    }

    /**
     * Reset days on list for fxml view
     *
     * @param event from button in fxml view
     */
    @FXML
    protected void resetDays(ActionEvent event) {
        listDays.clear();
    }

    /**
     * Add day to list in fxml view
     *
     * @param event from button in fxml view
     */
    @FXML
    protected void addDay(ActionEvent event) {
        String value;

        try{
            value = cmbDays.getSelectionModel().getSelectedItem().toString();
        } catch (Exception ex) {
            return;
        }

        if (listDays.contains(value)) {
            Alert.warning(resources.getString("view.appliaction.tabs.add_task.alert.content.value_has_been_added"),
                    resources.getString("view.appliaction.tabs.add_task.alert.title.warning"));
            return;
        };
        if (value.length() > 0){
            listDays.add(value);
        }
    }

    /**
     * Reset hours on list for fxml view
     *
     * @param event from button in fxml view
     */
    @FXML
    protected void resetHours(ActionEvent event) {
        listHours.clear();
    }

    /**
     * Add hour to list in fxml view
     *
     * @param event from button in fxml view
     */
    @FXML
    protected void addHour(ActionEvent event) {

        String value;

        try{
            value = cmbHours.getSelectionModel().getSelectedItem().toString();
        } catch (Exception ex) {
            return;
        }

        if (listHours.contains(value)) {
            Alert.warning(resources.getString("view.appliaction.tabs.add_task.alert.content.value_has_been_added"),
                    resources.getString("view.appliaction.tabs.add_task.alert.title.warning"));
            return;
        }
        if (value.length() > 0){
            listHours.add(value);
        }
    }

    /**
     * Validate all fields in fxml view for add task
     *
     * @return true if success validated, false if not success validated
     */
    private Boolean validate(){

        Boolean hasError = false;
        if (listDays.size() <= 0 ){
            lsvDays.getStyleClass().add("validateErrorControl");
            hasError = true;
        } else { lsvDays.getStyleClass().remove("validateErrorControl"); }

        if (listHours.size() <= 0 ){
            lsvHours.getStyleClass().add("validateErrorControl");
            hasError = true;
        } else { lsvHours.getStyleClass().remove("validateErrorControl"); }

        if (txtServer.getText().length() <= 0 ){
            txtServer.getStyleClass().add("validateErrorControl");
            hasError = true;
        } else { txtServer.getStyleClass().remove("validateErrorControl"); }

        if (txtDatabase.getText().length() <= 0 ){
            txtDatabase.getStyleClass().add("validateErrorControl");
            hasError = true;
        } else { txtDatabase.getStyleClass().remove("validateErrorControl"); }

        if (txtUserName.getText().length() <= 0 ){
            txtUserName.getStyleClass().add("validateErrorControl");
            hasError = true;
        } else { txtUserName.getStyleClass().remove("validateErrorControl"); }

        if (txtSavePath.getText().length() <= 0 ){
            txtSavePath.getStyleClass().add("validateErrorControl");
            hasError = true;
        } else { txtSavePath.getStyleClass().remove("validateErrorControl"); }

        return !hasError;
    }


}
