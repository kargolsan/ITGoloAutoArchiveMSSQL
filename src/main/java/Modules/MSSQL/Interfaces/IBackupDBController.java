package Modules.MSSQL.Interfaces;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public interface IBackupDBController {

    /**
     * Run auto backup DB
     */
    void run();

    /**
     * Stop auto backup DB
     */
    void stop();
}
