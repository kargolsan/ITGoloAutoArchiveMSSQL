package Modules.MSSQL.Models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import Modules.MSSQL.Interfaces.IDBConnection;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class DBConnection implements IDBConnection {

    /**
     * Check connection to database
     *
     * @param server       address to server
     * @param instance     instance of server
     * @param databaseName database in server
     * @param userName     user name to access of database
     * @param password     password to access of database
     * @return true if connection is success, false if connection not success
     */
    public boolean hasConnection(String server, String instance, String databaseName, String userName, String password) {
        Connection conn = null;
        String url = String.format("jdbc:jtds:sqlserver://%1$s;instance=%2$s;DatabaseName=%3$s", server, instance, databaseName);
        String driver = "net.sourceforge.jtds.jdbc.Driver";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
            conn.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}