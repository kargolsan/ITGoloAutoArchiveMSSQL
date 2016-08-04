package Modules.Database.Interfaces;

import org.hibernate.SessionFactory;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public interface ISessionFactory {

    /**
     * Get session factory to connection with database
     * @return session factory
     */
    SessionFactory getSessionFactory();

    /**
     * Shutdown opened session factory
     */
    void shutdown();
}
