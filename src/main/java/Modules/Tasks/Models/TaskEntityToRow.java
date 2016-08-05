package Modules.Tasks.Models;

import java.util.Set;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Modules.Application.Models.TaskRow;
import Modules.Tasks.Interfaces.ITaskFactory;
import Modules.Tasks.Interfaces.ITaskEntityToRow;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class TaskEntityToRow implements ITaskEntityToRow {

    /**
     * Get all task rows by tasks entities
     *
     * @return observable list with task rows for table view in fxml file
     */
    public ObservableList<TaskRow> getAll() {
        ObservableList<TaskRow> list;
        list = FXCollections.observableArrayList();
        ITaskFactory taskFactory = new TaskFactory();
        List<TaskEntity> tasks = taskFactory.getAll();

        for(TaskEntity task : tasks){
            String stringDays = daysOfWeekToString(task.getDaysOfWeek());
            String stringHours = hoursToString(task.getHours());

            TaskRow taskRow = new TaskRow(
                    task.getId(),
                    task.getServer(),
                    task.getInstance(),
                    task.getDatabase(),
                    task.getUserName(),
                    task.getPassword(),
                    task.getSavePath(),
                    task.getSavePathReserve(),
                    stringDays, stringHours);
            list.add(taskRow);
        }
        return list;
    }

    /**
     * Convert entity days of week to string
     *
     * @param days from DayOfWeekEntity
     * @return string with days
     */
    private String daysOfWeekToString(Set<DayOfWeekEntity> days){
        String stringDays = "";

        for(DayOfWeekEntity day : days)
        {
            stringDays = stringDays + " " + day.getDayOfWeek();
        }
        return stringDays;
    }

    /**
     * Convert entity hours to string
     *
     * @param hours from HourEntity
     * @return string with hours
     */
    private String hoursToString(Set<HourEntity> hours){
        String stringHours = "";

        for(HourEntity hour : hours)
        {
            stringHours = stringHours + " " + hour.getHour();
        }
        return stringHours;
    }
}
