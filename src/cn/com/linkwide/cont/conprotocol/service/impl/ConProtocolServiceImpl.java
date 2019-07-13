package cn.com.linkwide.cont.conprotocol.service.impl;
import cn.com.linkwide.cont.conprotocol.service.ConProtocolServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.conprotocol.entity.ConProtocolEntity;
import cn.com.linkwide.cont.conprotocoldetail.entity.ConProtocolDetailEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("conProtocolService")
@Transactional
public class ConProtocolServiceImpl extends CommonServiceImpl implements ConProtocolServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ConProtocolEntity)entity);
 	}
	
	public void addMain(ConProtocolEntity conProtocol,
	        List<ConProtocolDetailEntity> conProtocolDetailList){
			//保存主信息
			this.save(conProtocol);
		
			/**保存-合同协议*/
			for(ConProtocolDetailEntity conProtocolDetail:conProtocolDetailList){
				//外键设置
				conProtocolDetail.setProtocolId(conProtocol.getId());
				this.save(conProtocolDetail);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(conProtocol);
	}

	
	public void updateMain(ConProtocolEntity conProtocol,
	        List<ConProtocolDetailEntity> conProtocolDetailList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(conProtocol.getId())){
			try {
				ConProtocolEntity temp = findUniqueByProperty(ConProtocolEntity.class, "id", conProtocol.getId());
				MyBeanUtils.copyBeanNotNull2Bean(conProtocol, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(conProtocol);
		}
		//===================================================================================
		//获取参数
		Object id0 = conProtocol.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-合同协议
	    String hql0 = "from ConProtocolDetailEntity where 1 = 1 AND pROTOCOL_ID = ? ";
	    List<ConProtocolDetailEntity> conProtocolDetailOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-合同协议
		if(conProtocolDetailList!=null&&conProtocolDetailList.size()>0){
		for(ConProtocolDetailEntity oldE:conProtocolDetailOldList){
			boolean isUpdate = false;
				for(ConProtocolDetailEntity sendE:conProtocolDetailList){
					//需要更新的明细数据-合同协议
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-合同协议
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-合同协议
			for(ConProtocolDetailEntity conProtocolDetail:conProtocolDetailList){
				if(oConvertUtils.isEmpty(conProtocolDetail.getId())){
					//外键设置
					conProtocolDetail.setProtocolId(conProtocol.getId());
					this.save(conProtocolDetail);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(conProtocol);
	}

	
	public void delMain(ConProtocolEntity conProtocol) {
		//删除主表信息
		this.delete(conProtocol);
		//===================================================================================
		//获取参数
		Object id0 = conProtocol.getId();
		//===================================================================================
		//删除-合同协议
	    String hql0 = "from ConProtocolDetailEntity where 1 = 1 AND pROTOCOL_ID = ? ";
	    List<ConProtocolDetailEntity> conProtocolDetailOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(conProtocolDetailOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ConProtocolEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ConProtocolEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ConProtocolEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ConProtocolEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{protocol_number}",String.valueOf(t.getProtocolNumber()));
 		sql  = sql.replace("#{protocol_no}",String.valueOf(t.getProtocolNo()));
 		sql  = sql.replace("#{protocol_name}",String.valueOf(t.getProtocolName()));
 		sql  = sql.replace("#{protocol_money}",String.valueOf(t.getProtocolMoney()));
 		sql  = sql.replace("#{singe_date}",String.valueOf(t.getSingeDate()));
 		sql  = sql.replace("#{protocol_start}",String.valueOf(t.getProtocolStart()));
 		sql  = sql.replace("#{protocol_end}",String.valueOf(t.getProtocolEnd()));
 		sql  = sql.replace("#{dept_code}",String.valueOf(t.getDeptCode()));
 		sql  = sql.replace("#{protocol_unit}",String.valueOf(t.getProtocolUnit()));
 		sql  = sql.replace("#{other_name}",String.valueOf(t.getOtherName()));
 		sql  = sql.replace("#{other_idcode}",String.valueOf(t.getOtherIdcode()));
 		sql  = sql.replace("#{effect_emp}",String.valueOf(t.getEffectEmp()));
 		sql  = sql.replace("#{effect_date}",String.valueOf(t.getEffectDate()));
 		sql  = sql.replace("#{approval_emp}",String.valueOf(t.getApprovalEmp()));
 		sql  = sql.replace("#{approval_date}",String.valueOf(t.getApprovalDate()));
 		sql  = sql.replace("#{approval_state}",String.valueOf(t.getApprovalState()));
 		sql  = sql.replace("#{approval_id}",String.valueOf(t.getApprovalId()));
 		sql  = sql.replace("#{protocol_state}",String.valueOf(t.getProtocolState()));
 		sql  = sql.replace("#{protocol_bz}",String.valueOf(t.getProtocolBz()));
 		sql  = sql.replace("#{protocol_exect}",String.valueOf(t.getProtocolExect()));
 		sql  = sql.replace("#{protocol_exect2}",String.valueOf(t.getProtocolExect2()));
 		sql  = sql.replace("#{protocol_exect3}",String.valueOf(t.getProtocolExect3()));
 		sql  = sql.replace("#{protocol_exect4}",String.valueOf(t.getProtocolExect4()));
 		sql  = sql.replace("#{protocol_exect5}",String.valueOf(t.getProtocolExect5()));
 		sql  = sql.replace("#{other_teleph}",String.valueOf(t.getOtherTeleph()));
 		sql  = sql.replace("#{protocol_cont}",String.valueOf(t.getProtocolCont()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}