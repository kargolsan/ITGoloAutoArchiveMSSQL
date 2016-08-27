package Modules.BackupMSSQL.Models;

import java.sql.*;
import java.util.Set;
import java.util.List;
import java.io.IOException;
import java.util.ResourceBundle;
import Modules.Tasks.Models.Hour;
import Modules.Tasks.Models.Task;
import Modules.Tasks.Models.DayOfWeek;
import org.apache.logging.log4j.Logger;
import Modules.Logs.Services.LogService;
import org.apache.logging.log4j.LogManager;
import Application.Services.DateTimeService;
import Application.Services.LanguageService;
import Modules.BackupMSSQL.Interfaces.IDatabase;
import Modules.Tasks.Repositories.TaskRepository;
import Application.Services.FileOperationsService;
import Application.Services.CompressionFileService;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public class BackupDB {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /** translation */
    ResourceBundle trans;

    /** last hour of backup */
    static Integer lastHourBackup = 0;

    /** Service of log to table view */
    LogService log = new LogService();

    /**
     * Constructor of class
     */
    public BackupDB(){

        trans = LanguageService.getResourceBundle("Modules/BackupMSSQL/Resources/Languages/mssql");

    }

    /**
     * Backup Databases from tasks
     *
     * @throws Exception
     */
    public void backup() throws Exception{

        if (backupIsDoubleInCurrentHour()){
            return;
        }

        TaskRepository taskFactory = new TaskRepository();
        List<Task> tasks = taskFactory.getAll();

        for (Task task : tasks){

            if (!currentDayIsForBackup(task)) continue;

            if (!currentHourIsForBackup(task)) continue;

            if (!hasConnectionDB(task)) {
                log.addError(String.format(trans.getString("log.no_connection_database_of_task"), task.getId()));
                continue;
            }
            if (backupDBToFile(task)){
                log.addSuccess(String.format(trans.getString("log.completed_backup_database"), task.getId()));
            } else {
                log.addError(String.format(trans.getString("log.no_backup_database"), task.getId()));
            }
        }
    }

    /**
     * Check is task has been runner in current day
     *
     * @param task
     * @return true if task has current day
     */
    private Boolean currentDayIsForBackup(Task task){
        Set<DayOfWeek> days = task.getDaysOfWeek();

        for (DayOfWeek day : days){
            if (DateTimeService.getCurrentNumberDayInWeek() == day.getDayOfWeek()){
                return true;
            }
        }
        return false;
    }

    /**
     * Check is task has been runner in current hour
     *
     * @param task
     * @return true if task has current hour
     */
    private Boolean currentHourIsForBackup(Task task){
        Set<Hour> hours = task.getHours();

        for (Hour hour : hours){
            if (DateTimeService.getCurrentNumberHour() == hour.getHour()){
                return true;
            }
        }
        return false;
    }

    /**
     * Check backup is not double
     *
     * @return true if double, return false if not double backup in current hour
     */
    private Boolean backupIsDoubleInCurrentHour(){
        if (lastHourBackup == DateTimeService.getCurrentNumberHour()){
            return true;
        }
        lastHourBackup = DateTimeService.getCurrentNumberHour();
        return false;
    }

    /**
     * Check connection to database of MsSQL
     *
     * @param task with connection to database
     * @return true if has connection and return false if hasn't connection
     */
    private Boolean hasConnectionDB(Task task){
        IDatabase dbConn = new Database();
        return dbConn.hasConnection(
                task.getServer(),
                task.getInstance(),
                task.getDatabase(),
                task.getUserName(),
                task.getPassword()
        );
    }

    /**
     * Backup database to file
     *
     * @param task also TaskEntity
     * @return true if backup it has been completed successfully, false hasn't been completed
     */
    private Boolean backupDBToFile(Task task){
        Connection conn;
        String fileApe = genFileName(task, "ape");
        String pathFile = String.format("%1$s\\%2$s",task.getSavePath(), fileApe);
        String pathFileZip = String.format("%1$s\\%2$s",task.getSavePath(), genFileName(task, "zip"));

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn = DriverManager.getConnection(genConnectionString(task), task.getUserName(), task.getPassword());
            Statement statement = conn.createStatement();
            statement.execute(genCommandSQLBackup(task, pathFile));
            conn.close();

            if(compressionBackup(task, pathFile, pathFileZip, fileApe)){
                FileOperationsService fileOperations = new FileOperationsService();
                fileOperations.delete(pathFile);
            }

            return true;
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            return false;
        } catch (ClassNotFoundException ex) {
            logger.error(ex.getMessage());
            return false;
        }
    }

    /**
     * Get new name for wile of backup database
     *
     * @param task - TaskEntity
     * @param extensionFile for the file name
     * @return new name for file
     */
    private String genFileName(Task task, String extensionFile) {
        return String.format("%1$s_%2$s_%3$s.%4$s",
                trans.getString("file.archive"),
                task.getDatabase(),
                DateTimeService.getDateTimeForFile(),
                extensionFile
        );
    }

    /**
     * Generate connection string for backup database
     *
     * @param task - TaskEntity
     * @return connection string
     */
    private String genConnectionString(Task task){
        return String.format("jdbc:jtds:sqlserver://%1$s;instance=%2$s;DatabaseName=%3$s",
                task.getServer(),
                task.getInstance(),
                task.getDatabase()
        );
    }

    /**
     * Generate command SQL for backup database
     *
     * @param task - TaskEntity
     * @param pathFile - path for save file with backup of database
     * @return command of sql to backup database
     */
    private String genCommandSQLBackup(Task task, String pathFile){

        return String.format("BACKUP DATABASE %1$s TO DISK = '%2$s'",
                task.getDatabase(),
                pathFile
        );
    }

    /**
     * Compression file with backup
     *
     * @param task - EntityTask
     * @param pathFile with the backup
     * @param pathFileZip for the compression file with backup
     * @param fileInsideZip
     * @return true if file has been compressed, false if can not be compressed
     */
    private Boolean compressionBackup(Task task, String pathFile, String pathFileZip, String fileInsideZip){
        CompressionFileService compression = new CompressionFileService();
        try {
            compression.toZip(pathFile, pathFileZip, fileInsideZip);
            return true;
        } catch (IOException ex) {
            log.addWarning(String.format(trans.getString("file.can_not_compress_file"), task.getId(), ex.getMessage()));
            logger.warn(ex.getMessage());
            return false;
        }
    }
}
