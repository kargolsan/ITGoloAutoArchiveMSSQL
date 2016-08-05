package Modules.MSSQL;

import Modules.MSSQL.Interfaces.IDBConnection;
import Modules.MSSQL.Models.DBConnection;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class Examples {
    private void examples(){
        IDBConnection dbConn = new DBConnection();
        boolean result = dbConn.hasConnection("192.168.1.150", "WFMAG", "WFMAG", "username", "password");
        System.out.println(result);
    }
}
