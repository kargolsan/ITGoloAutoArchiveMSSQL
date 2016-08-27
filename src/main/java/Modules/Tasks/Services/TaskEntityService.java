package Modules.Tasks.Services;

import java.util.*;
import Application.Services.DateTimeService;
import Modules.Tasks.Models.DayOfWeek;
import Modules.Tasks.Models.Hour;
import Modules.Tasks.Models.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Modules.Tasks.Models.TaskRow;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class TaskEntityService {

    /**
     * Get all task rows by tasks entities
     *
     * @param tasks also entities
     * @return observable list with task rows for table view in fxml file
     */
    public ObservableList<TaskRow> entitiesToRows(List<Task> tasks) {
        ObservableList<TaskRow> list;
        list = FXCollections.observableArrayList();
        for(Task task : tasks){
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
    public static String daysOfWeekToString(Set<DayOfWeek> days){
        String stringDays = "";

        List<DayOfWeek> listDays = new ArrayList<DayOfWeek>(days);

        Collections.sort(listDays,new Comparator<DayOfWeek>(){
            public int compare(final DayOfWeek a, DayOfWeek b) {
                return Integer.signum(b.getDayOfWeek()-a.getDayOfWeek());
            }
        });

        for(DayOfWeek day : listDays)
        {
            stringDays = String.format("%1$s, %2$s", DateTimeService.getNameDayInWeek(day.getDayOfWeek()), stringDays);
        }

        if (stringDays.contains(", "))
        {
            stringDays = stringDays.substring(0,stringDays.length()-2);
        }

        return stringDays;
    }

    /**
     * Convert entity hours to string
     *
     * @param hours from HourEntity
     * @return string with hours
     */
    public static String hoursToString(Set<Hour> hours){
        String stringHours = "";

        List<Hour> listHours = new ArrayList<Hour>(hours);

        Collections.sort(listHours,new Comparator<Hour>(){
            public int compare(final Hour a, Hour b) {
                return Integer.signum(b.getHour()-a.getHour());
            }
        });

        for(Hour hour : listHours)
        {
            stringHours = String.format("%1$s, %2$s", DateTimeService.addZeroToHour(hour.getHour()), stringHours);
        }

        if (stringHours.contains(", "))
        {
            stringHours = stringHours.substring(0,stringHours.length()-2);
        }

        return stringHours;
    }
}
