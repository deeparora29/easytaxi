package com.mobilesoft.smarttaxi.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Utility for date convert
 * @author renmian
 *
 */
public class DateUtil {
private static Log log = LogFactory.getLog(DateUtil.class);
    
    private static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    private static String datePattern = "yyyy-MM-dd";
    private static String timePattern = "HH:mm";

    /**
     * Return default datePattern (yyyy-MM-dd)
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return datePattern;
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to yyyy-MM-dd.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static final String getDate(Date aDate) {
        if (aDate == null) {
        	return "";        	
        }
        SimpleDateFormat df = new SimpleDateFormat(datePattern);
        return df.format(aDate);        
    }



    /** 
     * 按aMask的格式定义，把日期格式的字符串strDate转换为Date对象
     * @param aMask
     * @param strDate
     * @return     如果日期格式的字符串strDate为空/null，则返回null
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}
		if (StringUtils.isEmpty(strDate)) {
			//throw new ParseException("待转换的日期格式字符串不能为空！",-999);
			return null;
		} 
		return new SimpleDateFormat(aMask).parse(strDate); 
	}

    /**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
    public static String convertTimeToString(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * This method returns the current date in the format: yyyy-MM-dd
     * 
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(convertStringToDate(todayAsString));

        return calendar;
    }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * 
     * @see java.text.SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(datePattern, aDate);
    }
    
    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(String datePattern, Date aDate) {
        return getDateTime(datePattern, aDate);
    }
    
    /**
     * This method generates a string representation of a date based
     * on the System Property 'dateFormat'
     * in the format you specify on input
     * 
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateTimeToString(Date aDate) {
        return getDateTime(dateTimePattern, aDate);
    }
    
    /**
     * This method converts a String to a date using the datePattern
     * 
     * @param strDate the date to convert (in format yyyy-MM-dd)
     * @return a date object
     * 
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate)
			throws ParseException {
		if (log.isDebugEnabled()) {
			log.debug("converting string ["+strDate+"] to Date with pattern[" + datePattern+"]");
		}

		return convertStringToDate(datePattern, strDate);
	}
    
    /**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format yyyy-MM-dd HH:mm:ss)
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
    public static Date convertStringToDateTime(String strDate) throws ParseException {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + dateTimePattern);
            }

            aDate = convertStringToDate(dateTimePattern, strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate
                      + "' to a date, throwing exception");
            pe.printStackTrace();
            throw new ParseException(pe.getMessage(),
                                     pe.getErrorOffset());
                    
        }
        return aDate;
    }
    
    /**
     * 返回一个指定日期的下一天
     *
     * @param date 初始日期
     * @return 初始日期加一天后的日期
     * @author zhanglelei
     * @date 2007-2-25 22:30:29 
     */
    public static Date getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
    }
    
    /**
     * 取得一个指定日期所在月的第一天
     * 
     * @return
     * @author liuwenu
     */
    public static Date getFirstDateOf(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.DATE, 1);
		calender.set(Calendar.HOUR_OF_DAY, 0);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		return calender.getTime();
    }
    
    /**
     * 获得一个指定日期所在月的下个月的第一天
     * @param date
     * @return
     */
    public static Date getFirstDateInNextMonthOf(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
    }
    /**
     * 取得一个指定日期所在月的最后一天
     * 
     * @return
     */
    public static Date getLastDateOf(Date date) {
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
    }
    
    /**
     * 取得以yyyyMMddHHmmss格式的时间戳字符串
     * 
     * @return
     */
    public static final String getCompactTimeStamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }
    
    /**
     * 取得指定日期所在的月份
     * @param date
     * @return
     */
    public static final int getMonthOf(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
    }
    
    /**
     * 取得指定日期所在的小时
     * @param date
     * @return
     */
    public static final int getHourOf(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR);
    }
    
    /**
     * 比较源日期是否在目标日期之前
     *
     * @param srcDateString 源日期字符串
     * @param targetDateString 目标日期字符串
     * @param pattern 字符串日期格式
     * @return 
     */
    public static final boolean before(String srcDateString,
    		String targetDateString, String pattern) throws Exception{
    	Date srcDate;
    	try {
    		srcDate = DateUtil.convertStringToDate(pattern, srcDateString);
    	} catch (ParseException e) { 
    		throw new Exception("源日期格式转换异常：["+srcDateString+"]",e);
    	}
    	Date targetDate;
    	try {
    		targetDate = DateUtil.convertStringToDate(pattern, targetDateString);
    	} catch (ParseException e) {
    		throw new Exception("目标日期格式转换异常：["+targetDateString+"]",e);
    	}
    	return before(srcDate, targetDate);
    }
    
    /**
     * 比较源日期是否在目标日期之前,日期格式为 yyyy-MM-dd
     * @param srcDateString
     * @param targetDateString
     * @return  
     */
    public static final boolean before(String srcDateString,
			String targetDateString) throws Exception {
    	Date srcDate;
		try {
			srcDate = DateUtil.convertStringToDate(srcDateString);
		} catch (ParseException e) { 
			throw new Exception("源日期格式转换异常：["+srcDateString+"]",e);
		}
    	Date targetDate;
		try {
			targetDate = DateUtil.convertStringToDate(targetDateString);
		} catch (ParseException e) {
			throw new Exception("目标日期格式转换异常：["+targetDateString+"]",e);
		}
		return before(srcDate, targetDate);
	}
    
    /**
     * 比较源日期是否在目标日期之前,日期格式为 yyyy-MM-dd
     * @param srcDateString
     * @param targetDate
     * @return
     */
    public static final boolean before(String srcDateString,Date targetDate) throws Exception{ 
		try {
			Date srcDate = DateUtil.convertStringToDate(srcDateString);
			return before(srcDate, targetDate);
		} catch (ParseException e) { 
			throw new Exception("源日期格式转换异常：["+srcDateString+"]",e);
		}
    }
    /**
     * 比较源日期是否在目标日期之后,日期格式为 yyyy-MM-dd
     * @param srcDateString
     * @param targetDate
     * @return
     */
    public static final boolean after(String srcDateString,Date targetDate) throws Exception{ 
		try {
			Date srcDate = DateUtil.convertStringToDate(srcDateString);
			return after(srcDate, targetDate);
		} catch (ParseException e) { 
			throw new Exception("源日期格式转换异常：["+srcDateString+"]",e);
		}
    }
    /**
     * 比较源日期是否在当前日期之前,日期格式为 yyyy-MM-dd
     * @param srcDateString
     * @return
     */
    public static final boolean beforeCurrentDate(String srcDateString) throws Exception{
    	return before(srcDateString, new Date());	
    }
    /**
     * 比较源日期是否在当前日期之后,日期格式为 yyyy-MM-dd
     * @param srcDateString
     * @return
     */
    public static final boolean afterCurrentDate(String srcDateString) throws Exception{
    	return after(srcDateString, new Date());	
    }
    /**
     * 比较源日期是否在目标日期之前,日期格式为 yyyy-MM-dd
     * @param srcDate
     * @param targetDate
     * @return
     */
    public static final boolean before(Date srcDate,Date targetDate) {
    	return srcDate.before(targetDate);
    }
    /**
     * 比较源日期是否在目标日期之后,日期格式为 yyyy-MM-dd
     * @param srcDate
     * @param targetDate
     * @return
     */
    public static final boolean after(Date srcDate,Date targetDate) {
    	return srcDate.after(targetDate);
    }
    
    /**
     * 比较源日期是否在目标日期之后,日期格式为 yyyy-MM-dd
     * @param srcDateString
     * @param targetDateString
     * @return
     */
    public static final boolean after(String srcDateString,
    		String targetDateString) throws Exception {
    	return before(targetDateString, srcDateString);
    }

    /**
     * 比较源日期是否在目标日期之后
     *
     * @param srcDateString 源日期字符串
     * @param targetDateString 目标日期字符串
     * @param pattern 字符串日期格式
     * @return
     * @date 2007-2-25 18:48:27 
     */
    public static final boolean after(String srcDateString,
			String targetDateString, String pattern) throws Exception {
    	return before(targetDateString, srcDateString, pattern);
    }
    /**
     * m为正数表示后几个月，m为负数表示前几个月
     * @param date
     * @param m
     * @return
     */
    public static String getStringDateAddMoth_YYYYMM(Date date, int m){
		String dateString = "";
		try {
			GregorianCalendar gc =new GregorianCalendar();
			gc.setTime(date);		
			gc.add(2,m);
			gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));				
			dateString = convertDateToString("yyyyMM", gc.getTime());	
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.toString());
		}
		return dateString; 
	}
    
    public static void main(String[] args){
    	System.out.println(getStringDateAddMoth_YYYYMM(new Date(), 0));
    }
}
