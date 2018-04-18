package com.sadasen.finance.modules.account.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;

import com.sadasen.finance.modules.account.entity.Account;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface AccountDao extends BaseMapper<Account> {
	
	public int countByUser(@Param("userId") long userId);
	
	public List<Account> selectListByUser(@Param("userId") long userId);

	public List<Account> selectListByWay(@Param("userId") long userId, @Param("wayId") long wayId);

	public List<Account> selectListByNotInWay(@Param("userId") long userId, @Param("wayId") long wayId);

}
