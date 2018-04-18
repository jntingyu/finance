package com.sadasen.finance.modules.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.modules.user.entity.User;

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
		User user = (User) getRequest().getSession().getAttribute("user");
		System.out.println(user);
		if(null!=user) {
			System.out.println(user.getUserName());
		}
		System.out.println("index:index");
		System.out.println("pageName : "+pageName);
		return pageName;
	}

}
