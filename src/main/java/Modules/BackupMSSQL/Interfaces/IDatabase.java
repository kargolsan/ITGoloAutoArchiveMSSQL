package Modules.BackupMSSQL.Interfaces;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public interface IDatabase {

    /**
     * Check connection to database
     *
     * @param server address to server
     * @param instance instance of server
     * @param databaseName database in server
     * @param userName user name to access of database
     * @param password password to access of database
     * @return true if connection is success, false if connection not success
     */
    boolean hasConnection(String server, String instance, String databaseName, String userName, String password);

    /**
     * Get version of SQL Server
     *
     * @param server address to server
     * @param instance instance of server
     * @param databaseName database in server
     * @param userName user name to access of database
     * @param password password to access of database
     * @return number version of SQL server (example: 7, 8, 9, 10 etc.)
     */
    Integer getVersionSQLServer(String server, String instance, String databaseName, String userName, String password);
}
