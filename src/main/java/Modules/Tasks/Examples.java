package Modules.Tasks;

import Modules.Database.Models.SessionFactory;
import Modules.Tasks.Interfaces.ITaskFactory;
import Modules.Tasks.Models.DayOfWeekEntity;
import Modules.Tasks.Models.HourEntity;
import Modules.Tasks.Models.TaskEntity;
import Modules.Tasks.Models.TaskFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class Examples {
    public void examples(){
        DayOfWeekEntity dayOfWeek1 = new DayOfWeekEntity();
        dayOfWeek1.setDayOfWeek(1);
        DayOfWeekEntity dayOfWeek2 = new DayOfWeekEntity();
        dayOfWeek2.setDayOfWeek(2);

        HourEntity hour1 = new HourEntity();
        hour1.setHour(11);
        HourEntity hour2 = new HourEntity();
        hour2.setHour(2);

        Set<DayOfWeekEntity> daysOfWeekOfTask = new HashSet<DayOfWeekEntity>();
        daysOfWeekOfTask.add(dayOfWeek1);
        daysOfWeekOfTask.add(dayOfWeek2);

        Set<HourEntity> hoursOfTask = new HashSet<HourEntity>();
        hoursOfTask.add(hour1);
        hoursOfTask.add(hour2);

        ITaskFactory taskFactory = new TaskFactory();

        TaskEntity task = new TaskEntity();
        task.setServer("server");
        task.setInstance("instance");
        task.setDatabase("database");
        task.setUserName("userName");
        task.setPassword("password");
        task.setSavePath("save_Path");
        task.setSavePathReserve("save_Path");

        task.setDaysOfWeek(daysOfWeekOfTask);
        task.setHours(hoursOfTask);

        taskFactory.add(task);

        //TaskEntity taskUpdate = taskFactory.get(new Long(6));
        //System.out.println(taskUpdate.getId());
        //taskUpdate.setServer("eggdssdg");

        //taskFactory.update(taskUpdate);

        List<TaskEntity> tasks = taskFactory.getAll();

        for(TaskEntity taskget : tasks){
            System.out.println(taskget.getId());
            System.out.println(taskget.getServer());

            Set<DayOfWeekEntity> days = taskget.getDaysOfWeek();

            for (DayOfWeekEntity day : days){
                System.out.println("Day: " + day.getDayOfWeek());
            }
        }
    }
}
