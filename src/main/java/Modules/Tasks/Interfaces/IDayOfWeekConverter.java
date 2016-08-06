package Modules.Tasks.Interfaces;

import Modules.Tasks.Models.DayOfWeekEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by Karol Golec on 06.08.2016.
 */
public interface IDayOfWeekConverter {

    /**
     * Convert array list to entities
     *
     * @param days in list
     * @return Entities of DayOfWeekEntity
     */
    Set<DayOfWeekEntity> listToEntities(List<String> days);
}
