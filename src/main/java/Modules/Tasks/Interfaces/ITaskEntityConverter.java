package Modules.Tasks.Interfaces;

import Modules.Application.Models.TaskRow;
import Modules.Tasks.Models.TaskEntity;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public interface ITaskEntityConverter {

    /**
     * Get all task rows by tasks entities
     *
     * @param tasks also entities
     * @return observable list with task rows for table view in fxml file
     */
    ObservableList<TaskRow> entitiesToRows(List<TaskEntity> tasks);

}
