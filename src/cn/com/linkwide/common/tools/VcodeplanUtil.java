package cn.com.linkwide.common.tools;

import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.service.SystemService;

import cn.com.linkwide.common.custom.vcodeplan.entity.VcodeplanEntity;

public class VcodeplanUtil {
	
	private static SystemService systemService=ApplicationContextUtil.getContext().getBean(SystemService.class);
	
	/**
	 * 通过菜单ID判断是否设置编码方案
	 * @author heyc
	 * @param tfid
	 * @return
	 */
	public static Boolean ifvcode(String tfid){
		VcodeplanEntity vcodeplanEntity=systemService.findUniqueByProperty(VcodeplanEntity.class, "vdef4", tfid);
	    if(vcodeplanEntity!=null){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	/**
	 * 通过ID获取编码方案
	 * @author heyc
	 * @param tfid
	 * @return
	 */
	public static String strvcode(String tfid){
		String str="";
		List<Integer> list=new ArrayList<Integer>();
		VcodeplanEntity vcodeplanEntity=systemService.findUniqueByProperty(VcodeplanEntity.class, "vdef4", tfid);
		list.add(vcodeplanEntity.getOnelevel());
		list.add(vcodeplanEntity.getTwolevel());
		list.add(vcodeplanEntity.getThreelevel());
		list.add(vcodeplanEntity.getFourlevel());
		list.add(vcodeplanEntity.getFivelevel());
		list.add(vcodeplanEntity.getSixlevel());
		list.add(vcodeplanEntity.getSevenlevel());
		list.add(vcodeplanEntity.getEightlevel());
		list.add(vcodeplanEntity.getNinelevel());
		list.add(vcodeplanEntity.getTenlevel());
		for(int i=0;i<list.size();i++){
			if(list.get(i)!=null&&list.get(i)>=0){
				String ss="";
				for(int j=0;j<list.get(i);j++){
					ss+="*";
				}
				str+=ss+" ";
			}
		}
		return str;
	}
	
	/**
	 * 通过ID获取最大长度
	 * @author heyc
	 * @param tfid
	 * @return
	 */
	public static int getbiglength(String tfid){
		VcodeplanEntity vcodeplanEntity=systemService.findUniqueByProperty(VcodeplanEntity.class, "vdef4", tfid);
		return vcodeplanEntity.getBiglength();
	}
	
	/**
	 * 通过ID获取最大级数
	 * @author heyc
	 * @param tfid
	 * @return
	 */
	public static int getbiglevel(String tfid){
		VcodeplanEntity vcodeplanEntity=systemService.findUniqueByProperty(VcodeplanEntity.class, "vdef4", tfid);
		return vcodeplanEntity.getBiglevel();
	}

}
