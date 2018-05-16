package com.sadasen.finance.modules.bill.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadasen.core.response.JsonResult;
import com.sadasen.core.response.status.Status;
import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.modules.bill.dto.BillDto;
import com.sadasen.finance.modules.bill.entity.Bill;
import com.sadasen.finance.modules.bill.service.BillService;
import com.sadasen.finance.util.Utils;
import com.sadasen.util.StringUtil;

/**
 * @date 2018年4月18日
 * @author lei.ys
 * @addr company
 * @desc
 */
@RestController
@RequestMapping("/bill")
public class BillController extends BaseController {

	private static final long serialVersionUID = -8685572019204021783L;
	
	@Autowired
	private BillService billService;
	
	/**
	 * 添加记账单
	 * @param bill
	 * @return
	 */
	@PostMapping
	public JsonResult add(Bill bill) {
		// 名称不能为空
		if(StringUtil.isEmpty(bill.getName())) {
			return JsonResult.instance(Status.REQUEST_LACK, "账单名称不能为空");
		}
		// 设置开始时间
		if(null==bill.getStartDate()) {
			bill.setStartDate(new Date());
		}
		// 设置用户ID
		bill.setUserId(Utils.getLoginUserId(getRequest()));
		// 调用service接口
		try {
			bill = billService.save(bill);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.instance(Status.SYSTEM_ERROR);
		}
		return JsonResult.instance(bill);
	}
	
	@GetMapping("/list")
	public JsonResult list(BillDto billQuery) {
		billQuery.setUserId(Utils.getLoginUserId(getRequest()));
		try {
			List<Bill> result = billService.findListByCondition(billQuery);
			return JsonResult.instance(result);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.instance(Status.SYSTEM_ERROR);
		}
	}

}
