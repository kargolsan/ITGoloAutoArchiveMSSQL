import Modules.Database.Models.SessionFactory;
import Modules.Tasks.Interfaces.ITaskFactory;
import Modules.Tasks.Models.DayOfWeekEntity;
import Modules.Tasks.Models.HourEntity;
import Modules.Tasks.Models.TaskEntity;
import Modules.Tasks.Models.TaskFactory;
import com.sun.javafx.tk.Toolkit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class Program {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * Main function for application
     *
     * @param args
     */
    public static void main(String[] args) {

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

        task.setDaysOfWeek(daysOfWeekOfTask);
        task.setHours(hoursOfTask);

        taskFactory.add(task);

        TaskEntity taskUpdate = taskFactory.get(new Long(6));
        System.out.println(taskUpdate.getId());
        taskUpdate.setServer("eggdssdg");

        taskFactory.update(taskUpdate);

        List<TaskEntity> tasks = taskFactory.getAll();

        for(TaskEntity taskget : tasks){
            System.out.println(taskget.getId());
            System.out.println(taskget.getServer());

            Set<DayOfWeekEntity> days = taskget.getDaysOfWeek();

            for (DayOfWeekEntity day : days){
                System.out.println("Day: " + day.getDayOfWeek());
            }
        }

        new SessionFactory().shutdown();

        //IDBConnection dbConn = new DBConnection();
        //boolean result = dbConn.hasConnection("192.168.1.150", "WFMAG", "WFMAG", "karolgolec", "tbh6247");
        //System.out.println(result);
    }
}
