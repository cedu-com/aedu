package net.chinaedu.aedu.utils;

import android.widget.TextView;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AeduDateUtils {

    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static String YMDHMS_12HOUR_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static String YMDHMS_24HOUR_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss ";
    public static String YMDHM_24HOUR_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static String HMS_24HOUR_DATE_FORMAT = "HH:mm:ss";
    public static String CHINESE_SHORT = "yyyy年MM月dd日 HH:mm";
    public static String CHINESE_YYMMDD = "yyyy年MM月dd日";
    public static String HOURS_MINUTE = "HH:mm";
    public static String CHINESE_YY_MM = "yyyy年MM月";

    public static String YMDHMS_24HOUR_DATE_FORMAT_S = "yyyy-MM-dd HH:mm:ss SSS";
    public static SimpleDateFormat CHINESE_SHORT_FORMAT = new SimpleDateFormat(CHINESE_SHORT, Locale.CHINESE);
    public static SimpleDateFormat HOURS_MINUTE_FORMAT = new SimpleDateFormat(HOURS_MINUTE, Locale.CHINESE);

    public static Date getNow() {
        Calendar calendar = Calendar.getInstance();
        Calendar current = new GregorianCalendar(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return current.getTime();
    }

    /**
     * 字符串转换到时间格式
     *
     * @param strDate   需要转换的字符串
     * @param strFormat 需要格式的目标字符串,  例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date String2Date(String strDate, String strFormat) {
        if (strDate == null || strDate.isEmpty()) {
            return null;
        }
        if (strFormat == null) {
            strFormat = DEFAULT_DATE_FORMAT;
        }

        SimpleDateFormat df = new SimpleDateFormat(strFormat);

        Date dtDate = null;
        try {
            dtDate = df.parse(strDate);
        } catch (ParseException pe) {
            //dtDate = null;
        }
        return dtDate;
    }

    public static String formatDate(int year, int month, int day, String strFormat) {
        if (strFormat == null || strFormat.isEmpty()) {
            strFormat = DEFAULT_DATE_FORMAT;
        }

        SimpleDateFormat df = new SimpleDateFormat(strFormat);
        String strDate = "";
        try {
            Calendar cl = Calendar.getInstance();
            cl.set(year, month, day);
            strDate = df.format(cl.getTime());
        } catch (Exception pe) {

        }
        return strDate;
    }

    public static String formatDate(Date date, String strFormat) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        return sdf.format(date);
    }


    public static String date2String(String dateFormat, Date date) {
        if (null == date) {
            return "";
        }
        if (dateFormat == null) {
            dateFormat = DEFAULT_DATE_FORMAT;
        }
        SimpleDateFormat df = new SimpleDateFormat(dateFormat, Locale.CHINESE);
        return df.format(date);
    }

    public static String getNowDateTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(YMDHMS_24HOUR_DATE_FORMAT, Locale.CHINESE);
        return df.format(new Date());
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(YMDHMS_24HOUR_DATE_FORMAT);
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(YMDHMS_24HOUR_DATE_FORMAT);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分:秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat(HMS_24HOUR_DATE_FORMAT);
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 判断是否润年
     *
     * @param date
     * @return
     */
    public static boolean isLeapYear(String date) {

        /**
         * 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 4.能被4整除同时能被100整除则不是闰年
         */
        Date d = String2Date(date, DEFAULT_DATE_FORMAT);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0) {
            return true;
        } else if ((year % 4) == 0) {
            if ((year % 100) == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(Date date1, Date date2) {
        return (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param strDate1
     * @param strDate2
     * @return
     */
    public static long getDays(String strDate1, String strDate2) {
        if (AeduStringUtil.isEmpty(strDate1)) {
            return 0;
        }
        if (AeduStringUtil.isEmpty(strDate2)) {
            return 0;
        }
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = myFormatter.parse(strDate1);
            date2 = myFormatter.parse(strDate2);
        } catch (Exception e) {
        }
        return (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
    }

    public static String getYMDHMForDayOfWeek(Date date) {
        String[] days = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        String dataStr = formatDate(date, YMDHM_24HOUR_DATE_FORMAT);
        StringBuilder stringBuilder = new StringBuilder();
        int index = dataStr.indexOf(" ");
        stringBuilder.append(dataStr.substring(0, index + 1));
        try {
            stringBuilder.append(days[dayForWeek(dataStr) - 1]);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        stringBuilder.append(dataStr.substring(index));
        return stringBuilder.toString();
    }

    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        Calendar c = Calendar.getInstance();

        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 判断两个日期是否相等
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 相等时返回 true
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return !(null == date1) && !(null == date2)
                && 0 == resetTime(date1).compareTo(resetTime(date2));
    }

    /**
     * 将指定日期的小时、分钟、秒清零
     *
     * @param date 指定的日期
     * @return 小时、分钟、秒被清零的日期
     */
    public static Date resetTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String findChineseTimeRange(Date startDate, Date endDate) {
        if (null == startDate || null == endDate) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(CHINESE_SHORT_FORMAT.format(startDate)).append("~").append(HOURS_MINUTE_FORMAT.format(endDate));
        return buffer.toString();
    }

    /**
     * 得到calendar的日期：xxxx-xx-xx
     */
    public static String getTagTimeStr(Calendar calendar) {
        String ss = "";
        if (calendar != null) {
            ss = AeduDateUtils.longToStr(calendar.getTimeInMillis(), AeduDateUtils.DEFAULT_DATE_FORMAT);
        }
        return ss;
    }

    public static String longToStr(long time, String format) {
        SimpleDateFormat dataFormat = new SimpleDateFormat(
                format);
        Date date = new Date();
        date.setTime(time);
        return dataFormat.format(date);
    }

    public static int monthSpace(Date startDate, Date endDate) {
        if (null == startDate || null == endDate || startDate.after(endDate)) {
            return 0;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(startDate);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(endDate);

        int result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        if (result < 0) {
            result = c2.get(Calendar.MONTH) + 12 - c1.get(Calendar.MONTH);
            c2.set(Calendar.YEAR, c2.get(Calendar.YEAR) - 1);
        }
        result += Math.abs((c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12) + 1;
        return result;
    }

    public static int weekSpace(Date startDate, Date endDate) {
        if (null == startDate || null == endDate || startDate.after(endDate)) {
            return 0;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        int startDayOfWeek = start.get(Calendar.DAY_OF_WEEK);
        if (startDayOfWeek > 0) {
            start.add(Calendar.DATE, -startDayOfWeek);
        }
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int endDayOfWeek = start.get(Calendar.DAY_OF_WEEK);
        if (endDayOfWeek > 0) {
            end.add(Calendar.DATE, 7 - endDayOfWeek);
        }
        long between_days = (end.getTimeInMillis() - start.getTimeInMillis()) / (1000 * 3600 * 24 * 7) + 1;
        return Integer.parseInt(String.valueOf(between_days));
    }


    public static void changeTimePoint(long timeDifference, TextView replyTimeTextView, Date timeStamp) {
        if (timeDifference < 60 * 1000) {//小于1分钟
            replyTimeTextView.setText("刚刚");
        } else if (timeDifference >= 60 * 1000 && timeDifference < 60 * 60 * 1000) {//大于1分钟小于一小时
            replyTimeTextView.setText(String.valueOf(timeDifference / (60 * 1000)) + "分钟前");
        } else if (timeDifference >= 60 * 60 * 1000 && timeDifference < 24 * 60 * 60 * 1000) {//大于一小时小于一天
            replyTimeTextView.setText(String.valueOf(timeDifference / (60 * 60 * 1000)) + "小时前");
        } else if (timeDifference >= 24 * 60 * 60 * 1000 && (timeDifference / (24 * 60 * 60 * 1000)) < 30) {//大于一天小于一个月
            replyTimeTextView.setText(String.valueOf(timeDifference / (24 * 60 * 60 * 1000)) + "天前");
        } else {//大于一个月
            String createTime2 = AeduDateUtils.formatDate(timeStamp, AeduDateUtils.CHINESE_YYMMDD);
            replyTimeTextView.setText(createTime2);
        }
    }

}
