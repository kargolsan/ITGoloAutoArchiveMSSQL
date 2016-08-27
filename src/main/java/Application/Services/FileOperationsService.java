package Application.Services;

import java.io.File;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by Karol Golec on 12.08.2016.
 */
public class FileOperationsService {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * Delete file
     *
     * @param pathFile of file to delete
     * @return true if file has been deleted, false if file can not be deleted
     */
    public Boolean delete(String pathFile) {
        try{

            File file = new File(pathFile);

            if(file.delete()){
                return true;
            }else{
                return false;
            }

        }catch(Exception ex){
            logger.error(ex.getMessage());
            return false;
        }
    }
}
