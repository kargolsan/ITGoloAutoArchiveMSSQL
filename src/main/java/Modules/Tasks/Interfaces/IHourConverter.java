package Modules.Tasks.Interfaces;

import java.util.Set;
import java.util.List;
import Modules.Tasks.Models.HourEntity;

/**
 * Created by Karol Golec on 06.08.2016.
 */
public interface IHourConverter {

    /**
     * Convert array list to entities
     *
     * @param hours in list
     * @return Entities of HourEntity
     */
    Set<HourEntity> listToEntities(List<String> hours);
}
