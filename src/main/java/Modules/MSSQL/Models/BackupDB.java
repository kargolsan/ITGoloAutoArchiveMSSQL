package Modules.MSSQL.Models;

import java.util.Set;
import java.util.List;
import Modules.DateTime.DateTime;
import Modules.Tasks.Models.HourEntity;
import Modules.Tasks.Models.TaskEntity;
import Modules.Tasks.Models.TaskFactory;
import Modules.Tasks.Models.DayOfWeekEntity;
import Modules.Tasks.Interfaces.ITaskFactory;
import Modules.Application.Interfaces.ILogService;
import Modules.Application.Models.Services.LogService;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public class BackupDB {

    /** last hour of backup */
    static Integer lastHourBackup = 0;

    /** Service of log to table view */
    ILogService log = new LogService();

    /**
     * Backup Databases from tasks
     *
     * @throws Exception
     */
    public void backup() throws Exception{

        ITaskFactory taskFactory = new TaskFactory();
        List<TaskEntity> tasks = taskFactory.getAll();

        for (TaskEntity task : tasks){

            if (!currentDayIsForBackup(task)) continue;

            if (!currentHourIsForBackup(task)) continue;

            log.addInfo("Can backup hour");
        }
    }

    /**
     * Check is task has been runner in current day
     *
     * @param task
     * @return true if task has current day
     */
    private Boolean currentDayIsForBackup(TaskEntity task){
        Set<DayOfWeekEntity> days = task.getDaysOfWeek();

        for (DayOfWeekEntity day : days){
            if (DateTime.getCurrentNumberDayInWeek() == day.getDayOfWeek()){
                return true;
            }
        }
        return false;
    }

    /**
     * Check is task has been runner in current hour
     *
     * @param task
     * @return true if task has current hour
     */
    private Boolean currentHourIsForBackup(TaskEntity task){
        Set<HourEntity> hours = task.getHours();

        for (HourEntity hour : hours){
            if (DateTime.getCurrentNumberHour() == hour.getHour() && lastHourBackup != hour.getHour()){
                lastHourBackup = hour.getHour();
                return true;
            }
        }
        return false;
    }
}
