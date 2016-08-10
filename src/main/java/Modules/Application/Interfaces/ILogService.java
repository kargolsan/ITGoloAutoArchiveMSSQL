package Modules.Application.Interfaces;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public interface ILogService {

    /**
     * Add success log to list of application
     * @param message
     */
    void addSuccess(String message);

    /**
     * Add error log to list of application
     * @param message
     */
    void addError(String message);

    /**
     * Add warning log to list of application
     * @param message
     */
    void addWarning(String message);

    /**
     * Add info log to list of application
     * @param message
     */
    void addInfo(String message);

    /**
     * Add log to list of application
     * @param message
     */
    void add(String message);
}
