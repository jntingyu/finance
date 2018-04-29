package com.sadasen.finance.modules.bill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadasen.core.response.JsonResult;
import com.sadasen.finance.base.BaseController;

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
	
	@GetMapping("/list")
	public JsonResult list() {
		
		return JsonResult.instance();
	}

}
