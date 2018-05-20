package com.skyworth.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shallow on 2018/5/11.
 */
public class TimeFormatUtil {

    private static Logger logger = LoggerFactory.getLogger(TimeFormatUtil.class);

    public static Date longToDate(Long timeStamp){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = format.format(timeStamp);
        try {
            Date date = format.parse(timeString);
            return date;
        } catch (ParseException e) {
            logger.error("Time Format Parsing Error",e);
        }
        return null;
    }

    public static long dateToLong(Date date){
        return date.getTime();
    }

    public static Date stringToDate(String dateString){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            logger.error("String to Date Error", e);
        }
        return null;
    }

    public static String dateToString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
