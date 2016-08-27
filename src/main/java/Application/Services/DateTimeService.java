package Application.Services;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class DateTimeService {

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
     * Get number day in week by full name day
     *
     * @param fullNameDay example "Monday", "Wednesday"
     * @return
     */
    public static Integer getNumberDayInWeekByFullName(String fullNameDay){
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());

        String[] dayNames = symbols.getWeekdays();

        for(Integer i=1 ; i < 8 ; i++){
            if(dayNames[i] == fullNameDay){
                return i;
            }
        }

        return null;
    }

    /**
     * Get current number of day in week
     *
     * @return number of current day in week
     */
    public static Integer getCurrentNumberDayInWeek(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get current number of hour
     *
     * @return number of current hour
     */
    public static Integer getCurrentNumberHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
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

    /**
     * Get full name all days in week to list
     *
     * @return
     */
    public static List<String> getFullNameDays(){

        List<String> days = new ArrayList<String>();

        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());

        String[] dayNames = symbols.getWeekdays();

        for(Integer i=1 ; i < 8 ; i++){
            days.add(dayNames[i]);
        }

        return days;
    }

    /**
     * Get all hours to list in day
     *
     * @return list in all hours in day (example: {"00:00", "01:00" ... })
     */
    public static List<String> getAllHoursInDay(){
        List<String> hours = new ArrayList<String>();

        for(Integer i=0 ; i < 24 ; i++){
            String prefix = "";
            if (i < 10) prefix = "0";

            String hour = String.format("%1$s%2$s:00", prefix, i);
            hours.add(hour);
        }
        return hours;
    }

    /**
     * Get date and time (format YYYY-MM-DD HH:MM:SS)
     *
     * @return
     */
    public static String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Get date and time with format for file (format YYYYMMDD_HHMMSS)
     *
     * @return string
     */
    public static String getDateTimeForFile(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
