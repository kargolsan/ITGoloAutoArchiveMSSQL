package Modules.Tasks.Interfaces;

import Modules.Tasks.Models.TaskEntity;

import java.util.List;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public interface ITaskFactory {

    /**
     * Add task in database
     *
     * @param TaskEntity task
     * @return task added to database got from database
     */
    TaskEntity add(TaskEntity task);

    /**
     * Update task in database
     *
     * @param TaskEntity task
     */
    void update(TaskEntity task);

    /**
     * Delete task in database
     *
      * @param id number identify item in database
     * @return true if deleted task to database, false if not deleted task to database
     */
    boolean delete(Long id);

    /**
     * Get all tasks from database
     *
     * @return all tasks from database
     */
    List<TaskEntity> getAll();

    /**
     * Get one task from database
     *
     * @param id number identify item in database
     * @return task from database
     */
    TaskEntity get(Long id);
}
