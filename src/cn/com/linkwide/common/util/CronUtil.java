package cn.com.linkwide.common.util;

import java.util.Date;

import org.jeecgframework.core.util.DateHandleUtil;

/**
 * Cron表达式工具类
 * @author liuxueting
 *
 */
public class CronUtil {

	 /** 
     *  
     *方法摘要：构建Cron表达式 
     *@param  taskScheduleModel 
     *@return String 
     */  
    public static String createCronExpression(TaskScheduleModel taskScheduleModel){  
        StringBuffer cronExp = new StringBuffer("");  
        
        if(null == taskScheduleModel.getJobType()) {
        	System.out.println("执行周期未配置" );//执行周期未配置
        }
        //计划开始执行时间
        Date startTime = taskScheduleModel.getScheduleStartTime();
		if (startTime!=null) {  
            //秒  
            cronExp.append(DateHandleUtil.getSecond(startTime)).append(" ");  
            //分  
            cronExp.append(DateHandleUtil.getMinute(startTime)).append(" ");  
            //小时  
            cronExp.append(DateHandleUtil.getHour(startTime)).append(" ");  
              
            //按每天  
            if(taskScheduleModel.getJobType().intValue() == 1){  
            	cronExp.append("* ");//日
            	cronExp.append("* ");//月
            	cronExp.append("?");//周
            }
            //按每周  
            else if(taskScheduleModel.getJobType().intValue() == 3){  
                //一个月中第几天  
                cronExp.append("? ");  
                //月份  
                cronExp.append("* ");  
                //周  
                Integer[] weeks = taskScheduleModel.getDayOfWeeks();  
                for(int i = 0; i < weeks.length; i++){  
                    if(i == 0){  
                        cronExp.append(weeks[i]);  
                    } else{  
                        cronExp.append(",").append(weeks[i]);  
                    }  
                }
                //每隔多少周  
                if(taskScheduleModel.getIntervalWeek().intValue() > 0){  
                    cronExp.append("/").append(taskScheduleModel.getIntervalWeek());  
                } 
                  
            }  
            //按每月  
            else if(taskScheduleModel.getJobType().intValue() == 2){  
            	//一个月中的哪几天  
            	Integer[] days = taskScheduleModel.getDayOfMonths();  
            	for(int i = 0; i < days.length; i++){  
            		if(i == 0){  
            			cronExp.append(days[i]);  
            		} else{  
            			cronExp.append(",").append(days[i]);  
            		}  
            	}  
                //月份  
                cronExp.append(" * ");  
                //周  
                cronExp.append("?");  
            } 
            //按间隔时间段
            else if(taskScheduleModel.getJobType().intValue() == 4){ 
            	//秒
            	if(taskScheduleModel.getIntervalCountOfSecond()==null||taskScheduleModel.getIntervalCountOfSecond().intValue()==0){
            		cronExp.append(DateHandleUtil.getSecond(startTime)).append(" "); 
            	}else{
            		cronExp.append(DateHandleUtil.getSecond(startTime)).append("/");  
                    cronExp.append(taskScheduleModel.getIntervalCountOfSecond()).append(" "); 
            	}
            	//分
            	if(taskScheduleModel.getIntervalCountOfMinute()==null ||taskScheduleModel.getIntervalCountOfMinute().intValue()==0){
            		cronExp.append(DateHandleUtil.getMinute(startTime)).append(" ");  
            	}else{
            		cronExp.append(DateHandleUtil.getMinute(startTime)).append("/");  
                    cronExp.append(taskScheduleModel.getIntervalCountOfMinute()).append(" ");
            	}
            	//小时
            	if(taskScheduleModel.getIntervalCountOfHour()==null || taskScheduleModel.getIntervalCountOfHour().intValue()==0){
            		cronExp.append(DateHandleUtil.getHour(startTime)).append(" ");  
            	}else{
            		cronExp.append(DateHandleUtil.getHour(startTime)).append("/");  
                    cronExp.append(taskScheduleModel.getIntervalCountOfHour()).append(" ");  
            	}
            	
            	//日
            	if(taskScheduleModel.getIntervalCountOfDay()==null || taskScheduleModel.getIntervalCountOfDay().intValue()==0){
            		cronExp.append(DateHandleUtil.getDayOfMonth(startTime)).append(" ");  
            	}else{
            		cronExp.append(DateHandleUtil.getDayOfMonth(startTime)).append("/");  
                    cronExp.append(taskScheduleModel.getIntervalCountOfDay()).append(" ");  
            	}
            	//月
            	if(taskScheduleModel.getIntervalCountOfMonth()==null||taskScheduleModel.getIntervalCountOfMonth().intValue()==0){
            		cronExp.append(DateHandleUtil.getMonth(startTime)).append(" ");  
            	}else{
            		cronExp.append(DateHandleUtil.getMonth(startTime)).append("/");  
                    cronExp.append(taskScheduleModel.getIntervalCountOfMonth());  
            	}
            }
        } else {
			System.out.println("未设置开始执行时间" );
		}
        return cronExp.toString();  
    }  

