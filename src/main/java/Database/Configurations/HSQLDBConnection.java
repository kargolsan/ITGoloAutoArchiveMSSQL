package Database.Configurations;

import org.hibernate.cfg.Configuration;
import Modules.Tasks.Models.Hour;
import Modules.Tasks.Models.Task;
import Modules.Tasks.Models.DayOfWeek;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class HSQLDBConnection {

    /**
     * Get configuration of hibernate
     *
     * @return Configuration of hibernate
     */
    public static Configuration getConfiguration()
    {
        return new Configuration()
                .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                .setProperty("hibernate.connection.url", "jdbc:hsqldb:file:database/database;shutdown=true")
                .setProperty("hibernate.connection.username", "AutoArchive")
                .setProperty("hibernate.connection.password", "AutoArchive")
                .setProperty("hibernate.connection.pool_size", "2")
                .setProperty("hibernate.show_sq", "true")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.current_session_context_class", "thread")
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(Hour.class)
                .addAnnotatedClass(DayOfWeek.class)
                .setProperty("cache.provider_class", "org.hibernate.cache.NoCacheProvider");

    }
}
