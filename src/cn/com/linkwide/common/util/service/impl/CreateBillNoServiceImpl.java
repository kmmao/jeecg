package cn.com.linkwide.common.util.service.impl;

import java.lang.reflect.Method;
import java.util.List;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.DateHandleUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.common.util.service.CreateBillNoService;
@Service("createBillNoService")
@Transactional
public class CreateBillNoServiceImpl implements CreateBillNoService {
	@Autowired
	private SystemService systemService;
	/***
	 * 实现功能：生成新的单据号；
	 * 单据号规则：单据类型编码+年月日（20170101）+bitNum位流水号
	 * @throws BusinessException 
	 */
	@Override
	public String createNewBillNo(String preChar,String className,String billNoComlumn,int bitNum) throws BusinessException {
		if(bitNum<=1)
			bitNum = 2;
		
		try {
			String qianZhui = getStrCapital(className);
			Class entity = Class.forName(className);		
			/**1、获取今日日期*/
			String currentDate = DateHandleUtil.formateDate("yyyyMMdd", new java.util.Date());
			StringBuffer billNo = new StringBuffer(preChar+currentDate);
			
			/**2、获取单据中今日的最大流水号*/
			String sql = "from "+entity.getSimpleName()+" where "+billNoComlumn+" like '"+qianZhui+billNo.toString()+"%'";
			List<Class> list =  systemService.findHql(sql);
		
			/**3、组装新的流水号，返回结果*/
			if(list==null || list.size()==0){
				for(int i=2;i<=bitNum;i++){
					billNo.append("0");
				}
				billNo.append("1");
			}else{
				billNoComlumn = billNoComlumn.substring(0,1).toUpperCase()+billNoComlumn.substring(1,billNoComlumn.length());
				Method method = entity.getDeclaredMethod("get"+billNoComlumn);
				int maxNo = 0;
				for (int i=0;i<list.size();i++) {
					String oldNo = method.invoke(list.get(i)).toString();
					oldNo = oldNo.replace(qianZhui, "");
					int temp = Integer.parseInt(oldNo.substring(oldNo.length()-bitNum));
					if(maxNo<temp)
						maxNo = temp;
				}
				
				String  newNo = maxNo+1+"";
				for(int i=2;i<=bitNum;i++){
					if(newNo.length()>=bitNum){
						break;
					}
					if(newNo.length()<i){
						newNo = "0"+newNo;
					}
				}
				billNo.append(newNo);
			}
			
			return qianZhui+billNo.toString();
			
		} catch (ClassNotFoundException e) {
			throw new BusinessException(className+",没有找到对应的实体类");
		}catch (Exception e) {
			throw new BusinessException(className+",没有找到获取单据编号的方法");
		} 
			
		
	}
	/**
	 * 获取实体简称作为单据号开头
	 * @param str
	 * @return
	 */
	public static String getStrCapital(String str){
		String cap ="";
		char[] srChar=str.toCharArray();
		for (char c : srChar) {
			if ((char)c>='A'&&(char)c<='Z') { 
				cap+=c; 
			}
		}
		cap = cap.substring(0,cap.length()-1);
		if(cap.length()>3){
			return cap.substring(2, 4);
		}else if(cap.length()>2){
			return cap.substring(1, 3);
		}
		return cap;
	}
}
