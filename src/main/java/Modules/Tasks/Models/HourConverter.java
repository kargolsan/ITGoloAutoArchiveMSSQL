package Modules.Tasks.Models;

import Modules.DateTime.DateTime;
import Modules.Tasks.Interfaces.IDayOfWeekConverter;
import Modules.Tasks.Interfaces.IHourConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Karol Golec on 06.08.2016.
 */
public class HourConverter implements IHourConverter {

    /**
     * Convert array list to entities
     *
     * @param hours in list
     * @return Entities of HourEntity
     */
    public Set<HourEntity> listToEntities(List<String> hours) {

        Set<HourEntity> hoursOfTask = new HashSet<HourEntity>();
        for(String hourAdded : hours){

            Integer hourInteger = DateTime.getHourInteger(hourAdded);

            if (hourInteger != null){
                HourEntity hour = new HourEntity();
                hour.setHour(hourInteger);
                hoursOfTask.add(hour);
            }
            System.out.println(hourInteger);
        }
        return hoursOfTask;
    }
}
