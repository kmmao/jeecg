package cn.com.linkwide.common.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author bianly
 * @date 2017-09-07
 * 日期处理工具类
 * */
public class DateHelperUtil extends org.apache.commons.lang.time.DateUtils {
	
	 
    /**
     * 默认的日期格式化样式
     */
    public static final String DAY_PATTERN = "yyyy-MM-dd";
    /**
     * 默认的时间格式化样式
     */
    public static final String TIME_PATTERN = "HH:mm:ss";
	
	/**
	 * 获取前一天日期yyyyMMdd
	 * @see 测试时，将其放到<code>calendar.add(Calendar.DATE, -1);</code>前面即可
	 * @return 返回的日期格式为yyyyMMdd
	 */
	public static String getYestoday(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
	}
	/**
	 * 获取当前的日期yyyyMMdd
	 */
	public static String getCurrentDate(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	
	/**
	 * 获取当前的时间yyyyMMddHHmmss
	 */
	public static String getCurrentTime(){
		return new SimpleDateFormat("yyyy MM-dd HH:mm:ss").format(new Date());
	}
	
	/** 

     * 将日期格式转换成yyyy-MM-dd的字符串格式 

     * 返回值如：2017-09-07 

     * @param time 要转换的日期 
     */  

    public static  String dateToString(Date time)  {  

          

        SimpleDateFormat formatter = new  SimpleDateFormat ("yyyy-MM-dd"); //定义将日期格式要换成的格式   

        String stringTime  =  formatter.format(time);  
        return  stringTime;  
      }  
    
    /**
     * 将Date格式化成符合默认格式的字符串
     * 
     * @param date
     * @return 返回样例:2012-03-29 14:32:23
     */
    public static String format(Date date) {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(DAY_PATTERN + " " + TIME_PATTERN);
        return formatTool.format(date);
    }
    
    /**
     * 将Date格式化成符合默认日期格式的字符串
     * 
     * @param date
     * @return 返回样例:2012-03-29
     */
    public static String formatDate(Date date) {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(DAY_PATTERN);
        return formatTool.format(date);
    }
    
    /**
     * 将Date格式化成符合默认时间格式的字符串
     * 
     * @param date
     * @return 返回样例:14:32:23
     */
    public static String formatTime(Date date) {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(TIME_PATTERN);
        return formatTool.format(date);
    }
    
    /**
     * 按照pattern格式格式化Date
     * 
     * @param date
     * @param pattern
     *            样例: yyyy/MM/dd
     * @return 返回样例:2012/03/29
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(pattern);
        return formatTool.format(date);
    }
    
    /**
     * 将符合默认格式的字符串转换成Date
     * 
     * @param dateValue
     *            样例:2012-03-29 14:32:23
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateValue) throws ParseException {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(DAY_PATTERN + " " + TIME_PATTERN);
        return formatTool.parse(dateValue);
    }
    /**
     * 将符合默认格式的日期字符串转换成Date
     * 
     * @param dateValue
     *            样例:2012-03-29
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateValue) throws ParseException {
        SimpleDateFormat formatTool = new SimpleDateFormat();
        formatTool.applyPattern(DAY_PATTERN);
        return formatTool.parse(dateValue);
    }
   
    /**
     * 得到指定月的天数
     * 
     * @param year
     * @param month
     * @return
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
 
    /**
     * 返回指定时间加上num天后的时间
     * 
     * @param date
     * @param num
     * @return
     */
    public static Date add(Date date, int num) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.add(Calendar.DAY_OF_MONTH, num);
        return a.getTime();
    }
 
    /**
     * 返回false：date=null，date是1970年；其它都返回true
     * 
     * @param date
     * @return
     */
    public static boolean isNotEmpty(Date date) {
        if (date != null) {
            Calendar a = Calendar.getInstance();
            a.setTime(date);
            return a.get(Calendar.YEAR) != 1970;
        }
        return Boolean.FALSE;
    }
 
    /**
     * 获得date的小时数0-23
     * 
     * @param date
     * @return
     */
    public static int getHours(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        return a.get(Calendar.HOUR_OF_DAY);
    }
 
    /**
     * 获得date是一周的第几天，注意：周日 返回 1，周六 返回 7
     * 
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        return a.get(Calendar.DAY_OF_WEEK);
    }
 

 
   
 

 
  
 
}
