package com.sadasen.finance.modules.bill.service;

import java.util.List;

import com.sadasen.finance.modules.bill.dto.BillDto;
import com.sadasen.finance.modules.bill.entity.Bill;

/**
 * @date 2018年4月22日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface BillService {
	
	public Bill save(Bill bill);
	
	public List<Bill> findListByCondition(BillDto billDto);

}
