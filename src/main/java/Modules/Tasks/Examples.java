package Modules.Tasks;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import Modules.Tasks.Models.Task;
import Modules.Tasks.Models.Hour;
import Modules.Tasks.Repositories.TaskRepository;
import Modules.Tasks.Models.DayOfWeek;
/**
 * Created by Karol Golec on 05.08.2016.
 */
public class Examples {
    public void examples(){
        DayOfWeek dayOfWeek1 = new DayOfWeek();
        dayOfWeek1.setDayOfWeek(1);
        DayOfWeek dayOfWeek2 = new DayOfWeek();
        dayOfWeek2.setDayOfWeek(2);

        Hour hour1 = new Hour();
        hour1.setHour(11);
        Hour hour2 = new Hour();
        hour2.setHour(2);

        Set<DayOfWeek> daysOfWeekOfTask = new HashSet<DayOfWeek>();
        daysOfWeekOfTask.add(dayOfWeek1);
        daysOfWeekOfTask.add(dayOfWeek2);

        Set<Hour> hoursOfTask = new HashSet<Hour>();
        hoursOfTask.add(hour1);
        hoursOfTask.add(hour2);

        TaskRepository taskFactory = new TaskRepository();

        Task task = new Task();
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

        List<Task> tasks = taskFactory.getAll();

        for(Task taskget : tasks){
            System.out.println(taskget.getId());
            System.out.println(taskget.getServer());

            Set<DayOfWeek> days = taskget.getDaysOfWeek();

            for (DayOfWeek day : days){
                System.out.println("Day: " + day.getDayOfWeek());
            }
        }
    }
}
