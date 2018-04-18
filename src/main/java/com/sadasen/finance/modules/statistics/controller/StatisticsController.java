package com.sadasen.finance.modules.statistics.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadasen.core.response.JsonResult;
import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.modules.statistics.dto.StatsPara;
import com.sadasen.finance.modules.statistics.service.StatisticsService;
import com.sadasen.finance.modules.statistics.vo.StatisticsInfo;
import com.sadasen.finance.util.Utils;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController extends BaseController {
	
	private static final long serialVersionUID = -2004982525225925956L;
	
	@Autowired
	private StatisticsService statisticsService;

	@RequestMapping("/index")
	public JsonResult index(int type) {
		long userId = Utils.getLoginUserId(getRequest());
		StatsPara para = new StatsPara();
		para.setType(type);
		para.setUserId(userId);
		Map<String, String> data = new HashMap<>();
		data.put("todayAmount", statisticsService.findTotalToday(userId, type)/100.0+"");
		long totalMonthAmount = statisticsService.getTotalMonth(para);
		long avgMonthAmount = totalMonthAmount/LocalDate.now().getDayOfMonth();
		data.put("totalMonthAmount", totalMonthAmount/100.0+"");
		data.put("avgMonthAmount", avgMonthAmount/100.0+"");
		long totalAllAmount = statisticsService.getTotalAll(para);
		long avgAllAmount = statisticsService.getAvgAll(para);
		data.put("totalAllAmount", totalAllAmount/100.0+"");
		data.put("avgAllAmount", avgAllAmount/100.0+"");
		return JsonResult.instance(data);
	}
	
	@GetMapping("/everyMonth/{type}")
	public JsonResult everyMonthTotal(@PathVariable("type") int type) {
		long userId = Utils.getLoginUserId(getRequest());
		StatsPara para = new StatsPara();
		para.setType(type);
		para.setUserId(userId);
		List<StatisticsInfo> data = statisticsService.getEveryMonth(para);
		return JsonResult.instance(data);
	}
	
	@GetMapping("/everyConsume/{type}")
	public JsonResult everyConsumeTotal(@PathVariable("type") int type) {
		long userId = Utils.getLoginUserId(getRequest());
		StatsPara para = new StatsPara();
		para.setType(type);
		para.setUserId(userId);
		List<StatisticsInfo> data = statisticsService.getConsumeTotal(para);
		return JsonResult.instance(data);
	}
	
	@GetMapping("/everyBaseConsume/{type}")
	public JsonResult everyBaseConsumeTotal(@PathVariable("type") int type) {
		long userId = Utils.getLoginUserId(getRequest());
		StatsPara para = new StatsPara();
		para.setType(type);
		para.setUserId(userId);
		List<StatisticsInfo> data = statisticsService.getBaseConsumeTotal(para);
		return JsonResult.instance(data);
	}

}
