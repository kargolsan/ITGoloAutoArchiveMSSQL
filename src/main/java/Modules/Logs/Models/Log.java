package Modules.Logs.Models;

/**
 * Created by Karol Golec on 10.08.2016.
 */
public class Log {
    private String created_at;
    private String level;
    private String message;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
