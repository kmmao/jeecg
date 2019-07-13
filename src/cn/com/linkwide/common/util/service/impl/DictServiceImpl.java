package cn.com.linkwide.common.util.service.impl;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.material.materunit.entity.LBaMaterialMaterUnitEntity;
import cn.com.linkwide.common.util.DictUtil;
import cn.com.linkwide.common.util.service.DictService;

@Service("dictService")
@Transactional
public class DictServiceImpl extends CommonServiceImpl implements DictService {
	@Override
	public void initAllMateUnit() {
		List<LBaMaterialMaterUnitEntity> units = this.commonDao.loadAll(LBaMaterialMaterUnitEntity.class);
		DictUtil.allMateUnits = this.list2Map(LBaMaterialMaterUnitEntity.class, units, "id");
		
	}
	@Override
	public void refreshMateUnit() {
		DictUtil.allMateUnits.clear();
		initAllMateUnit();
	}
	@Override
	public void initAll() {
		initAllMateUnit();
	}
}