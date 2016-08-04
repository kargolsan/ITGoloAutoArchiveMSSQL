package Modules.Database.Models;

import Modules.Tasks.Models.DayOfWeekEntity;
import Modules.Tasks.Models.HourEntity;
import Modules.Tasks.Models.TaskEntity;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class Configuration {

    /**
     * Get configuration of hibernate
     *
     * @return Configuration of hibernate
     */
    public static org.hibernate.cfg.Configuration getConfiguration()
    {
        return new org.hibernate.cfg.Configuration()
                .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                .setProperty("hibernate.connection.url", "jdbc:hsqldb:file:database/database;shutdown=true")
                .setProperty("hibernate.connection.username", "AutoArchive")
                .setProperty("hibernate.connection.password", "AutoArchive")
                .setProperty("hibernate.connection.pool_size", "1")
                .setProperty("hibernate.show_sq", "true")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.current_session_context_class", "thread")
                .addAnnotatedClass(TaskEntity.class)
                .addAnnotatedClass(HourEntity.class)
                .addAnnotatedClass(DayOfWeekEntity.class)
                .setProperty("cache.provider_class", "org.hibernate.cache.NoCacheProvider");

    }
}
