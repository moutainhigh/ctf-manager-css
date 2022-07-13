package com.ctf.utils;


import cn.hutool.core.date.DateUtil;
import com.lenovo.exception.Asserts;
import com.lenovo.result.ResultCode;
import net.sf.cglib.core.Local;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @anthor Satellite
 * DateUtils
 * 日期处理工具类
 * http://www.javalow.com
 * @date 2018-11-19-22:33
 **/
public class DateUtils {
    private final static String GMT = "GMT";
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    public final static String DATE_YYYYMMDD_PATTERN = "yyyyMMdd";
    public final static String DATA_HHMMSS_PATTERN = "HH:mm:ss";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式(yyyy-MM-ddHHmmss)
     */
    public final static String TIMESTAMP_PATTERN = "yyyyMMddHHmmss";
    public final static String HHMM_PATTERN = "HHmm";

    /**
     * excel时间格式
     */
    public static String XLSX_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static String XLSX_DATE_TIME_FORMAT2 = "yyyy/M/dd HH:mm:ss";
    public static String XLSX_DATE_TIME_FORMAT3 = "yyyy/M/d H:mm:ss";
    public static String TIME_ZONE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static String TIME_POINT_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";


    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern,Locale.getDefault());
            df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return df.format(date);
        }
        return null;
    }

    public static String format(Date date, String pattern,String timezone) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            df.setTimeZone(TimeZone.getTimeZone(timezone));
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LocalDate stringToLocalDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(strDate, fmt);
        //return LocalDateTime.parse(strDate, fmt);
    }


    public static void main(String[] args) {
        String s = format(utcToTimeZone("2021-04-28T08:07:30Z", "yyyy-MM-dd'T'HH:mm:ss",8),DATE_TIME_PATTERN);
        System.out.println(s);
//        LocalDateTime of1 = LocalDateTime.of(2019, 8, 25, 1, 1);//2018-9-25 01:01
//        LocalDateTime of2 = LocalDateTime.of(2019, 9, 25, 23, 16); //2019-9-25 23:16
//        System.out.println(diffDay(of1,of2));//365
      //  System.out.println(DateUtil.to("Mar 5, 2014 12:00:00 AM", "yyyy-MM-dd HH:ss:mm"));

        Date date = new Date();
        String format1 = format(date, DATE_TIME_PATTERN);
        System.out.println(format1);

        String format = format(DateUtils.toTimeZoneDate(new Date(), TimeZone.getTimeZone(Constant.TIMEZONE), TimeZone.getTimeZone("GMT" + "+02:00")), DATE_TIME_PATTERN);
        System.out.println(format);

        String format2 = format(utcToTimeZone("2021-05-03T04:40:30", "yyyy-MM-dd'T'HH:mm:ss", 8), DATE_TIME_PATTERN);
        Date date1 = stringToDate(format2, DATE_TIME_PATTERN);
        System.out.println("utcToTimeZone" + format2);
        System.out.println("utcToTimeZone" + date1);
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date    日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date    日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date  日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 天数差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffDay(LocalDateTime before, LocalDateTime after) {
        long be = before.toEpochSecond(ZoneOffset.ofHours(0));
        long beDate = be /(60*60*24);

        long af = after.toEpochSecond(ZoneOffset.ofHours(0));
        long afDate = af /(60*60*24);

        return (int) (afDate -
                beDate);
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date  日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date   日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date  日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 获取两个时间的中间的间隔 单位是秒
     *
     * @param d1
     * @param d2
     * @return
     */
    public static long getBetweenSecond(Date d1, Date d2) {
        return Math.abs((d1.getTime() - d2.getTime()) / 1000);
    }

    /**
     * 取得某天所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    /**
     * 取得某天所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    public static Date convert2Date(String dateStr, String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        try {
            simple.setLenient(false);
            return simple.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 一组时间  返回最大时间
     *
     * @param List<Date>date
     * @return
     */
    public static Date getMaxDate(List<Date> date) {
        Date nums[] = new Date[date.size()];
        for (int i = 0, j = date.size(); i < j; i++) {
            nums[i] = date.get(i);
        }
        for (int i = 0; i <= nums.length - 1; i++) {
            for (int j = i + 1; j <= nums.length - 1; j++) {
                if (nums[i].getTime() > nums[j].getTime()) {
                    Date temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums[nums.length - 1];
    }

    /**
     *  时间字符转成 localDateTime
     * @param dateStr
     * @param pattern
     * @return
     */
    public static LocalDateTime str2LocalDateTime(String dateStr,String pattern){
        if(StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern)){
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateStr,df);
    }

    /**
     * LocalDaetTime 转换 String
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String LocalDateTime2Str(LocalDateTime localDateTime,String pattern){
        if(localDateTime == null || StringUtils.isBlank(pattern)){
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(localDateTime);
    }

    /**
     * 由字符串时区获取整数时区
     *
     * @param timeZone
     * @return
     */
    public static Integer getIntegerTimeZone(String timeZone) {
        List<String> matches = RegexUtils.getMatches("(?<=(\\S*))(\\+|\\-)\\S*", timeZone);
        if (!matches.isEmpty()) {
            try {
                return Integer.parseInt(matches.get(0));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Asserts.fail(ResultCode.ERROR, "timezone type error");
            }
        }
        return null;
    }


    public static String getStringTimeZone(Integer timeZone) {

        if (timeZone != null) {
            if (timeZone >= 0) {
                return (GMT + "+" + timeZone.toString());
            } else {
                return (GMT + timeZone.toString());
            }
        }
        return null;
    }


    /**
     * 时间转字符窜
     * @param date
     * @param format
     * @return String 返回类型
     */
    public static String date2string(Date date, String format) {
        if(date==null){
            return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        String result = null;
        try {
            result = df.format(date);
        } catch (Exception e) {
        }
        return result;
    }

    public static Date toTimeZoneDate(Date date, TimeZone timeZone,TimeZone toTimeZone){
//        int zone = timeZone.getOffset(System.currentTimeMillis()) / (3600000);
//        int toZone = toTimeZone.getOffset(System.currentTimeMillis()) / (3600000);
        /*TimeZone srcTimeZone = TimeZone.getTimeZone("GMT" + zone);
        TimeZone destTimeZone = TimeZone.getTimeZone("GMT"+toZone);*/
        long l = date.getTime() - timeZone.getRawOffset() + toTimeZone.getRawOffset();
        Date date1 = new Date(l);
        return date1;
    }

    public static TimeZone getRequestTimeZone(HttpServletRequest request){
        Locale clientLocale = request.getLocale();
        Calendar calendar = Calendar.getInstance(clientLocale);
        TimeZone clientTimeZone = calendar.getTimeZone();
        return clientTimeZone;
    }

    // 获取本月的开始时间
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    // 获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    // 获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    // 获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0,
                0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 获取本月的结束时间
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    // 获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23,
                59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Date utcToTimeZone(String utcTime,String pattern,Integer timeZone){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("UTC时间: " + date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + timeZone);
        //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
        System.out.println("北京时间: " + calendar.getTime());
        return calendar.getTime();
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断当前时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔(天)
     * @param startTime
     * @param endTime
     * @return
     */
    public static int differentDaysByMillisecond(String startTime,String endTime) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=sdf.parse(startTime);
        Date date2=sdf.parse(endTime);
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }


}
