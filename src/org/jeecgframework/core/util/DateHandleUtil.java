package org.jeecgframework.core.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 日期的工具类
 * 说明：每周开始日期为 周一
 * @author zhanglj
 *
 */
public  class DateHandleUtil {
	
	/**
	 * 返回指定格式的当前日期
	 * @param formate
	 * @return
	 */
	public static String currentDateTime(){
		return formateDate("yyyy-MM-dd HH:mm:ss", new Date());
	}
	/**
	 * 返回指定格式的当前日期 
	 * @param formate
	 * @return
	 */
	public static String currentDate(){
		return formateDate("yyyy-MM-dd", new Date());
	}
	/**
	 * 返回指定格式的当前日期 
	 * @param formate
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDate(String date_str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date_str);
	}
	
	/**
	 * 返回指定格式的当前日期 
	 * @param formate
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDateTime(String date_str) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(date_str);
	}
	/**
	 * 返回指定格式的当前日期
	 * @param formate
	 * @return
	 */
	public static String currentDate(String formate){
		return formateDate(formate, new Date());
	}
	/**
	 * 返回当前日期所在周的周一与周日日期
	 * @param formate
	 * @return
	 * @throws ParseException 
	 */
	public static Date[] currentDayOfWeek() throws ParseException{
		return dayOfWeek(new Date());
	}
	/**
	 * 返回当前日期所在年的周次
	 * @return
	 */
	public static Integer currentWeekOfYear(){
		return weekOfYear(new Date());
	}
	/**
	 * 返回制定格式的日期
	 * @param formate yyyy-MM-dd HH:mm:ss yyyy-MM-dd HH:mm yyyy/MM/dd HH:mm:ss 等等
	 * @param date
	 * @return
	 */
	public static String formateDate(String formate,Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		return sdf.format(date);
	}
	
