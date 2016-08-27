package Modules.Tasks.Models;

import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by Karol Golec on 03.08.2016.
 */
@Entity
@Table(name = "TASKS", uniqueConstraints = { @UniqueConstraint(columnNames = "TASK_ID") })
public class Task {

    /** id of task in database */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="TASK_ID")
    private Long id;

    /** address of server */
    @Column(name="SERVER", length = 100)
    private String server;

    /** instance of server */
    @Column(name="INSTANCE", length = 100)
    private String instance;

    /** name database */
    @Column(name="DATABASE", length = 100)
    private String database;

    /** for access to database */
    @Column(name="USERNAME", length = 100)
    private String userName;

    /** for access to database */
    @Column(name="PASSWORD", length = 100)
    private String password;

    /** path to save backup database */
    @Column(name="SAVE_PATH", length = 400)
    private String savePath;

    /** reserve path to save backup database */
    @Column(name="SAVE_PATH_RESERVE", length = 400)
    private String savePathReserve;

    /** days of week for auto backup of database */
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            joinColumns=@JoinColumn(name="DAY_OF_WEEK_ID"))
    private Set<DayOfWeek> daysOfWeek;

    /** hours in day for auto backup of database */
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            joinColumns=@JoinColumn(name="HOUR_ID"))
    private Set<Hour> hours;

     /**
     * Constructor of Task
     */
    public Task()
    {

    }

    /**
     * Get id of task in database
     *
     * @return id of task in database
     */
    public Long getId() {
        return id;
    }

    /**
     * Get address of server
     *
     * @return address of server
     */
    public String getServer() {
        return server;
    }

    /**
     * Set address of server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Get instance of server
     *
     * @return instance of server
     */
    public String getInstance() {
        return instance;
    }

    /**
     * Set instance of server
     */
    public void setInstance(String instance) {
        this.instance = instance;
    }

    /**
     * Get name database
     *
     * @return name database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Set name database
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * Get username for access to database
     *
     * @return username for access to database
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set username for access to database
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get password for access to database
     *
     * @return password for access to database
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password for access to database
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get path to save backup database
     *
     * @return path to save backup database
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     * Set path to save backup database
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * Get reservepath to save backup database
     *
     * @return reserve path to save backup database
     */
    public String getSavePathReserve() {
        return savePathReserve;
    }

    /**
     * Set reserve path to save backup database
     */
    public void setSavePathReserve(String savePathReserve) {
        this.savePathReserve = savePathReserve;
    }

    /**
     * Get days of week for auto backup of database
     *
     * @return days of week for auto backup of database
     */
    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    /**
     * Set days of week for auto backup of database
     */
    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    /**
     * Get hours in day for auto backup of database
     *
     * @return hours in day for auto backup of database
     */
    public Set<Hour> getHours() {
        return hours;
    }

    /**
     * Set hours in day for auto backup of database
     */
    public void setHours(Set<Hour> hours) {
        this.hours = hours;
    }
}
