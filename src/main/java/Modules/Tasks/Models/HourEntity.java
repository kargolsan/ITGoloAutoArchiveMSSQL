package Modules.Tasks.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Karol Golec on 03.08.2016.
 */
@Entity
@Table(name = "Hours", uniqueConstraints = { @UniqueConstraint(columnNames = "HOUR_ID") })
public class HourEntity {

    /** id of task in database */
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="HOUR_ID")
    private Long id;

    /** address of server */
    @Column(name="HOUR", length = 2)
    private int hour;

     /**
     * Constructor of this class
     */
    public HourEntity() {


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
     * Get hour in day for auto backup of database
     *
     * @return hour in day for auto backup of database
     */
    public int getHour() {
        return hour;
    }

    /**
     * Set hour in day for auto backup of database
     */
    public void setHour(int hour) {
        this.hour = hour;
    }
}
