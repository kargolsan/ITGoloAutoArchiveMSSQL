package Database.Exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Karol Golec
 * Date: 26.08.2016
 * Time: 09:32
 */
public class LockFileDatabaseException extends Exception {
    public LockFileDatabaseException() { super(); }
    public LockFileDatabaseException(String message) { super(message); }
    public LockFileDatabaseException(String message, Throwable cause) { super(message, cause); }
    public LockFileDatabaseException(Throwable cause) { super(cause); }
}
