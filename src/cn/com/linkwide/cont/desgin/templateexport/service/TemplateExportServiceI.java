package cn.com.linkwide.cont.desgin.templateexport.service;
import cn.com.linkwide.cont.desgin.templateexport.entity.TemplateExportEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TemplateExportServiceI extends CommonService{
	
 	public void delete(TemplateExportEntity entity) throws Exception;
 	
 	public Serializable save(TemplateExportEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TemplateExportEntity entity) throws Exception;
 	
}
