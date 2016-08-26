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
     * Create session of database
     * for application
     *
     * @throws LockFileDatabaseException if file of database is locking
     */
    public static void createSession() throws LockFileDatabaseException {
        try {
                SessionFactoryBuild();
        } catch (Exception e) {
            if (hasLockDatabaseException(e)) {
                throw new LockFileDatabaseException("File of database is locking.", e);
            }
        }
    }

    /**
     * Get session for transaction in database
     *
     * @return session
     */
    public static Session getSession() {
        if (sessionFactory == null){
            SessionFactoryBuild();
        }
        if (sessionFactory.getCurrentSession().isOpen()) {
            return sessionFactory.getCurrentSession();
        } else {
            return sessionFactory.openSession();
        }
    }

    /**
     * Close opened session factory
     * without display error, but put error to logs
     */
    public static void close() {
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

    /**
     * Check exception contains information
     * about lock file of database
     *
     * @param e exception to check
     * @return true if file of database is locking or false if it is not locking
     */
    public static Boolean hasLockDatabaseException(Exception e) {
        SQLException exception = (SQLException) e.getCause().getCause();
        return exception.getSQLState().contains("S1000");
    }

}
