package org.skrapivnoy.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeConverter {
    public static String secondsToHHmmString(int seconds){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, seconds);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String timeString = sdf.format(calendar.getTime());
        return timeString;
    }
}
