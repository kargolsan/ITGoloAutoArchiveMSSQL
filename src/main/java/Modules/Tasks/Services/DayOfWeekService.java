package Modules.Tasks.Services;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import Application.Services.DateTimeService;
import Modules.Tasks.Models.DayOfWeek;

/**
 * Created by Karol Golec on 06.08.2016.
 */
public class DayOfWeekService {

    /**
     * Convert array list to entities
     *
     * @param days in list
     * @return Entities of DayOfWeekEntity
     */
    public Set<DayOfWeek> listToEntities(List<String> days) {

        Set<DayOfWeek> daysOfWeekOfTask = new HashSet<DayOfWeek>();
        for(String dayAdded : days){
            Integer numberDayInWeek = DateTimeService.getNumberDayInWeekByFullName(dayAdded);

            if (numberDayInWeek != null){
                DayOfWeek dayOfWeek = new DayOfWeek();
                dayOfWeek.setDayOfWeek(numberDayInWeek);
                daysOfWeekOfTask.add(dayOfWeek);
            }
        }
        return daysOfWeekOfTask;
    }
}
