package Modules.DateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormatSymbols;
import java.util.Locale;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class DateTime {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * Get name day from number day in week
     *
     * @param dayInWeek
     * @return
     */
    public static String getNameDayInWeek(Integer dayInWeek){
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());

        String[] dayNames = symbols.getShortWeekdays();

        return dayNames[dayInWeek];
    }

    /**
     * Add zero to hour
     *
     * @param hour
     * @return string with format 00:00
     */
    public static String addZeroToHour(Integer hour){
        String newHour = hour.toString();

        if (newHour.length() == 1){
            newHour = String.format("0%1$s:00", newHour);
        } else if (newHour.length() == 2){
            newHour = String.format("%1$s:00", newHour);
        }

        return newHour;
    }

    /**
     * Get number day in week by short name day
     *
     * @param shortNameDay example "Mon", "Thu"
     * @return
     */
    public static Integer getNumberDayInWeekByShortName(String shortNameDay){
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());

        String[] dayNames = symbols.getShortWeekdays();

        for(Integer i=1 ; i < 8 ; i++){
            if(dayNames[i] == shortNameDay){
                return i;
            }
        }

        return null;
    }

    /**
     * Get integer of hour from string example "01:00"
     * @param hour
     * @return
     */
    public static Integer getHourInteger(String hour){
        try {
            return Integer.parseInt(hour.replace(":00", ""));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return null;
    }
}
