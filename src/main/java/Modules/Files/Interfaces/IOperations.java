package Modules.Files.Interfaces;

/**
 * Created by Karol Golec on 12.08.2016.
 */
public interface IOperations {
    /**
     * Delete file
     *
     * @param pathFile of file to delete
     * @return true if file has been deleted, false if file can not be deleted
     */
    Boolean delete(String pathFile);
}
