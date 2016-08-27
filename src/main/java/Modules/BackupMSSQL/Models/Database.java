package Modules.BackupMSSQL.Models;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Modules.BackupMSSQL.Interfaces.IDatabase;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class Database implements IDatabase {

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
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

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
    public Integer getVersionSQLServer(String server, String instance, String databaseName, String userName, String password) {
        Connection conn = null;
        String url = String.format("jdbc:jtds:sqlserver://%1$s;instance=%2$s;DatabaseName=%3$s", server, instance, databaseName);
        String driver = "net.sourceforge.jtds.jdbc.Driver";
        String version = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT SERVERPROPERTY('productversion')");
            while (rs.next()) {
                version = rs.getString(1);
                break;
            }
            Pattern pattern = Pattern.compile("(.*?)\\.");
            Matcher matcher = pattern.matcher(version);
            if (matcher.find())
            {
                version = matcher.group(1);
            }
            conn.close();
            return Integer.parseInt(version);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}