package Modules.Tasks.Models;

import java.util.List;
import org.hibernate.Session;
import javax.persistence.Query;
import org.hibernate.Transaction;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Modules.Tasks.Interfaces.ITaskFactory;
import Modules.Database.Models.SessionFactory;
import Modules.Database.Interfaces.ISessionFactory;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class TaskFactory implements ITaskFactory {

    /** logger for this class */
    private Logger logger = LogManager.getLogger();

    /**
     * Add and update task in database
     *
     * @param task
     * @return task added to database got from database
     */
    public TaskEntity add(TaskEntity task) {

        ISessionFactory sessionFactory = new SessionFactory();
        Session session = sessionFactory.getSessionFactory().openSession();
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
    public void update(TaskEntity task){
        ISessionFactory sessionFactory = new SessionFactory();
        Session session = sessionFactory.getSessionFactory().openSession();
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

        ISessionFactory sessionFactory = new SessionFactory();
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from TaskEntity where TASK_ID=:TASK_ID");
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
    public List<TaskEntity> getAll() {

        List<TaskEntity> tasks = null;

        ISessionFactory sessionFactory = new SessionFactory();
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Query query = session.createQuery("FROM TaskEntity");

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
    public TaskEntity get(Long id) {

        TaskEntity task = null;

        ISessionFactory sessionFactory = new SessionFactory();
        Session session = sessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            task = session.get(TaskEntity.class, id);

            transaction.commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            transaction.rollback();
        }
        session.close();
        return task;
    }
}
