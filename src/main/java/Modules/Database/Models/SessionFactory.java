package Modules.Database.Models;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.service.ServiceRegistry;
import Modules.Database.Interfaces.ISessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class SessionFactory implements ISessionFactory {

    /** logger for this class */
    private Logger logger = LogManager.getLogger();

    /** Session factory for connect to database */
    private static org.hibernate.SessionFactory sessionFactory;

    /** Service registry */
    private static ServiceRegistry serviceRegistry;

    /**
     * Get session factory to connection with database
     *
     * @return session factory
     */
    public org.hibernate.SessionFactory getSessionFactory() {
        try {
            if (sessionFactory == null) {
                SessionFactoryBuild();
            }
        } catch (Exception ex){
            logger.fatal(ex.getMessage());
        }
        return sessionFactory;
    }

    /**
     * Close opened session factory
     */
    public void shutdown() {
        try {
            if (sessionFactory != null){
                sessionFactory.close();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * Build the session factory
     */
    private void SessionFactoryBuild()
    {
        try {
            org.hibernate.cfg.Configuration configuration = Configuration.getConfiguration();

            serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            logger.fatal(ex.getMessage());
        }
    }

}
