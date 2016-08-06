package Modules.Application.Controllers;

import Modules.Tasks.Interfaces.IDayOfWeekConverter;
import Modules.Tasks.Interfaces.IHourConverter;
import Modules.Tasks.Models.DayOfWeekConverter;
import Modules.Tasks.Models.DayOfWeekEntity;
import Modules.Tasks.Models.HourConverter;
import Modules.Tasks.Models.HourEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Karol Golec on 06.08.2016.
 */
public class AddTaskController {

    @FXML
    protected void addTask(ActionEvent event) {

        List<String> daysAdded = new ArrayList<String>();
        daysAdded.add("N");
        daysAdded.add("Pn");
        daysAdded.add("Pn");

        List<String> hoursAdded = new ArrayList<String>();
        hoursAdded.add("00:00");
        hoursAdded.add("03:00");
        hoursAdded.add("24:00");

        IDayOfWeekConverter daysConverter = new DayOfWeekConverter();
        Set<DayOfWeekEntity> daysOfWeekOfTask = daysConverter.listToEntities(daysAdded);

        IHourConverter hoursConverter = new HourConverter();
        Set<HourEntity> hoursOfTask = hoursConverter.listToEntities(hoursAdded);

//        HourEntity hour1 = new HourEntity();
//        HourEntity hour2 = new HourEntity();
//        hour2.setHour(2);
//        hour1.setHour(11);
//
//
//
//        Set<HourEntity> hoursOfTask = new HashSet<HourEntity>();
//        hoursOfTask.add(hour1);
//        hoursOfTask.add(hour2);
//
//        ITaskFactory taskFactory = new TaskFactory();
//
//        TaskEntity task = new TaskEntity();
//        task.setServer("server");
//        task.setInstance("instance");
//        task.setDatabase("database");
//        task.setUserName("userName");
//        task.setPassword("password");
//        task.setSavePath("save_Path");
//        task.setSavePathReserve("save_Path");
//
//        task.setDaysOfWeek(daysOfWeekOfTask);
//        task.setHours(hoursOfTask);
//
//        taskFactory.add(task);
//
//        TaskRow taskRow = new TaskRow(
//                task.getId(),
//                task.getServer(),
//                task.getInstance(),
//                task.getDatabase(),
//                task.getUserName(),
//                task.getPassword(),
//                task.getSavePath(),
//                task.getSavePathReserve(),
//                stringDays, stringHours);
//        list.add(taskRow);
    }
}
