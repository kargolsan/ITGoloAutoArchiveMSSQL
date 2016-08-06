package Modules.Tasks.Models;

import Modules.DateTime.DateTime;
import Modules.Tasks.Interfaces.IDayOfWeekConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            Integer numberDayInWeek = DateTime.getNumberDayInWeekByShortName(dayAdded);

            if (numberDayInWeek != null){
                DayOfWeekEntity dayOfWeek = new DayOfWeekEntity();
                dayOfWeek.setDayOfWeek(numberDayInWeek);
                daysOfWeekOfTask.add(dayOfWeek);
            }
            System.out.println(numberDayInWeek);
        }
        return daysOfWeekOfTask;
    }
}
