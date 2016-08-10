package Modules.Tasks.Interfaces;

import java.util.Set;
import java.util.List;
import Modules.Tasks.Models.DayOfWeekEntity;

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