	/**
	 * 获取指定日期当前所在周的周一与周日；
	 * @param date
	 * @param formate
	 * @return 0：周一日期 1：周日日期
	 * @throws ParseException 
	 */
	public static Date[] dayOfWeek(Date date) throws ParseException{
		Date[] weeks = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);	
		//日期为本周的周几
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		//获取本周第一天
		calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek()-dayOfWeek);		
		Date week_begin = calendar.getTime();		
		//第一天+6 本周最后一天
		calendar.add(Calendar.DATE, 6);
		Date week_end = calendar.getTime();	
		weeks[0]=getDateTime(formateDate("yyyy-MM-dd", week_begin)+" 00:00:00");
		weeks[1]=getDateTime(formateDate("yyyy-MM-dd", week_end)+" 23:59:59");
		return weeks;
	}
	/**
	 * 获取指定日期所在年的第几周
	 * @return
	 */
	public static Integer weekOfYear(Date date){
		//期数，以周一为本周第一天
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		Integer weeks = calendar.get(Calendar.WEEK_OF_YEAR);
		return weeks;
	}
	/**
	 * 获取日期后几天的日期-String
	 * @return
	 */
	public static String getSomeDaysAfterday(Date day,int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(Calendar.DATE, days);
		return formateDate("yyyy-MM-dd",calendar.getTime());
	}
	
	/**
	 * 获取日期后几天的日期-String
	 * @return
	 */
	public static Date getDateAfterday(Date day,int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	/*
	 * 获取日期后几天的日期-String
	 */
	public static String getSomeDaysAfterdayTime(Date day,int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(Calendar.DATE, days);
		return formateDate("yyyy-MM-dd HH:mm:ss",calendar.getTime());
	}
	/**
	 * 获取当前月第一天-Date
	 * @return
	 */
	public static  Date getNowMonthFirstDay(Date day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前月最后一天-Date
	 * @return
	 */
	public static  Date getNowMonthLastDay(Date day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	/**
	 * 获取当前月最后一天-Date
	 * @return
	 */
	public static  Date getDaysAfterday(Date day,int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);		
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	public static  String encodeStr(String str){
		try {
			return new String (str.getBytes("ISO-8859-1"),"UTF-8");
		}
		catch(Exception exception){
			return null;
		}
	}
	/**
	 * 获取日期所在月的最大日期
	 * @param date
	 * @return
	 */
	public static int getDaysNumOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);		
		return calendar.getActualMaximum(Calendar.DATE);
	}
	
	
	/**
	 * 获取日期日期期间内的日期数组-String
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static String[] getDaysOfPeriod(String startTime,String endTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar tempStart = Calendar.getInstance();
 		Calendar tempEnd = Calendar.getInstance();
 		tempStart.setTime(sdf.parse(startTime));		
 		tempEnd.setTime(sdf.parse(endTime));
		List<String> result = new ArrayList<String>();
		while(tempStart.before(tempEnd)||tempStart.equals(tempEnd)){
			result.add(formateDate("yyyy-MM-dd", tempStart.getTime()));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}			
		return result.toArray(new String[0]);
	}

	/**
	 * 获取日期日期期间内的日期数组
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date[] getDatesOfPeriod(Date startTime,Date endTime) throws ParseException{
		Calendar tempStart = Calendar.getInstance();
 		Calendar tempEnd = Calendar.getInstance();
 		tempStart.setTime(startTime);		
 		tempEnd.setTime(endTime);
		List<Date> result = new ArrayList<Date>();
		while(tempStart.before(tempEnd)||tempStart.equals(tempEnd)){
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}			
		return result.toArray(new Date[0]);
	}
	/**
	 * 返回日期是周几-String
	 */
	public static String getWeek(String DateTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf_week = new SimpleDateFormat("EEEE");
		return sdf_week.format(sdf.parse(DateTime));
	}
	/**
	 * 返回日期是周几
	 */
	public static String getWeek(Date DateTime) throws ParseException{
		SimpleDateFormat sdf_week = new SimpleDateFormat("EEEE");
		return sdf_week.format(DateTime);
	}
	/**
	 * 返回当前日期后四周的 区间数组
	 * [2017-03-20~2017-03-26,2017-03-27~2017-04-02,2017-04-03~2017-04-09,2017-04-10~2017-03-16]
	 * @return
	 * @throws ParseException 
	 */
	public static String[] getWeekIn30Day() throws ParseException{
		String[] result = new String[4];
		Date[] firstDate = dayOfWeek(new Date());
		result[0]=formateDate("yyyy-MM-dd", firstDate[0])+"~"+formateDate("yyyy-MM-dd", firstDate[1]);
		Date[] secondDate = dayOfWeek(getDaysAfterday(firstDate[1], 1));
		result[1]=formateDate("yyyy-MM-dd", secondDate[0])+"~"+formateDate("yyyy-MM-dd", secondDate[1]);
		Date[] thirdDate = dayOfWeek(getDaysAfterday(secondDate[1], 1));
		result[2]=formateDate("yyyy-MM-dd", thirdDate[0])+"~"+formateDate("yyyy-MM-dd", thirdDate[1]);
		Date[] fourthDate = dayOfWeek(getDaysAfterday(thirdDate[1], 1));
		result[3]=formateDate("yyyy-MM-dd", fourthDate[0])+"~"+formateDate("yyyy-MM-dd", fourthDate[1]);		
		return result;
	}
	
	/**
	 * 返回当前年，前后两年的数组
	 * [2015,2016,2017,2018,2019]
	 * @return
	 */
	public static String[] getYears(){
		String[] result = new String[5];
		String currentDate = currentDate("yyyy-MM-dd");
		int years = Integer.parseInt(currentDate.substring(0,4));	
		result[0] = (years-2)+"";
		result[1] = (years-1)+"";
		result[2] = (years)+"";
		result[3] = (years+1)+"";
		result[4] = (years+2)+"";
		return result;
	}
	/**
	 * 获取本月最后一天
	 * @return
	 */
	public  static Date getMonthEndDate(){
		Calendar temp = Calendar.getInstance();
		temp.set(Calendar.DAY_OF_MONTH, temp.getActualMaximum(Calendar.DAY_OF_MONTH));
		return temp.getTime();
	}
	
	/**
	 * 返回指定格式的当前日期
	 * @param formate
	 * @return
	 */
	public static String TimeDifference(Date startTime,Date endTime){
		long dayDiff = 1000*24*60*60;//天的毫秒数		
		long diff = (endTime.getTime()-startTime.getTime())/dayDiff;
		DecimalFormat df = new DecimalFormat("#.0");
		if(diff<1)
			diff=1;
		return df.format(diff);
	}
	/**
	 * 返回指定格式的当前日期
	 * @param formate
	 * @return
	 */
	public static int TimeDifferenceInt(Date startTime,Date endTime){
		long dayDiff = 1000*24*60*60;//天的毫秒数		
		Long diff = (endTime.getTime()-startTime.getTime())/dayDiff;
		return diff.intValue() ;
	}
	
	/**
	 * 获取指定日期的秒
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.SECOND);
	}
	/**
	 * 获取指定日期的分钟
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MINUTE);
	}
	/**
	 * 获取指定日期的小时
	 * @param date
	 * @return
	 */
	public static int getHour(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR);
	}
	/**
	 * 获取指定日期的日
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DATE);
	}
	
	/**
	 * 获取指定日期的月
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
	
	/**
	 * 获取指定日期的年
	 * @param date
	 * @return
	 */
	public static int getYear(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
}
