package Modules.Tasks.Models;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import Modules.DateTime.DateTime;
import Modules.Tasks.Interfaces.IDayOfWeekConverter;

/**
 * Created by Karol Golec on 06.08.2016.
 */
public class DayOfWeekConverter implements IDayOfWeekConverter {

    /**
     * Convert array list to entities
     *
     * @param days in list
     * @return Entities of DayOfWeekEntity
     */
    public Set<DayOfWeekEntity> listToEntities(List<String> days) {

        Set<DayOfWeekEntity> daysOfWeekOfTask = new HashSet<DayOfWeekEntity>();
        for(String dayAdded : days){
            Integer numberDayInWeek = DateTime.getNumberDayInWeekByFullName(dayAdded);

            if (numberDayInWeek != null){
                DayOfWeekEntity dayOfWeek = new DayOfWeekEntity();
                dayOfWeek.setDayOfWeek(numberDayInWeek);
                daysOfWeekOfTask.add(dayOfWeek);
            }
        }
        return daysOfWeekOfTask;
    }
}
