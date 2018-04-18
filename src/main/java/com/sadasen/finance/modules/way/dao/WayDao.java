package com.sadasen.finance.modules.way.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;

import com.sadasen.finance.modules.way.entity.Way;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface WayDao extends BaseMapper<Way> {
	
	public int insertWayAccount(@Param("wayId") long wayId, @Param("accountId") long accountId);
	
	public int countByUser(@Param("userId") long userId);
	
	public List<Way> selectListByUser(@Param("userId") long userId);

}