    /** 
     *  
     *方法摘要：生成计划的详细描述 
     *@param  taskScheduleModel 
     *@return String 
     */  
    public static String createDescription(TaskScheduleModel taskScheduleModel){  
        StringBuffer description = new StringBuffer("");  
        //计划执行开始时间  
      Date startTime = taskScheduleModel.getScheduleStartTime();  
          
        if (startTime!=null) { 
            //按每天  
            if(taskScheduleModel.getJobType().intValue() == 1){  
            	description.append("每天");  
            	description.append(taskScheduleModel.getHour()).append("时");  
            	description.append(taskScheduleModel.getMinute()).append("分");  
            	description.append(taskScheduleModel.getSecond()).append("秒");  
            	description.append("执行");  
            }  
              
            //按每周  
            else if(taskScheduleModel.getJobType().intValue() == 3){  
                if(taskScheduleModel.getDayOfWeeks() != null && taskScheduleModel.getDayOfWeeks().length > 0) {  
                	String days = "";
                	for(int i : taskScheduleModel.getDayOfWeeks()) {
                		days += "周" + i;
                	}
                    description.append("每周的").append(days).append(" ");  
                }  
                if (null != taskScheduleModel.getSecond()
        				&& null != taskScheduleModel.getMinute()
        				&& null != taskScheduleModel.getHour()) {   
                    description.append(",");   
                    description.append(taskScheduleModel.getHour()).append("时");  
                	description.append(taskScheduleModel.getMinute()).append("分");  
                	description.append(taskScheduleModel.getSecond()).append("秒"); 
                }  
                description.append("执行");  
            }  
              
            //按每月  
            else if(taskScheduleModel.getJobType().intValue() == 2){  
                //选择月份  
            	if(taskScheduleModel.getDayOfMonths() != null && taskScheduleModel.getDayOfMonths().length > 0) {  
                	String days = "";
                	for(int i : taskScheduleModel.getDayOfMonths()) {
                		days += i + "号";
                	}
                    description.append("每月的").append(days).append(" ");  
                }    
            	description.append(taskScheduleModel.getHour()).append("时");  
            	description.append(taskScheduleModel.getMinute()).append("分");  
            	description.append(taskScheduleModel.getSecond()).append("秒"); 
                description.append("执行");  
            }  
          //按间隔时间段
            else if(taskScheduleModel.getJobType().intValue() == 4){
            	//没填写间隔信息  
                if((null == taskScheduleModel.getIntervalCountOfSecond() || taskScheduleModel.getIntervalCountOfSecond().intValue() == 0) &&  
                    (null == taskScheduleModel.getIntervalCountOfMinute() || taskScheduleModel.getIntervalCountOfMinute().intValue() == 0) &&  
                    (null == taskScheduleModel.getIntervalCountOfHour() || taskScheduleModel.getIntervalCountOfHour().intValue() == 0) &&  
                    (null == taskScheduleModel.getIntervalCountOfDay() || taskScheduleModel.getIntervalCountOfDay().intValue() == 0) &&  
                    (null == taskScheduleModel.getIntervalCountOfMonth() || taskScheduleModel.getIntervalCountOfMonth().intValue() == 0)   
                ){  
                    description.append("从").append(DateHandleUtil.formateDate( "yyyy-MM-dd",startTime));  
                    description.append("起，于");
                    description.append(DateHandleUtil.formateDate( "HH:mm:ss",startTime));  
                    description.append("执行一次");  
                } else{  
                    //秒  
                    if(null != taskScheduleModel.getIntervalCountOfSecond() && taskScheduleModel.getIntervalCountOfSecond().intValue() != 0){  
                        description.append("从").append(DateHandleUtil.formateDate( "yyyy-MM-dd",startTime));  
                        description.append("起，每隔");  
                        description.append(taskScheduleModel.getIntervalCountOfSecond()).append("秒，于");  
                        description.append(DateHandleUtil.formateDate( "HH:mm:ss",startTime));  
                        description.append("执行");  
                    }  
                    //分  
                    if(null != taskScheduleModel.getIntervalCountOfMinute() && taskScheduleModel.getIntervalCountOfMinute().intValue() != 0){  
                        description.append("从").append(DateHandleUtil.formateDate( "yyyy-MM-dd",startTime));  
                        description.append("起，每隔");  
                        description.append(taskScheduleModel.getIntervalCountOfMinute()).append("分钟，于");  
                        description.append(DateHandleUtil.formateDate( "HH:mm:ss",startTime));  
                        description.append("执行");  
                    }  
                    //小时  
                    if(null != taskScheduleModel.getIntervalCountOfHour() && taskScheduleModel.getIntervalCountOfHour().intValue() != 0){  
                        description.append("从").append(DateHandleUtil.formateDate( "yyyy-MM-dd",startTime));  
                        description.append("起，每隔");  
                        description.append(taskScheduleModel.getIntervalCountOfHour()).append("小时，于");  
                        description.append(DateHandleUtil.formateDate( "HH:mm:ss",startTime));  
                        description.append("执行");  
                    }  
                    //日  
                    if(null != taskScheduleModel.getIntervalCountOfDay() && taskScheduleModel.getIntervalCountOfDay().intValue() != 0){  
                        description.append("从").append(DateHandleUtil.formateDate( "yyyy-MM-dd",startTime));  
                        description.append("起，每隔");  
                        description.append(taskScheduleModel.getIntervalCountOfDay()).append("天，于");  
                        description.append(DateHandleUtil.formateDate( "HH:mm:ss",startTime));  
                        description.append("执行");  
                    }  
                    //月  
                    if(null != taskScheduleModel.getIntervalCountOfMonth() && taskScheduleModel.getIntervalCountOfMonth().intValue() != 0){  
                        description.append("从").append(DateHandleUtil.formateDate( "yyyy-MM-dd",startTime));  
                        description.append("起，每隔");  
                        description.append(taskScheduleModel.getIntervalCountOfMonth()).append("个月，于");  
                        description.append(DateHandleUtil.formateDate( "HH:mm:ss",startTime));  
                        description.append("执行");  
                    }  
                }
            }
        }else {
			System.out.println("未设置开始执行时间" );
		} 
        return description.toString();  
    }
    
