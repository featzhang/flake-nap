package com.github.featzhang.snap.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String formatTime(long timeInMillis) {

        if (timeInMillis < 1000000000000L) {
            timeInMillis *= 1000;
        }

        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        // Create a Date object from the milliseconds
        Date date = new Date(timeInMillis);

        // Format the date as a string using the SimpleDateFormat object
        String dateString = dateFormat.format(date);

        System.out.println(dateString); // Output: 2021-03-26 12:55:00

        return dateString;
    }
}
