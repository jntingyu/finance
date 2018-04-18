package com.sadasen.finance.modules.way.service.impl;

import java.util.List;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sadasen.finance.modules.way.dao.WayDao;
import com.sadasen.finance.modules.way.entity.Way;
import com.sadasen.finance.modules.way.service.WayService;
import com.sadasen.util.StringUtil;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Service
public class WayServiceImpl implements WayService {
	
	@Autowired
	private SQLManager sqlManager;
	@Autowired
	private WayDao wayDao;

	@Override
	public Way save(Way way) {
		// 参数验证
		if(StringUtil.isEmpty(way.getName())) {
			way.setId(-1L);
			return way;
		}
		// 排序
		int sort = wayDao.countByUser(way.getUserId());
		way.setSort(sort);
		
		int r = sqlManager.insertTemplate(way, true);
		if(1==r) {
			return way;
		}
		return null;
	}
	
	@Override
	public int saveWayAccount(long wayId, long accountId) {
		return wayDao.insertWayAccount(wayId, accountId);
	}
	
	@Override
	public Way findById(long id) {
		return wayDao.single(id);
	}

	@Override
	public List<Way> findListByUser(long userId) {
		return wayDao.selectListByUser(userId);
	}

}
