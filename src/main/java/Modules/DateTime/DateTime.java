package Modules.DateTime;

import java.text.DateFormatSymbols;
import java.util.Locale;

/**
 * Created by Karol Golec on 05.08.2016.
 */
public class DateTime {

    /**
     * Get name day from number day in week
     *
     * @return name day
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
}
