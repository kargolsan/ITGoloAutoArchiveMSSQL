package Modules.Tasks.Services;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import Application.Services.DateTimeService;
import Modules.Tasks.Models.Hour;

/**
 * Created by Karol Golec on 06.08.2016.
 */
public class HourService {

    /**
     * Convert array list to entities
     *
     * @param hours in list
     * @return Entities of HourEntity
     */
    public Set<Hour> listToEntities(List<String> hours) {

        Set<Hour> hoursOfTask = new HashSet<Hour>();
        for(String hourAdded : hours){

            Integer hourInteger = DateTimeService.getHourInteger(hourAdded);

            if (hourInteger != null){
                Hour hour = new Hour();
                hour.setHour(hourInteger);
                hoursOfTask.add(hour);
            }

        }
        return hoursOfTask;
    }
}
