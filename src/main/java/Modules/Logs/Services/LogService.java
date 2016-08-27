package Modules.Logs.Services;

import Application.Services.DateTimeService;
import Modules.Logs.Models.Log;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import Modules.Logs.Controllers.LogsController;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public class LogService {

    /**
     * logger for this class
     */
    private Logger logger = LogManager.getLogger();

    /**
     * Add success log to list of application
     * @param message
     */
    public void addSuccess(String message){
        addLog(message, "success");
    }

    /**
     * Add error log to list of application
     *
     * @param message
     */
    public void addError(String message) {
        addLog(message, "error");
    }

    /**
     * Add warning log to list of application
     *
     * @param message
     */
    public void addWarning(String message) {
        addLog(message, "warning");
    }

    /**
     * Add info log to list of application
     *
     * @param message
     */
    public void addInfo(String message) {
        addLog(message, "info");
    }

    /**
     * Add log to list of application
     *
     * @param message
     */
    public void add(String message) {
        addLog(message, "normal");
    }

    /**
     * Add log to table view
     *
     * @param message of log
     * @param level of log
     */
    private void addLog(String message, String level)
    {
        try {
            Log log = new Log();
            log.setCreated_at(DateTimeService.getDateTime());
            log.setLevel(level);
            log.setMessage(message);
            LogsController.listLogs.add(0, log);
        } catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }
}
