package Modules.Tasks.Models;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import Modules.DateTime.DateTime;
import Modules.Tasks.Interfaces.IHourConverter;

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

        }
        return hoursOfTask;
    }
}
