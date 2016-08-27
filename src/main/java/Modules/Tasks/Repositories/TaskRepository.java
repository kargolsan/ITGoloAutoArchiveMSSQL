package Modules.Tasks.Repositories;

import java.util.List;

import Modules.Tasks.Models.Task;
import org.hibernate.Session;
import javax.persistence.Query;
import org.hibernate.Transaction;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Database.Services.SessionService;
/**
 * Created by Karol Golec on 03.08.2016.
 */
public class TaskRepository {

    /** logger for this class */
    private Logger logger = LogManager.getLogger();

    /**
     * Add and update task in database
     *
     * @param task
     * @return task added to database got from database
     */
    public Task add(Task task) {

        Session session = SessionService.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Long id = (Long)session.save(task);
            task = get(id);

            transaction.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            transaction.rollback();
        }
        session.close();
        return task;
    }

    /**
     * Update task in database
     *
     * @param TaskEntity task
     */
    public void update(Task task){
        Session session = SessionService.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.update(task);

            transaction.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            transaction.rollback();
        }
        session.close();
    }

    /**
     * Delete task in database
     *
     * @param id number identify item in database
     * @return true if deleted task to database, false if not deleted task to database
     */
    public boolean delete(Long id) {

        boolean result = false;

        Session session = SessionService.getSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Task where TASK_ID=:TASK_ID");
            query.setParameter("TASK_ID", id);

            result = (query.executeUpdate() != 0);

            transaction.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            transaction.rollback();
        }
        session.close();
        return result;
    }

    /**
     * Get all tasks from database
     *
     * @return all tasks from database
     */
    public List<Task> getAll() {

        List<Task> tasks = null;

        Session session = SessionService.getSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM Task");

            tasks = query.getResultList();

            transaction.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            transaction.rollback();
        }
        session.close();
        return tasks;
    }

    /**
     * Get one task from database
     *
     * @param id number identify item in database
     * @return task from database
     */
    public Task get(Long id) {

        Task task = null;

        Session session = SessionService.getSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            task = session.get(Task.class, id);

            transaction.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            transaction.rollback();
        }
        session.close();
        return task;
    }
}
