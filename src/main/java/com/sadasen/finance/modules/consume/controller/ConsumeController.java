package com.sadasen.finance.modules.consume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadasen.core.response.JsonResult;
import com.sadasen.core.response.status.Status;
import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.modules.consume.dto.ConsumeDto;
import com.sadasen.finance.modules.consume.entity.Consume;
import com.sadasen.finance.modules.consume.service.ConsumeService;
import com.sadasen.finance.util.Utils;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
@RestController
@RequestMapping("/consume")
public class ConsumeController extends BaseController {

	private static final long serialVersionUID = 1452929117248745829L;
	
	@Autowired
	private ConsumeService consumeService;
	
	@PostMapping("/add")
	public JsonResult add(ConsumeDto consumeDto) {
		Consume consume = new Consume(consumeDto);
		consume.setUserId(Utils.getLoginUserId(getRequest()));
		consume = consumeService.save(consume);
		if(null==consume) {
			return JsonResult.instance(Status.SYSTEM_ERROR);
		} else if(-1L==consume.getId()) {
			return JsonResult.instance(Status.REQUEST_VALID);
		}
		return JsonResult.instance(consume);
	}
	
	@GetMapping("/listByParent/{type}/{parentId}")
	public JsonResult list(@PathVariable("type") int type, @PathVariable("parentId") long parentId) {
		Consume param = new Consume();
		param.setUserId(Utils.getLoginUserId(getRequest()));
		param.setType(type);
		param.setParentId(parentId);
		List<Consume> data = consumeService.findByParent(param);
		return JsonResult.instance(data);
	}

}
