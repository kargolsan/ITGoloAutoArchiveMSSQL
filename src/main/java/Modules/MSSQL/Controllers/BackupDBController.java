package Modules.MSSQL.Controllers;

import java.util.concurrent.*;
import Modules.MSSQL.Models.BackupDB;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Modules.MSSQL.Interfaces.IBackupDBController;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public class BackupDBController implements IBackupDBController {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /** Thread for backup */
    public static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    /**
     * Run auto backup DB
     */
    public void run() {
        service.scheduleAtFixedRate(()->{
            BackupDB backupDB = new BackupDB();
            try {
                backupDB.backup();
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }, 0, 20, TimeUnit.MINUTES);
    }

    /**
     * Stop auto backup DB
     */
    public void stop() {
        service.shutdown();
    }


}
