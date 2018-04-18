package com.sadasen.finance.modules.record.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadasen.core.response.JsonResult;
import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.modules.record.dto.RecordPara;
import com.sadasen.finance.modules.record.entity.Record;
import com.sadasen.finance.modules.record.service.RecordService;
import com.sadasen.finance.modules.record.vo.RecordInfo;
import com.sadasen.finance.util.Utils;
import com.sadasen.util.BigDecimalUtil;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
@RestController
@RequestMapping("/record")
public class RecondController extends BaseController {

	private static final long serialVersionUID = -5615248355788811605L;
	
	@Autowired
	private RecordService recordService;
	
	@PostMapping("/add")
	public JsonResult add(Record record) {
		record.setAmount(BigDecimalUtil.mul(record.getDamount(), "100").intValue());
		record.setUserId(Utils.getLoginUserId(getRequest()));
		record = recordService.save(record);
		return JsonResult.instance("记账成功", record);
	}
	
	@RequestMapping("/queryList")
	public JsonResult listAll(RecordPara para) {
		para.setUserId(Utils.getLoginUserId(getRequest()));
		List<RecordInfo> data = recordService.findInfoListByCondition(para);
		return JsonResult.instance(data);
	}
	
}
