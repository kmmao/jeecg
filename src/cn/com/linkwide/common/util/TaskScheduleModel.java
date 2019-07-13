package cn.com.linkwide.common.util;

import java.util.Date;
/**
 * 获取cron表达式所需实体参数
 * @author liuxueting
 *
 */
public class TaskScheduleModel {

	private Integer jobType; //执行周期    1 每天；2 每月 ； 3 每周 ；4按间隔时间段
	private Integer hour; //时
	private Integer minute; //分
	private Integer second; //秒
	private Integer [] dayOfWeeks; //每周的哪几天
	private Integer intervalWeek; //每隔多少周  
	private Integer [] dayOfMonths; // 每月的哪几天
	private Date scheduleStartTime; //计划执行开始时间
	private Integer intervalCountOfSecond; // 间隔多少秒
	private Integer intervalCountOfMinute; //间隔多少分钟
	private Integer intervalCountOfHour; //间隔多少小时
	private Integer intervalCountOfDay; //间隔多少天
	private Integer intervalCountOfMonth; //间隔多少月
	
	public Integer getJobType() {
		return jobType;
	}
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	public Integer getSecond() {
		return second;
	}
	public void setSecond(Integer second) {
		this.second = second;
	}
	public Integer[] getDayOfWeeks() {
		return dayOfWeeks;
	}
	public void setDayOfWeeks(Integer[] dayOfWeeks) {
		this.dayOfWeeks = dayOfWeeks;
	}
	public Integer[] getDayOfMonths() {
		return dayOfMonths;
	}
	public void setDayOfMonths(Integer[] dayOfMonths) {
		this.dayOfMonths = dayOfMonths;
	}
	public Integer getIntervalWeek() {
		return intervalWeek;
	}
	public void setIntervalWeek(Integer intervalWeek) {
		this.intervalWeek = intervalWeek;
	}
	
	public Integer getIntervalCountOfSecond() {
		return intervalCountOfSecond;
	}
	public void setIntervalCountOfSecond(Integer intervalCountOfSecond) {
		this.intervalCountOfSecond = intervalCountOfSecond;
	}
	public Integer getIntervalCountOfMinute() {
		return intervalCountOfMinute;
	}
	public void setIntervalCountOfMinute(Integer intervalCountOfMinute) {
		this.intervalCountOfMinute = intervalCountOfMinute;
	}
	public Date getScheduleStartTime() {
		return scheduleStartTime;
	}
	public void setScheduleStartTime(Date scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}
	public Integer getIntervalCountOfHour() {
		return intervalCountOfHour;
	}
	public void setIntervalCountOfHour(Integer intervalCountOfHour) {
		this.intervalCountOfHour = intervalCountOfHour;
	}
	public Integer getIntervalCountOfDay() {
		return intervalCountOfDay;
	}
	public void setIntervalCountOfDay(Integer intervalCountOfDay) {
		this.intervalCountOfDay = intervalCountOfDay;
	}
	public Integer getIntervalCountOfMonth() {
		return intervalCountOfMonth;
	}
	public void setIntervalCountOfMonth(Integer intervalCountOfMonth) {
		this.intervalCountOfMonth = intervalCountOfMonth;
	}
	
	
	
}
