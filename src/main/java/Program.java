import Modules.Application.Models.Application;
import Modules.MSSQL.Interfaces.IBackupDB;
import Modules.MSSQL.Models.BackupDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Karol Golec on 03.08.2016.
 */
public class Program {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * Main function for application
     *
     * @param args
     */
    public static void main(String[] args) {
        IBackupDB backupDB = new BackupDB();
        backupDB.run();
        Application.launch(Application.class, args);
    }
}