    //参考例子
    public static void main(String[] args) {
    	//执行时间：每天的12时12分12秒 start
    	TaskScheduleModel taskScheduleModel = new TaskScheduleModel();
//    	taskScheduleModel.setJobType(1);//按每天
//    	Integer hour = 12; //时
//    	Integer minute = 12; //分
//    	Integer second = 12; //秒
//    	taskScheduleModel.setHour(hour);
//    	taskScheduleModel.setMinute(minute);
//    	taskScheduleModel.setSecond(second);
//    	String cropExp = createCronExpression(taskScheduleModel);
//    	System.out.println(cropExp + ":" + createDescription(taskScheduleModel));
//    	//执行时间：每天的12时12分12秒 end
//    	
//    	taskScheduleModel.setJobType(3);//每周的哪几天执行
//    	Integer[] dayOfWeeks = new Integer[3];
//    	dayOfWeeks[0] = 1;
//    	dayOfWeeks[1] = 2;
//    	dayOfWeeks[2] = 3;
//    	taskScheduleModel.setDayOfWeeks(dayOfWeeks);
//    	cropExp = createCronExpression(taskScheduleModel);
//    	System.out.println(cropExp + ":" + createDescription(taskScheduleModel));
//    	
//    	taskScheduleModel.setJobType(2);//每月的哪几天执行
//    	Integer[] dayOfMonths = new Integer[3];
//    	dayOfMonths[0] = 1;
//    	dayOfMonths[1] = 21;
//    	dayOfMonths[2] = 13;
//    	taskScheduleModel.setDayOfMonths(dayOfMonths);
//    	cropExp = createCronExpression(taskScheduleModel);
//    	System.out.println(cropExp + ":" + createDescription(taskScheduleModel));
    	
    	taskScheduleModel.setJobType(4);//按时间间隔
    	taskScheduleModel.setScheduleStartTime(new Date());
    	taskScheduleModel.setIntervalCountOfSecond(3);
    	String cropExp = createCronExpression(taskScheduleModel);
    	System.out.println(cropExp + ":"+createDescription(taskScheduleModel) );
    	
	}

}
