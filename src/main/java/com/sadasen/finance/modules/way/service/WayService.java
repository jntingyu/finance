package com.sadasen.finance.modules.way.service;

import java.util.List;

import com.sadasen.finance.modules.way.entity.Way;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface WayService {
	
	public Way save(Way way);
	
	public int saveWayAccount(long wayId, long accountId);
	
	public Way findById(long id);
	
	public List<Way> findListByUser(long userId);

}
