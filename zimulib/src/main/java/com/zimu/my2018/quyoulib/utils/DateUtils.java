package com.zimu.my2018.quyoulib.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 功能：
 * 描述：
 * Created by hxl on 2018/10/4
 */
public class DateUtils {
    public static final long ONE_SECOND = 1000;
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    public static final long ONE_DAY = ONE_HOUR * 24;
    public static final long ONE_WEEK = ONE_DAY * 7;

    public static final long ONE_MOUNTH = ONE_DAY * 30;
    public static final long ONE_YEAR = ONE_DAY * 365;
    public static String[] WEEK = new String[]{"天", "一", "二", "三", "四", "五", "六"};
    public static String[] WEEKZ = new String[]{"日", "一", "二", "三", "四", "五", "六"};


    /**
     * 获取现在时间
     *
     * @return
     */
    public static String getNow() {
        return formatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化日期
     *
     * @param date   日期
     * @param format 格式化字符串
     * @return
     */
    public static String formatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * String 日期 转换 Date
     *
     * @param str    日期 字符串
     * @param format 格式化字符串
     * @return null为格式化失败
     */
    public static Date formatStrDate2Date(String str, String format) {
        try {
            return new SimpleDateFormat(format, Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * yyyy-MM-dd HH:mm:ss 日期 转换 Date
     *
     * @param str 日期 字符串
     * @return null为格式化失败
     */
    public static Date formatStrDate2Date(String str) {
        return formatStrDate2Date(str, "yyyy-MM-dd'T'HH:mm:ss");
    }

    /**
     * Date（long） 转换 StringDate
     *
     * @param time   long类型日期
     * @param format 格式化字符串
     * @return 字符串类型的时间
     */
    public static String formatLong2StrDate(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(time);
    }

    /**
     * 字符串日期转换为字符串日期
     *
     * @param str          字符串日期
     * @param format       字符串日期的格式化格式
     * @param secondFormat 要返回的字符串日期的格式化格式
     * @return 字符串日期
     */
    public static String formatStrDate2StrDate(String str, String format, String secondFormat) {
        try {
            long time = new SimpleDateFormat(format, Locale.getDefault()).parse(str).getTime();
            return formatLong2StrDate(time, secondFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date().toString();
    }

    /**
     * 获取周几
     *
     * @param date
     * @return
     */
    public static String formatWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return "星期" + WEEK[dayOfWeek - 1];
    }

    /**
     * 获取周几
     *
     * @param date
     * @return
     */
    public static String formatWeekZ(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return "周" + WEEKZ[dayOfWeek - 1];
    }

    /**
     * 获取当前日期
     *
     * @return yyyy年MM月dd日
     */
    public static String getCurrentDate() {
        return formatDateTime(new Date(), "yyyy年MM月dd日");
    }

    /**
     * 获取当前日期
     *
     * @return yyyy.MM.dd
     */
    public static String getCurrentDate2() {
        return formatDateTime(new Date(), "yyyy.MM.dd");
    }

    /**
     * 获取当前日期
     *
     * @return yyyy.MM
     */
    public static String getCurrentDate3() {
        return formatDateTime(new Date(), "yyyy.MM");
    }


    /**
     * 获取接下来几天的日期(0-6)
     *
     * @param position
     * @return
     */
    public static String get2Week(int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

        Date currentDate = new Date();
        Date fdate;
        List<String> list = new ArrayList<String>();
        Long fTime = currentDate.getTime();
        for (int a = 0; a < 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(sdf.format(fdate));
        }
        return list.get(position);
    }

    //一个日期与现在时间差多少天    , 例如：还有多少天生日
    public static String getLastDays(String noticeTime, String format) {
        Date now = new Date(System.currentTimeMillis());
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(now);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        int year = cal1.get(Calendar.YEAR);
        Date notice = formatStrDate2Date(noticeTime, format);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(notice);
        cal2.set(Calendar.YEAR, year);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        long splitTime = cal2.getTimeInMillis() - cal1.getTimeInMillis();
        long day = splitTime / ONE_DAY;
        if (splitTime > 0) {
            day = day + 1;
        }

        day = day % 365;
        day = Math.abs(day);
        return String.format(Locale.getDefault(), "%d天", day);
    }

    /**
     * 两个日期相差天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getBetDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            return day2 - day1;
        }
    }

    public static String getLocalTime(String time) {
        // 取出年月日来，比较字符串即可
        String str_curTime = DateFormat.format("yyyy-MM-dd", new Date()).toString();
        int result = str_curTime.compareTo(time.substring(0, time.indexOf(" ")));
        if (result > 0) {
            return "昨天"
                    + time.substring(time.indexOf(" "), time.lastIndexOf(":"));
        } else if (result == 0) {
            return "今天"
                    + time.substring(time.indexOf(" "), time.lastIndexOf(":"));
        } else {
            return time;
        }
    }


    /**
     * long日期 去除 时分秒（时分秒全部为0）
     *
     * @param date date
     * @return long
     */
    public static long getYearMonthDay(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取目标时间和当前时间之间的差距(30天)
     *
     * @param date    date
     * @param formart 如果超过友好显示的时间段的格式化字符串
     * @return String
     */
    public static String getTimestampString(Date date, String formart) {
        Date curDate = new Date();
        long splitTime = curDate.getTime() - date.getTime();
        if (splitTime < (30 * ONE_DAY)) {
            if (splitTime < ONE_MINUTE) {
                return "刚刚";
            }
            if (splitTime < ONE_HOUR) {
                return String.format(Locale.getDefault(), "%d分钟前", splitTime / ONE_MINUTE);
            }

            if (splitTime < ONE_DAY) {
                return String.format(Locale.getDefault(), "%d小时前", splitTime / ONE_HOUR);
            }


            if (splitTime < ONE_WEEK) {
                return date2MMddWeekD(date);
            }

            return String.format(Locale.getDefault(), "%d天前", splitTime / ONE_DAY);
        }
        return formatDateTime(date, formart);
    }

    public static String date2MMddWeekD(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return new SimpleDateFormat("星期", Locale.getDefault()).format(date) + WEEK[dayOfWeek - 1];
    }


    /**
     * 获取目标时间跟当前时间之间的差距 友好显示
     *
     * @param sdate "yyyy-MM-dd HH:mm:ss"
     * @return 时间友好显示
     */
    public static String getTimestampString(String sdate) {
        if (sdate == null || "".equals(sdate))
            return null;

        Date date = formatStrDate2Date(sdate, "yyyy-MM-dd HH:mm:ss");

        if (date == null)
            return null;

        return getTimestampString(date, "yyyy年MM月dd日");
    }


    /**
     * 24小时制 转换 12小时制
     *
     * @param time hh:mm
     * @return 12:23 上午
     */
    public static String trans24To12(String time) {
        String str[] = time.split(":");
        int h = Integer.valueOf(str[0]);
        int m = Integer.valueOf(str[1]);
        String sx;
        if (h < 1) {
            h = 12;
            sx = "上午";
        } else if (h < 12) {
            sx = "上午";
        } else if (h < 13) {
            sx = "下午";
        } else {
            sx = "下午";
            h -= 12;
        }
        return String.format(Locale.getDefault(), "%d:%02d%s", h, m, sx);
    }


    /**
     * Date 转换 HH
     *
     * @param date date
     * @return String
     */
    public static String getHH(Date date) {
        return formatDateTime(date, "HH");
    }


    /**
     * Date 转换 HH:mm
     *
     * @param date date
     * @return String
     */
    public static String getHHmm(Date date) {
        return formatDateTime(date, "HH:mm");
    }


    /**
     * Date 转换 HH:mm:ss
     *
     * @param date date
     * @return String
     */
    public static String getHHmmss(Date date) {
        return formatDateTime(date, "HH:mm:ss");
    }


    /**
     * Date 转换 MM.dd
     *
     * @param date MM.dd
     * @return String
     */
    public static String getMd(Date date) {
        return formatDateTime(date, "MM.dd");
    }

    /**
     * Date 转换 MM-dd
     *
     * @param date MM-dd
     * @return String
     */
    public static String getMd2(Date date) {
        return formatDateTime(date, "MM-dd");
    }

    /**
     * 格式化日期
     *
     * @return yyyy年MM月dd日
     */
    public static String getyMd(Date date) {
        return formatDateTime(date, "yyyy年MM月dd日");
    }

    /**
     * 格式化日期
     *
     * @return yyyy年MM月dd日
     */
    public static String getyYear(Date date) {
        return formatDateTime(date, "yyyy年");
    }

    /**
     * 格式化日期
     *
     * @return yyyy年MM月dd日
     */
    public static String getyMdd(Date date) {
        return formatDateTime(date, "yyyy-MM-dd'T'HH:mm:ss");
    }

    /**
     * Date 转换 yyyy-MM-dd
     *
     * @param date date
     * @return String
     */
    public static String getyMd2(Date date) {
        return formatDateTime(date, "yyyy-MM-dd");
    }

    /**
     * Date 转换 yyyy.MM.dd
     *
     * @param date date
     * @return String
     */
    public static String getyMd3(Date date) {
        return formatDateTime(date, "yyyy.MM.dd");
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"   ==>  "yyyy年MM月dd日 HH:mm"
     *
     * @param sDate "yyyy.MM.dd HH:mm"
     * @return "yyyy年MM月dd日 HH:mm:ss"
     */
    public static String getyMd(String sDate) {
        return formatStrDate2StrDate(sDate, "yyyy-MM-dd'T'HH:mm:ss", "yyyy年MM月dd日");
    }

    /**
     * Date 转换 HH:mm
     *
     * @param date date
     * @return String
     */
    public static String getHHmm(String date) {
        return formatStrDate2StrDate(date, "yyyy-MM-dd HH:mm:ss", "HH:mm");
    }


    /**
     * Date 转换 yyyy年MM月dd日 星期
     *
     * @param date date
     * @return String
     */
    public static String getyMdWeek(Date date) {
        return formatDateTime(date, "yyyy年MM月dd日") + " " + formatWeek(date);
    }

    /**
     * 时间字符串 转换 yyyy年MM月dd日 (星期) HH:mm:ss
     *
     * @param sDate "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getyMdWeekHms(String sDate) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return formatStrDate2StrDate(sDate, format, "yyyy年MM月dd日 （" + formatWeek(formatStrDate2Date(sDate, format)) + "）HH:mm:ss");
    }

    /**
     * Date 转换 MM月dd日 星期
     *
     * @param date date
     * @return String
     */
    public static String getMdWeek(Date date) {
        return formatDateTime(date, "MM月dd日") + "·" + formatWeekZ(date);
    }


    /**
     * MM/yyyy
     *
     * @param date
     * @return
     */
    public static String getMy(Date date) {
        return formatDateTime(date, "MM/yyyy");
    }


    /**
     * 时间字符串 转换 yyyy年MM月dd日 星期
     *
     * @param sDate "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getyMdWeek(String sDate) {
        String format = "yyyy-MM-dd HH:mm:ss";
        return formatStrDate2StrDate(sDate, format, "yyyy年MM月dd日 " + formatWeek(formatStrDate2Date(sDate, format)));
    }


    /**
     * "yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String getyMdHms(Date date) {
        return formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * "yyyy-MM-dd HH:mm:ss"   ==>  "yyyy年MM月dd日 HH:mm:ss"
     *
     * @param sDate "yyyy-MM-dd HH:mm:ss"
     * @return "yyyy年MM月dd日 HH:mm:ss"
     */
    public static String getyMdHms(String sDate) {
        return formatStrDate2StrDate(sDate, "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日 HH:mm:ss");
    }

    /**
     * "yyyy.MM.dd HH:mm"   ==>  "yyyy年MM月dd日 HH:mm:ss"
     *
     * @param sDate "yyyy.MM.dd HH:mm"
     * @return "yyyy年MM月dd日 HH:mm:ss"
     */
    public static String getyMdHms2(String sDate) {

        return formatStrDate2StrDate(sDate, "yyyy.MM.dd HH:mm", "yyyy年MM月dd日 HH:mm");

    }


    /**
     * "yyyy-MM-dd HH:mm:ss"   ==>  "yyyy年MM月dd日 HH:mm"
     *
     * @param sDate "yyyy.MM.dd HH:mm"
     * @return "yyyy年MM月dd日 HH:mm:ss"
     */
    public static String getyMdHm(String sDate) {
        return formatStrDate2StrDate(sDate, "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日 HH:mm");
    }

    /**
     * yyyy-MM-dd"   ==>  "yyyy.MM
     */
    public static String getYM(String sDate) {
        return formatStrDate2StrDate(sDate, "yyyy-MM-dd", "yyyy.MM");
    }

    /**
     * yyyy-MM-dd"   ==>  "yyyy年MM月
     */
    public static String getYM2(String sDate) {
        return formatStrDate2StrDate(sDate, "yyyy-MM-dd", "yyyy年MM月");
    }

    /**
     * yyyy-MM-dd"   ==>  "yyyy年MM月dd日
     */
    public static String getYMR(String sDate) {
        return formatStrDate2StrDate(sDate, "yyyy-MM-dd", "yyyy年MM月dd日");
    }

    /**
     * yyyy年MM月dd日"   ==>  "yyyy-MM-dd
     */
    public static String getY_M_R(String sDate) {
        return formatStrDate2StrDate(sDate, "yyyy年MM月dd日", "yyyy-MM-dd");
    }

    /**
     * 两个时间段相差月份
     */
    public static int countMonths(String date1, String date2, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);

        //开始日期若小于结束日期
        if (year < 0) {
            year = -year;
            return year * 12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        }

        return year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
    }
}

