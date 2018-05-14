package com.sadasen.finance.modules.bill.service.impl;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sadasen.finance.modules.bill.entity.Bill;
import com.sadasen.finance.modules.bill.service.BillService;

/**
 * @date 2018年4月22日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private SQLManager sqlManager;

	@Override
	public Bill save(Bill bill) {
		int r = sqlManager.insertTemplate(bill, true);
		if(1==r) {
			return bill;
		}
		return null;
	}

}
