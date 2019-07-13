package cn.com.linkwide.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.StringUtil;

import cn.com.linkwide.common.custom.billcode.manage.entity.BillcodeManageEntity;
import cn.com.linkwide.common.custom.billcode.manage.service.BillcodeManageServiceI;


/**
 *生成单据号工具
 */
public class BillCodeUtil {

	/**
	 *生成单据号
	 *@author chencp
	 *2017年9月26日上午10:01:08
	 * @param billType
	 * @return
	 */
	public static String productBillCode(String billType){
		String billCode=null;
		billCode=getBillCode(billType,billCode);
		return billCode;
	}
	/**
	 * 不是第一次请求时生成单据号
	 *@author chencp
	 *2017年9月26日上午10:01:38
	 * @param vdef1
	 * @param billcodeManage
	 * @param billCode
	 * @return
	 */
	public static String getBillCode(String billType,String billCode){
		if(StringUtil.isEmpty(billType)){
			throw new BusinessException("单据类型为空");
		}
		BillcodeManageServiceI billcodeManageService = ApplicationContextUtil.getContext().getBean(BillcodeManageServiceI.class);
			//根据单据号类型查询
			BillcodeManageEntity billcodeManage = billcodeManageService.findUniqueByProperty(BillcodeManageEntity.class, "billType", billType);
			//将请求生成单据号的次数记录在预留字段1中
			if(billcodeManage==null){
				throw new BusinessException("该单据类型的单据还未定义");
			}
		Date date = new Date();
		String vdef1=billcodeManage.getVdef1();
		//月份
		String codeMonth = billcodeManage.getCodeMoth();
		//日期
		String codeDay = billcodeManage.getCodeDay();
		//年份
		String year = billcodeManage.getYear();
		//月份
		String month = billcodeManage.getMonth();
		//日期
		String day = billcodeManage.getDay();
		//对象1
		String billObj1 = billcodeManage.getBillObj1()==null?"":billcodeManage.getBillObj1();
		//对象2
		String billObj2 = billcodeManage.getBillObj2()==null?"":billcodeManage.getBillObj2();
		//流水号归零标识
		String zeroMark=billcodeManage.getZeroMark();
		//单据号后几位的位数
		int endNum=Integer.parseInt(billcodeManage.getEndNum());
		if("none".equals(zeroMark)){//不归零时
			billCode=generateBillCode(vdef1, endNum, billcodeManage, billCode);
		}else if("year".equals(zeroMark)){//按年归零
			SimpleDateFormat sdf = new SimpleDateFormat("yy");
			String d="";
			//当前年份
			String nowYear = sdf.format(date);
			if(nowYear.equals(year)){//和当前年份相等时
				billCode=generateBillCode(vdef1, endNum, billcodeManage, billCode);
			}else{//和当前年份不相等时
				if(StringUtil.isNotEmpty(codeDay)){//日期不为空时
					SimpleDateFormat daySdf = new SimpleDateFormat("yyMMdd");
				    d = daySdf.format(date);
				}else if(StringUtil.isNotEmpty(codeMonth)){//月份不为空时
					SimpleDateFormat daySdf = new SimpleDateFormat("yyMM");
					 d = daySdf.format(date);
				}
				String n= String.format("%0"+endNum+"d",1);
				billCode=billType+billObj1+billObj2+d + n;
				billcodeManage.setVdef1("1");
				billcodeManage.setYear(nowYear);
			}
		}else if("month".equals(zeroMark)){//按月归零
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			//当前年份月份
			String yearAndMonth = sdf.format(date);
			//和当前月份相等时
			if(yearAndMonth.equals(month)){
				billCode=generateBillCode(vdef1, endNum, billcodeManage, billCode);
			}else{//和当前月份不想等时
				String d="";
				if(StringUtil.isNotEmpty(codeDay)){//日期不为空时
					SimpleDateFormat daySdf = new SimpleDateFormat("yyMMdd");
				    d = daySdf.format(date);
				}else if(StringUtil.isNotEmpty(codeMonth)){//月份不为空时
					SimpleDateFormat daySdf = new SimpleDateFormat("yyMM");
					 d = daySdf.format(date);
				}
				String n= String.format("%0"+endNum+"d",1);
				billCode=billType+billObj1+billObj2+d + n;
				billcodeManage.setVdef1("1");
				billcodeManage.setMonth(yearAndMonth);
			}
			
		}else if("day".equals(zeroMark)){//按天归零
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			//当前年份月份日期
			String yearAndMonthAndDate = sdf.format(date);
			//和当前月份相等时
			if(yearAndMonthAndDate.equals(day)){
				billCode=generateBillCode(vdef1, endNum, billcodeManage, billCode);
			}else{//和当前日期不想等时
				if(StringUtil.isNotEmpty(codeDay)){//日期不为空时
					SimpleDateFormat daySdf = new SimpleDateFormat("yyMMdd");
				    String d = daySdf.format(date);
				    String n= String.format("%0"+endNum+"d",1);
				    billCode=billType+billObj1+billObj2+d + n;
					billcodeManage.setVdef1("1");
					billcodeManage.setDay(yearAndMonthAndDate);
				}
			}
		}
		billcodeManage.setVdef2(billCode);
		billcodeManageService.updateEntitie(billcodeManage);
		return billCode;
	}
	/**
	 * 生成递增的单据号
	 * @param vdef1
	 * @param endNum
	 * @param billcodeManage
	 * @param billCode
	 *2017年11月29日
	 *@author chencp
	 */
	public static String generateBillCode(String vdef1,int endNum,BillcodeManageEntity billcodeManage,String billCode){
		BillcodeManageServiceI billcodeManageService = ApplicationContextUtil.getContext().getBean(BillcodeManageServiceI.class);
		if(StringUtil.isEmpty(vdef1)){//第一次请求时
			billCode=billcodeManage.getBillCode();
			billcodeManage.setVdef1("1");
		}else{
			//请求次数
			int vdefInt=Integer.parseInt(vdef1)+1;
			String vdef1Str=Integer.toString(vdefInt);
			//请求次数的长度
			int len=vdef1Str.length();
			if(len>endNum){
				throw new BusinessException("单据号超出设定的最大位数："+endNum);
			}
			String code=billcodeManage.getVdef2();
			String newCode=code.substring(0,code.length()-len);
			billCode=newCode+vdef1Str;
			billcodeManage.setVdef1(vdef1Str);
		}
		billcodeManageService.updateEntitie(billcodeManage);
		return billCode;
	}
}
