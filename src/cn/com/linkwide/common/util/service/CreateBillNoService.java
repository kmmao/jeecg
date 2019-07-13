package cn.com.linkwide.common.util.service;

import org.jeecgframework.core.common.exception.BusinessException;

public interface  CreateBillNoService  {
	/**
	 * 实现功能：生成新的单据编号
	 * @param preChar 编码前缀
	 * @param className 单据实体名
	 * @param billNoComlumn 单据编号字段名，与实体中定义一致，且区分大小写
	 * @param bitNum 流水号位数，最少为2
	 * @return
	 */
	public String createNewBillNo(String preChar,String className,String billNoComlumn,int bitNum) throws BusinessException;
	
}
