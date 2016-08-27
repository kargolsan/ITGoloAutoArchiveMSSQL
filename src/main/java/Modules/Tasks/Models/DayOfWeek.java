package Modules.Tasks.Models;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by Karol Golec on 03.08.2016.
 */
@Entity
@Table(name = "DaysOfWeek", uniqueConstraints = { @UniqueConstraint(columnNames = "DAY_OF_WEEK_ID") })
public class DayOfWeek {

    /** id of task in database */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="DAY_OF_WEEK_ID")
    private Long id;

    /** address of server */
    @Column(name="DAY_OF_WEEK", length = 1)
    private int dayOfWeek;

    /**
     *
     /**
     * Constructor of this class
     */
    public DayOfWeek(){

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
     * Get day of week for auto backup of database
     *
     * @return day of week for auto backup of database
     */
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Set day of week for auto backup of database
     */
    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
