package com.buss.activiti.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buss.activiti.service.ActivitiBusinessDocumentSerivice;
@Service("activitiBusinessDocumentSerivice")
@Transactional
public class ActivitiBusinessDocumentImpl implements ActivitiBusinessDocumentSerivice {
	/**
	 * 启动业务单据审批工作流
	 */
	@Override
	public void startProcess(String BillType,String billId) {
		// TODO Auto-generated method stub
		
	}

}
