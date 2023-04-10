package com.group.booking.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUltil {
    
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static String getCurrentTimeStamp() {
        return dtf.format(LocalDateTime.now());
    }

    public static Long getCurrentTimeMillis() {
        long msec = System.currentTimeMillis();
        return msec;
    }

    public static Long getOneHourMillis() {
        long tenMinute = 1000 * 60 * 60;
        return tenMinute;
    }

    public static Long getTenMinuteMillis() {
        long tenMinute = 1000 * 60 * 10;
        return tenMinute;
    }

    public static Long getThreeDayMillis() {
        long threeDay = 1000 * 60 * 60 * 24 * 3;
        return threeDay;
    }
    
}
