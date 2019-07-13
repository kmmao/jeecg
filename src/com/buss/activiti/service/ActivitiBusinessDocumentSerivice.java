package com.buss.activiti.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 业务单据与工作流的接口
 * @author admin
 *
 */

public interface ActivitiBusinessDocumentSerivice {
	public void startProcess(String BillType,String billId);
}
