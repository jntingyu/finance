package com.sadasen.finance.modules.bill.dao;

import java.util.List;

import org.beetl.sql.core.mapper.BaseMapper;

import com.sadasen.finance.modules.bill.entity.Bill;

/**
 * @date 2018年4月18日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface BillDao extends BaseMapper<Bill> {
	
	public List<Bill> selectListByCondition(long userId);

}
