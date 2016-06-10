package com.weixin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The Class DateUtils.
 *
 * @author lzd, Arlight
 */
public class DateUtils {

    //默认时间格式
    public static SimpleDateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 比较两个时间的差
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static String compareDate(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return "";
        }

        StringBuffer date = new StringBuffer();
        long time = Math.abs(endDate.getTime() - startDate.getTime());
        int hour = (int) (time / (1000 * 3600));
        if (hour > 0 || date.length() > 0) {
            date = date.append(hour).append(" 时  ");
        }

        time = time - hour * (1000 * 3600);
        int minute = (int) (time / (1000 * 60));
        if (minute > 0 || date.length() > 0) {
            date = date.append(minute).append(" 分 ");
        }

        time = time - minute * (1000 * 60);
        int second = (int) (time / (1000));
        if (second > 0 || date.length() > 0) {
            date = date.append(second).append(" 秒 ");
        } else {
            date = date.append(0).append(" 秒 ");
        }
        //		logger.debug("ExecuteDate:"+date.toString());
        return date.toString();
    }

    /**
     * 取间隔若干天后的日期.
     *
     * @param date  the date
     * @param count the day count
     * @return the Date
     */
    public static Date addDay(Date date, int count) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, count);
        return cal.getTime();

    }

    /**
     * 取间隔若干月后的日期.
     *
     * @param date  the date
     * @param count the month count
     * @return the Date
     */
    public static Date addMonth(Date date, int count) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, count);
        return cal.getTime();

    }

    /**
     * 取两个日期中较早的一个日期
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Date getEarlierDate(Date d1, Date d2) {
        if (d1.after(d2)) {
            return d2;
        } else {
            return d1;
        }
    }

    /**
     * 取两个日期中较晚的一个日期
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Date getLaterDate(Date d1, Date d2) {
        if (d1.before(d2)) {
            return d2;
        } else {
            return d1;
        }
    }


    /**
     * 取指定数据小时转成日期Date型
     *
     * @param dataHour 格式yyyymmddhh
     * @return
     */
    public static Date hourToDate(Long dataHour) {
        String dateStr = String.valueOf(dataHour);
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int mon = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));
        int hr = Integer.parseInt(dateStr.substring(8, 10));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mon - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hr);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //		cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 取指定数据日期的开始时间（0时0分0秒）
     *
     * @param dataDate 格式yyyymmdd， 兼容yyyymmddhh
     * @return
     */
    public static Date parseDateStart(Long dataDate) {
        String dateStr = String.valueOf(dataDate);
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int mon = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mon - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //		cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 取指定数据日期的开始时间（0时0分0秒）
     *
     * @param date 数据日期
     * @return
     */
    public static Date parseDateStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //		cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 取指定数据日期的开始时间（23时59分59秒）
     *
     * @param dataDate 格式yyyymmdd， 兼容yyyymmddhh
     * @return
     */
    public static Date parseDateEnd(Long dataDate) {
        String dateStr = String.valueOf(dataDate);
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int mon = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mon - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        //		cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 取指定数据日期的结束时间（23时59分59秒）
     *
     * @param date 数据日期
     * @return
     */
    public static Date parseDateEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        //		cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 根据日期返回日期所属的“yyyymm”格式的月份
     *
     * @param date
     * @return
     */
    public static long dateToMon(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int mon = cal.get(Calendar.MONTH);
        return year * 100 + mon + 1;
    }

    /**
     * 根据"yyyymmdd"格式的日期返回日期所属的“yyyymm”格式的月份
     *
     * @param date
     * @return
     */
    public static long dateToMon(long date) {
        String mon = String.valueOf(date).substring(0, 6);
        return Long.parseLong(mon);
    }

    /**
     * 取与当前月份相间隔指定月份数的月份
     *
     * @param month  格式为 "yyyymm"
     * @param offset 间隔月份数,正负均可
     */
    public static long addMonth(long month, int offset) {
        int year = Integer.parseInt(String.valueOf(month).substring(0, 4));
        int mon = Integer.parseInt(String.valueOf(month).substring(4, 6));
        if (offset >= 0) {
            for (int i = 1; i <= offset; i++) {
                mon++;
                if (mon > 12) {
                    mon = 1;
                    year++;
                }
            }
        } else {
            for (int i = -1; i >= offset; i--) {
                mon--;
                if (mon < 1) {
                    mon = 12;
                    year--;
                }
            }
        }
        return year * 100 + mon;
    }

    /**
     * 取与指定时间间隔若干小时的时间
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date addHour(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, offset);
        return cal.getTime();
    }

    /**
     * 取与指定时间间隔若干小时的时间
     *
     * @param dateTime:yyyy-MM-dd hh:mm:ss
     * @param offset
     * @return String yyyy-MM-dd hh:mm:ss;
     */
    public static String addHour(String dateTime, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseDate(dateTime, DEFAULT_DATETIME_FORMAT));
        cal.add(Calendar.HOUR_OF_DAY, offset);
        return DEFAULT_DATETIME_FORMAT.format(cal.getTime());
    }

    /**
     * 取与指定时间间隔若干小时的时间
     *
     * @param hour
     * @param offset
     * @return
     */
    public static long addHour(long hour, int offset) {
        String dateStr = String.valueOf(hour);
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int mon = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mon - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, offset);
        return dateToHour(cal.getTime());
    }

    /**
     * 取与指定时间间隔若干分钟的时间
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date addMinute(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, offset);
        return cal.getTime();
    }

    /**
     * 取与指定时间间隔若干秒的时间
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date addSecond(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, offset);
        return cal.getTime();
    }

    /**
     * 根据日期返回日期所属的“yyyymmddhh”格式小时
     *
     * @param date
     * @return yyyymmddhh
     */
    public static long dateToHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int mon = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hr = cal.get(Calendar.HOUR_OF_DAY);
        return year * 1000000 + mon * 10000 + day * 100 + hr;
    }


    /**
     * 取指定时间所在小时内的起始时间（0分0秒0毫秒）
     *
     * @param date 精确到小时的Date
     * @return
     */
    public static Date parseHourStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //		cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 取指定时间所在小时内的起始时间（0分0秒0毫秒）
     *
     * @param hour 格式yyyymmddhh
     * @return
     */
    public static Date parseHourStart(Long hour) {
        String dateStr = String.valueOf(hour);
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int mon = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));
        int hr = Integer.parseInt(dateStr.substring(8, 10));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mon - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hr);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //		cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 取指定时间所在小时内的结束时间（59分59秒999毫秒）
     *
     * @param date 精确到小时的Date
     * @return
     */
    public static Date parseHourEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        //		cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 取指定小时内的结束时间（0分0秒0毫秒）
     *
     * @param hour 格式yyyymmddhh
     * @return
     */
    public static Date parseHourEnd(Long hour) {
        String dateStr = String.valueOf(hour);
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int mon = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));
        int hr = Integer.parseInt(dateStr.substring(8, 10));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, mon - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hr);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        //		cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 算时间差
     * 某个时间和当前系统时间 间距秒数
     *
     * @param statrDate 开始时间
     * @return 秒数
     */
    public static long getSecond(Date statrDate) {
        long endDate = System.currentTimeMillis();
        return (endDate - statrDate.getTime()) / 1000;
    }


    public static Date getStrToDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = null;
        try {
            d = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }


    /**
     * 几天前的日期
     *
     * @param d
     * @param day 0 为当前时间  正数为之前的时间  负数是之后的时间
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date da = null;
        try {
            da = format.parse(sdf.format(now.getTime()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return da;
    }


    /**
     * 根据给定的日期/时间格式，转换日期
     *
     * @param aDate
     * @param format 日期格式:EEEE是星期, MM月,dd日,yyyy是年(ASCII 字符)
     * @return
     */
    public static String convertDateToString(Date aDate, String format) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (format == null || format.equals("")) {
            format = "yyyy-MM-dd";
        }
        if (aDate == null) {
            System.err.println("日期为空!");
        } else {
            df = new SimpleDateFormat(format);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static String parseString(Date aDate) {
        return convertDateToString(aDate, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按指定格式将日期字串转成 Date类型
     *
     * @param strDate
     * @param format
     * @return
     */
    public static final Date parseDate(String strDate, DateFormat format) {
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return date;
    }

    public static final Date parseDate(String strDate) {
        Date date = null;
        try {
            date = DEFAULT_DATETIME_FORMAT.parse(strDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return date;
    }


    /**
     * 根据开始时间，结束时间，时间段分隔时间(分钟)
     *
     * @param startDate
     * @param endDate
     * @param interval
     * @return
     * @throws Exception
     */
    public static List<Date[]> dateSplit(Date startDate, Date endDate, int interval)
            throws Exception {
        if (startDate.getTime() > endDate.getTime()) {
            throw new Exception("开始时间不能小于结束时间！");
        }
        List<Date[]> returnlist = new ArrayList<Date[]>();

        Date dates = startDate;
        Date datee = null;
        int i = 0;
        while (true) {
            if (i == 0) {
                dates = startDate;
            }
            datee = DateUtils.addMinute(dates, interval);

            if (datee.getTime() >= endDate.getTime()) {
                datee = endDate;
                returnlist.add(new Date[]{dates, datee});
                break;
            }

            returnlist.add(new Date[]{dates, datee});
            dates = datee;
            i++;
        }
        return returnlist;

    }

    public static String format(Date date) {
        return DEFAULT_DATETIME_FORMAT.format(date);
    }

    public static String formatDate(Date date,String format) {
        SimpleDateFormat dataFormat = new SimpleDateFormat(format);
        return dataFormat.format(date);
    }

    /**
     * 取间隔若干月后的日期.
     *
     * @param date  the date
     * @param count the month count
     * @return the Date
     */
    public static Date minuteSub(Date date, int count) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -count);
        return cal.getTime();
    }

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
//        System.out.println(DateUtils.convertDateToString(new Date(), "YYYY-MM-dd"));
        System.out.println(DateUtils.convertDateToString(new Date(), "YYYYMMdd"));
    }


}

