package com.sadasen.finance.modules.consume.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;

import com.sadasen.finance.modules.consume.entity.Consume;
import com.sadasen.finance.modules.consume.entity.ConsumeParent;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface ConsumeDao extends BaseMapper<Consume> {
	
	public int insertConsumeParent(ConsumeParent cp);
	
	public int updateHasChild(@Param("id") long id, @Param("hasChild") int hasChild);
	
	public String findMaxCodeByParentId(@Param("type") int type, @Param("parentId") long parentId);
	
	public List<Consume> selectByParent(Consume param);
	
	public int countByParentId(@Param("parentId") long parentId);

}
