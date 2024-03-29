package com.group.booking.Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeUltil {
    
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
    public static final DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getCurrentMonthAgoYear() {   
        LocalDateTime localDateTime = LocalDateTime.now();

        String currentYear = String.valueOf(localDateTime.getYear());
        String agoYear = String.valueOf(localDateTime.getYear()-1);
        
        return dtf3.format(LocalDateTime.now()).replace(currentYear, agoYear).substring(0, 8)+"01";
    }

    public static String getDateFormatYYYYMMDD(String date) {
        return dtf3.format(LocalDateTime.of(Integer.valueOf(date.split("-")[0]), Integer.valueOf(date.split("-")[1]), Integer.valueOf(date.split("-")[2]), 0, 0, 0));
    }

    public static String getCurrentMonth() {
        return dtf3.format(LocalDateTime.now());
    }

    public static String getCurrentTimeStamp() {
        return dtf2.format(LocalDateTime.now());
    }

    public static Date getDateYYYYMMDD(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        try {
            Long time = sdf.parse(date).getTime();
            Date parseDate = new Date(time);
            return parseDate;
        } catch (Exception e) {
            return null;
        }
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
