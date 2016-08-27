package Application.Services;

import java.util.ResourceBundle;
import Database.Services.SessionService;
import Database.Exceptions.LockFileDatabaseException;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 26.08.2016
 * Time: 10:33
 */
public class LoaderService {

    /**
     * Create session of database for
     * application and check connection with it
     * and display alert with exception for UI
     *
     * @return true session created or false if session uncreated
     */
    public static Boolean createSessionDatabase(ResourceBundle resources) {
        try {
            SessionService.createSession();
            return true;
        } catch (LockFileDatabaseException e) {
            AlertService.errorException(
                    resources.getString("loader_service.closing_application"),
                    resources.getString("loader_service.your_database_is_open"),
                    resources.getString("loader_service.find_instance_application_and_close"),
                    e,
                    resources
            );
        } catch (Exception e) {
            AlertService.errorException(
                    resources.getString("loader_service.closing_application"),
                    resources.getString("loader_service.unexpected_error"),
                    resources.getString("loader_service.application_has_unexpected_error"),
                    e,
                    resources
            );
        }
        return false;
    }

    /**
     * Load with loader asynchronous without run application
     */
    public static void load(){
        String LANGUAGE = "Application/Resources/Languages/application";
        if (!createSessionDatabase(LanguageService.getResourceBundle(LANGUAGE))) {
            ApplicationService.terminate();
        }
    }
}
