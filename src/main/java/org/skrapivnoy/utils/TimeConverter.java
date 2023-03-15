package org.skrapivnoy.utils;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@UtilityClass
public class TimeConverter {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public static String secondsToHHmmString(int seconds){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, seconds);
        String timeString = timeFormat.format(calendar.getTime());
        return timeString;
    }
}
