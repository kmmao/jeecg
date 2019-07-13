package cn.com.linkwide.common.util;
import java.util.HashMap;
import java.util.Map;
import cn.com.linkwide.ba.material.materunit.entity.LBaMaterialMaterUnitEntity;

public class DictUtil {
	/**
	 * 缓存物资计量单位【缓存】
	 */
	public static Map<String, LBaMaterialMaterUnitEntity> allMateUnits = new HashMap<String,LBaMaterialMaterUnitEntity>(); 
}
