package com.buss.activiti.util;
/**
 * 单据状态
 * @author liuxueting
 *
 */
public class BILL_STATE {
	/**待提交*/	
	public static final String TO_BE_SUBMIT="新建";
	/**待审核*/
	public static final String TO_BE_CHECK="待审核";
	/**已审核*/
	public static final String CHECKED="已审核";
	/**已批复*/
	public static final String REPLYED="已批复";
	/**退回*/
	public static final String REJECT="退回";
	/**已支付*/
	public static final String PAY="已支付";
	/**弃审*/
	public static final String Abandoned="弃审";
}
