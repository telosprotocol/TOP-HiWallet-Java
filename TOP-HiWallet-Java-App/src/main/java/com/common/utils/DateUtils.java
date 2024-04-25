package com.common.utils;

import java.util.Calendar;
import java.util.Date;

import com.gitee.magic.framework.head.utils.TimeUtils;

public class DateUtils extends TimeUtils {

    public static Date getThisDayStart() {
        return getDayStart(new Date());
    }

    public static Date getDayStart(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        Date start = todayStart.getTime();
        return start;
    }

    public static Date getDayEnd(Date date) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 23);
        todayStart.set(Calendar.MINUTE, 59);
        todayStart.set(Calendar.SECOND, 59);
        Date end = todayStart.getTime();
        return end;
    }

    public static Date getThisDayEnd() {
        return getDayEnd(new Date());
    }

    public static Date addMin(Date date, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, min);
        return calendar.getTime();
    }

    public static String getGuessGamePhase(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if ((hour & 1) == 1) {
            hour = hour - 1;
        }
        String str = hour < 10 ? ("0" + hour) : String.valueOf(hour);
        return format(date, YYYYMMDD) + str;
    }

    public static String getGuessGameLastPhase(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -2);
        return getGuessGamePhase(calendar.getTime());
    }

    public static String getGuessStartTime(Date date) {
        return getGuessGamePhase(date) + "0000";
    }

    public static String getGuessLastStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -2);
        return getGuessStartTime(calendar.getTime());
    }

    public static String getGuessLastEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -2);
        return getGuessEndTime(calendar.getTime());
    }

    public static String getGuessEndTime(Date date) {
        String start = getGuessStartTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format(start, TimeUtils.YYYYMMDDHHMMSS));
        calendar.add(Calendar.HOUR, 2);
        return format(calendar.getTime(), TimeUtils.YYYYMMDDHHMMSS);
    }

    public static Date getWeekStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date date = cal.getTime();
        return getDayStart(date);
    }

    public static Date getWeekBeforeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        return calendar.getTime();
    }
    public static Date addDay(Date date,Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static int getDayDiff(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND, 0);
        date1 = calendar.getTime();
        calendar.setTime(date2);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND, 0);
        date2 = calendar.getTime();
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days > 0 ? days : -days;
    }

    public static void main(String[] args) {

//        System.out.println(getWeekBeforeDate(new Date()).getTime());
//        System.out.println(Long.valueOf(getGuessEndTime(new Date("2019/10/11 23:59:11"))));
//        System.out.println(StringUtils.random());
//        System.out.println((Base64.encode(StringUtils.random().getBytes())));
    }


}
