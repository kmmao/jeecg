package com.jeecg.chat.service.impl;

import com.jeecg.chat.dao.ImDao;
import com.jeecg.chat.entity.P3ImTSBaseUser;
import com.jeecg.chat.entity.P3ImTSDepart;
import com.jeecg.chat.service.ImService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.jeecgframework.minidao.aop.MiniDaoHandler;
import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("imService")
public class ImServiceImpl
  implements ImService
{
  private Logger log = Logger.getLogger(ImServiceImpl.class);
  @Resource
  private ImDao imDao;
  
  public List<P3ImTSBaseUser> getUsers()
  {
    List<P3ImTSBaseUser> imTSUsers = new ArrayList();
    try
    {
      MiniDaoHandler minidaoConfig = (MiniDaoHandler)ApplicationContextUtil.getContext().getBean(MiniDaoHandler.class);
      if ("sqlserver".equals(minidaoConfig.getDbType())) {
        imTSUsers = getUsersSqlServer();
      } else {
        imTSUsers = getUsersOrcl();
      }
    }
    catch (Exception e)
    {
      this.log.error("������������:" + e.toString());
    }
    return imTSUsers;
  }
  
  public List<P3ImTSDepart> getDeparts()
  {
    return this.imDao.getDeparts();
  }
  
  public boolean isInThisOrg(String deptid, String oid)
  {
	P3ImTSDepart d = this.imDao.getDepart(deptid);
	if(d != null){
		
		String orgcode = d.getOrgCode();
		boolean reslut = orgcode.startsWith(oid);
		return reslut;
	}
	return false;
  }
  
  public int updateSign(String sign, String uid)
  {
    return this.imDao.update(sign, uid);
  }
  
  public List<P3ImTSBaseUser> getUsersSqlServer()
  {
    return this.imDao.getUsersSqlServer();
  }
  
  public List<P3ImTSBaseUser> getUsersOrcl()
    throws Exception
  {
    return this.imDao.getUsers();
  }
}
