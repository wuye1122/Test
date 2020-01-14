package com.channel.omp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.StringUtils;


public class DateUtils
{
 //广州联通手动采集专用
  //private static Log logger = LogFactory.getLog(DateUtils.class);
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

  public static int daysBetween(String startTime, String endTime)
  {
    try
    {
      Calendar c = Calendar.getInstance();
      Date sdate = dateFormat.parse(startTime);
      c.setTime(sdate);
      long beginMillis = c.getTimeInMillis();
      Date edate = dateFormat.parse(endTime);
      c.setTime(edate);
      long endMillis = c.getTimeInMillis();
      long between_days = (endMillis - beginMillis) / 86400000L;
      return Integer.parseInt(String.valueOf(between_days));
    } catch (ParseException e) {
     // logger.error("日期转换异常", e);
      e.printStackTrace();
    }
    return 0;
  }

  public static String getNextDateString(String dateStr)
    throws ParseException
  {
    if (StringUtils.isBlank(dateStr)) {
   //   logger.warn("dateStr is null.");
      return null;
    }
    Date date = format.parse(dateStr);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(5, 1);
    return format.format(calendar.getTime());
  }

  public static Date getDate(String dateStr)
    throws ParseException
  {
    if (StringUtils.isBlank(dateStr)) {
     // logger.warn("dateStr is null.");
      return null;
    }
    return dateFormat.parse(dateStr);
  }
}