package com.aaron.util.date;

import java.text.SimpleDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * @author FengHaixin
 * @description 一句话描述该文件的用途
 * @date 2016-05-23
 */
public final class DateUtils
{
    private static final Log LOG_RECORD = LogFactory.getLog(DateUtils.class);

    private static final String DEFAULT_FORMATTER = "yyyy-MM-dd HH:mm:ss";


    private DateUtils()
    {

    }


    /**
     * 日期转换为字符串
     *
     * @param date Date:时间
     * @return 字符串类型的时间
     */
    public static String dateToString(Date date)
    {
        SimpleDateFormat format;
        String dateString = null;
        try
        {
            format = new SimpleDateFormat(DEFAULT_FORMATTER);
            dateString = format.format(date);
        }
        catch (Exception e)
        {
            LOG_RECORD.error("date convert to string error,", e);
        }

        return dateString;
    }


    /**
     * 日期转换为字符串
     *
     * @param date Date：时间
     * @param formatter String：时间格式
     * @return String：字符串类型的时间
     */
    public static String dateToString(Date date, String formatter)
    {
        String dateString = null;
        try
        {
            SimpleDateFormat format = new SimpleDateFormat(formatter);

            dateString = format.format(date);
        }
        catch (Exception e)
        {
            LOG_RECORD.error("date convert to string error,", e);
        }

        return dateString;
    }


    /**
     * 字符串转换为日期
     *
     * @param dateString String：字符串类型的时间
     * @return Date：日期
     */
    public static Date stringToDate(String dateString)
    {
        Date date = null;
        SimpleDateFormat format = null;
        try
        {
            format = new SimpleDateFormat(DEFAULT_FORMATTER);
            date = format.parse(dateString);
        }
        catch (Exception e)
        {
            LOG_RECORD.error("string convert date error,", e);
        }
        return date;
    }


    /**
     * 字符串转换为日期
     *
     * @param dateString String：字符串类型的时间
     * @param formatter String：时间格式
     * @return Date：日期
     */
    public static Date stringToDate(String dateString, String formatter)
    {
        Date date = null;
        SimpleDateFormat format = null;
        try
        {
            format = new SimpleDateFormat(formatter);
            date = format.parse(dateString);
        }
        catch (Exception e)
        {
            LOG_RECORD.error("string convert date error,", e);
        }
        return date;
    }


    /**
     * 获取基于当前日期的一个相对日期
     *
     * @param interval
     * @param amount
     * @return
     */
    public static Date getDateBeforeCurrent(int interval, int amount)
    {

        Calendar calendar = Calendar.getInstance();

        calendar.add(interval, amount);

        return calendar.getTime();
    }

}
