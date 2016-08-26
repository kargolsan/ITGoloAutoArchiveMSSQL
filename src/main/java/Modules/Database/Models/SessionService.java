package Modules.Database.Models;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.apache.logging.log4j.LogManager;
import org.hibernate.service.ServiceRegistry;
import Database.Services.ConfigurationService;
import Database.Exceptions.LockFileDatabaseException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class SessionService {

    /**
     * Session factory for connect to database
     */
    private static SessionFactory sessionFactory;

    /**
     * Service registry
     */
    private static ServiceRegistry serviceRegistry;

    /**
     * Get session factory to connection with database
     *
     * @return session factory
     * @throws LockFileDatabaseException if file with database is locking
     */
    public static SessionFactory getSessionFactory() throws LockFileDatabaseException {

        return sessionFactory;
    }

    /**
     * Close opened session factory
     * without display error, but put error to logs
     */
    public void shutdown() {
        try {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        } catch (Exception e) {
            LogManager.getLogger().error(e.getMessage());
        }
    }

    /**
     * Build the session factory from
     * class with data connection to database
     */
    private static void SessionFactoryBuild() {
            Configuration configuration = ConfigurationService.getConfigurationForHibernate();

            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static Session getSession() {
        if (sessionFactory.getCurrentSession().isOpen()) {
            return sessionFactory.getCurrentSession();
        } else {
            return sessionFactory.openSession();
        }
    }

    public static void createSession() throws LockFileDatabaseException {
        try {
            if (sessionFactory == null) {
                SessionFactoryBuild();
            }
        } catch (Exception e){
            if (hasLockDatabaseException(e)){
                throw new LockFileDatabaseException("File of database is locking.", e);
            }
        }
    }




    public static Boolean hasLockDatabaseException(Exception error) {
        try {
            SQLException exception = (SQLException) error.getCause().getCause();
            if (exception.getSQLState().contains("S1000")) {
                return true;
            }
        } catch (NullPointerException  e) {
            LogManager.getLogger().error(e.getMessage());
        }
        return false;
    }

}
