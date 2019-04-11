package com.wzz.acg.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateTimeUtil {

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

    public static Date strToDate(String str){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(str);
        return dateTime.toDate();
    }

//    public static void main(String[] args) {
//        Date date = new Date();
//        System.out.println(date.toString());
//        String str = DateTimeUtil.dateToStr(date);
//        System.out.println(str);
//        Date date1 = DateTimeUtil.strToDate("2018-06-05 00:00:00");
//        System.out.println(date1.toString());
//        if(date == date1)
//            System.out.println("yes");
//        System.out.println("no");
//    }
}
