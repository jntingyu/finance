package com.sadasen.finance.modules.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.modules.user.entity.User;

/**
 * @date 2018年4月19日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Controller
@RequestMapping("/view")
public class ViewController extends BaseController {

	private static final long serialVersionUID = 1338964243292884261L;

	@GetMapping("/{module}/{viewName}")
	public String page(@PathVariable("module") String module, @PathVariable("viewName") String viewName) {
		User user = (User) getRequest().getSession().getAttribute("user");
		System.out.println(user);
		if(null!=user) {
			System.out.println(user.getUserName());
		} else {
			return "redirect:/page/login";
		}
		System.out.println("viewName : "+"view/"+module+ "/" +viewName);
		return "view/"+module+ "/" +viewName;
	}

}
