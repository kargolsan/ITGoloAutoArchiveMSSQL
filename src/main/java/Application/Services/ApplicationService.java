package Application.Services;

import javafx.application.Platform;
import Modules.Database.Models.SessionService;
import Modules.MSSQL.Interfaces.IBackupDBController;
import Modules.MSSQL.Controllers.BackupDBController;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 26.08.2016
 * Time: 16:27
 */
public class ApplicationService {

    /**
     * Exit application
     */
    public static void exit(){
        IBackupDBController backupDB = new BackupDBController();
        backupDB.stop();
        SessionService.close();
    }

    /**
     * Terminate application
     */
    public static void terminate(){
        exit();
        Platform.exit();
        System.exit(0);
    }
}
