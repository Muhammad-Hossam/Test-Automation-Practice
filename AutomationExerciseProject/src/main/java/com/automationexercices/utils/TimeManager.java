package com.automationexercices.utils;

public class TimeManager {
    /**
     * @return current time in format yyyy-MM-dd HH:mm:ss
     */
    public static String getTimestamp(){
       return new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
    }
    /**
     * @return current time in format hh-mm-ss
     * for unique timestamp
     */
    public static String getSimpleTimestamp(){
        return Long.toString(System.currentTimeMillis());
    }
}
