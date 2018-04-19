package com.sadasen.finance.modules.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sadasen.finance.base.BaseController;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Controller
@RequestMapping("/page")
public class PageController extends BaseController {

	private static final long serialVersionUID = 3067971115547821466L;

	@GetMapping("/{pageName}")
	public String page(@PathVariable("pageName") String pageName) {
		System.out.println("pageName : "+"page/"+pageName);
		return "page/"+pageName;
	}

}
